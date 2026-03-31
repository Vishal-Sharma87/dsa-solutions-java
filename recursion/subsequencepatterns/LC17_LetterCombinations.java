package recursion.subsequencepatterns;
// Created at: 01 - April - 2025

// Last revised at: 01 - April - 2025
// Link: https://leetcode.com/problems/letter-combinations-of-a-phone-number/
// Time Complexity: O(4^n * n) — 4^n combinations in worst case (all 7/9-letter keys), each string copy is O(n)
// Space Complexity: O(n) — recursion stack depth equal to digits length

/*
 * Problem Description:
 * Given a string containing digits from 2-9 inclusive, return all possible letter
 * combinations that the number could represent on a phone keypad. Return the answer
 * in any order.
 *
 * Example:
 *   Input:  digits = "23"
 *   Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 *
 * Constraints:
 *   - 0 <= digits.length <= 4
 *   - digits[i] is a digit in the range ['2', '9']
 */

/*
 * Approaches:
 *
 * Approach 1: Recursive DFS, one digit at a time ★
 *   Idea: Map each digit to its phone key characters. At each recursion level, pick
 *         one character from the current digit's mapping, append it to the running
 *         string, and recurse on the next digit. When all digits are consumed, record
 *         the result.
 *   Time: O(4^n * n) — up to 4 branches per level, n levels deep, string copy at each leaf
 *   Space: O(n) — recursion depth, no explicit backtrack needed since curr + ch is immutable
 *   Key Insight: Using `curr + ch` (immutable String) instead of StringBuilder means
 *                each call frame naturally gets its own copy — no manual backtracking needed.
 *                Clean tradeoff for small n (max 4 digits per constraints).
 */

import java.util.ArrayList;
import java.util.List;

public class LC17_LetterCombinations {

    private static final String[] KEYS = { "", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz" };

    /**
     * Builds combinations character by character, one digit per recursion level.
     *
     * @param digits the input digit string
     * @param i      index of the current digit being processed
     * @param curr   combination built so far
     * @param ans    accumulator for completed combinations
     */
    private void getCombinations(String digits, int i, String curr, List<String> ans) {
        // all digits consumed — curr is a complete combination
        if (i == digits.length()) {
            ans.add(curr);
            return;
        }

        // map digit character to its phone key letters
        String keyChars = KEYS[digits.charAt(i) - '0'];

        // branch once per letter mapped to this digit
        for (char ch : keyChars.toCharArray()) {
            // immutable concat — each branch gets its own string, no backtrack needed
            getCombinations(digits, i + 1, curr + ch, ans);
        }
    }

    /**
     * Returns all possible letter combinations the digit string could represent
     * on a standard phone keypad.
     *
     * @param digits string of digits ('2'–'9')
     * @return list of all possible letter combinations, empty list if input is
     *         empty
     */
    public List<String> letterCombinations(String digits) {
        List<String> combinations = new ArrayList<>();

        if (digits.isEmpty())
            return combinations;

        getCombinations(digits, 0, "", combinations);

        return combinations;
    }
}