package dp.grids;

// Created at: 08-June-2026
// Last revised at: 08-June-2026
// Link: https://leetcode.com/problems/unique-paths/

import java.util.Arrays;

/*
Problem Description:
--------------------
Statement:
    There is a robot on an m x n grid. The robot is initially located at the top-left corner.
    The robot tries to move to the bottom-right corner. The robot can only move either down or right.
    Given m and n, return the number of possible unique paths.

Example:
    Input: m = 3, n = 7
    Output: 28

    Input: m = 3, n = 2
    Output: 3
    Explanation: From top-left, 3 paths:
        1. Right -> Down -> Down
        2. Down -> Down -> Right
        3. Down -> Right -> Down

Constraints:
    1 <= m, n <= 100
*/

/*
Approach 1: Brute Force (Pure Recursion)
-----------------------------------------
Idea:
    At each cell (row, col), the robot could have come from (row-1, col) or (row, col-1).
    Recursively count all paths back to (0, 0).
    Base case: if row == 0 or col == 0, only one path exists (straight line).

Time Complexity:  O(2^(m+n)) — exponential, recomputes subproblems
Space Complexity: O(m+n) — recursion stack depth

Drawbacks:
    Massive overlapping subproblems. Same cells recomputed many times.


Approach 2: Memoization (Top-Down DP)
---------------------------------------
Idea:
    Same recursion as brute force, but cache results in a dp[row][col] table.
    Before computing, check if already solved.

Time Complexity:  O(m * n)
Space Complexity: O(m * n) — dp table + recursion stack

Drawbacks:
    Stack overhead still present. Extra space for recursion.


Approach 3: Tabulation (Bottom-Up DP)
---------------------------------------
Idea:
    Build dp[i][j] iteratively from (0,0) to (m-1, n-1).
    dp[i][j] = dp[i-1][j] + dp[i][j-1]
    Base: first row and first column all set to 1.

Time Complexity:  O(m * n)
Space Complexity: O(m * n)

Drawbacks:
    Full 2D table allocated even though only previous row is needed.


★ Approach 4: Space Optimized (Two Arrays)
--------------------------------------------
Idea:
    At any point, dp[i][j] only depends on dp[i-1][j] (above) and dp[i][j-1] (left).
    Keep only prev row and build curr row on each iteration.
    After each row, prev = curr.

Time Complexity:  O(m * n)
Space Complexity: O(n) — only two 1D arrays
*/

/*
Method to Solve:
----------------
1. Initialize prev[] of size n, all filled with 1 (base case: first row).
2. For each row i from 1 to m-1:
   a. Create curr[], set curr[0] = 1 (base case: first column).
   b. For each col j from 1 to n-1:
      curr[j] = curr[j-1] + prev[j]  (left + above)
   c. prev = curr
3. Return prev[n-1].
*/

public class LC62UniquePaths {

    /**
     * Counts unique paths from top-left to bottom-right in an m x n grid.
     * Robot can only move right or down.
     * Space-optimized using two 1D arrays instead of full 2D table.
     *
     * @param m number of rows
     * @param n number of columns
     * @return total number of unique paths
     */
    // Time Complexity: O(m * n)
    // Space Complexity: O(n)
    public int uniquePaths(int m, int n) {
        int[] prev = new int[n];
        Arrays.fill(prev, 1); // first row — only one way to reach any cell

        for (int i = 1; i < m; i++) {
            int[] curr = new int[n];
            curr[0] = 1; // first column — only one way (straight down)

            for (int j = 1; j < n; j++) {
                curr[j] = curr[j - 1] + prev[j]; // left + above
            }
            prev = curr;
        }

        return prev[n - 1];
    }

    // ─── Archived Approaches (for revision) ───────────────────────────────────

    /**
     * Brute force recursion — exponential, kept for approach comparison.
     *
     * @param row current row index
     * @param col current column index
     * @return number of unique paths to reach (row, col) from (0, 0)
     */
    @SuppressWarnings("unused")
    private int getPathsBrute(int row, int col) {
        if (row == 0 || col == 0)
            return 1;

        return getPathsBrute(row, col - 1) + getPathsBrute(row - 1, col);
    }

    /**
     * Memoized recursion — O(m*n) time with dp cache.
     *
     * @param row current row index
     * @param col current column index
     * @param dp  memoization table
     * @return number of unique paths to reach (row, col)
     */
    @SuppressWarnings("unused")
    private int memoized(int row, int col, Integer[][] dp) {
        if (row == 0 || col == 0)
            return 1;
        if (dp[row][col] != null)
            return dp[row][col];

        return dp[row][col] = memoized(row, col - 1, dp) + memoized(row - 1, col, dp);
    }

    /**
     * Tabulated bottom-up DP — O(m*n) time and space.
     *
     * @param m number of rows
     * @param n number of columns
     * @return number of unique paths
     */
    @SuppressWarnings("unused")
    private int tabulated(int m, int n) {
        Integer[][] dp = new Integer[m][n];

        for (int i = 0; i < m; i++)
            dp[i][0] = 1; // first column

        Arrays.fill(dp[0], 1); // first row

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i][j - 1] + dp[i - 1][j];
            }
        }

        return dp[m - 1][n - 1];
    }
}