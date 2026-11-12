package graph.bfs.multi_source_bfs;

// Created at: 12-November-2026
// Last revised at: 12-November-2026
// Link: https://leetcode.com/problems/shortest-bridge/

import java.util.*;

/*
Problem Description:
--------------------
Statement:
Given an n x n binary grid containing exactly two islands,
find the minimum number of 0s that must be flipped to 1s
to connect the two islands.

An island is a group of 1s connected in four directions.

Example:
Input:
grid = [[0,1,0],
        [0,0,0],
        [0,0,1]]

Output: 2

Explanation:
Flip the two water cells between the islands to connect them.

Constraints:
1 <= n <= 100
grid[i][j] is either 0 or 1.
There are exactly two islands.
*/

/*
Approach 1: DFS + Multi-source BFS

Idea:
1. Use DFS to identify the first island.
2. Add every cell belonging to this island to the BFS queue.
3. Start multi-source BFS from all these cells simultaneously.
4. Expand level by level, exploring adjacent water cells.
5. When another island is encountered, return the current BFS distance.

Why it works:
BFS explores an unweighted graph level by level.
Starting from every cell of the first island ensures that
the shortest distance from any point of that island is explored.

Time Complexity:
O(n²) - Every grid cell is visited at most once.

Space Complexity:
O(n²) - Visited matrix, BFS queue and DFS recursion stack.

Key Insight:
DFS identifies the source connected component.
Multi-source BFS finds the minimum distance to the target component.
*/

class LC934ShortestBridge {

    private record Cell(int row, int col) {
    }

    private static final int[] DR = { 0, 0, 1, -1 };
    private static final int[] DC = { 1, -1, 0, 0 };

    /**
     * Checks whether a cell lies within the grid boundaries.
     *
     * @param row current row
     * @param col current column
     * @param n   grid dimension
     * @return true if the cell is within bounds
     */
    private boolean withinBounds(int row, int col, int n) {
        return row >= 0 && row < n && col >= 0 && col < n;
    }

    /**
     * Identifies the first island using DFS and adds all its
     * cells to the initial multi-source BFS queue.
     *
     * @param row  current row
     * @param col  current column
     * @param grid input grid
     * @param q    initial BFS queue
     * @param vis  visited matrix
     * @return none
     */
    private void dfs(int row, int col, int[][] grid,
            Deque<Cell> q, boolean[][] vis) {

        vis[row][col] = true;
        q.offer(new Cell(row, col));

        for (int k = 0; k < 4; k++) {
            int nR = row + DR[k];
            int nC = col + DC[k];

            if (withinBounds(nR, nC, grid.length)
                    && !vis[nR][nC]
                    && grid[nR][nC] == 1) {

                dfs(nR, nC, grid, q, vis);
            }
        }
    }

    /*
     * Method to Solve:
     * ----------------
     * 1. Find the first island using DFS.
     * 2. Add all its cells to the BFS queue.
     * 3. Maintain a visited matrix to avoid revisiting cells.
     * 4. Process BFS level by level.
     * 5. Expand into adjacent water cells.
     * 6. Return the current distance when the second island is reached.
     */

    /**
     * Finds the minimum number of water cells to flip
     * to connect the two islands.
     *
     * @param grid binary grid containing exactly two islands
     * @return minimum number of flips required
     */
    public int shortestBridge(int[][] grid) {

        int n = grid.length;

        Deque<Cell> q = new ArrayDeque<>();
        boolean[][] vis = new boolean[n][n];

        // Identify the first island.
        for (int i = 0; i < n; i++) {
            boolean found = false;

            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    dfs(i, j, grid, q, vis);
                    found = true;
                    break;
                }
            }

            if (found)
                break;
        }

        if (q.isEmpty())
            throw new IllegalStateException("No island found");

        int distance = 0;

        // Expand from the entire first island.
        while (!q.isEmpty()) {

            int size = q.size();

            for (int i = 0; i < size; i++) {

                Cell cell = q.poll();

                for (int k = 0; k < 4; k++) {

                    int nR = cell.row() + DR[k];
                    int nC = cell.col() + DC[k];

                    if (withinBounds(nR, nC, n)
                            && !vis[nR][nC]) {

                        if (grid[nR][nC] == 1) {
                            return distance;
                        }

                        q.offer(new Cell(nR, nC));
                        vis[nR][nC] = true;
                    }
                }
            }

            distance++;
        }

        return -1;
    }
}

// Time Complexity: O(n²)
// Space Complexity: O(n²)