package dp.stringmatching;

// Created at: 18-June-2026
// Last revised at: 18-June-2026
// Link: https://leetcode.com/problems/wildcard-matching/

/*
Problem Description:
--------------------
Statement:
Given an input string s and a pattern p, implement wildcard matching
with support for:

'?' -> Matches any single character.
'*' -> Matches any sequence of characters (including empty sequence).

Return true if the entire string matches the entire pattern.

Example:

Input:
s = "aa"
p = "a"

Output:
false

Example:

Input:
s = "aa"
p = "*"

Output:
true

Example:

Input:
s = "cb"
p = "?a"

Output:
false

Constraints:
0 <= s.length, p.length <= 2000
s contains only lowercase English letters.
p contains lowercase English letters, '?' and '*'.
*/

/*
Approach 1: Brute Force Recursion
Idea:
Process characters from both strings.

Cases:

1. Normal character:
   Must match exactly.

2. '?':
   Matches any single character.

3. '*':
   Try:
   - matching zero characters
   - matching one character
   - matching multiple characters

Time Complexity:
Exponential

Space Complexity:
O(n + m)

Drawbacks:
Large number of overlapping states.
*/

/*
Approach 2: Memoization
Idea:
Store answer for every state (i, j).

State:
(i, j) represents whether first i characters of s
can match first j characters of p.

Time Complexity:
O(n × m)

Space Complexity:
O(n × m)

Key Insight:
Each state gets evaluated once.
*/

/*
Approach 3: Tabulation
Idea:
Convert recursion into bottom-up DP.

Transition:

If pattern character is '*':
dp[i][j] =
dp[i][j-1]      // empty match
OR
dp[i-1][j]      // consume character
OR
dp[i-1][j-1]

If pattern character is '?' or exact match:
dp[i][j] = dp[i-1][j-1]

Time Complexity:
O(n × m)

Space Complexity:
O(n × m)

Key Insight:
Every state depends only on left, top and diagonal.
*/

/*
Approach 4: Space Optimized DP
Idea:
Only previous row is required to compute current row.

Maintain:
prev[] -> previous row
curr[] -> current row

Time Complexity:
O(n × m)

Space Complexity:
O(m)

Key Insight:
Full DP table is unnecessary.
*/

/*
Method to Solve:
----------------
1. Define dp[i][j] as whether first i characters of s
   match first j characters of pattern p.
2. Initialize empty string cases.
3. If pattern contains leading '*',
   it can match an empty string.
4. For every character pair:
   - '*' can expand or skip.
   - '?' matches any character.
   - normal characters must match exactly.
5. Store only previous and current rows.
6. Return final state.
*/

import java.util.Arrays;

public class LC44WildcardMatching {

    /**
     * Memoized solution.
     *
     * @param s input string
     * @param p wildcard pattern
     * @return true if pattern matches string
     */
    public boolean isMatchMemoized(String s, String p) {

        Boolean[][] dp = new Boolean[s.length() + 1][p.length() + 1];

        return memoized(
                s,
                p,
                s.length(),
                p.length(),
                dp);
    }

    /**
     * Memoized state.
     *
     * @param s  input string
     * @param p  pattern string
     * @param i  characters considered from s
     * @param j  characters considered from p
     * @param dp memo table
     * @return matching status
     */
    private boolean memoized(
            String s,
            String p,
            int i,
            int j,
            Boolean[][] dp) {

        if (dp[i][j] != null) {
            return dp[i][j];
        }

        if (j == 0) {
            return i == 0;
        }

        if (i == 0) {

            while (j > 0 && p.charAt(j - 1) == '*') {
                j--;
            }

            return j == 0;
        }

        char current = p.charAt(j - 1);
        char toMatch = s.charAt(i - 1);

        boolean canMatch = false;

        if (current == '*') {

            canMatch = memoized(s, p, i, j - 1, dp)
                    || memoized(s, p, i - 1, j, dp)
                    || memoized(s, p, i - 1, j - 1, dp);

        } else if (current == '?') {

            canMatch = memoized(s, p, i - 1, j - 1, dp);

        } else if (current == toMatch) {

            canMatch = memoized(s, p, i - 1, j - 1, dp);
        }

        return dp[i][j] = canMatch;
    }

    /**
     * Space optimized DP solution.
     *
     * @param s input string
     * @param p wildcard pattern
     * @return true if pattern matches string
     */
    public boolean isMatch(String s, String p) {

        int sLen = s.length();
        int pLen = p.length();

        boolean[] prev = new boolean[pLen + 1];
        boolean[] curr = new boolean[pLen + 1];

        prev[0] = true;

        // initialize empty string row
        for (int col = 1; col <= pLen; col++) {

            if (p.charAt(col - 1) == '*') {
                prev[col] = true;
            } else {
                break;
            }
        }

        for (int i = 1; i <= sLen; i++) {

            Arrays.fill(curr, false);

            for (int j = 1; j <= pLen; j++) {

                char current = p.charAt(j - 1);
                char toMatch = s.charAt(i - 1);

                boolean canMatch = false;

                if (current == '*') {

                    canMatch = curr[j - 1]
                            || prev[j]
                            || prev[j - 1];

                } else if (current == '?' || current == toMatch) {

                    canMatch = prev[j - 1];
                }

                curr[j] = canMatch;
            }

            boolean[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[pLen];
    }
}

// Time Complexity: O(n × m)
// Space Complexity: O(m)