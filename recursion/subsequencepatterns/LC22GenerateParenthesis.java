package recursion.subsequencepatterns;

import java.util.ArrayList;
import java.util.List;

public class LC22GenerateParenthesis {
    // Created at: 29-March-2025
    // Last revised at: 29-March-2025
    // Link: https://leetcode.com/problems/generate-parentheses/
    // Time Complexity: O(4^N / sqrt(N)) — Catalan number of valid combinations
    // Space Complexity: O(N) — recursion depth

    /*
     * Problem Description:
     * Given n pairs of parentheses, generate all combinations of well-formed
     * parentheses.
     *
     * Example:
     * Input: n = 3
     * Output: ["((()))", "(()())", "(())()", "()(())", "()()()"]
     *
     * Constraints:
     * 1 <= n <= 8
     */

    /*
     * Approach: Recursion / Backtracking
     *
     * Idea:
     * Build the string position by position.
     * At each step, decide whether to place '(' or ')'.
     *
     * Rules:
     * - Place '(' only if we haven't used all n opens yet
     * - Place ')' only if there's an unmatched '(' to close (cnt > 0)
     *
     * Track state with:
     * - openUsed : how many '(' placed so far
     * - i : current position (0 to 2n-1)
     * - cnt : unmatched open brackets (balance)
     * note: cnt = openUsed - closeUsed = 2*openUsed - i
     * (redundant but kept explicit for clarity)
     *
     * Time: O(4^N / sqrt(N)) — Nth Catalan number of valid strings
     * Space: O(N) — recursion depth
     */

    /**
     * Recursively builds all valid parenthesis combinations.
     *
     * @param ans      list collecting valid combinations
     * @param n        total number of pairs
     * @param curr     string built so far
     * @param openUsed number of '(' placed so far
     * @param i        current position in the string (0-indexed)
     * @param cnt      number of unmatched '(' currently open
     */
    public void generate(List<String> ans, int n, String curr, int openUsed, int i, int cnt) {
        if (i == 2 * n) {
            ans.add(curr);
            return;
        }

        if (openUsed < n) {
            generate(ans, n, curr + "(", openUsed + 1, i + 1, cnt + 1); // use an open
            if (cnt > 0) {
                generate(ans, n, curr + ")", openUsed, i + 1, cnt - 1); // close an unmatched open
            }
        } else {
            generate(ans, n, curr + ")", openUsed, i + 1, cnt - 1); // all opens used, only close now
        }
    }

    /**
     * Returns all well-formed parenthesis combinations for n pairs.
     *
     * @param n number of parenthesis pairs
     * @return list of valid combinations
     */
    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        if (n <= 0)
            return ans;
        generate(ans, n, "", 0, 0, 0);
        return ans;
    }

    // -----------------------------------------------------------------------
    // Cleaner version — drops redundant parameters (i and cnt)
    // cnt = open - close (balance, always derivable)
    // i = curr.length() (position, always derivable)
    // Two independent ifs replace the if-else — each branch is its own rule
    // -----------------------------------------------------------------------

    /*
     * Approach: Recursion (simplified, same logic)
     *
     * open = '(' placed so far
     * close = ')' placed so far
     *
     * Place '(' if open < n
     * Place ')' if close < open (i.e. there's an unmatched open to close)
     */

    /**
     * @param ans   list collecting valid combinations
     * @param n     total number of pairs
     * @param curr  string built so far
     * @param open  number of '(' placed so far
     * @param close number of ')' placed so far
     */
    public void generate(List<String> ans, int n, String curr, int open, int close) {
        if (curr.length() == 2 * n) {
            ans.add(curr);
            return;
        }

        if (open < n)
            generate(ans, n, curr + "(", open + 1, close); // still have opens to place

        if (close < open)
            generate(ans, n, curr + ")", open, close + 1); // unmatched open exists, safe to close
    }
}
