package slidingwindow.fixed_window;

// Created at: 30-October-2026
// Last revised at: 30-October-2026
// Link: https://leetcode.com/problems/find-all-anagrams-in-a-string/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/*
Problem Description:
--------------------
Given two strings s and p, return an array of all the start indices
of p's anagrams in s.

An anagram is a string formed by rearranging the characters of another
string, using exactly the same character frequencies.

Example 1:
Input: s = "cbaebabacd", p = "abc"
Output: [0, 6]

Example 2:
Input: s = "abab", p = "ab"
Output: [0, 1, 2]

Constraints:
- 1 <= s.length, p.length <= 3 * 10^4
- s and p consist of lowercase English letters.
*/

/*
Approach 1: Variable Sliding Window + Frequency Arrays

Idea:
-----
1. Build a frequency array for the target string p.
2. Expand the window using the right pointer.
3. If the current character does not exist in p:
   - Reset the window frequency array.
   - Move both pointers to the next position.
4. Otherwise, increment the frequency of the incoming character.
5. If its frequency exceeds the allowed frequency in p,
   shrink the window from the left until the constraint is restored.
6. When the window length equals p.length(), record its starting index.

Key Insight:
------------
The window maintains two invariants:

1. Every character in the window exists in p.
2. No character frequency in the window exceeds its frequency in p.

When the window length equals p.length(), both frequency arrays
must be identical.

Therefore, an additional frequency-array comparison is unnecessary.

Time Complexity:
----------------
O(n + m), where n = s.length() and m = p.length().

Space Complexity:
-----------------
O(1), using two fixed-size frequency arrays of 26 characters.

Drawbacks / Key Insight:
------------------------
The implementation uses a variable-size window with explicit resets
for irrelevant characters.

Although the window size is not fixed during traversal, an anagram
is recognized only when its size reaches p.length().

The frequency invariant eliminates the need for an explicit
character-by-character equality check.
*/

/*
Method to Solve:
----------------
1. Return an empty list if s is shorter than p.
2. Construct the target frequency array.
3. Initialize left and right pointers.
4. Expand the window by processing the character at right.
5. Reset the window whenever an irrelevant character is encountered.
6. Shrink the window whenever a character exceeds its allowed frequency.
7. Record left whenever the window reaches p.length().
8. Return all recorded starting indices.
*/

class LC438FindAllAnagramsInAString {

    /**
     * Finds all starting indices of anagrams of p in s.
     *
     * @param s the source string
     * @param p the target string whose anagrams must be found
     * @return list of starting indices of all anagrams
     */
    public List<Integer> findAnagrams(String s, String p) {
        if (s.length() < p.length())
            return Collections.emptyList();

        int[] freqInP = new int[26];
        int[] freqInS = new int[26];

        for (char ch : p.toCharArray())
            freqInP[ch - 'a']++;

        int left = 0;
        int right = 0;

        List<Integer> startIndices = new ArrayList<>();

        while (right < s.length()) {
            int curr = s.charAt(right) - 'a';

            if (freqInP[curr] == 0) {
                Arrays.fill(freqInS, 0);
                right++;
                left = right;
                continue;
            }

            freqInS[curr]++;

            // shrink the window when a character exceeds its allowed frequency
            while (left < s.length() && freqInS[curr] > freqInP[curr]) {
                freqInS[s.charAt(left) - 'a']--;
                left++;
            }

            if (right - left + 1 == p.length())
                startIndices.add(left);

            right++;
        }

        return startIndices;
    }
}

// Time Complexity: O(n + m)
// Space Complexity: O(1)