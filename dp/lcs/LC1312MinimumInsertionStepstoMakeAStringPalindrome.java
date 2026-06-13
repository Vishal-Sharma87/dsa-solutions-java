package dp.lcs;

// Created at: 14-June-2026
// Last revised at: 14-June-2026
// Link: https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/

/*
Problem Description:
--------------------
Statement:
Given a string s, return the minimum number of insertions needed
to make s a palindrome.

A palindrome reads the same forward and backward.

Example:
Input:
s = "zzazz"

Output:
0

Explanation:
The string is already a palindrome.

Example:
Input:
s = "mbadm"

Output:
2

Explanation:
One possible palindrome is "mbdadbm".

Example:
Input:
s = "leetcode"

Output:
5

Constraints:
1 <= s.length() <= 500
s consists of lowercase English letters.
*/

/*
Approach 1: Recursive Brute Force
Idea:
For a substring s[i...j]:

- If characters match, move both pointers inward.
- Otherwise, insert a character on either side and choose the better option.

Time Complexity:
O(2^n)

Space Complexity:
O(n)

Drawbacks:
Massive overlap between subproblems.
*/

/*
Approach 2: Memoized Dynamic Programming
Idea:
Let dp[i][j] represent the minimum insertions required to convert
substring s[i...j] into a palindrome.

Transition:

If s[i] == s[j]:
    dp[i][j] = dp[i + 1][j - 1]

Otherwise:
    dp[i][j] = 1 + min(
                    dp[i][j - 1],
                    dp[i + 1][j]
                )

Time Complexity:
O(n²)

Space Complexity:
O(n²)

Key Insight:
Whenever end characters mismatch, one insertion is mandatory.
Choose the side producing fewer future insertions.
*/

/*
Approach 3: LPS Based Solution
Idea:
Minimum Insertions = n - Longest Palindromic Subsequence

LPS can be found using:
LCS(originalString, reversedString)

Time Complexity:
O(n²)

Space Complexity:
O(n²) or O(n)

Key Insight:
Characters not belonging to the LPS must be inserted.
*/

/*
Method to Solve:
----------------
1. Define a recursive function for substring s[i...j].
2. If the substring length is 0 or 1, return 0.
3. If characters at both ends match, solve the inner substring.
4. Otherwise:
   - Insert near the left side.
   - Insert near the right side.
5. Take the minimum result and add one insertion.
6. Memoize every state.
7. Return the answer for the entire string.
*/

public class LC1312MinimumInsertionStepstoMakeAStringPalindrome {

    /**
     * Computes minimum insertions needed to make
     * substring s[i...j] a palindrome.
     *
     * @param s  input string
     * @param i  left index
     * @param j  right index
     * @param dp memoization table
     * @return minimum insertions required
     */
    private int minimumInsertions(String s, int i, int j, Integer[][] dp) {

        if (i >= j) {
            return 0;
        }

        if (dp[i][j] != null) {
            return dp[i][j];
        }

        if (s.charAt(i) == s.charAt(j)) {
            return dp[i][j] = minimumInsertions(s, i + 1, j - 1, dp);
        }

        return dp[i][j] = 1 + Math.min(
                minimumInsertions(s, i, j - 1, dp),
                minimumInsertions(s, i + 1, j, dp));
    }

    /**
     * Finds the minimum number of insertions required
     * to make the string a palindrome.
     *
     * @param s input string
     * @return minimum insertions required
     */
    public int minInsertions(String s) {

        int n = s.length();

        return minimumInsertions(s, 0, n - 1, new Integer[n][n]);
    }
}

// Time Complexity: O(n²)
// Space Complexity: O(n²)
