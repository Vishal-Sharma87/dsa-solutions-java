
package dp.lcs;

// Created at: 17-June-2026
// Last revised at: 17-June-2026
// Link: https://leetcode.com/problems/shortest-common-supersequence/

/*
Problem Description:
--------------------
Statement:
Given two strings s and t, return the shortest string that has both s and t as subsequences.
If multiple answers exist, return any one of them.

Example:
Input: s = "abac", t = "cab"
Output: "cabac"

Input: s = "aaaaaaaa", t = "aaaaaaaa"
Output: "aaaaaaaa"

Constraints:
- 1 <= s.length, t.length <= 1000
- s and t consist of lowercase English letters.
*/

/*
Approach 1: Generate All Supersequences (Brute Force)
Idea:
Generate possible supersequences and find the shortest valid one.

Time Complexity:
Exponential

Space Complexity:
Exponential

Drawbacks:
Not feasible even for moderate input sizes.
*/

/*
Approach 2: LCS-Based Reconstruction (Optimal)
Idea:
1. Compute the Longest Common Subsequence (LCS) using DP.
2. Traverse the DP table from the end.
3. When characters match, include it once.
4. Otherwise include the character that belongs to the direction chosen during LCS reconstruction.
5. Append remaining characters and reverse the result.

Time Complexity:
O(m * n)

Space Complexity:
O(m * n)

Key Insight:
Characters belonging to the LCS should appear only once in the final supersequence. The DP table helps reconstruct the shortest valid answer directly.
*/

/*
Method to Solve:
----------------
1. Build the LCS DP table for strings s and t.
2. Start from dp[m][n].
3. If characters match, add the character and move diagonally.
4. Otherwise move toward the larger LCS value and add the skipped character.
5. Append leftover characters from either string.
6. Reverse the constructed string and return it.
*/

public class LC1092ShortestCommonSupersequence {

    /**
     * Returns the shortest common supersequence of two strings.
     *
     * @param s first string
     * @param t second string
     * @return shortest common supersequence
     */
    public String shortestCommonSupersequence(String s, String t) {
        int sLen = s.length();
        int tLen = t.length();

        int[][] dp = buildLcsTable(s, t);

        StringBuilder supersequence = new StringBuilder();

        int i = sLen;
        int j = tLen;

        while (i > 0 && j > 0) {

            if (s.charAt(i - 1) == t.charAt(j - 1)) {
                supersequence.append(s.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i - 1][j] > dp[i][j - 1]) {
                supersequence.append(s.charAt(i - 1));
                i--;
            } else {
                supersequence.append(t.charAt(j - 1));
                j--;
            }
        }

        while (i > 0) {
            supersequence.append(s.charAt(i - 1));
            i--;
        }

        while (j > 0) {
            supersequence.append(t.charAt(j - 1));
            j--;
        }

        return supersequence.reverse().toString();
    }

    /**
     * Builds the LCS DP table.
     *
     * @param s first string
     * @param t second string
     * @return LCS DP table
     */
    private int[][] buildLcsTable(String s, String t) {

        int sLen = s.length();
        int tLen = t.length();

        int[][] dp = new int[sLen + 1][tLen + 1];

        for (int i = 1; i <= sLen; i++) {

            for (int j = 1; j <= tLen; j++) {

                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp;
    }
}

// Time Complexity: O(m * n)
// Space Complexity: O(m * n)
