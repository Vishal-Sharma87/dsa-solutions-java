package dp.stringmatching;

// Created at: 18-June-2026
// Last revised at: 18-June-2026
// Link: https://leetcode.com/problems/distinct-subsequences/

/*
Problem Description:
--------------------
Statement:
Given two strings s and t, return the number of distinct subsequences of s
which equals t.

A subsequence of a string is a new string generated from the original string
with some characters deleted without changing the relative order of the
remaining characters.

Example:

Input:
s = "rabbbit"
t = "rabbit"

Output:
3

Input:
s = "babgbag"
t = "bag"

Output:
5

Constraints:
1 <= s.length, t.length <= 1000
s and t consist of English letters.
The answer fits within a 32-bit signed integer.
*/

/*
Approach 1: Brute Force Recursion
Idea:
For every matching character:
1. Include the current character in the subsequence.
2. Exclude the current character and continue searching.

If characters do not match, skip the current character from s.

Time Complexity:
O(2^n)

Space Complexity:
O(n)

Drawbacks:
Large amount of overlapping subproblems causing exponential growth.
*/

/*
Approach 2: Memoization
Idea:
Store results for every state (i, j) where:
i -> length considered in s
j -> length considered in t

Avoid recomputation of identical states.

Time Complexity:
O(n × m)

Space Complexity:
O(n × m) + O(n)

Key Insight:
Each state gets computed only once.
*/

/*
Approach 3: Tabulation
Idea:
dp[i][j] represents number of ways to form first j characters of t
using first i characters of s.

Transition:

If characters match:
dp[i][j] = dp[i-1][j] + dp[i-1][j-1]

Else:
dp[i][j] = dp[i-1][j]

Time Complexity:
O(n × m)

Space Complexity:
O(n × m)

Key Insight:
Bottom-up conversion of memoization.
*/

/*
Approach 4: Space Optimized DP
Idea:
Current row only depends on previous row.

Use:
prev[] -> previous row
curr[] -> current row

This reduces memory from O(n × m) to O(m).

Time Complexity:
O(n × m)

Space Complexity:
O(m)

Key Insight:
Only previous row is required to compute current row.
*/

/*
Method to Solve:
----------------
1. Let dp[i][j] denote the number of ways to form first j characters of t
   using first i characters of s.
2. Initialize dp[*][0] = 1 because an empty string can always be formed.
3. Iterate through characters of s.
4. If characters match:
      include current character
      exclude current character
5. Otherwise carry forward previous result.
6. Maintain only previous and current rows.
7. Return answer stored in prev[tLen].
*/

import java.util.Arrays;

public class LC115DistinctSubsequences {

    /**
     * Brute force recursive solution.
     *
     * @param s source string
     * @param t target string
     * @return number of distinct subsequences
     */
    public int numDistinctBruteForce(String s, String t) {
        return brute(s, t, s.length(), t.length());
    }

    /**
     * Recursive state.
     *
     * @param s source string
     * @param t target string
     * @param i current length of s considered
     * @param j current length of t considered
     * @return number of valid subsequences
     */
    private int brute(String s, String t, int i, int j) {

        if (j == 0) {
            return 1;
        }

        if (i == 0) {
            return 0;
        }

        if (s.charAt(i - 1) == t.charAt(j - 1)) {

            int include = brute(s, t, i - 1, j - 1);

            int exclude = brute(s, t, i - 1, j);

            return include + exclude;
        }

        return brute(s, t, i - 1, j);
    }

    /**
     * Memoized solution.
     *
     * @param s source string
     * @param t target string
     * @return number of distinct subsequences
     */
    public int numDistinctMemoized(String s, String t) {

        Integer[][] dp = new Integer[s.length() + 1][t.length() + 1];

        return memoized(s, t, s.length(), t.length(), dp);
    }

    /**
     * Memoized state.
     *
     * @param s  source string
     * @param t  target string
     * @param i  current length of s considered
     * @param j  current length of t considered
     * @param dp memo table
     * @return number of valid subsequences
     */
    private int memoized(String s, String t, int i, int j, Integer[][] dp) {

        if (j == 0) {
            return 1;
        }

        if (i == 0) {
            return 0;
        }

        if (dp[i][j] != null) {
            return dp[i][j];
        }

        if (s.charAt(i - 1) == t.charAt(j - 1)) {

            int include = memoized(s, t, i - 1, j - 1, dp);

            int exclude = memoized(s, t, i - 1, j, dp);

            return dp[i][j] = include + exclude;
        }

        return dp[i][j] = memoized(s, t, i - 1, j, dp);
    }

    /**
     * Space optimized dynamic programming solution.
     *
     * @param s source string
     * @param t target string
     * @return number of distinct subsequences
     */
    public int numDistinct(String s, String t) {

        int sLen = s.length();
        int tLen = t.length();

        int[] prev = new int[tLen + 1];
        int[] curr = new int[tLen + 1];

        prev[0] = 1;

        for (int i = 1; i <= sLen; i++) {

            Arrays.fill(curr, 0);

            curr[0] = 1;

            for (int j = 1; j <= tLen; j++) {

                if (s.charAt(i - 1) == t.charAt(j - 1)) {
                    curr[j] = prev[j] + prev[j - 1];
                } else {
                    curr[j] = prev[j];
                }
            }

            int[] temp = curr;
            curr = prev;
            prev = temp;
        }

        return prev[tLen];
    }
}

// Time Complexity: O(n × m)
// Space Complexity: O(m)