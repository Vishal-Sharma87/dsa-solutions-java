package stack.monotonicstack;

import java.util.Stack;

public class LC84LargestReactangleAreaHistogram {
    // Created at: 18-April-2025
    // Last revised at: 18-April-2025
    // https://leetcode.com/problems/largest-rectangle-in-histogram/

    /*
     * Problem Description:
     * Given an array of integers `heights` representing the histogram's bar heights
     * where the width of each bar is 1, return the area of the largest rectangle
     * that can be formed in the histogram.
     *
     * Example:
     * Input: heights = [2, 1, 5, 6, 2, 3]
     * Output: 10
     *
     * Constraints:
     * - 1 <= heights.length <= 10^5
     * - 0 <= heights[i] <= 10^4
     */

    /*
     * Approaches:
     *
     * 1. Brute Force
     * Idea: For every pair (i, j), compute the minimum height in that range and
     * multiply by (j - i + 1). Track the global max.
     * Time: O(n²) or O(n³)
     * Space: O(1)
     * Drawbacks: Too slow for n = 10^5.
     *
     * 2. PSE + NSE with Monotonic Stack ★
     * Idea: For each bar i, the largest rectangle with heights[i] as the
     * limiting height spans from its Previous Smaller Element (PSE) to its
     * Next Smaller Element (NSE). Precompute NSE indices in one pass,
     * then compute PSE on-the-fly in a second pass.
     * Time: O(n)
     * Space: O(n)
     * Key Insight: A sentinel value (len for NSE, -1 for PSE) avoids boundary
     * checks and handles bars with no smaller neighbour cleanly.
     */

    // Time Complexity: O(n) — two linear passes, each element pushed/popped once
    // Space Complexity: O(n) — two index arrays + two stacks

    /**
     * Precomputes the Next Smaller Element index for each bar.
     * Uses {@code len} as a sentinel meaning "no smaller element to the right".
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
            // pop bars that are >= current, they can't be the NSE
            while (st.size() > 1 && heights[st.peek()] >= heights[i])
                st.pop();

            indices[i] = st.peek();
            st.push(i);
        }
        return indices;
    }

    /**
     * Returns the area of the largest rectangle that can be formed in the
     * histogram.
     *
     * @param heights array of histogram bar heights
     * @return maximum rectangular area
     */
    public int largestRectangleArea(int[] heights) {
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

            // width = gap between PSE and NSE, excluding both boundaries
            int width = nseIndices[i] - pseIndices[i] - 1;
            maximalArea = Math.max(maximalArea, heights[i] * width);
        }

        return maximalArea;
    }
}
