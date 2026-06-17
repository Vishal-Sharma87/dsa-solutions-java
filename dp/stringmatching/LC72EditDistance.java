package dp.stringmatching;

// Created at: 18-June-2026
// Last revised at: 18-June-2026
// Link: https://leetcode.com/problems/edit-distance/

/*
Problem Description:
--------------------
Statement:
Given two strings word1 and word2, return the minimum number of operations
required to convert word1 into word2.

Allowed operations:
1. Insert a character
2. Delete a character
3. Replace a character

Example:

Input:
word1 = "horse"
word2 = "ros"

Output:
3

Explanation:
horse -> rorse (replace h with r)
rorse -> rose  (remove r)
rose  -> ros   (remove e)

Example:

Input:
word1 = "intention"
word2 = "execution"

Output:
5

Constraints:
0 <= word1.length, word2.length <= 500
word1 and word2 consist of lowercase English letters.
*/

/*
Approach 1: Brute Force Recursion
Idea:
At every mismatch we have three choices:

1. Insert a character
2. Delete a character
3. Replace a character

Explore all possibilities and choose the minimum cost.

Time Complexity:
O(3^(n+m))

Space Complexity:
O(n + m)

Drawbacks:
Massive overlap of subproblems causing exponential runtime.
*/

/*
Approach 2: Memoization
Idea:
Store answer for every state (i, j).

State:
dp[i][j] = minimum operations needed to convert
first i characters of word1 into first j characters of word2.

Time Complexity:
O(n × m)

Space Complexity:
O(n × m)

Key Insight:
Each state is solved only once.
*/

/*
Approach 3: Tabulation
Idea:
Build the DP table from smaller prefixes.

Transition:

If characters match:
dp[i][j] = dp[i-1][j-1]

Else:
dp[i][j] =
1 + min(
    insert,
    delete,
    replace
)

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
Current row depends only on:
1. Previous row
2. Current row's previous column

Store only two rows.

Time Complexity:
O(n × m)

Space Complexity:
O(m)

Key Insight:
Entire DP table is unnecessary.
*/

/*
Method to Solve:
----------------
1. Let dp[i][j] represent minimum edits required to convert
   first i characters of word1 into first j characters of word2.
2. Initialize:
      dp[0][j] = j
      dp[i][0] = i
3. If characters match:
      carry diagonal value.
4. Otherwise compute:
      insert
      delete
      replace
5. Add one operation cost.
6. Keep only previous and current rows.
7. Return dp[n][m].
*/

import java.util.Arrays;

public class LC72EditDistance {

    /**
     * Brute force recursive solution.
     *
     * @param word1 source string
     * @param word2 target string
     * @return minimum edit distance
     */
    public int minDistanceBruteForce(String word1, String word2) {
        return brute(word1, word2, word1.length(), word2.length());
    }

    /**
     * Recursive state.
     *
     * @param s source string
     * @param t target string
     * @param i current length considered in source
     * @param j current length considered in target
     * @return minimum operations required
     */
    private int brute(String s, String t, int i, int j) {

        if (i == 0) {
            return j;
        }

        if (j == 0) {
            return i;
        }

        if (s.charAt(i - 1) == t.charAt(j - 1)) {
            return brute(s, t, i - 1, j - 1);
        }

        int deleteOperation = brute(s, t, i, j - 1);

        int insertOperation = brute(s, t, i - 1, j);

        int replaceOperation = brute(s, t, i - 1, j - 1);

        return 1 + Math.min(
                replaceOperation,
                Math.min(deleteOperation, insertOperation));
    }

    /**
     * Space optimized dynamic programming solution.
     *
     * @param word1 source string
     * @param word2 target string
     * @return minimum edit distance
     */
    public int minDistance(String word1, String word2) {

        int n = word1.length();
        int m = word2.length();

        int[] prev = new int[m + 1];
        int[] curr = new int[m + 1];

        for (int j = 0; j <= m; j++) {
            prev[j] = j;
        }

        for (int i = 1; i <= n; i++) {

            Arrays.fill(curr, 0);

            curr[0] = i;

            for (int j = 1; j <= m; j++) {

                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {

                    curr[j] = prev[j - 1];

                } else {

                    int deleteOperation = curr[j - 1];

                    int insertOperation = prev[j];

                    int replaceOperation = prev[j - 1];

                    curr[j] = 1 + Math.min(
                            replaceOperation,
                            Math.min(deleteOperation, insertOperation));
                }
            }

            int[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[m];
    }
}

// Time Complexity: O(n × m)
// Space Complexity: O(m)