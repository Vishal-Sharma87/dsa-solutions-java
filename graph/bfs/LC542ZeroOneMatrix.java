package graph.bfs;

// Created at: 21-May-2026
// Last revised at: 21-May-2026
// Link: https://leetcode.com/problems/01-matrix/

/*
Problem Description:
--------------------
Statement:
Given an m x n binary matrix mat, return a matrix of the same dimensions
where each cell contains the distance to the nearest 0. Distance is measured
as the number of steps moving horizontally or vertically.

Example:
Input:
  mat = [[0,0,0],
         [0,1,0],
         [1,1,1]]
Output:
       [[0,0,0],
        [0,1,0],
        [1,2,1]]

Constraints:
- m == mat.length, n == mat[i].length
- 1 <= m, n <= 10^4
- 1 <= m * n <= 10^4
- mat[i][j] is either 0 or 1.
- There is at least one 0 in mat.
*/

/*
Approach 1: Single-source BFS (Naive)
---------------------------------------
Idea:
For every 1-cell, run a BFS to find the nearest 0-cell.

Time Complexity: O((m*n)^2) — BFS per cell.
Space Complexity: O(m*n)

Drawbacks:
Extremely slow for large grids; redundant work across cells.

Approach 2: Multi-source BFS ★
--------------------------------
Idea:
Instead of finding the nearest 0 for each 1, reverse the perspective:
seed the BFS simultaneously from all 0-cells at distance 0.
Expand level by level — the first time BFS reaches a 1-cell, that
level number is exactly the shortest distance to any 0.

Mark cells visited by setting mat[r][c] = 0 in-place once enqueued,
so no cell is ever enqueued twice.

Time Complexity: O(m * n) — each cell is enqueued and processed once.
Space Complexity: O(m * n) — BFS queue + distances array.

Key Insight:
Multi-source BFS is equivalent to adding a virtual super-source connected
to all 0-cells at distance 0, then running a single BFS from it.
This eliminates redundant traversal entirely.
*/

/*
Method to Solve:
----------------
1. Enqueue all 0-cells at dis=0 into the BFS queue.
2. Process level by level:
   a. Poll all cells at the current level, write dis to distances[][].
   b. For each of their 4 neighbours, if not yet visited (mat != 0),
      enqueue and mark visited (mat = 0).
   c. Increment dis only if the queue is still non-empty.
3. Return the distances matrix.
*/

import java.util.LinkedList;
import java.util.Queue;

// Time Complexity: O(m * n)
// Space Complexity: O(m * n)
public class LC542ZeroOneMatrix {

    private static class Position {
        int row;
        int col;

        public Position(int r, int c) {
            row = r;
            col = c;
        }
    }

    /**
     * Checks whether (i, j) falls within the grid boundaries.
     *
     * @param i row index
     * @param j column index
     * @param m number of rows
     * @param n number of columns
     * @return true if (i, j) is a valid cell
     */
    private boolean withinBound(int i, int j, int m, int n) {
        return i >= 0 && i < m && j >= 0 && j < n;
    }

    /**
     * Returns the distance of each cell to its nearest 0-cell,
     * using a multi-source BFS seeded from all 0-cells simultaneously.
     *
     * @param mat binary matrix of 0s and 1s (mutated in-place as visited marker)
     * @return matrix of shortest distances to the nearest 0
     */
    public int[][] updateMatrix(int[][] mat) {
        int rows = mat.length;
        int cols = mat[0].length;

        Queue<Position> q = new LinkedList<>();

        // seed all 0-cells at distance 0
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (mat[i][j] == 0)
                    q.offer(new Position(i, j));
            }
        }

        int dis = 0;
        int[][] distances = new int[rows][cols];

        int[] dr = { 0, 0, -1, 1 };
        int[] dc = { -1, 1, 0, 0 };

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                Position pos = q.poll();
                int r = pos.row;
                int c = pos.col;

                distances[r][c] = dis;

                for (int k = 0; k < 4; k++) {
                    int nR = r + dr[k];
                    int nC = c + dc[k];

                    if (withinBound(nR, nC, rows, cols) && mat[nR][nC] != 0) {
                        q.offer(new Position(nR, nC));
                        mat[nR][nC] = 0; // mark visited
                    }
                }
            }

            if (!q.isEmpty())
                dis++;
        }

        return distances;
    }
}
