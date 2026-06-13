package dp.lcs;

// Created at: 14-June-2026
// Last revised at: 14-June-2026
// Link: https://leetcode.com/problems/delete-operation-for-two-strings/

/*
Problem Description:
--------------------
Statement:
Given two strings word1 and word2, return the minimum number of deletions
required to make both strings equal.

In one operation, you can delete a character from either string.

Example:
Input:
word1 = "sea"
word2 = "eat"

Output:
2

Explanation:
Delete 's' from word1 and 't' from word2.

Example:
Input:
word1 = "leetcode"
word2 = "etco"

Output:
4

Constraints:
1 <= word1.length(), word2.length() <= 500
word1 and word2 consist of lowercase English letters.
*/

/*
Approach 1: Recursive Brute Force
Idea:
At every mismatch:
- Delete a character from word1.
- Delete a character from word2.

Try both possibilities and take the minimum.

Time Complexity:
O(2^(n+m))

Space Complexity:
O(n+m)

Drawbacks:
Huge number of repeated subproblems.
*/

/*
Approach 2: Dynamic Programming on Strings
Idea:
Let dp[i][j] represent the minimum deletions required to make
suffixes equal.

Build answers using previously computed states.

Time Complexity:
O(n * m)

Space Complexity:
O(n * m)

Drawbacks:
Requires maintaining an entire DP table.
*/

/*
Approach 3: Longest Common Subsequence
Idea:
Characters belonging to the LCS can be preserved in both strings.

All remaining characters must be deleted.

Minimum Deletions:
(word1.length - LCS) + (word2.length - LCS)

Time Complexity:
O(n * m)

Space Complexity:
O(m)

Key Insight:
The common subsequence that should be preserved is the LCS.
Everything else contributes to the deletion count.
*/

/*
Method to Solve:
----------------
1. Compute the Longest Common Subsequence length.
2. Characters present in the LCS remain untouched.
3. Delete remaining characters from word1.
4. Delete remaining characters from word2.
5. Return:
   word1.length + word2.length - 2 * LCS
*/

import java.util.Arrays;

public class LC583DeleteOperationForTwoStrings {

    /**
     * Computes the Longest Common Subsequence length.
     *
     * @param s first string
     * @param t second string
     * @return length of the LCS
     */
    private int longestCommonSubsequence(String s, String t) {

        int len1 = s.length();
        int len2 = t.length();

        int[] prev = new int[len2 + 1];
        int[] curr = new int[len2 + 1];

        for (int i = 1; i <= len1; i++) {

            Arrays.fill(curr, 0);

            for (int j = 1; j <= len2; j++) {

                if (s.charAt(i - 1) == t.charAt(j - 1)) {

                    curr[j] = 1 + prev[j - 1];

                } else {

                    int sPreserved = curr[j - 1];
                    int tPreserved = prev[j];

                    curr[j] = Math.max(sPreserved, tPreserved);
                }
            }

            int[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[len2];
    }

    /**
     * Finds the minimum deletions required
     * to make both strings equal.
     *
     * @param word1 first string
     * @param word2 second string
     * @return minimum deletion operations
     */
    public int minDistance(String word1, String word2) {

        int lcsLength = longestCommonSubsequence(word1, word2);

        return word1.length()
                + word2.length()
                - (2 * lcsLength);
    }
}

// Time Complexity: O(n * m)
// Space Complexity: O(m)