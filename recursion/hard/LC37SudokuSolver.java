package recursion.hard;

public class LC37SudokuSolver {
    // Created at : 06 - April - 2026
    // Last revised at: 06 - April - 2026
    // Link : https://leetcode.com/problems/sudoku-solver/
    // Time Complexity : O(9^m) — m = number of empty cells; 9 choices per cell
    // Space Complexity: O(1) — board is fixed 9×9; maps are constant size

    /*
     * Problem Description:
     * ---------------------
     * Given a 9×9 sudoku board with some cells pre-filled and empty cells marked
     * as '.', fill in the board so that every row, column, and 3×3 box contains
     * digits 1–9 exactly once. The input is guaranteed to have exactly one
     * solution.
     *
     * Example:
     * Input:
     * [['5','3','.','.','7','.','.','.','.'],
     * ['6','.','.','1','9','5','.','.','.'],
     * ['.','9','8','.','.','.','.','6','.'],
     * ['8','.','.','.','6','.','.','.','3'],
     * ['4','.','.','8','.','3','.','.','1'],
     * ['7','.','.','.','2','.','.','.','6'],
     * ['.','6','.','.','.','.','2','8','.'],
     * ['.','.','.','4','1','9','.','.','5'],
     * ['.','.','.','.','8','.','.','7','9']]
     *
     * Output: board filled with the unique valid solution
     *
     * Constraints:
     * - board.length == 9, board[i].length == 9
     * - board[i][j] is a digit '1'-'9' or '.'
     * - Each row, column, and 3×3 box has no repeated digits in the given clues
     * - Exactly one solution exists
     *
     * -------------------------------------------------------------------------
     * Approaches:
     * -------------------------------------------------------------------------
     *
     * ★ Approach 1: Backtracking with O(1) constraint lookup maps
     * Idea : Flatten the board to a single index 0..80 and process cells
     * one by one. Skip pre-filled cells. For empty cells, try digits
     * 1–9, checking three boolean maps (row / col / box) in O(1).
     * Place the digit, recurse, and undo on failure.
     * Box index = (row/3)*3 + (col/3) maps each cell to its 3×3 box.
     * Time : O(9^m) where m = empty cell count
     * Space : O(1) — maps are fixed-size 10×10
     */

    // constraint maps — indexed by [row/col/box][digit 1..9]
    private boolean[][] rowMap;
    private boolean[][] colMap;
    private boolean[][] box;

    /**
     * Returns true if placing `val` at (r, c) violates no row, column, or box
     * constraint.
     *
     * @param b   the current board state
     * @param r   row index
     * @param c   column index
     * @param val digit to place (1–9)
     * @return true if the placement is legal
     */
    private boolean isAllowed(char[][] b, int r, int c, int val) {
        // digit must be absent from its row, column, and 3×3 box
        return !(rowMap[r][val] || colMap[c][val] || box[((r / 3) * 3) + (c / 3)][val]);
    }

    /**
     * Recursively solves the board by trying every legal digit for each empty cell.
     * Processes cells in row-major order using a single index n (0..80).
     *
     * @param b the 9×9 board (mutated in-place)
     * @param n current cell index (row = n/9, col = n%9)
     * @return true if the board is fully and correctly filled
     */
    private boolean solve(char[][] b, int n) {
        if (n == 81)
            return true; // all 81 cells filled — done

        int row = n / 9;
        int col = n % 9;

        if (b[row][col] != '.') {
            return solve(b, n + 1); // pre-filled — skip
        }

        for (int i = 1; i <= 9; i++) {

            if (isAllowed(b, row, col, i)) {
                // place digit and update all three maps
                rowMap[row][i] = true;
                colMap[col][i] = true;
                box[((row / 3) * 3) + (col / 3)][i] = true;
                b[row][col] = (char) (i + '0');

                if (solve(b, n + 1))
                    return true;

                // backtrack — undo placement
                b[row][col] = '.';
                rowMap[row][i] = false;
                colMap[col][i] = false;
                box[((row / 3) * 3) + (col / 3)][i] = false;
            }
        }

        return false; // no digit worked here — trigger backtrack in caller
    }

    /**
     * Entry point. Pre-processes the board to populate constraint maps, then
     * solves.
     * Modifies the board in-place.
     *
     * @param board 9×9 sudoku board with '.' for empty cells
     */
    public void solveSudoku(char[][] board) {
        rowMap = new boolean[10][10];
        colMap = new boolean[10][10];
        box = new boolean[10][10];

        // register all pre-filled clues into the constraint maps
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (board[i][j] != '.') {
                    int val = board[i][j] - '0';
                    rowMap[i][val] = true;
                    colMap[j][val] = true;
                    box[((i / 3) * 3) + (j / 3)][val] = true;
                }
            }
        }

        solve(board, 0);
    }
}
