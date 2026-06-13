package dp.lcs;

// Created at: 14-June-2026
// Last revised at: 14-June-2026
// Link: <problem-link>

/*
Problem Description:
--------------------
Statement:
Given a string s, return the length of the longest palindromic subsequence.

A subsequence is a sequence that can be derived from another sequence by
deleting some or no characters without changing the order of the remaining
characters.

Example:
Input:
s = "bbbab"

Output:
4

Explanation:
The longest palindromic subsequence is "bbbb".

Example:
Input:
s = "cbbd"

Output:
2

Explanation:
The longest palindromic subsequence is "bb".

Constraints:
1 <= s.length() <= 1000
s consists of lowercase English letters.
*/

/*
Approach 1: Recursive Brute Force
Idea:
For every pair of indices:
- If characters match, include both characters.
- Otherwise, skip one character from either end and take the best result.

Time Complexity:
O(2^n)

Space Complexity:
O(n)

Drawbacks:
Large number of overlapping subproblems.
*/

/*
Approach 2: Dynamic Programming on String Range
Idea:
Let dp[i][j] represent the longest palindromic subsequence length inside
substring s[i...j].

Time Complexity:
O(n²)

Space Complexity:
O(n²)

Drawbacks:
Requires a full DP table.
*/

/*
Approach 3: Longest Common Subsequence with Reverse String
Idea:
A palindrome reads the same forward and backward.

Reverse the string and find the Longest Common Subsequence between:
- Original string
- Reversed string

The resulting LCS length equals the Longest Palindromic Subsequence length.

Time Complexity:
O(n²)

Space Complexity:
O(n)

Key Insight:
LPS can be transformed into a standard LCS problem and solved using
space-optimized DP.
*/

/*
Method to Solve:
----------------
1. Reverse the given string.
2. Compute LCS between the original and reversed strings.
3. Use two DP rows for space optimization.
4. If characters match, extend the subsequence.
5. Otherwise, preserve the best result from either string.
6. Return the final LCS length.
*/

public class LC516LongestPalindromicSubsequence {

    /**
     * Finds the length of the longest palindromic subsequence.
     *
     * @param s input string
     * @return longest palindromic subsequence length
     */
    public int longestPalindromeSubseq(String s) {

        String reversed = new StringBuilder(s).reverse().toString();

        return new LC1143LongestCommonSubsequence().longestCommonSubsequence(s, reversed);
    }
}

// Time Complexity: O(n²)
// Space Complexity: O(n)