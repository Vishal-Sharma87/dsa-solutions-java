package stack.monotonicstack;

import java.util.Stack;

public class LC503NextGreterElement2 {
    public int[] nextGreaterElements(int[] nums) {

        // step 1: stack buiding
        Stack<Integer> st = new Stack<>();
        st.push(-1);
        int len = nums.length;

        for (int i = len - 1; i >= 0; i--) {

            while (st.size() > 1 && st.peek() <= nums[i])
                st.pop();

            st.push(nums[i]);
        }

        // step 2: ans building (modifying given array so that LC give a clap for less
        // memory usage)
        for (int i = len - 1; i >= 0; i--) {
            int val = nums[i];
            while (st.size() > 1 && st.peek() <= val)
                st.pop();
            nums[i] = st.peek();
            st.push(val);
        }

        return nums;
    }
}
