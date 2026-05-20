package graph.bfs;

// Created at: 21-May-2026
// Last revised at: 21-May-2026
// Link: https://leetcode.com/problems/surrounded-regions/

/*
Problem Description:
--------------------
Statement:
Given an m x n board of characters 'X' and 'O', capture all regions of 'O'
that are fully surrounded by 'X'. A region is captured by flipping all 'O's
in it to 'X'. An 'O' is NOT captured if it is on the border or connected
(horizontally/vertically) to a border 'O'.

Example:
Input:
  board = [['X','X','X','X'],
           ['X','O','O','X'],
           ['X','X','O','X'],
           ['X','O','X','X']]
Output:
         [['X','X','X','X'],
          ['X','X','X','X'],
          ['X','X','X','X'],
          ['X','O','X','X']]
Explanation:
  The bottom-left 'O' touches the border — safe.
  The inner 'O's are surrounded — flipped to 'X'.

Constraints:
- m == board.length, n == board[i].length
- 1 <= m, n <= 200
- board[i][j] is 'X' or 'O'.
*/

/*
Approach 1: DFS/BFS from every interior 'O' (Naive)
------------------------------------------------------
Idea:
For each 'O', BFS/DFS outward to check if it can reach a border.
If yes → safe; if no → flip to 'X'.

Time Complexity: O((m*n)^2) in the worst case — redundant traversal.
Space Complexity: O(m*n)

Drawbacks:
Revisits the same cells repeatedly; impractical for large boards.

Approach 2: Reverse BFS from border 'O's ★
--------------------------------------------
Idea:
Flip the problem: instead of checking each interior 'O' outward,
seed BFS from every border 'O' and mark all reachable 'O's as safe ('-').
After BFS, any remaining 'O' is surrounded → flip to 'X'.
Then restore '-' → 'O'.

Three-pass structure:
1. Seed: scan all 4 borders, enqueue 'O's and mark '-'.
2. BFS: expand into unvisited 'O' neighbours, mark them '-'.
3. Sweep: 'O' → 'X' (surrounded), '-' → 'O' (border-safe).

Time Complexity: O(m * n) — each cell touched at most twice.
Space Complexity: O(m * n) — BFS queue.

Key Insight:
Any 'O' not reachable from the border is surrounded.
It's far cheaper to find the safe set (border-connected) and
flip everything else, than to prove each cell surrounded individually.
*/

/*
Method to Solve:
----------------
1. Early exit if rows <= 2 or cols <= 2 (all cells are border — nothing surrounded).
2. Scan left & right columns, top & bottom rows.
   Enqueue border 'O's, mark them '-'.
3. BFS: for each dequeued cell, expand into 4-directional 'O' neighbours,
   mark '-', enqueue.
4. Final sweep:
   - 'O' → 'X'  (was never reached from border → surrounded)
   - '-' → 'O'  (border-connected → restore)
*/

import java.util.LinkedList;
import java.util.Queue;

// Time Complexity: O(m * n)
// Space Complexity: O(m * n)
public class LC130SurroundedRegions {

    private static final int[] dr = { 0, 0, -1, 1 };
    private static final int[] dc = { -1, 1, 0, 0 };

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
     * Captures all 'O' regions fully surrounded by 'X' by flipping them to 'X'.
     * Border-connected 'O's are left unchanged.
     * Modifies the board in-place.
     *
     * @param board the character grid of 'X' and 'O'
     */
    public void solve(char[][] board) {
        int rows = board.length;
        int cols = board[0].length;

        // every cell is a border cell — nothing can be surrounded
        if (rows <= 2 || cols <= 2)
            return;

        Queue<Position> q = new LinkedList<>();

        // seed from left and right border columns
        for (int row = 0; row < rows; row++) {
            if (board[row][0] == 'O') {
                q.offer(new Position(row, 0));
                board[row][0] = '-';
            }
            if (board[row][cols - 1] == 'O') {
                q.offer(new Position(row, cols - 1));
                board[row][cols - 1] = '-';
            }
        }

        // seed from top and bottom border rows
        for (int col = 0; col < cols; col++) {
            if (board[0][col] == 'O') {
                q.offer(new Position(0, col));
                board[0][col] = '-';
            }
            if (board[rows - 1][col] == 'O') {
                q.offer(new Position(rows - 1, col));
                board[rows - 1][col] = '-';
            }
        }

        // BFS: mark all border-connected 'O's as safe
        while (!q.isEmpty()) {
            Position pos = q.poll();
            int r = pos.row;
            int c = pos.col;

            for (int k = 0; k < 4; k++) {
                int nR = r + dr[k];
                int nC = c + dc[k];

                if (withinBound(nR, nC, rows, cols) && board[nR][nC] == 'O') {
                    q.offer(new Position(nR, nC));
                    board[nR][nC] = '-'; // safe — reachable from border
                }
            }
        }

        // final pass: flip surrounded 'O's, restore safe '-'s
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i][j] == 'O')
                    board[i][j] = 'X'; // surrounded
                else if (board[i][j] == '-')
                    board[i][j] = 'O'; // border-safe
            }
        }
    }
}