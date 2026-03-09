package binarysearch.on2DMatrix;

/**
 * GFG: Median in a Row-Wise Sorted Matrix
 *
 * Problem:
 * Given a row-wise sorted matrix of odd size (r × c where r*c is odd),
 * find the median element.
 *
 * Constraints:
 * - Each row is sorted in non-decreasing order
 * - r * c is always odd (guarantees a unique median)
 * - 1 ≤ r, c ≤ 450
 * - 1 ≤ mat[i][j] ≤ 2000
 *
 * ─────────────────────────────────────────────────────────────────
 * APPROACH COMPARISON
 * ─────────────────────────────────────────────────────────────────
 * | Approach | Time | Space | Recommended |
 * |-----------------------|-------------------|-------|-------------|
 * | Brute Force (flatten) | O(r*c * log(r*c)) | O(r*c)| ❌ |
 * | K-Way Merge | O(r² * c) | O(r) | ❌ |
 * | Binary Search | O(r*log(c)*log(V))| O(1) | ✅ |
 * ─────────────────────────────────────────────────────────────────
 * where V = max_value - min_value (~2000 for given constraints)
 */
public class GFG_MatrixMedian {

    // ═══════════════════════════════════════════════════════════════
    // APPROACH 1 — BRUTE FORCE (Flatten + Sort)
    // ═══════════════════════════════════════════════════════════════
    //
    // Idea:
    // Copy all elements into a 1D array, sort it, return the middle element.
    //
    // Time: O(r*c * log(r*c)) — dominated by sort
    // Space: O(r*c) — extra array
    //
    // Drawbacks:
    // - Ignores the row-sorted property entirely
    // - High memory usage for large matrices
    //
    // ───────────────────────────────────────────────────────────────
    public int medianBruteForce(int[][] mat) {
        int rows = mat.length, cols = mat[0].length;
        int[] flat = new int[rows * cols];
        int idx = 0;

        for (int[] row : mat)
            for (int val : row)
                flat[idx++] = val;

        java.util.Arrays.sort(flat);
        return flat[(rows * cols) / 2]; // middle of sorted array = median
    }

    // ═══════════════════════════════════════════════════════════════
    // APPROACH 2 — K-WAY MERGE (Row Pointers)
    // ═══════════════════════════════════════════════════════════════
    //
    // Idea:
    // Treat each row as a sorted stream. Maintain one pointer per row.
    // Repeatedly pick the minimum across all active row heads until
    // we have advanced (r*c + 1)/2 steps — that element is the median.
    //
    // Time: O(r² * c) — for each of (r*c)/2 iterations, scan r pointers
    // Space: O(r) — pointer array
    //
    // Drawbacks:
    // - Redundant comparisons on tied minimum values each iteration
    // - No early-exit when minIndex == -1 (all rows exhausted)
    // - Much slower than binary search for large inputs
    //
    // ───────────────────────────────────────────────────────────────
    public int medianKWayMerge(int[][] mat) {
        int rows = mat.length;
        if (rows <= 0)
            return -1;
        int cols = mat[0].length;

        int[] pointers = new int[rows];
        int medianIndex = ((rows * cols) + 1) / 2;

        for (int i = 1; i <= medianIndex; i++) {
            int mini = Integer.MAX_VALUE, minRow = -1;

            for (int p = 0; p < rows; p++) {
                if (pointers[p] >= cols)
                    continue;
                if (mat[p][pointers[p]] < mini) {
                    mini = mat[p][pointers[p]];
                    minRow = p;
                }
            }

            if (minRow == -1)
                return -1; // all rows exhausted (shouldn't happen)
            if (i == medianIndex)
                return mat[minRow][pointers[minRow]];
            pointers[minRow]++;
        }
        return -1;
    }

    // ═══════════════════════════════════════════════════════════════
    // APPROACH 3 — BINARY SEARCH ON VALUE ✅ OPTIMAL
    // ═══════════════════════════════════════════════════════════════
    //
    // Key Insight:
    // Instead of extracting elements one-by-one, binary search on the
    // ANSWER VALUE in range [globalMin, globalMax].
    //
    // For a candidate value `mid`, count how many matrix elements are ≤ mid.
    // - Each row is sorted → use upperBound (binary search) per row → O(log c)
    // - Total count check: O(r * log c)
    //
    // The median is the smallest value x such that
    // at least (r*c + 1)/2 elements are ≤ x.
    //
    // Time: O(r * log(c) * log(V)) where V = globalMax - globalMin
    // Space: O(1)
    //
    // Why it works:
    // - Binary search converges to the exact median value (not an index)
    // - The median must exist in the matrix, so `lo` will land on a real element
    //
    // ───────────────────────────────────────────────────────────────
    public int medianBinarySearch(int[][] mat) {
        int rows = mat.length, cols = mat[0].length;
        int lo = Integer.MAX_VALUE, hi = Integer.MIN_VALUE;

        // Step 1: Find global min (first col) and global max (last col)
        for (int[] row : mat) {
            lo = Math.min(lo, row[0]);
            hi = Math.max(hi, row[cols - 1]);
        }

        int desired = (rows * cols + 1) / 2;

        // Step 2: Binary search on value
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            // Step 3: Count elements <= mid across all rows
            int count = 0;
            for (int[] row : mat) {
                count += upperBound(row, mid); // # of elements <= mid in this row
            }

            // Step 4: Narrow search range
            if (count < desired)
                lo = mid + 1; // need more elements → go higher
            else
                hi = mid; // mid might be the answer → don't skip it
        }

        return lo; // lo == hi == smallest value with count >= desired
    }

    /**
     * Returns the number of elements in a sorted array that are ≤ target.
     * Equivalent to: first index where arr[i] > target (i.e., upper bound).
     *
     * @param arr    sorted row of the matrix
     * @param target value to compare against
     * @return count of elements ≤ target
     */
    private int upperBound(int[] arr, int target) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] <= target)
                left = mid + 1;
            else
                right = mid;
        }
        return left;
    }

    // ═══════════════════════════════════════════════════════════════
    // DRIVER — Verify all three approaches give the same result
    // ═══════════════════════════════════════════════════════════════
    public static void main(String[] args) {
        GFG_MatrixMedian sol = new GFG_MatrixMedian();

        int[][] mat1 = {
                { 1, 3, 5 },
                { 2, 6, 9 },
                { 3, 6, 9 }
        };

        int[][] mat2 = {
                { 1 },
                { 2 },
                { 3 }
        };

        int[][] mat3 = {
                { 1, 3, 5, 7, 9 },
                { 2, 4, 6, 8, 10 },
                { 11, 13, 15, 17, 19 }
        };

        int[][][] tests = { mat1, mat2, mat3 };
        int[] expected = { 6, 2, 9 };

        for (int t = 0; t < tests.length; t++) {
            int bf = sol.medianBruteForce(tests[t]);
            int kwm = sol.medianKWayMerge(tests[t]);
            int bs = sol.medianBinarySearch(tests[t]);

            System.out.printf(
                    "Test %d | BruteForce: %d | KWayMerge: %d | BinarySearch: %d | Expected: %d | %s%n",
                    t + 1, bf, kwm, bs, expected[t],
                    (bf == expected[t] && kwm == expected[t] && bs == expected[t]) ? "✅ PASS" : "❌ FAIL");
        }
    }
}