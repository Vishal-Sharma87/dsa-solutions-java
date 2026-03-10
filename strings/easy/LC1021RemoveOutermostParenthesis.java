package strings.easy;

public class LC1021RemoveOutermostParenthesis {
    // Created at: 11 - March - 2026
    // Last revised at: 11 - March - 2026
    // link : https://leetcode.com/problems/remove-outermost-parentheses/

    /*
     * Problem Description:
     * Statement:
     * A valid parentheses string can be decomposed into primitive parts —
     * non-empty substrings that cannot be split further into two valid ones.
     * Given a valid parentheses string s, remove the outermost parentheses
     * of every primitive group in its decomposition and return the result.
     * 
     * Example:
     * Input: s = "(()())(())"
     * Output: "()()()"
     * Explanation:
     * Primitive groups → "(()())" and "(())"
     * After removing outer parens → "()()" + "()" = "()()()"
     * 
     * Constraints:
     * - 1 <= s.length <= 10^5
     * - s[i] is either '(' or ')'
     * - s is a valid parentheses string
     */

    // Approaches:
    //
    // 1. Brute Force (Identify Primitives Explicitly)
    // Idea: Scan to find where each primitive group starts and ends
    // (depth hits 0), then copy everything except the first
    // and last character of each group.
    // Time: O(n²) — repeated string concatenation in worst case
    // Space: O(n) — result string
    // Drawbacks:
    // - String concatenation creates new objects each iteration
    // - Redundant boundary tracking adds overhead
    //
    // 2. Stack-Based
    // Idea: Push '(' onto stack; pop on ')'. Append character only
    // when stack is non-empty before push / after pop,
    // effectively skipping the outermost brackets.
    // Time: O(n)
    // Space: O(n) — stack storage
    // Drawbacks:
    // - Stack overhead; same logic achievable with a plain counter
    //
    // 3. Depth Counter ✅ OPTIMAL
    // Key Insight:
    // The outermost '(' is always the one that bumps cnt 0 → 1, and
    // the outermost ')' is the one that drops cnt 1 → 0.
    // Skip exactly those two; append everything else.
    // Time: O(n)
    // Space: O(n) — only the result StringBuilder

    // Method to solve the Problem:
    // Step 1: Iterate through each character of s.
    // Step 2: For '(':
    // - If cnt >= 1, it's an inner '(' → append to result.
    // - Increment cnt (entering deeper nesting).
    // Step 3: For ')':
    // - If cnt != 1, it's an inner ')' → append to result.
    // - Decrement cnt (exiting one level of nesting).
    // Step 4: When cnt returns to 0, one full primitive group is complete.
    // Step 5: Return the built result string.

    /**
     * Removes the outermost parentheses of every primitive group in the
     * decomposition of a valid parentheses string.
     *
     * @param s a valid parentheses string (1 <= s.length <= 10^5)
     * @return the string with outermost parentheses of each primitive removed
     */
    public String removeOuterParentheses(String s) {
        int len = s.length();
        if (len <= 1)
            return s;

        StringBuilder sb = new StringBuilder();
        int cnt = 0;

        for (int i = 0; i < len; i++) {
            char ch = s.charAt(i);
            if (ch == '(') {
                if (cnt >= 1)
                    sb.append(ch);
                cnt++;
            } else {
                if (cnt != 1)
                    sb.append(ch);
                cnt--;
            }
        }

        return new String(sb);
    }

    // Time Complexity: O(n) — single pass through the string
    // Space Complexity: O(n) — StringBuilder to store the result
}
