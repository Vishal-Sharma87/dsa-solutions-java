package graph.shortestpath;

// Created at: 27-May-2026
// Last revised at: 27-May-2026
// Link: https://leetcode.com/problems/path-with-minimum-effort/

import java.util.*;

/*
Problem Description:
--------------------
Statement:
You are given a 2D array `heights` of size rows x cols. A path's effort is defined
as the maximum absolute difference in heights between consecutive cells along that path.
Return the minimum effort required to travel from (0,0) to (rows-1, cols-1).
You can move up, down, left, or right.

Example:
heights = [[1,2,2],[3,8,2],[5,3,5]]
Output: 2
(Path: 1→3→5→3→5, max diff = 2)

Constraints:
rows == heights.length
cols == heights[i].length
1 <= rows, cols <= 100
1 <= heights[i][j] <= 10^6
*/

/*
Approach: Dijkstra's (Modified — Bottleneck Shortest Path)
-----------------------------------------------------------
Idea:
This is NOT a standard sum-of-weights problem. The "cost" of a path is its maximum
edge weight (bottleneck), not the sum. Still greedy-safe: if we always process the
path with the lowest current max-effort first, the first time we reach the destination
is guaranteed to be optimal.

Treat each cell as a node. Edge weight between two adjacent cells = |h[r1][c1] - h[r2][c2]|.
For a path, the effort = max of all edge weights along it.

`best = max(currentPathMaxEffort, edgeWeight)` — carries forward the running maximum.

Time Complexity: O(rows * cols * log(rows * cols)) — Dijkstra with heap over grid nodes
Space Complexity: O(rows * cols) — effort matrix + heap

Key Insight:
The stale-entry guard `if (mini > effort[row][col]) continue` is critical here.
Without it, already-settled cells get re-expanded, which doesn't cause wrong answers
but adds unnecessary heap operations — significant on a 100x100 grid.
*/

/*
Method to Solve:
----------------
1. Init effort[][] = MAX_VALUE (worst case). Set effort[0][0] = 0.
2. Push (row=0, col=0, mini=0) into min-heap ordered by mini (effort so far).
3. Poll minimum. If it's a stale entry (mini > effort[row][col]), skip.
4. If destination reached, return mini — first pop = optimal by Dijkstra's guarantee.
5. For each of 4 neighbors: compute edge weight, compute best = max(mini, edgeWeight).
6. If best < effort[nR][nC], update effort and push to heap.
*/

public class LC1631PathWithMinimumEffort {

    private static class Info {
        int row;
        int col;
        int mini;

        public Info(int r, int c, int m) {
            row = r;
            col = c;
            mini = m;
        }
    }

    private static final int[] dr = { -1, 1, 0, 0 };
    private static final int[] dc = { 0, 0, -1, 1 };

    /**
     * Checks if cell (i, j) is within the grid bounds.
     *
     * @param i row index
     * @param j col index
     * @param m total rows
     * @param n total cols
     * @return true if within bounds
     */
    private boolean withinBound(int i, int j, int m, int n) {
        return i >= 0 && i < m && j >= 0 && j < n;
    }

    /**
     * Returns the minimum effort path from top-left to bottom-right of the height
     * grid.
     * Effort = maximum absolute height difference between consecutive cells along
     * the path.
     *
     * @param h 2D heights grid
     * @return minimum effort required
     */
    public int minimumEffortPath(int[][] h) {
        int rows = h.length;
        int cols = h[0].length;

        if (rows == 1 && cols == 1)
            return 0;

        int[][] effort = new int[rows][cols];
        for (int[] e : effort)
            Arrays.fill(e, Integer.MAX_VALUE);

        PriorityQueue<Info> minHeap = new PriorityQueue<>((x, y) -> x.mini - y.mini);

        minHeap.offer(new Info(0, 0, 0));
        effort[0][0] = 0;

        while (!minHeap.isEmpty()) {
            Info info = minHeap.poll();
            int row = info.row;
            int col = info.col;
            int mini = info.mini;

            // stale entry — a lower-effort path to this cell was already settled
            if (mini > effort[row][col])
                continue;

            // first time destination is popped = optimal answer
            if (row == rows - 1 && col == cols - 1)
                return mini;

            for (int k = 0; k < 4; k++) {
                int nR = row + dr[k];
                int nC = col + dc[k];

                if (withinBound(nR, nC, rows, cols)) {
                    int edgeWeight = Math.abs(h[nR][nC] - h[row][col]);
                    // bottleneck: carry forward the running max effort
                    int best = Math.max(mini, edgeWeight);

                    if (effort[nR][nC] > best) {
                        effort[nR][nC] = best;
                        minHeap.offer(new Info(nR, nC, best));
                    }
                }
            }
        }

        return -1;
    }
}

// Time Complexity: O(rows * cols * log(rows * cols))
// Space Complexity: O(rows * cols)