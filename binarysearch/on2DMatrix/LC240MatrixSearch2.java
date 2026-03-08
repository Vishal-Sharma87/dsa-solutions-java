package binarysearch.on2DMatrix;

public class LC240MatrixSearch2 {
    // Created at:
    // Last revised at:
    // link: https://leetcode.com/problems/search-a-2d-matrix-ii/

    /*
     * ─────────────────────────────────────────────────────────────────
     * PROBLEM STATEMENT
     * ─────────────────────────────────────────────────────────────────
     * 240. Search a 2D Matrix II (Medium)
     *
     * Write an efficient algorithm that searches for a value target in an
     * m x n integer matrix with the following properties:
     * - Integers in each row are sorted in ascending order (left → right).
     * - Integers in each column are sorted in ascending order (top → bottom).
     *
     * Example 1:
     * Input : matrix = [[1,4,7,11,15],
     * [2,5,8,12,19],
     * [3,6,9,16,22],
     * [10,13,14,17,24],
     * [18,21,23,26,30]], target = 5
     * Output: true
     *
     * Example 2:
     * Input : same matrix, target = 20
     * Output: false
     *
     * Constraints:
     * m == matrix.length, n == matrix[i].length
     * 1 <= m, n <= 300
     * -10^9 <= matrix[i][j], target <= 10^9
     * All rows and columns are strictly sorted ascending.
     * ─────────────────────────────────────────────────────────────────
     *
     * APPROACHES SUMMARY
     * ──────────────────────────────────────────────────────────────────
     * # Name Time Space Notes
     * 1 Brute Force O(m × n) O(1) Check every cell
     * 2 Binary Search per Row O(m log n) O(1) Skip rows, BS on rest ← your solution
     * 3 Staircase Search O(m + n) O(1) Optimal — start top-right
     * ─────────────────────────────────────────────────────────────────
     */

    // ═══════════════════════════════════════════════════════════════════
    // APPROACH 1 — BRUTE FORCE
    // ═══════════════════════════════════════════════════════════════════
    /*
     * Core Logic:
     * Ignore all sorting properties.
     * Scan every cell row by row, column by column.
     * Return true the moment target is found.
     *
     * Why it works (trivially):
     * Every cell is visited, so target cannot be missed.
     *
     * Time Complexity: O(m × n) — every cell is visited in the worst case
     * Space Complexity: O(1) — no extra data structures
     */
    public boolean searchMatrixBrute(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] == target)
                    return true;
            }
        }
        return false;
    }

    // ═══════════════════════════════════════════════════════════════════
    // APPROACH 2 — BINARY SEARCH PER ROW (your solution, kept as-is)
    // ═══════════════════════════════════════════════════════════════════
    /*
     * Core Logic:
     * Use the row's first and last element as a range gate.
     * If target falls within [row[0], row[cols-1]], it *could* live in
     * this row → run binary search on just that row.
     * Otherwise skip the row entirely.
     *
     * Why it works:
     * Each row is sorted, so binary search is valid within a row.
     * The range gate skips rows where target is provably absent,
     * cutting down the number of binary searches needed in practice.
     * Note: the gate doesn't skip rows in the worst case (e.g. all rows
     * span the same range), so worst-case is still O(m log n).
     *
     * Note on comment typo (lines marked ✱):
     * "val > target → search in right" should read "search in left" ✱
     * "val < target → search in left" should read "search in right" ✱
     * The actual high/low updates are correct; only the comments are swapped.
     *
     * Time Complexity: O(m log n) — up to m rows, each with O(log n) binary search
     * Space Complexity: O(1)
     */
    public boolean searchMatrix(int[][] matrix, int target) {
        int rows = matrix.length;
        if (rows <= 0)
            return false;
        int cols = matrix[0].length;

        for (int i = 0; i < rows; i++) {
            int X = matrix[i][0]; // smallest value in this row
            int Y = matrix[i][cols - 1]; // largest value in this row

            // target can only exist in this row if it lies within [X, Y]
            if (X <= target && target <= Y) {
                int low = 0, high = cols - 1;

                while (low <= high) {
                    int mid = low + (high - low) / 2;
                    int val = matrix[i][mid];

                    if (val == target)
                        return true;

                    if (val > target) {
                        // target is smaller than current cell → move left
                        high = mid - 1;
                    } else {
                        // target is larger than current cell → move right
                        low = mid + 1;
                    }
                }
            }
        }
        return false;
    }

    // ═══════════════════════════════════════════════════════════════════
    // APPROACH 3 — STAIRCASE SEARCH (optimal)
    // ═══════════════════════════════════════════════════════════════════
    /*
     * Core Logic:
     * Start at the TOP-RIGHT corner — a uniquely decisive position:
     * • Moving LEFT decreases the value (row is sorted ascending).
     * • Moving DOWN increases the value (column is sorted ascending).
     * At every step exactly one dimension is eliminated:
     * - cell == target → found, return true.
     * - cell > target → entire column below is also > target (col sorted),
     * so move LEFT (col--).
     * - cell < target → entire row to the left is also < target (row sorted),
     * so move DOWN (row++).
     * If the pointer walks off the matrix, target is absent.
     *
     * Why O(m + n):
     * Each step either decrements col or increments row.
     * col can decrease at most n times, row can increase at most m times
     * → total steps ≤ m + n.
     *
     * Time Complexity: O(m + n) — optimal for this problem
     * Space Complexity: O(1)
     */
    public boolean searchMatrixOptimal(int[][] matrix, int target) {
        int rows = matrix.length;
        int cols = matrix[0].length;

        int row = 0;
        int col = cols - 1; // start at top-right corner

        while (row < rows && col >= 0) {
            int cell = matrix[row][col];

            if (cell == target) {
                return true; // found
            } else if (cell > target) {
                col--; // too big → eliminate this column, move left
            } else {
                row++; // too small → eliminate this row, move down
            }
        }
        return false; // walked off the matrix without finding target
    }
}