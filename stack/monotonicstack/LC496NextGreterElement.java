package stack.monotonicstack;

import java.util.HashMap;
import java.util.Stack;

public class LC496NextGreterElement {
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {

        Stack<Integer> st = new Stack<>();

        HashMap<Integer, Integer> map = new HashMap<>();

        st.push(-1); // last element have no NGE so -1 for edge case

        for (int i = nums2.length - 1; i >= 0; i--) {
            while (st.size() > 1 && st.peek() <= nums2[i]) {
                st.pop();
            }
            map.put(nums2[i], st.peek());
            st.push(nums2[i]);
        }

        int[] ans = new int[nums1.length];

        for (int i = 0; i < nums1.length; i++) {
            ans[i] = map.get(nums1[i]);
        }
        return ans;
    }
}
