package graph.bfs;

import java.util.LinkedList;
import java.util.Queue;

// Created at: 20-May-2026
// Last revised at: 20-May-2026
// Link: https://leetcode.com/problems/rotting-oranges/

/*
Problem Description:
--------------------
Statement:
You are given an m x n grid where each cell can have one of three values:
  0 — empty cell
  1 — fresh orange
  2 — rotten orange

Every minute, any fresh orange 4-directionally adjacent to a rotten orange
becomes rotten. Return the minimum number of minutes until no fresh orange
remains. If this is impossible, return -1.

Example:
Input:  grid = [[2,1,1],[1,1,0],[0,1,1]]
Output: 4

Input:  grid = [[0,2]]
Output: 0

Constraints:
- m == grid.length, n == grid[i].length
- 1 <= m, n <= 10
- grid[i][j] is 0, 1, or 2
*/

/*
Approach 1: Multi-Source BFS ★
-------------------------------
Idea:
Seed the BFS queue with all initially rotten oranges simultaneously. Process
level by level — each level represents one minute. A fresh orange becomes
rotten only when dequeued as a neighbor, so we track a fresh counter inline
and increment minutes only when the queue is non-empty after a batch, meaning
at least one new orange rotted this round.

Time Complexity:  O(m × n) — every cell is enqueued and processed at most once.
Space Complexity: O(m × n) — queue can hold up to m × n coordinates.

Key Insight:
Multi-source BFS naturally models simultaneous spread from multiple origins.
The "increment only if queue non-empty after batch" trick cleanly handles
the off-by-one — the initial rotten oranges consume no minute themselves.
*/

/*
Method to Solve:
----------------
1. Scan the grid: enqueue all rotten oranges (value 2), count fresh oranges.
2. Run BFS level by level using a size snapshot per round.
3. For each rotten orange, check 4 neighbors — if fresh, mark rotten,
   enqueue, and decrement fresh count.
4. After processing a batch, increment minutes only if the queue is non-empty
   (i.e., new oranges were just added).
5. After BFS, return -1 if any fresh orange remains, else return minutes.
*/

// Time Complexity: O(m × n)
// Space Complexity: O(m × n)

public class LC994RottingOranges {

    private static class Coordinate {
        int row;
        int col;

        public Coordinate(int r, int c) {
            row = r;
            col = c;
        }
    }

    /**
     * Checks whether the given cell is within grid bounds.
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
     * Returns the minimum minutes for all fresh oranges to rot, or -1 if
     * some fresh oranges are unreachable.
     *
     * @param grid m x n grid of 0s (empty), 1s (fresh), and 2s (rotten)
     * @return minimum minutes to rot all fresh oranges, or -1 if impossible
     */
    public int orangesRotting(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int minutes = 0;
        int fresh = 0;
        Queue<Coordinate> queue = new LinkedList<>();

        for (int row = 0; row < m; row++) {
            for (int col = 0; col < n; col++) {
                if (grid[row][col] == 2) {
                    queue.offer(new Coordinate(row, col));
                } else if (grid[row][col] == 1) {
                    fresh++;
                }
            }
        }

        int[] dr = { 0, 0, 1, -1 };
        int[] dc = { 1, -1, 0, 0 };

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int orange = 0; orange < size; orange++) {
                Coordinate c = queue.poll();

                for (int k = 0; k < 4; k++) {
                    int nR = c.row + dr[k];
                    int nC = c.col + dc[k];

                    if (withinBound(nR, nC, m, n) && grid[nR][nC] == 1) {
                        queue.offer(new Coordinate(nR, nC));
                        grid[nR][nC] = 2;
                        fresh--;
                    }
                }
            }

            // new oranges rotted this round
            if (!queue.isEmpty())
                minutes++;
        }

        return fresh > 0 ? -1 : minutes;
    }
}
