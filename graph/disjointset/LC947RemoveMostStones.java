package graph.disjointset;

// Created at: 01-June-2025
// Last revised at: 01-June-2025
// Link: https://leetcode.com/problems/most-stones-removed-with-same-row-or-column/

import java.util.*;

/*
Problem Description:
--------------------
Statement:
On a 2D plane, we place n stones at integer coordinate points. A stone can be removed
if it shares either the same row or the same column as another stone that has not been removed.
Return the largest possible number of stones that can be removed.

Example:
Input: stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
Output: 5
Explanation: One stone cannot be removed — one per connected component must remain.

Constraints:
- 1 <= stones.length <= 1000
- 0 <= stones[i][j] <= 10^4
- No two stones are at the same position.
*/

/*
Approach 1: Brute Force (Pairwise Union)
Idea:
Treat each stone as a node. For every pair (i, j), if they share a row or column,
union them. Count remaining roots — answer is n - roots.

Time Complexity: O(n^2 * α(n))
Space Complexity: O(n)

Drawbacks:
Comparing every pair is unnecessary. We can do this in one pass using coordinate maps.

★ Approach 2: DSU with Coordinate Maps (Optimal)
Idea:
For each stone, check if its row or column was already claimed by a previous stone.
If yes, union current stone with that representative. If no, register current stone
as the representative for that coordinate.
Answer = total stones - number of connected components (roots in DSU).

Time Complexity: O(n * α(n)) ≈ O(n)
Space Complexity: O(n)

Key Insight:
We don't need to update the map after first insertion — any future stone sharing
that coordinate can union with the original representative. Transitive connectivity
handles the rest.
*/

/*
Method to Solve:
----------------
1. Init DSU with n nodes (parent = -1, size = 1).
2. Maintain rowMap (x → stone index) and colMap (y → stone index).
3. For each stone i at (x, y):
   a. If rowMap has x → union(rowMap[x], i), else → rowMap[x] = i
   b. If colMap has y → union(colMap[y], i), else → colMap[y] = i
4. Count stones where parent[i] == -1 → these are roots (one per component).
5. Return n - rootCount.
*/

// Time Complexity: O(n * α(n)) ≈ O(n)
// Space Complexity: O(n)

public class LC947RemoveMostStones {

    private class DSU {
        int[] parent;
        int[] size;
        Map<Integer, Integer> rowMap;
        Map<Integer, Integer> colMap;

        /**
         * Initialises DSU with n nodes, each as its own root.
         *
         * @param nodes total number of stones
         */
        public DSU(int nodes) {
            parent = new int[nodes];
            size = new int[nodes];
            Arrays.fill(parent, -1);
            Arrays.fill(size, 1);
            rowMap = new HashMap<>();
            colMap = new HashMap<>();
        }

        /**
         * Finds root of node with path compression.
         *
         * @param node the node to find root for
         * @return root of the component
         */
        public int findParent(int node) {
            if (parent[node] == -1)
                return node;
            return parent[node] = findParent(parent[node]);
        }

        /**
         * Unions two nodes by size.
         *
         * @param a first node
         * @param b second node
         */
        public void unionBySize(int a, int b) {
            int parA = findParent(a);
            int parB = findParent(b);

            if (parA == parB)
                return;

            if (size[parA] > size[parB]) {
                parent[parB] = parA;
                size[parA] += size[parB];
            } else {
                parent[parA] = parB;
                size[parB] += size[parA];
            }
        }
    }

    /**
     * Returns the maximum number of stones that can be removed.
     *
     * @param stones 2D array of stone coordinates
     * @return maximum removable stones
     */
    public int removeStones(int[][] stones) {
        int total = stones.length;
        DSU dsu = new DSU(total);

        for (int i = 0; i < total; i++) {
            int x = stones[i][0];
            int y = stones[i][1];

            // handle row coordinate
            if (dsu.rowMap.containsKey(x)) {
                dsu.unionBySize(dsu.rowMap.get(x), i);
            } else {
                dsu.rowMap.put(x, i);
            }

            // handle col coordinate
            if (dsu.colMap.containsKey(y)) {
                dsu.unionBySize(dsu.colMap.get(y), i);
            } else {
                dsu.colMap.put(y, i);
            }
        }

        // count components
        int groups = 0;
        for (int par : dsu.parent) {
            if (par == -1)
                groups++;
        }

        return total - groups;
    }
}