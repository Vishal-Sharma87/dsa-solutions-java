package slidingwindow.fixed_window;

// Created at: 31-October-2026
// Last revised at: 31-October-2026
// Link: https://leetcode.com/problems/permutation-in-string/

import java.util.Arrays;

/*
Problem Description:
--------------------
Statement:
Given two strings s1 and s2, return true if s2 contains a permutation of s1,
or false otherwise. In other words, return true if one of s1's permutations
is a substring of s2.

Example:
Input: s1 = "ab", s2 = "eidbaooo"
Output: true
Explanation: s2 contains "ba", a permutation of s1.

Input: s1 = "ab", s2 = "eidboaoo"
Output: false

Constraints:
1 <= s1.length, s2.length <= 10^4
s1 and s2 consist of lowercase English letters.
*/

/*
Approach 1: Brute Force
Idea:
Generate every permutation of s1 (swap-based backtracking) and store them in a
HashSet. Slide a window of size len1 over s2 and check whether the window
string is present in the set.

Time Complexity:
O(n! * n) to generate and store the permutations, plus O(len2 * len1) for the
window checks.

Space Complexity:
O(n! * n) for the set of permutation strings.

Drawbacks:
Factorial blow-up, TLE/MLE already around n = 10. Also does useless work:
a permutation only means "same characters, same counts", the order never matters.

Approach 2 ★: Sliding Window with Frequency Counts
Idea:
Compare character counts instead of strings. Keep a window over s2 whose count
for every letter never exceeds that letter's count in s1. A character not in s1
resets the window, an over-used character shrinks it from the left. When the
window size reaches len1 with no letter over budget, the counts sum to len1 on
both sides and each is <=, so they must be exactly equal.

Time Complexity:
O(len1 + len2). Every character enters and leaves the window at most once,
each reset costs a constant 26.

Space Complexity:
O(1), two arrays of size 26.

Drawbacks / Key Insight:
Assumes lowercase a-z only. The reset for absent characters is just a shortcut,
the shrink loop alone would also empty the window (absent char has budget 0).
*/

/*
Method to Solve:
----------------
1. Count the characters of s1 into freqInS1.
2. Move right across s2 and track counts of the current window.
3. If s2[right] is not in s1, no window containing it can match,
   so clear the window and restart after it.
4. If s2[right] exceeds its budget in s1, shrink from the left until it fits.
5. The window now holds only characters within s1's budget, so
   size == len1 means every count matches exactly, return true.
*/
public class LC567PermutationInString {

    /**
     * Checks whether s2 contains any permutation of s1 as a substring.
     *
     * @param s1 the string whose permutation we look for
     * @param s2 the string to search in
     * @return true if some permutation of s1 is a substring of s2
     */
    public boolean checkInclusion(String s1, String s2) {
        int len1 = s1.length();
        int len2 = s2.length();

        if (len1 > len2)
            return false;

        int[] freqInS1 = buildFrequency(s1);
        int[] freqInWindow = new int[26];

        int left = 0;
        int right = 0;

        while (right < len2) {
            int curr = s2.charAt(right) - 'a';

            // char not in s1, restart the window after it
            if (freqInS1[curr] == 0) {
                right++;
                left = right;
                Arrays.fill(freqInWindow, 0);
                continue;
            }

            freqInWindow[curr]++;

            // over budget for curr, shrink from the left
            while (freqInWindow[curr] > freqInS1[curr]) {
                freqInWindow[s2.charAt(left) - 'a']--;
                left++;
            }

            if (right - left + 1 == len1)
                return true;

            right++;
        }

        return false;
    }

    /**
     * Builds the frequency array of lowercase letters for a string.
     *
     * @param s input string (lowercase a-z only)
     * @return array of size 26 where index i holds the count of ('a' + i)
     */
    private int[] buildFrequency(String s) {
        int[] freq = new int[26];
        for (char ch : s.toCharArray())
            freq[ch - 'a']++;
        return freq;
    }

    // Time Complexity: O(len1 + len2)
    // Space Complexity: O(1)
}