package strings.easy;

public class LC242Anagram {
    // Created at: 15 - March - 2026
    // Last revised at: 15 - March - 2026
    // Link: https://leetcode.com/problems/valid-anagram/
    // Time Complexity: O(n)
    // Space Complexity: O(1) — fixed 26-size array

    /*
     * Problem Description:
     * Given two strings s and t, return true if t is an anagram of s, and false
     * otherwise.
     * An anagram is a word formed by rearranging the letters of another word using
     * all
     * the original letters exactly once.
     *
     * Example:
     * Input: s = "anagram", t = "nagaram"
     * Output: true
     *
     * Input: s = "rat", t = "car"
     * Output: false
     *
     * Constraints:
     * - 1 <= s.length, t.length <= 5 * 10^4
     * - s and t consist of lowercase English letters
     *
     * -------------------------------------------------------------------------
     *
     * Approach 1: Sorting
     * Idea: Sort both strings. If they are anagrams, sorted versions will be
     * identical.
     * Time: O(n log n)
     * Space: O(n) — for the sorted char arrays
     * Drawback: Slower due to sorting overhead; not optimal.
     *
     * Approach 2: Frequency Array (Optimal) ✅
     * Idea: Use a fixed int[26] array to track character frequency.
     * Increment count for each char in s, decrement for each char in t
     * in a single pass. If all counts are zero, strings are anagrams.
     * Time: O(n)
     * Space: O(1) — array size is always 26, independent of input size
     * Key Insight: Simultaneous increment/decrement in one loop avoids a
     * second traversal and keeps the logic concise.
     */

    /*
     * Method to solve (Approach 2 — Frequency Array):
     * 1. Return false immediately if lengths differ — anagrams must have equal
     * length.
     * 2. Allocate int[26] to hold net frequency of each lowercase letter.
     * 3. In a single loop over index i:
     * a. Map s.charAt(i) → index by subtracting 'a', increment freq[index].
     * b. Map t.charAt(i) → index by subtracting 'a', decrement freq[index].
     * 4. After the loop, scan freq[]:
     * - Any non-zero entry means a character appeared a different number of
     * times in s vs t → return false.
     * 5. If all entries are zero, return true.
     */

    /**
     * Determines whether two strings are anagrams of each other.
     * Uses a frequency-difference array for a single-pass O(n) solution.
     *
     * @param s the source string (lowercase English letters)bvhgbj
     * @param t the target string to compare against s
     * @return {@code true} if t is an anagram of s, {@code false} otherwise
     */
    public boolean isAnagram(String s, String t) {

        int len = s.length();
        if (len != t.length())
            return false;

        int[] freq = new int[26];

        for (int i = 0; i < len; i++) {
            int sc = s.charAt(i) - 'a';
            int tc = t.charAt(i) - 'a';
            freq[sc]++;
            freq[tc]--;
        }

        for (int f : freq) {
            if (f != 0)
                return false;
        }
        return true;

    }
}
