package stack.monotonicstack;

import java.util.Stack;

public class LC85MaximalAreaRectangle {
    // Created at: 18-April-2025
    // Last revised at: 18-April-2025
    // https://leetcode.com/problems/maximal-rectangle/

    /*
     * Problem Description:
     * Given a 2D binary matrix filled with '0's and '1's, find the largest
     * rectangle
     * containing only '1's and return its area.
     *
     * Example:
     * Input: matrix = [["1","0","1","0","0"],
     * ["1","0","1","1","1"],
     * ["1","1","1","1","1"],
     * ["1","0","0","1","0"]]
     * Output: 6
     *
     * Constraints:
     * - rows == matrix.length, cols == matrix[i].length
     * - 1 <= rows, cols <= 200
     * - matrix[i][j] is '0' or '1'
     */

    /*
     * Approaches:
     *
     * 1. Brute Force
     * Idea: For every cell (i, j) that is '1', try to expand a rectangle in all
     * directions and track the max area.
     * Time: O((rows * cols)²)
     * Space: O(1)
     * Drawbacks: Way too slow for 200×200 matrices.
     *
     * 2. Row-by-row Histogram + Largest Rectangle in Histogram ★
     * Idea: Treat each row as the base of a histogram. For each cell, if it's '1',
     * its histogram height is prev row's height + 1; if '0', height resets to 0.
     * Run LC84 (Largest Rectangle in Histogram) on each row's height array.
     * Time: O(rows * cols) — one histogram pass per row, each O(cols)
     * Space: O(cols) — only two height arrays kept at a time (prev + curr)
     * Key Insight: Reduces a 2D problem to repeated applications of a known 1D
     * problem.
     */

    // Time Complexity: O(rows * cols)
    // Space Complexity: O(cols)

    /**
     * Precomputes the Next Smaller Element index for each bar in the histogram.
     * Uses {@code len} as a sentinel for bars with no smaller element to the right.
     *
     * @param heights array of histogram bar heights
     * @return array where indices[i] is the index of the next bar shorter than
     *         heights[i]
     */
    private int[] findNseIndices(int[] heights) {
        int len = heights.length;
        int[] indices = new int[len];
        Stack<Integer> st = new Stack<>();

        st.push(len); // sentinel

        for (int i = len - 1; i >= 0; i--) {
            // pop bars >= current, they can't be the NSE
            while (st.size() > 1 && heights[st.peek()] >= heights[i])
                st.pop();

            indices[i] = st.peek();
            st.push(i);
        }
        return indices;
    }

    /**
     * Returns the area of the largest rectangle in a histogram.
     * For each bar, the rectangle width spans between its PSE and NSE.
     *
     * @param heights array of histogram bar heights
     * @return maximum rectangular area
     */
    private int largestRectangleArea(int[] heights) {
        int len = heights.length;

        if (len == 0)
            return 0;
        if (len == 1)
            return heights[0]; // single bar

        int[] nseIndices = findNseIndices(heights);
        int[] pseIndices = new int[len];

        Stack<Integer> st = new Stack<>();
        st.push(-1); // sentinel

        int maximalArea = heights[0];

        for (int i = 0; i < len; i++) {
            // pop bars >= current to find PSE
            while (st.size() > 1 && heights[st.peek()] >= heights[i])
                st.pop();

            pseIndices[i] = st.peek();
            st.push(i);

            int width = nseIndices[i] - pseIndices[i] - 1;
            maximalArea = Math.max(maximalArea, heights[i] * width);
        }

        return maximalArea;
    }

    /**
     * Finds the largest rectangle of '1's in a binary matrix by reducing
     * each row to a histogram and applying largestRectangleArea row by row.
     *
     * @param matrix 2D binary character matrix of '0's and '1's
     * @return area of the largest all-'1' rectangle
     */
    public int maximalRectangle(char[][] matrix) {
        int rows = matrix.length;
        if (rows == 0)
            return 0;

        int col = matrix[0].length;

        int[] prev = new int[col];
        int[] curr = new int[col];

        // build histogram for first row
        for (int i = 0; i < col; i++)
            prev[i] = matrix[0][i] == '0' ? 0 : 1;

        int matrixMaximalArea = largestRectangleArea(prev);

        for (int i = 1; i < rows; i++) {
            for (int j = 0; j < col; j++) {
                // '0' breaks the column streak; '1' stacks on top of previous height
                curr[j] = matrix[i][j] == '0' ? 0 : prev[j] + 1;
            }

            int area = largestRectangleArea(curr);
            matrixMaximalArea = Math.max(matrixMaximalArea, area);

            prev = curr;
            curr = new int[col];
        }

        return matrixMaximalArea;
    }
}
