package graph.disjointset;

// Created at: 10-July-2026
// Last revised at: 10-July-2026
// Link: https://leetcode.com/problems/path-existence-queries-in-a-graph-i/

/*
Problem Description:
--------------------
Statement:
You are given an integer n and a 0-indexed integer array nums of size n sorted
in non-decreasing order, and an integer maxDiff. An undirected edge exists
between indices i and j (i != j) if |nums[i] - nums[j]| <= maxDiff.

You are also given a 2D integer array queries, where queries[i] = [u_i, v_i].
For each query, determine whether there exists a path between u_i and v_i
(direct edge or via intermediate nodes).

Return a boolean array answer such that answer[i] is true if u_i and v_i are
connected in the graph, and false otherwise.

Example:
Input: n = 5, nums = [1,3,4,6,10], maxDiff = 2, queries = [[0,4],[0,1],[1,3]]
Output: [false,true,true]

Constraints:
1 <= n == nums.length <= 1e5
0 <= nums[i] <= 1e5
nums is sorted in non-decreasing order.
0 <= maxDiff <= 1e5
1 <= queries.length <= 1e5
queries[i] == [u_i, v_i]
0 <= u_i, v_i < n
*/

/*
Approach 1: Brute Force (all pairs)
Idea:
For every pair (i, j) with i < j, check if |nums[i] - nums[j]| <= maxDiff and
union them directly if so.

Time Complexity:
O(n^2) in the worst case (e.g. maxDiff large enough that the early break
never triggers).

Space Complexity:
O(n) for the DSU arrays.

Drawbacks:
n can be up to 1e5, so O(n^2) unions/comparisons times out. Redundant work
too -- most of these unions are implied transitively and don't need to be
made directly.
*/

/*
Approach 2: Adjacent-Index Union (Optimal)
Idea:
nums is sorted, so if nums[i] and nums[j] (i < j) are within maxDiff of each
other, every consecutive pair between them is also within maxDiff (since
consecutive differences only shrink the gap). So it's enough to union each
index with its immediate neighbor whenever the adjacent difference is within
maxDiff -- DSU's find (with path compression) handles the transitive
connectivity for free. No need to explicitly connect far-apart pairs.

Time Complexity:
O(n * alpha(n)) to build the DSU, O(q * alpha(n)) to answer queries --
effectively O(n + q).

Space Complexity:
O(n) for the DSU parent/size arrays.

Drawbacks:
None significant -- this is optimal for the given constraints.
*/

/*
Method to Solve:
----------------
1. Build a DSU over n indices.
2. Walk through nums once; for each i, if nums[i+1] - nums[i] <= maxDiff,
   union(i, i+1).
3. For each query [u, v], answer true if findParent(u) == findParent(v).
*/

public class LC3532PathExistenceQueriesInAGraphI {

    private int[] parent;
    private int[] size;

    /**
     * Initializes the DSU with n isolated nodes, each its own parent.
     *
     * @param n number of nodes
     */
    private void initDsu(int n) {
        parent = new int[n];
        size = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1;
        }
    }

    /**
     * Finds the root parent of a node, compressing the path along the way.
     *
     * @param node the node to find the root for
     * @return the root parent of the node
     */
    private int findParent(int node) {
        if (parent[node] == node) {
            return node;
        }
        return parent[node] = findParent(parent[node]);
    }

    /**
     * Unions two nodes by attaching the smaller-sized tree under the larger one.
     *
     * @param a first node
     * @param b second node
     */
    private void unionBySize(int a, int b) {
        int parentA = findParent(a);
        int parentB = findParent(b);

        if (parentA == parentB) {
            return;
        }

        if (size[parentA] < size[parentB]) {
            parent[parentA] = parentB;
            size[parentB] += size[parentA];
            return;
        }

        parent[parentB] = parentA;
        size[parentA] += size[parentB];
    }

    /**
     * Determines, for each query, whether the two given indices are connected
     * under the "close enough" adjacency rule defined by maxDiff.
     *
     * @param n       number of indices
     * @param nums    sorted non-decreasing array of values
     * @param maxDiff maximum allowed difference for an edge to exist
     * @param queries list of [u, v] index pairs to check connectivity for
     * @return boolean array where result[i] is true if queries[i] are connected
     */
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        initDsu(n);

        // only adjacent unions needed -- transitivity covers the rest
        for (int i = 0; i < n - 1; i++) {
            if (nums[i + 1] - nums[i] <= maxDiff) {
                unionBySize(i, i + 1);
            }
        }

        boolean[] ans = new boolean[queries.length];
        int index = 0;
        for (int[] q : queries) {
            ans[index++] = findParent(q[0]) == findParent(q[1]);
        }

        return ans;
    }
}

// Time Complexity: O(n + q) amortized (with inverse-Ackermann DSU factor)
// Space Complexity: O(n)