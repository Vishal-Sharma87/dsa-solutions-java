package strings.easy;

public class LC151ReverseWords {
    // Created at: 11 - March - 2026
    // Last revised at: 11 - March - 2026
    // link : https://leetcode.com/problems/reverse-words-in-a-string/

    /*
     * Problem Description:
     * Statement:
     * Given a string s, reverse the order of words in the string.
     * A word is defined as a sequence of non-space characters.
     * The input may contain leading/trailing spaces or multiple spaces
     * between words. The returned string must have words separated by
     * a single space with no leading or trailing spaces.
     * 
     * Example:
     * Input: s = "  the   sky is  blue  "
     * Output: "blue is sky the"
     * Explanation:
     * Leading/trailing spaces removed, multiple spaces collapsed,
     * and word order reversed.
     * 
     * Constraints:
     * - 1 <= s.length <= 10^4
     * - s contains English letters, digits, and spaces ' '
     * - There is at least one word in s
     */

    /*
     * Approaches:
     * 
     * 1. Built-in Split + Reverse
     * Idea: Split on "\\s+" to get words, reverse the array, join with " ".
     * Time: O(n)
     * Space: O(n) — word array + result string
     * Drawbacks:
     * - Relies on regex engine overhead for split
     * - Creates intermediate String[] array
     * 
     * 2. Two-Pointer + StringBuilder (used below) ✅
     * Key Insight:
     * Skip leading/trailing spaces manually using two pointers (i, j).
     * For each word found in [i, k-1], reverse it character by character
     * into the StringBuilder, then append a space.
     * Finally, reverse the entire StringBuilder to restore correct word order.
     * 
     * Why reverse each word then reverse all?
     * Reversing each word individually + reversing the whole string
     * is equivalent to just reversing word order:
     * "the sky" → reverse each word → "eht yks" → reverse all → "sky the" ✅
     * 
     * Time: O(n)
     * Space: O(n) — StringBuilder for result
     */

    // Method to solve the Problem:
    // Step 1: Skip leading spaces with pointer i.
    // Step 2: Skip trailing spaces with pointer j; mark last valid index.
    // Step 3: For each word starting at i:
    // a. Advance k to find the end of the word.
    // b. Append characters from k-1 down to i (reverse the word).
    // c. Append a space after the word.
    // d. Advance k past any inter-word spaces to find next word start.
    // Step 4: Delete the trailing space added after the last word.
    // Step 5: Reverse the entire StringBuilder — this restores correct word order.
    // Step 6: Return the result as a String.

    class Solution {

        /**
         * Reverses the order of words in a given string,
         * removing leading, trailing, and extra spaces between words.
         *
         * @param s the input string containing one or more words
         * @return a string with words in reversed order separated by a single space
         */
        public String reverseWords(String s) {
            int len = s.length();

            if (len <= 0)
                return s;

            StringBuilder sb = new StringBuilder();

            // remove leading spaces
            int i = 0;
            while (i < len && s.charAt(i) == ' ')
                i++;

            // remove trailing spaces
            int j = len - 1;
            while (j > i && s.charAt(j) == ' ')
                j--;

            int last = j;

            while (i <= last) {
                int k = i + 1;
                while (k <= last && s.charAt(k) != ' ')
                    k++;

                // k is either exhausted or at a blank space
                // push all characters in range [i, k-1] reversed
                j = k - 1;
                while (j >= i)
                    sb.append(s.charAt(j--));

                // insert the white space
                sb.append(' ');

                // k is at a blank space, find the start of next word
                while (k <= last && s.charAt(k) == ' ')
                    k++;

                i = k;
            }

            sb.deleteCharAt(sb.length() - 1);
            return sb.reverse().toString();
        }
    }

    // Time Complexity: O(n) — each character is visited at most twice (forward +
    // reverse)
    // Space Complexity: O(n) — StringBuilder stores the final result
}
