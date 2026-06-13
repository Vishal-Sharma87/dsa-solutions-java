package dp.lcs;
// Created at: 14-June-2026

// Last revised at: 14-June-2026
// Link: <problem-link>

/*
Problem Description:
--------------------
Statement:
Given two strings s and t, find the length of the longest common substring.

A substring is a contiguous sequence of characters within a string.

Example:
Input:
s = "ABCDGH"
t = "ACDGHR"

Output:
4

Explanation:
The longest common substring is "CDGH".

Constraints:
1 <= s.length(), t.length() <= 10^3
*/

/*
Approach 1: Recursive Brute Force
Idea:
Try every possible starting position in both strings and extend while
characters match.

Time Complexity:
O(n * m * min(n, m))

Space Complexity:
O(1)

Drawbacks:
Repeated comparisons lead to excessive work.
*/

/*
Approach 2: Dynamic Programming
Idea:
Let dp[i][j] represent the length of the longest common substring ending
at s[i - 1] and t[j - 1].

If characters match:
dp[i][j] = 1 + dp[i - 1][j - 1]

Otherwise:
dp[i][j] = 0

Track the maximum value encountered.

Time Complexity:
O(n * m)

Space Complexity:
O(n * m)

Drawbacks:
Requires a full DP table.
*/

/*
Approach 3: Space Optimized Dynamic Programming
Idea:
Only the previous row is required to compute the current row.
Use two 1D arrays and swap references after each iteration.

Time Complexity:
O(n * m)

Space Complexity:
O(m)

Key Insight:
For longest common substring, only the diagonal value from the previous
row is needed, making row compression possible.
*/

/*
Method to Solve:
----------------
1. Initialize two DP rows: prev and curr.
2. Iterate through both strings.
3. If characters match, extend the previous diagonal match.
4. Update the global maximum length.
5. Reset unmatched positions to 0.
6. Swap current and previous rows after processing each row.
7. Return the maximum length found.
*/

import java.util.Arrays;

public class GFG_LongestCommonSubstring {

    /**
     * Finds the length of the longest common substring.
     *
     * @param s first string
     * @param t second string
     * @return length of the longest common substring
     */
    public int longestCommonSubstring(String s, String t) {

        int len1 = s.length();
        int len2 = t.length();

        int[] prev = new int[len2 + 1];
        int[] curr = new int[len2 + 1];

        int maxLen = 0;

        for (int i = 1; i <= len1; i++) {

            Arrays.fill(curr, 0);

            for (int j = 1; j <= len2; j++) {

                if (s.charAt(i - 1) == t.charAt(j - 1)) {

                    curr[j] = 1 + prev[j - 1];

                    maxLen = Math.max(maxLen, curr[j]);
                }
            }

            // swap rows
            int[] reference = prev;
            prev = curr;
            curr = reference;
        }

        return maxLen;
    }
}

// Time Complexity: O(n * m)
// Space Complexity: O(m)
