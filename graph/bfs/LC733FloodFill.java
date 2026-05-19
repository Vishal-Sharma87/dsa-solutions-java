package graph.bfs;

import java.util.LinkedList;
import java.util.Queue;

// Created at: 20-May-2026
// Last revised at: 20-May-2026
// Link: https://leetcode.com/problems/flood-fill/

/*
Problem Description:
--------------------
Statement:
You are given an image represented as an m x n integer grid, a starting pixel
(sr, sc), and a new color. Perform a flood fill starting from (sr, sc): color
the starting pixel and all 4-directionally connected pixels that share the same
original color, then repeat for newly colored pixels.

Example:
Input:  image = [[1,1,1],[1,1,0],[1,0,1]], sr = 1, sc = 1, color = 2
Output: [[2,2,2],[2,2,0],[2,0,1]]

Input:  image = [[0,0,0],[0,0,0]], sr = 0, sc = 0, color = 0
Output: [[0,0,0],[0,0,0]]

Constraints:
- m == image.length, n == image[i].length
- 1 <= m, n <= 50
- 0 <= image[i][j], color <= 65535
- 0 <= sr < m, 0 <= sc < n
*/

/*
Approach 1: BFS ★
------------------
Idea:
Start BFS from (sr, sc). Mark each visited cell with the new color immediately
on discovery. Spread only to 4-directional neighbors that still carry the
original color. Since original != new color (guarded upfront), the original
color acts as a natural "unvisited" marker — no separate visited array needed.

Time Complexity:  O(m × n) — every cell is processed at most once.
Space Complexity: O(m × n) — queue can hold up to m × n coordinates.

Key Insight:
Marking cells on discovery (not on dequeue) is critical — it prevents the same
cell from being enqueued multiple times from different neighbors, keeping BFS
efficient and correct.
*/

/*
Method to Solve:
----------------
1. Capture original color at (sr, sc).
2. If original == new color, return image unchanged.
3. Color (sr, sc) and enqueue it.
4. BFS: for each dequeued cell, check 4 neighbors.
5. If neighbor is within bounds and still has original color, color it and enqueue.
6. Return the modified image.
*/

// Time Complexity: O(m × n)
// Space Complexity: O(m × n)

public class LC733FloodFill {

    private static class Coordinate {
        int row;
        int col;

        public Coordinate(int r, int c) {
            row = r;
            col = c;
        }
    }

    /**
     * Checks whether the given cell is within image bounds.
     *
     * @param r row index to validate
     * @param c column index to validate
     * @param m total number of rows
     * @param n total number of columns
     * @return true if (r, c) is a valid cell
     */
    private boolean withinBound(int r, int c, int m, int n) {
        return r >= 0 && r < m && c >= 0 && c < n;
    }

    /**
     * Performs flood fill on the image starting from (sr, sc),
     * recoloring all 4-directionally connected pixels sharing
     * the original color.
     *
     * @param image 2D grid representing the image
     * @param sr    starting row
     * @param sc    starting column
     * @param color new color to apply
     * @return the flood-filled image
     */
    public int[][] floodFill(int[][] image, int sr, int sc, int color) {
        int m = image.length;
        int n = image[0].length;

        int original = image[sr][sc];

        // no-op if already the target color
        if (original == color)
            return image;

        int[] dr = { 0, 0, 1, -1 };
        int[] dc = { 1, -1, 0, 0 };

        Queue<Coordinate> queue = new LinkedList<>();
        image[sr][sc] = color;
        queue.offer(new Coordinate(sr, sc));

        while (!queue.isEmpty()) {
            Coordinate c = queue.poll();

            for (int k = 0; k < 4; k++) {
                int nR = c.row + dr[k];
                int nC = c.col + dc[k];

                if (withinBound(nR, nC, m, n) && image[nR][nC] == original) {
                    image[nR][nC] = color;
                    queue.offer(new Coordinate(nR, nC));
                }
            }
        }

        return image;
    }
}