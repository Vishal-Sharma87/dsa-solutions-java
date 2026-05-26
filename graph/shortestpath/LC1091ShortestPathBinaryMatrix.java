package graph.shortestpath;

// Created at: 27-May-2026
// Last revised at: 27-May-2026
// Link: https://leetcode.com/problems/shortest-path-in-binary-matrix/

import java.util.*;

/*
Problem Description:
--------------------
Statement:
Given an n x n binary matrix `grid`, return the length of the shortest clear path
from top-left (0,0) to bottom-right (n-1,n-1). A clear path consists only of cells
with value 0 and can move in all 8 directions. Return -1 if no such path exists.
The length of a path is the number of cells visited.

Example:
grid = [[0,1],[1,0]]
Output: 2

Constraints:
n == grid.length == grid[i].length
1 <= n <= 100
grid[i][j] is 0 or 1
*/

/*
Approach: BFS (Level-order, 8-directional)
-------------------------------------------
Idea:
BFS from (0,0) expanding in all 8 directions. Since all moves have equal cost,
BFS guarantees the first time we reach the destination it's via the shortest path.
Mark visited cells by flipping grid[r][c] = 1 in-place — avoids a separate boolean array.

Time Complexity: O(n^2) — each cell is visited at most once
Space Complexity: O(n^2) — queue can hold up to all cells

Key Insight:
`len` is incremented at the START of each level (before polling), so the source
cell itself counts as length 1. This matches the problem's definition of path length
as number of cells visited, not number of edges.
*/

/*
Method to Solve:
----------------
1. If grid[0][0] == 1 or grid[n-1][n-1] == 1, return -1 immediately.
2. BFS from (0,0). Mark (0,0) visited by setting grid[0][0] = 1.
3. Per level: increment `len`, capture queue size, poll all nodes in current level.
4. If destination is reached, return current `len`.
5. For each of the 8 neighbors: enqueue if in bounds and grid == 0, mark visited.
6. If BFS exhausts without reaching destination, return -1.
*/

public class LC1091ShortestPathBinaryMatrix {

    private static class Pair {
        int row;
        int col;

        public Pair(int r, int c) {
            row = r;
            col = c;
        }
    }

    private static final int[] dr = { -1, -1, -1, 0, 1, 1, 1, 0 };
    private static final int[] dc = { -1, 0, 1, 1, 1, 0, -1, -1 };

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
     * Returns the length of the shortest clear path in a binary matrix from (0,0)
     * to (n-1,n-1).
     *
     * @param grid n x n binary matrix
     * @return shortest path length (number of cells), or -1 if no path exists
     */
    public int shortestPathBinaryMatrix(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        if (grid[0][0] == 1 || grid[rows - 1][cols - 1] == 1)
            return -1;

        Deque<Pair> q = new LinkedList<>();
        q.offer(new Pair(0, 0));
        grid[0][0] = 1; // mark visited

        int len = 0;

        while (!q.isEmpty()) {
            len++; // each level = one more cell in the path
            int size = q.size();

            for (int i = 0; i < size; i++) {
                Pair popped = q.poll();
                int row = popped.row;
                int col = popped.col;

                if (row == rows - 1 && col == cols - 1)
                    return len;

                for (int k = 0; k < 8; k++) {
                    int nR = row + dr[k];
                    int nC = col + dc[k];

                    if (withinBound(nR, nC, rows, cols) && grid[nR][nC] == 0) {
                        q.offer(new Pair(nR, nC));
                        grid[nR][nC] = 1; // mark visited
                    }
                }
            }
        }

        return -1;
    }
}

// Time Complexity: O(n^2)
// Space Complexity: O(n^2)