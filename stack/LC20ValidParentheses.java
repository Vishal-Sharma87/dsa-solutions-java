
package stack;
// Link: https://leetcode.com/problems/valid-parentheses/

// Time Complexity: O(n)
// Space Complexity: O(n)
// Created at: 11 - April - 2025
// Last revised at: 11 - April - 2025

import java.util.Stack;

/*
 * Problem Description:
 * Given a string s containing just the characters '(', ')', '{', '}', '[' and ']',
 * determine if the input string is valid.
 *
 * A string is valid if:
 *   - Every open bracket is closed by the same type of closing bracket.
 *   - Open brackets are closed in the correct order.
 *   - Every close bracket has a corresponding open bracket.
 *
 * Example:
 *   Input: s = "()[]{}"
 *   Output: true
 *
 *   Input: s = "([)]"
 *   Output: false
 *
 * Constraints:
 *   - 1 <= s.length() <= 10^4
 *   - s consists of parentheses only '()[]{}'
 */

/*
 * Approaches:
 *
 * Approach 1 — Stack (chosen) ★
 *   Idea: Push open brackets onto a stack. For each closing bracket,
 *         check if the top of the stack is the matching opener.
 *   Time: O(n)
 *   Space: O(n) — stack holds at most n/2 characters
 *   Key Insight: Odd-length strings can never be valid — short-circuit early.
 */

public class LC20ValidParentheses {

    /**
     * Determines whether the given bracket string is valid.
     *
     * @param s the input string containing only bracket characters
     * @return true if all brackets are properly matched and ordered, false
     *         otherwise
     */
    public boolean isValid(String s) {

        // odd length can never be balanced
        if (s.length() % 2 != 0)
            return false;

        Stack<Character> st = new Stack<>();

        for (char ch : s.toCharArray()) {

            // push openers
            if (ch == '(' || ch == '[' || ch == '{') {
                st.push(ch);
                continue;
            }

            // unmatched closer
            if (st.isEmpty())
                return false;

            Character top = st.peek();
            switch (ch) {
                case ')' -> {
                    if ('(' != top)
                        return false;
                    st.pop();
                }
                case ']' -> {
                    if ('[' != top)
                        return false;
                    st.pop();
                }
                case '}' -> {
                    if ('{' != top)
                        return false;
                    st.pop();
                }
            }
        }

        // leftover openers = invalid
        return st.isEmpty();
    }
}