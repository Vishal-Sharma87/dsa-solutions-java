package binarysearch.on2DMatrix;

/**
 * LC1901PeakElement2 - Solution for LeetCode 1901: Find a Peak Element II
 *
 * Created at: - 10 march 2026
 * Last revised at: - 10 march 2026
 * Link: https://leetcode.com/problems/find-a-peak-element-ii/
 *
 * ---------------------------------------------------------------------------
 * Problem Description:
 * Given an m x n matrix `mat` where no two adjacent cells are equal, find
 * ANY peak element and return its position [row, col].
 *
 * A peak element is one that is strictly greater than all of its
 * 4-directional neighbors (up, down, left, right). Corner/edge cells only
 * need to exceed their existing neighbors.
 *
 * Example:
 * Input : mat = [[1,4],[3,2]]
 * Output: [0,1] → mat[0][1] = 4 > 1 (left) and 4 > 2 (below)
 *
 * Constraints:
 * - m == mat.length, n == mat[i].length
 * - 1 ≤ m, n ≤ 500
 * - 1 ≤ mat[i][j] ≤ 10^5
 * - No two adjacent cells are equal
 * ---------------------------------------------------------------------------
 *
 * Approach: Binary Search on Columns
 * 1. Binary-search over columns (mid column each iteration).
 * 2. In the mid column, find the row that holds the maximum element.
 * 3. Compare that maximum against its left and right neighbors:
 * - If it is greater than both → peak found, return [row, mid].
 * - If the left neighbor is larger → a peak must exist to the left,
 * so move the search window left (high = mid - 1).
 * - Otherwise → move the search window right
 * (low = mid + 1).
 * The guarantee that a peak always exists ensures the loop terminates.
 *
 * Time Complexity: O(m * log n) – log n column iterations, O(m) scan each
 * Space Complexity: O(1) – only a constant amount of extra variables
 */
public class LC1901PeakElement2 {

    /**
     * Finds the row index of the maximum element in a given column.
     *
     * <p>
     * Performs a linear scan of all rows at the specified column and
     * returns the row index whose value is largest. Ties are broken in
     * favour of the first (topmost) occurrence.
     * </p>
     *
     * @param mat the 2-D matrix to search
     * @param col the column index to scan
     * @return the row index of the maximum element in {@code col},
     *         or {@code -1} if the matrix is empty or {@code col} is
     *         out of bounds
     */
    public int findRowOfMaxElement(int[][] mat, int col) {
        int rows = mat.length;

        // Guard: empty matrix or column index out of range
        if (rows <= 0 || mat[0].length <= col)
            return -1;

        int maxi = mat[0][col]; // current maximum value
        int index = 0; // row index of the current maximum

        for (int i = 1; i < rows; i++) {
            if (mat[i][col] > maxi) {
                maxi = mat[i][col];
                index = i;
            }
        }
        return index;
    }

    /**
     * Finds a peak element in the matrix and returns its [row, col] position.
     *
     * <p>
     * Uses binary search on columns combined with a per-column maximum
     * scan. At each binary-search step the algorithm narrows the search
     * window based on whether a larger neighbor exists to the left or right,
     * guaranteeing convergence to a valid peak.
     * </p>
     *
     * <p>
     * The returned peak satisfies:
     * 
     * <pre>
     *   mat[r][c] > mat[r][c-1]  (if c > 0)
     *   mat[r][c] > mat[r][c+1]  (if c < n-1)
     *   mat[r][c] > mat[r-1][c]  (if r > 0)      ← ensured by column-max step
     *   mat[r][c] > mat[r+1][c]  (if r < m-1)    ← ensured by column-max step
     * </pre>
     * </p>
     *
     * @param mat the 2-D matrix (must satisfy the problem constraints above)
     * @return a two-element array {@code [row, col]} identifying a peak
     *         element, or {@code [-1, -1]} if the matrix is empty
     */
    public int[] findPeakGrid(int[][] mat) {
        int rows = mat.length;
        int[] indices = new int[] { -1, -1 }; // default "not found" sentinel

        // Guard: empty matrix
        if (rows <= 0)
            return indices;

        int cols = mat[0].length;
        int low = 0;
        int high = cols - 1;

        // Binary search over the column space
        while (low <= high) {
            int mid = low + (high - low) / 2; // avoids integer overflow

            // Step 1: find the row with the largest value in column 'mid'
            int mRI = findRowOfMaxElement(mat, mid);
            int X = mat[mRI][mid]; // the candidate peak value

            // Step 2: fetch left and right neighbors (-1 if on an edge)
            int left = (mid - 1 >= 0) ? mat[mRI][mid - 1] : -1;
            int right = (mid + 1 < cols) ? mat[mRI][mid + 1] : -1;

            // Step 3: peak check – X already dominates its column, so we
            // only need to verify the horizontal neighbors
            if (left < X && X > right)
                return new int[] { mRI, mid }; // peak confirmed

            // Step 4: move toward the side with the larger neighbor,
            // because a peak is guaranteed to exist in that direction
            if (left > X) {
                high = mid - 1; // descend into the left half
            } else {
                low = mid + 1; // descend into the right half
            }
        }

        return indices; // unreachable for a valid input, but satisfies compiler
    }
}