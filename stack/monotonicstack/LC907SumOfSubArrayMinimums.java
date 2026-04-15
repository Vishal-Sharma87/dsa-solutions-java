package stack.monotonicstack;

import java.util.Stack;

public class LC907SumOfSubArrayMinimums {
    /**
     * Finds the index of the next smaller element for each position.
     * For index i, returns the index j where j > i and nums[j] < nums[i],
     * choosing the closest such j. If no such j exists, returns array length.
     * 
     * Time: O(n), Space: O(n)
     */
    private int[] nseIndices(int[] nums) {
        Stack<Integer> st = new Stack<>();
        int[] nse = new int[nums.length];

        // Start with array length as sentinel (no element found case)
        st.push(nums.length);

        // Traverse from right to left
        for (int i = nums.length - 1; i >= 0; i--) {
            // Pop elements >= current (they won't be NSE for any future element)
            while (st.size() > 1 && nums[st.peek()] >= nums[i]) {
                st.pop();
            }

            // Top of stack is NSE for nums[i]
            nse[i] = st.peek();
            st.push(i);
        }
        return nse;
    }

    /**
     * Finds the index of the previous smaller element for each position.
     * For index i, returns the index j where j < i and nums[j] < nums[i],
     * choosing the closest such j. If no such j exists, returns -1.
     * 
     * Time: O(n), Space: O(n)
     */
    private int[] pseIndices(int[] nums) {
        Stack<Integer> st = new Stack<>();
        int[] pse = new int[nums.length];

        // Start with -1 as sentinel (no element found case)
        st.push(-1);

        // Traverse from left to right
        for (int i = 0; i < nums.length; i++) {
            // Pop elements > current (strictly greater)
            while (st.size() > 1 && nums[st.peek()] > nums[i]) {
                st.pop();
            }

            // Top of stack is PSE for nums[i]
            pse[i] = st.peek();
            st.push(i);
        }
        return pse;
    }

    /**
     * Calculates the sum of minimum elements across all subarrays.
     * 
     * Approach: For each element, count how many subarrays have it as minimum,
     * then multiply by the element value.
     * 
     * Time: O(n), Space: O(n)
     */
    public int sumSubarrayMins(int[] nums) {
        int len = nums.length;
        final int MOD = 1000000007;

        // Get boundaries for each element
        int[] nse = nseIndices(nums); // next smaller element index
        int[] pse = pseIndices(nums); // previous smaller element index

        long ans = 0;

        // For each element, calculate its contribution
        for (int i = 0; i < len; i++) {
            // Distance to PSE (how many elements on left where nums[i] is minimum)
            // pse[i] is excluded, so count = i - pse[i]
            int X = i - pse[i];

            // Distance to NSE (how many elements on right where nums[i] is minimum)
            // nse[i] is excluded, so count = nse[i] - i
            int Y = nse[i] - i;

            // Total subarrays where nums[i] is minimum = X * Y
            long combinations = (long) X * Y % MOD;

            // Contribution of nums[i] = nums[i] * combinations
            long contribution = (long) nums[i] * combinations % MOD;

            ans = (ans + contribution) % MOD;
        }

        return (int) ans;
    }

}
