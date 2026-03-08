package binarysearch.on2DMatrix;

/*
 * PROBLEM: Row With Maximum 1s (GFG)
 * =====================================================
 * Given a boolean 2D matrix of n × m dimensions where EACH ROW IS SORTED,
 * find the 0-based index of the first row that has the maximum number of 1s.
 *
 * EXAMPLE:
 *   mat[][] = [[0, 1, 1, 1],
 *              [0, 0, 1, 1],
 *              [1, 1, 1, 1],   ← 4 ones → answer
 *              [0, 0, 0, 0]]
 *   Output: 2
 *
 * KEY PROPERTY WE EXPLOIT:
 *   Each row is sorted → all 0s come before all 1s.
 *   This means the number of 1s in a row = (total columns) - (first index of 1).
 *
 * ─────────────────────────────────────────────────────
 * APPROACH 1 — BETTER: Binary Search  →  O(N log M)
 * ─────────────────────────────────────────────────────
 *   For each row, binary search for the FIRST occurrence of 1.
 *   Since the row is sorted [0...0, 1...1], the first 1 gives us:
 *       onesCount = totalCols - firstIndexOf1
 *   We track the row with the highest onesCount seen so far.
 *
 *   TIME : O(N log M) — binary search per row
 *   SPACE: O(1)
 *
 * ─────────────────────────────────────────────────────
 * APPROACH 2 — OPTIMAL: Staircase Search  →  O(N + M)
 * ─────────────────────────────────────────────────────
 *   Start at the TOP-RIGHT corner (row=0, col=m-1).
 *
 *   The idea: we use the sorted property across rows AND columns.
 *     → If mat[row][col] == 0 → this row can't beat current best, move DOWN.
 *     → If mat[row][col] == 1 → this row is a candidate, move LEFT to count more 1s.
 *
 *   Visualised as a staircase:
 *
 *     col →  0  1  2  3
 *     row 0 [0, 1, 1, 1]   ← find 1 at col=3, move left
 *     row 1 [0, 0, 1, 1]   ← col=2 is now the threshold, find 1, move left
 *     row 2 [1, 1, 1, 1]   ← col=1, find 1, move left
 *     row 3 [0, 0, 0, 0]   ← col=0, no 1 found, drop down → done
 *
 *   We never move right or up → at most N + M steps total.
 *   Each leftward move raises the bar: the next row must have a 1 at
 *   an even earlier column to be "better", so no valid candidate is missed.
 *
 *   TIME : O(N + M) — at most N down-moves and M left-moves
 *   SPACE: O(1)
 */
public class GFG_RowWithMaxOne {

    /**
     * Binary Search helper: finds the index of the FIRST occurrence of 1
     * in a sorted binary array [0, 0, ..., 1, 1, ...].
     *
     * Standard "find leftmost target" binary search pattern:
     * → If mid == 1, record it and keep searching LEFT for an earlier 1.
     * → If mid == 0, the first 1 must be to the right.
     *
     * @param binary a sorted row of 0s and 1s
     * @return index of the first 1, or -1 if no 1 exists in the row
     */
    public int firstOccurrenceOfOne(int[] binary) {
        int low = 0;
        int high = binary.length - 1;

        if (high < 0)
            return -1;

        int firstIndex = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            if (binary[mid] == 1) {
                firstIndex = mid; // valid candidate, but look further left
                high = mid - 1;
            } else {
                low = mid + 1; // first 1 must be to the right
            }
        }
        return firstIndex;
    }

    /**
     * Finds the 0-based index of the first row with the maximum number of 1s.
     * Two approaches are included below — the binary search approach is commented
     * out; the active staircase approach runs in optimal O(N + M) time.
     */
    public int rowWithMax1s(int mat[][]) {

        /*
         * ─── APPROACH 1: BETTER — Binary Search O(N log M) ───────────────────────
         *
         * Strategy:
         * For every row, find the first index of 1 using binary search.
         * Number of 1s in that row = cols - firstIndex.
         * Track the row with the highest count seen so far.
         *
         * Why it works:
         * Rows are sorted → binary search is valid per row.
         * We never need to scan the whole row; the first 1 tells us everything.
         *
         * Why it's not optimal:
         * We treat each row independently. We don't reuse the insight that
         * finding a better row narrows the search for subsequent rows.
         *
         *
         * int rows = mat.length;
         * if (rows <= 0) return -1;
         *
         * int cols = mat[0].length;
         *
         * int firstValidRow = -1; // index of the best row found so far
         * int maxOnes = 0; // number of 1s in that best row
         *
         * for (int i = 0; i < rows; i++) {
         * int startIndex = firstOccurrenceOfOne(mat[i]);
         *
         * // row has no 1s at all, skip it
         * if (startIndex == -1) continue;
         *
         * // number of 1s = everything from startIndex to end of row
         * int onesInCurrent = cols - startIndex;
         *
         * // update best only if strictly greater (preserves first occurrence)
         * if (onesInCurrent > maxOnes) {
         * maxOnes = onesInCurrent;
         * firstValidRow = i;
         * }
         * }
         * return firstValidRow;
         *
         * ───────────────────────────────────────────────────────────────────────────
         */

        // ─── APPROACH 2: OPTIMAL — Staircase Search O(N + M) ─────────────────────
        //
        // Start at the top-right corner and move in an L-shaped staircase:
        // → cell is 0: move DOWN (this row can't beat current best)
        // → cell is 1: record row, move LEFT (push the bar further left)
        //
        // The column pointer never moves right, so each step either advances the
        // row or tightens the column bound — at most N + M total moves.

        int n = mat.length;
        if (n <= 0)
            return -1;

        int index = -1; // best row index found so far
        int row = 0;
        int col = mat[0].length - 1; // start at top-right corner

        while (row < n && col >= 0) {

            // skip down through rows that have 0 at the current column threshold
            // (they can't possibly have more 1s than our current best)
            while (row < n && mat[row][col] != 1) {
                row++;
            }

            // exhausted all rows without finding a better candidate
            if (row == n)
                return index;

            // mat[row][col] == 1 → this row beats or ties the current best
            index = row;

            // move left within this row to raise the bar:
            // the next qualifying row must have a 1 even further left
            while (col >= 0 && mat[row][col] == 1) {
                col--;
            }

            // move to the next row; current row is fully evaluated
            row++;
        }

        return index;
    }
}