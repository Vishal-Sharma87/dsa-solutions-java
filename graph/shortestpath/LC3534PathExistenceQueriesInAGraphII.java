package graph.shortestpath;

// Created at: 11-July-2026
// Last revised at: 11-July-2026
// Link: https://leetcode.com/problems/path-existence-queries-in-a-graph-ii/

/*
Problem Description:
--------------------
Statement:
Given n nodes labeled 0 to n-1, an integer array nums of length n, and an
integer maxDiff, an undirected edge exists between nodes i and j if
|nums[i] - nums[j]| <= maxDiff. For each query [u, v], find the minimum
number of edges on a path between u and v. If no path exists, return -1.

Example:
Input: n = 5, nums = [1,8,3,4,2], maxDiff = 3, queries = [[0,3],[2,4]]
Output: [1,1]

Constraints:
1 <= n <= 10^5
nums.length == n
0 <= nums[i] <= 10^9
0 <= maxDiff <= 10^9
1 <= queries.length <= 10^5
queries[i].length == 2
0 <= ui, vi <= n - 1
*/

/*
Approach 1: DSU (connectivity gate) + BFS (distance) with memoization
Idea:
Build the graph by brute-force checking every pair (i, j) for the edge
condition. Use DSU purely as an O(1) connectivity gate so BFS is never
run on a pair that's already known to be disconnected. Run BFS from a
node only when needed, and cache every distance discovered during that
BFS call (not just the queried target), since BFS naturally yields
shortest distances to all reachable nodes in one pass — this pays off
when multiple queries share an endpoint.

Time Complexity: O(n^2) for pair construction + O(q * (n + E)) worst case for BFS
Space Complexity: O(n^2) worst case for adjacency list when maxDiff is large

Drawbacks:
The pairwise edge construction is O(n^2), independent of maxDiff. When
maxDiff is large relative to the value range, the graph approaches
complete, making both the adjacency list and BFS blow up. This fails at
scale (n, queries up to 10^5) — TLEs on large maxDiff test cases.

Key Insight (parked, not implemented here):
Sorting nodes by value turns "reachable in one hop" into a contiguous
window, computable in O(n) via two pointers (the window's right boundary
only moves forward as the sweep advances, since nums is sorted). Min-hop
queries between arbitrary sorted positions can then be answered in
O(log n) using binary lifting (a jump table doubling in powers of 2),
giving O((n + q) log n) overall. Deprioritized for now — binary lifting
has low relevance for SDE-1 interview prep; the two-pointer window
insight is the transferable takeaway.
*/

import java.util.*;

import graph.disjointset.DisjointSet;

public class LC3534PathExistenceQueriesInAGraphII {

    private Map<Integer, Map<Integer, Integer>> distanceCache;

    /*
     * Method to Solve:
     * ----------------
     * 1. Build all edges by brute-force pairwise comparison; union connected
     * nodes in DSU and add them to the adjacency list.
     * 2. For each query, short-circuit u == v to 0.
     * 3. Use DSU to reject disconnected pairs in O(1) as -1.
     * 4. For connected pairs, check the distance cache; if missing, run BFS
     * from u and cache every distance discovered along the way.
     */

    /**
     * Answers minimum-hop-distance queries on a graph built from a
     * maxDiff-based edge rule, using DSU for connectivity and cached BFS
     * for distance.
     *
     * @param n       number of nodes
     * @param nums    value associated with each node
     * @param maxDiff maximum allowed value difference for an edge to exist
     * @param queries list of [u, v] distance queries
     * @return array of minimum distances (or -1 if disconnected) per query
     */
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        distanceCache = new HashMap<>();
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        DisjointSet dsu = new DisjointSet(n);

        // brute-force pair check to build edges
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(nums[i] - nums[j]) <= maxDiff) {
                    dsu.unionBySize(i, j);

                    adjList.computeIfAbsent(i, k -> new ArrayList<>()).add(j);
                    adjList.computeIfAbsent(j, k -> new ArrayList<>()).add(i);

                    distanceCache.putIfAbsent(i, new HashMap<>());
                    distanceCache.putIfAbsent(j, new HashMap<>());
                }
            }
        }

        int[] ans = new int[queries.length];
        Arrays.fill(ans, -1);

        for (int index = 0; index < queries.length; index++) {
            int u = queries[index][0];
            int v = queries[index][1];

            if (u == v) {
                ans[index] = 0;
            } else if (dsu.isConnected(u, v)) {
                Integer cached = distanceCache.get(u).get(v);
                ans[index] = cached == null ? bfsShortestDistance(adjList, u, v, n) : cached;
            }
        }

        return ans;
    }

    /**
     * Runs BFS from start and caches shortest distances to every node
     * visited along the way, not just the target.
     *
     * @param adjList adjacency list of the graph
     * @param start   BFS source node
     * @param target  node whose distance from start is required
     * @param total   total number of nodes (for the visited array size)
     * @return shortest distance from start to target, or -1 if unreachable
     */
    private int bfsShortestDistance(Map<Integer, List<Integer>> adjList, int start, int target, int total) {
        boolean[] visited = new boolean[total];
        Deque<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;

        int distance = 0;
        while (!queue.isEmpty()) {
            int levelSize = queue.size();

            for (int i = 0; i < levelSize; i++) {
                int node = queue.poll();

                if (node == target) {
                    distanceCache.get(start).put(target, distance);
                    distanceCache.get(target).put(start, distance);
                    return distance;
                }

                // cache distance to every visited node, not just the target
                distanceCache.get(start).put(node, distance);
                distanceCache.get(node).put(start, distance);

                for (int neighbor : adjList.getOrDefault(node, Collections.emptyList())) {
                    if (!visited[neighbor]) {
                        queue.offer(neighbor);
                        visited[neighbor] = true;
                    }
                }
            }

            if (!queue.isEmpty())
                distance++;
        }

        return -1;
    }

}