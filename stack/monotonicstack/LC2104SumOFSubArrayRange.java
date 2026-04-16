package stack.monotonicstack;

import java.util.Stack;

public class LC2104SumOFSubArrayRange {
    // LC 2104 · Sum of Subarray Ranges
    // https://leetcode.com/problems/sum-of-subarray-ranges/
    //
    // Created at : 17-April-2026
    // Last revised : 17-April-2026

    /*
     * Problem Description:
     *
     * Given an integer array `nums`, the range of a subarray is defined as
     * max(subarray) - min(subarray). Return the sum of ranges of all subarrays.
     *
     * Example:
     * Input : [1, 2, 3]
     * Output: 4
     * Explanation: subarrays → [1]=0, [2]=0, [3]=0, [1,2]=1, [2,3]=1, [1,2,3]=2 →
     * sum=4
     *
     * Constraints:
     * - 1 <= nums.length <= 1000
     * - -10^9 <= nums[i] <= 10^9
     */

    /*
     * Approach: Contribution Technique with Monotonic Stacks ★
     *
     * Idea : sum(ranges) = sum(subarray maxima) − sum(subarray minima).
     *
     * For each index i, count subarrays where nums[i] is the maximum
     * (or minimum). If nums[i] is dominant over X elements to its left
     * and Y elements to its right, it contributes nums[i] * X * Y to
     * the total.
     *
     * To avoid double-counting duplicates, boundaries are made
     * asymmetric:
     * - Max: left boundary uses "previous greater OR EQUAL" (PGEE),
     * right boundary uses "next strictly greater" (NGE)
     * - Min: left boundary uses "previous smaller OR EQUAL" (PSEE),
     * right boundary uses "next strictly smaller" (NSE)
     *
     * Time : O(n) — each element is pushed/popped at most once per helper
     * Space : O(n) — four boundary arrays + four stacks
     * Drawbacks : Four separate monotonic stack passes; more code than the O(n²)
     * brute force, though asymptotically better
     */

    /**
     * Finds the index of the Next Greater Element for each position.
     * Uses sentinel value `len` for elements with no NGE (right boundary).
     *
     * @param nums input array
     * @return nge[i] = smallest index j > i such that nums[j] > nums[i], or len if
     *         none
     */
    private int[] findNge(int[] nums) {
        int len = nums.length;
        int[] nge = new int[len];
        Stack<Integer> st = new Stack<>();
        st.push(len); // sentinel: no valid NGE found → len

        for (int i = len - 1; i >= 0; i--) {
            // pop indices whose values are <= nums[i]; they can't be NGE for i
            while (st.size() > 1 && nums[st.peek()] <= nums[i])
                st.pop();
            nge[i] = st.peek();
            st.push(i);
        }
        return nge;
    }

    /**
     * Finds the index of the Previous Greater or Equal Element for each position.
     * Uses sentinel value `-1` for elements with no PGEE (left boundary).
     *
     * @param nums input array
     * @return pgee[i] = largest index j < i such that nums[j] >= nums[i], or -1 if
     *         none
     */
    private int[] findPgee(int[] nums) {
        int len = nums.length;
        int[] pgee = new int[len];
        Stack<Integer> st = new Stack<>();
        st.push(-1); // sentinel: no valid PGEE found → -1

        for (int i = 0; i < len; i++) {
            // pop indices whose values are strictly less than nums[i]
            while (st.size() > 1 && nums[st.peek()] < nums[i])
                st.pop();
            pgee[i] = st.peek();
            st.push(i);
        }
        return pgee;
    }

    /**
     * Computes the sum of maximums across all subarrays using the contribution
     * technique.
     * Each nums[i] contributes nums[i] * X * Y, where X and Y are the counts of
     * subarrays on each side for which nums[i] is the maximum.
     *
     * @param nums input array
     * @return sum of maximum elements over all subarrays
     */
    private long subArraySumMaximum(int[] nums) {
        int len = nums.length;
        int[] nge = findNge(nums);
        int[] pgee = findPgee(nums);

        long maximumSum = 0;

        for (int i = 0; i < len; i++) {
            int X = i - pgee[i]; // elements to the left where nums[i] dominates
            int Y = nge[i] - i; // elements to the right where nums[i] dominates
            maximumSum += (long) nums[i] * (X * Y);
        }
        return maximumSum;
    }

    /**
     * Finds the index of the Next Smaller Element for each position.
     * Uses sentinel value `len` for elements with no NSE (right boundary).
     *
     * @param nums input array
     * @return nse[i] = smallest index j > i such that nums[j] < nums[i], or len if
     *         none
     */
    private int[] findNse(int[] nums) {
        int len = nums.length;
        int[] nse = new int[len];
        Stack<Integer> st = new Stack<>();
        st.push(len); // sentinel

        for (int i = len - 1; i >= 0; i--) {
            // pop indices whose values are >= nums[i]; they can't be NSE for i
            while (st.size() > 1 && nums[st.peek()] >= nums[i])
                st.pop();
            nse[i] = st.peek();
            st.push(i);
        }
        return nse;
    }

    /**
     * Finds the index of the Previous Smaller or Equal Element for each position.
     * Uses sentinel value `-1` for elements with no PSEE (left boundary).
     *
     * @param nums input array
     * @return psee[i] = largest index j < i such that nums[j] <= nums[i], or -1 if
     *         none
     */
    private int[] findPsee(int[] nums) {
        int len = nums.length;
        int[] psee = new int[len];
        Stack<Integer> st = new Stack<>();
        st.push(-1); // sentinel

        for (int i = 0; i < len; i++) {
            // pop indices whose values are strictly greater than nums[i]
            while (st.size() > 1 && nums[st.peek()] > nums[i])
                st.pop();
            psee[i] = st.peek();
            st.push(i);
        }
        return psee;
    }

    /**
     * Computes the sum of minimums across all subarrays using the contribution
     * technique.
     *
     * @param nums input array
     * @return sum of minimum elements over all subarrays
     */
    private long subArraySumMinimum(int[] nums) {
        int len = nums.length;
        int[] nse = findNse(nums);
        int[] psee = findPsee(nums);

        long minimumSum = 0;

        for (int i = 0; i < len; i++) {
            int X = i - psee[i]; // elements to the left where nums[i] is the minimum
            int Y = nse[i] - i; // elements to the right where nums[i] is the minimum
            minimumSum += (long) nums[i] * (X * Y);
        }
        return minimumSum;
    }

    /**
     * Returns the sum of ranges (max - min) across all subarrays of nums.
     *
     * @param nums input array of integers
     * @return sum of (max - min) for every subarray
     */
    public long subArrayRanges(int[] nums) {
        return subArraySumMaximum(nums) - subArraySumMinimum(nums);
    }
}

// Time Complexity : O(n)
// Space Complexity : O(n)
