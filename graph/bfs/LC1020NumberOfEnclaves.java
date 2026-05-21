package graph.bfs;

// Created at: 22-May-2025
// Last revised at: 22-May-2025
// Link: https://leetcode.com/problems/number-of-enclaves/

import java.util.Deque;
import java.util.LinkedList;

/*
Problem Description:
--------------------
Statement:
    You are given an m x n binary matrix grid, where 0 represents sea and 1 represents land.
    A land cell is an enclave if it cannot "walk off" the boundary of the grid in any number
    of moves (4-directional). Return the number of land cells that are enclaves.

Example:
    Input:  grid = [[0,0,0,0],
                    [1,0,1,0],
                    [0,1,1,0],
                    [0,0,0,0]]
    Output: 3
    Explanation: The two 1s in column 0 row 1 and row 2 column 1,2 are enclosed.
                 The top-right 1 is also enclosed.

Constraints:
    - m == grid.length, n == grid[i].length
    - 1 <= m, n <= 500
    - grid[i][j] is either 0 or 1
*/

/*
Approach 1: Brute Force DFS from every land cell
Idea:
    For each land cell, run a DFS/BFS and check if it can reach a boundary.
    If not, count all cells in that component.

Time Complexity: O((m * n)^2) — worst case DFS per cell
Space Complexity: O(m * n) — visited array

Drawbacks:
    Redundant work: every cell in the same component triggers a full traversal.
    Cannot easily reuse results across cells in the same island.

Approach 2: Multi-source BFS from all border land cells (Optimal)
Idea:
    Any land cell reachable from the border is NOT an enclave.
    Seed the BFS queue with all border land cells, flood-fill inland marking
    them as visited (grid value = 2). Whatever land (value = 1) remains after
    the BFS is completely enclosed — count those.

Time Complexity: O(m * n) — every cell visited at most once
Space Complexity: O(m * n) — BFS queue in worst case

Key Insight:
    Instead of asking "can this cell escape?", flip the question —
    mark everything that CAN escape, then count what's left.
*/

/*
Method to Solve:
----------------
1. Seed BFS queue with every land cell on the 4 borders; mark each as 2.
2. BFS: for each dequeued cell, expand to 4 neighbours.
   If neighbour is land (1), mark it 2 and enqueue it.
3. After BFS, iterate the grid. Count cells still equal to 1 — those are enclaves.
*/

// Time Complexity: O(m * n)
// Space Complexity: O(m * n)

public class LC1020NumberOfEnclaves {

    private final int[] dr = { 0, 0, -1, 1 };
    private final int[] dc = { -1, 1, 0, 0 };

    private static class Position {
        int row, col;

        Position(int r, int c) {
            row = r;
            col = c;
        }
    }

    /**
     * Returns true if (i, j) falls inside the grid boundaries.
     *
     * @param i row index
     * @param j column index
     * @param m total rows
     * @param n total columns
     * @return true if within bounds
     */
    private boolean withinBound(int i, int j, int m, int n) {
        return i >= 0 && i < m && j >= 0 && j < n;
    }

    /**
     * Floods all border-connected land cells using BFS, marking them as 2.
     * Any remaining 1s after the flood are enclaves.
     *
     * @param grid binary grid of 0s and 1s
     * @param q    BFS queue pre-seeded with border land cells
     */
    private void floodFromBorders(int[][] grid, Deque<Position> q) {
        int rows = grid.length;
        int cols = grid[0].length;

        while (!q.isEmpty()) {
            Position pos = q.poll();
            int r = pos.row;
            int c = pos.col;

            for (int k = 0; k < 4; k++) {
                int nR = r + dr[k];
                int nC = c + dc[k];
                if (withinBound(nR, nC, rows, cols) && grid[nR][nC] == 1) {
                    grid[nR][nC] = 2; // mark reachable from border
                    q.offer(new Position(nR, nC));
                }
            }
        }
    }

    /**
     * Counts land cells that cannot reach the grid boundary (enclaves).
     *
     * @param grid binary grid where 0 = sea, 1 = land
     * @return count of enclave land cells
     */
    public int numEnclaves(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        Deque<Position> q = new LinkedList<>();

        // seed all border land cells
        for (int row = 0; row < rows; row++) {
            if (grid[row][0] == 1) {
                q.offer(new Position(row, 0));
                grid[row][0] = 2;
            }
            if (grid[row][cols - 1] == 1) {
                q.offer(new Position(row, cols - 1));
                grid[row][cols - 1] = 2;
            }
        }
        for (int col = 0; col < cols; col++) {
            if (grid[0][col] == 1) {
                q.offer(new Position(0, col));
                grid[0][col] = 2;
            }
            if (grid[rows - 1][col] == 1) {
                q.offer(new Position(rows - 1, col));
                grid[rows - 1][col] = 2;
            }
        }

        floodFromBorders(grid, q);

        // whatever land remains is enclosed
        int cnt = 0;
        for (int[] row : grid) {
            for (int cell : row) {
                if (cell == 1)
                    cnt++;
            }
        }
        return cnt;
    }
}
