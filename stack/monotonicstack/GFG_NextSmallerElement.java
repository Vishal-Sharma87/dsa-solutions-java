package stack.monotonicstack;

import java.util.ArrayList;
import java.util.Stack;

public class GFG_NextSmallerElement {
    public ArrayList<Integer> nextSmallerEle(int[] nums) {
        // code here
        Stack<Integer> st = new Stack<>();

        st.push(-1); // last element have no NGE so -1 for edge case

        for (int i = nums.length - 1; i >= 0; i--) {
            int val = nums[i];
            while (st.size() > 1 && st.peek() >= val) {
                st.pop();
            }
            nums[i] = st.peek();

            st.push(val);
        }
        ArrayList<Integer> ans = new ArrayList<>();

        for (int val : nums) {
            ans.add(val);
        }

        return ans;
    }
}
