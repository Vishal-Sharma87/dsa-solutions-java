package dp.lcs;

// Created at: 13-June-2026
// Last revised at: 13-June-2026
// Link: https://www.codingninjas.com/

/*
Problem Description:
--------------------
Statement:
Given two strings, return the actual Longest Common
Subsequence (LCS).

Example:
Input:
s = "abcde"
t = "ace"

Output:
"ace"

Constraints:
1 <= n, m <= 1000
*/

/*
Approach 1: Generate All Subsequences
Idea:
Generate subsequences of one string and check
whether they exist in the other.

Time Complexity:
Exponential

Space Complexity:
Exponential

Drawbacks:
Not feasible for large inputs.
*/

/*
Approach 2: DP + Backtracking
Idea:
First compute LCS length using tabulation.
Then reconstruct the subsequence by walking
backwards through the DP table.

Time Complexity:
O(n*m)

Space Complexity:
O(n*m)

Key Insight:
The DP table stores enough information to rebuild
one valid longest common subsequence.
*/

/*
Method to Solve:
----------------
1. Build the LCS DP table.
2. Start from dp[n][m].
3. If characters match, include them in answer.
4. Otherwise move towards the larger DP value.
5. Reverse the collected characters.
6. Return the reconstructed LCS.
*/

// Time Complexity: O(n * m)
// Space Complexity: O(n * m)

public class PrintLongestCommonSubsequence {

    /**
     * Returns the actual longest common subsequence.
     *
     * @param n length of first string
     * @param m length of second string
     * @param s first string
     * @param t second string
     * @return longest common subsequence
     */
    public String findLCS(int n, int m, String s, String t) {
        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    int sPreserved = dp[i][j - 1];
                    int tPreserved = dp[i - 1][j];

                    dp[i][j] = Math.max(sPreserved, tPreserved);
                }
            }
        }

        StringBuilder lcs = new StringBuilder();

        int i = n;
        int j = m;

        while (i > 0 && j > 0) {
            if (s.charAt(i - 1) == t.charAt(j - 1)) {
                lcs.append(s.charAt(i - 1));
                i--;
                j--;
            } else if (dp[i][j - 1] > dp[i - 1][j]) {
                j--;
            } else {
                i--;
            }
        }

        return lcs.reverse().toString();
    }
}