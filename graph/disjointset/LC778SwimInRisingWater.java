package graph.disjointset;

// Created at: 02-June-2026
// Last revised at: 02-June-2026
// Link: https://leetcode.com/problems/swim-in-rising-water/

import java.util.*;

/*
Problem Description:
--------------------
Statement:
You are given an n x n integer grid where grid[i][j] represents the elevation at cell (i, j).
It rains and the water level rises. At time t, the water level is t. You can swim from one cell
to an adjacent (4-directional) cell if both cells have elevation <= t. You can swim infinite
distances in zero time. Find the minimum time t such that you can swim from (0, 0) to (n-1, n-1).

Example:
Input: grid = [[0,2],[1,3]]
Output: 3
Explanation: At t=3, all cells are reachable and a path from (0,0) to (1,1) exists.

Constraints:
- n == grid.length == grid[i].length
- 1 <= n <= 50
- 0 <= grid[i][j] < n^2
- All values in grid are unique (it's a permutation of [0, n^2 - 1])
*/

/*
Approach 1: Binary Search + BFS/DFS
Idea:
Binary search on the answer t. For each candidate t, BFS/DFS to check
if a path from (0,0) to (n-1,n-1) exists using only cells with elevation <= t.

Time Complexity: O(n^2 * log(n^2)) = O(n^2 * log n)
Space Complexity: O(n^2)

Drawbacks:
More code. Two separate concerns — binary search and graph traversal — interleaved.

★ Approach 2: DSU with Elevation Sort
Idea:
Since grid is a permutation, invert it: build valToPos[elevation] = cell index.
Iterate t from 0 to n^2 - 1. At each t, "activate" the cell with elevation t.
Union it with any already-activated (elevation < t) neighbour.
The moment (0,0) and (n-1,n-1) are in the same component, return t.

Key insight: we process cells in elevation order, so a neighbour with elevation < t
is already in the DSU. We never need to union with equal-elevation cells because
all elevations are distinct (permutation guarantee).

Time Complexity: O(n^2 * α(n^2)) — near-linear
Space Complexity: O(n^2)
*/

/*
Method to Solve:
----------------
1. Build valToPos[] — maps elevation value to its flat cell index (row * n + col).
2. Iterate t from 0 to n^2 - 1:
   a. Get the cell with elevation t from valToPos.
   b. For each of its 4 neighbours, if in-bounds and elevation < t, union them.
   c. After processing all 4 neighbours, check if cell 0 and cell n^2-1 are connected.
   d. If yes, return t.
3. The check must be outside the neighbour loop — all unions at time t must complete
   before checking connectivity.
*/

// Time Complexity: O(n^2 * α(n^2))
// Space Complexity: O(n^2)

public class LC778SwimInRisingWater {

    private class DSU {
        int[] parent;
        int[] size;

        public DSU(int n) {
            parent = new int[n];
            size = new int[n];
            Arrays.fill(size, 1);
            for (int i = 0; i < n; i++) {
                parent[i] = i;
            }
        }

        /**
         * Finds root of node with path compression.
         *
         * @param node the node to find root for
         * @return root of the component
         */
        public int findParent(int node) {
            if (parent[node] == node)
                return node;
            return parent[node] = findParent(parent[node]);
        }

        /**
         * Unions two components by size.
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

        /**
         * Checks if two nodes belong to the same component.
         *
         * @param i first node
         * @param j second node
         * @return true if connected
         */
        public boolean isConnected(int i, int j) {
            return findParent(i) == findParent(j);
        }
    }

    /**
     * Returns the minimum time t to swim from top-left to bottom-right.
     * Cells are activated in elevation order; DSU tracks reachability.
     *
     * @param grid n x n grid where grid[i][j] is the elevation at cell (i, j)
     * @return minimum t such that a path from (0,0) to (n-1,n-1) exists
     */
    public int swimInWater(int[][] grid) {
        int n = grid.length;

        DSU dsu = new DSU(n * n);

        // invert grid: elevation → flat cell index
        int[] valToPos = new int[n * n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                valToPos[grid[i][j]] = i * n + j;
            }
        }

        int[] dr = { 0, 0, -1, 1 };
        int[] dc = { -1, 1, 0, 0 };

        for (int t = 0; t < n * n; t++) {
            int pos = valToPos[t];
            int r = pos / n;
            int c = pos % n;

            for (int k = 0; k < 4; k++) {
                int nR = r + dr[k];
                int nC = c + dc[k];
                // neighbour must be in-bounds and already activated (elevation < t)
                // grid is a permutation so grid[nR][nC] == t is impossible — < and <= are
                // equivalent
                if (nR >= 0 && nR < n && nC >= 0 && nC < n && grid[nR][nC] < t) {
                    dsu.unionBySize(pos, nR * n + nC);
                }
            }

            // check after all neighbours at time t are processed — not inside the loop
            if (dsu.isConnected(0, n * n - 1))
                return t;
        }

        // unreachable given problem constraints
        return n * n - 1;
    }
}