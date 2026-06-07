package dp.grids;

// Created at: 08-June-2026
// Last revised at: 08-June-2026
// Link: https://leetcode.com/problems/minimum-path-sum/

/*
Problem Description:
--------------------
Statement:
    Given an m x n grid filled with non-negative numbers, find a path from the
    top-left to the bottom-right corner which minimizes the sum of all numbers
    along its path. You can only move either down or right at any point in time.

Example:
    Input: grid = [[1,3,1],[1,5,1],[4,2,1]]
    Output: 7
    Explanation: Path 1→3→1→1→1 minimizes the sum.

    Input: grid = [[1,2,3],[4,5,6]]
    Output: 12

Constraints:
    m == grid.length
    n == grid[0].length
    1 <= m, n <= 200
    0 <= grid[i][j] <= 200
*/

/*
Approach 1: Brute Force (Pure Recursion)
-----------------------------------------
Idea:
    At each cell (i, j), the robot could have come from (i-1, j) or (i, j-1).
    Recurse back to (0, 0), taking the minimum of both paths at each step.
    Base: return grid[0][0] when at origin.
    Out of bounds: return Integer.MAX_VALUE as sentinel (never added to avoid overflow).

Time Complexity:  O(2^(m+n)) — exponential overlapping recomputation
Space Complexity: O(m+n) — recursion stack depth

Drawbacks:
    Massive recomputation of overlapping subproblems.
    Integer.MAX_VALUE sentinel must never be added to avoid overflow.


Approach 2: Memoization (Top-Down DP)
---------------------------------------
Idea:
    Same recursion with Long[][] dp cache.
    Before computing, check if dp[i][j] is already solved.
    Long used to safely handle sentinel comparisons.

Time Complexity:  O(m * n)
Space Complexity: O(m * n) — dp table + recursion stack

Drawbacks:
    Recursion stack overhead. Full table allocated.


Approach 3: Tabulation (Bottom-Up DP)
---------------------------------------
Idea:
    dp[i][j] = minimum path sum to reach (i, j).
    Base: first row and first column are prefix sums (only one direction reachable).
    Transition: dp[i][j] = min(dp[i-1][j], dp[i][j-1]) + grid[i][j]

Time Complexity:  O(m * n)
Space Complexity: O(m * n)

Drawbacks:
    Full 2D table allocated even though only previous row is needed.


★ Approach 4: Space Optimized (Two Arrays)
--------------------------------------------
Idea:
    prev[] stores minimum path sums of the previous row.
    First row init: running prefix sum directly into prev[].
    For each new row, build curr[] where:
      curr[0] = prev[0] + grid[i][0]  (first column — only one way down)
      curr[j] = min(curr[j-1], prev[j]) + grid[i][j]
    After each row, prev = curr.

Time Complexity:  O(m * n)
Space Complexity: O(n)
*/

/*
Method to Solve:
----------------
1. Initialize prev[] with prefix sums of first row (only rightward path exists).
2. For each row i from 1 to m-1:
   a. curr[0] = prev[0] + grid[i][0]  (accumulate first column downward)
   b. For each col j from 1 to n-1:
      curr[j] = min(curr[j-1], prev[j]) + grid[i][j]
   c. prev = curr
3. Return prev[n-1].
*/

public class LC64MinimumPathSum {

    /**
     * Finds the minimum path sum from top-left to bottom-right in a grid.
     * Movement restricted to right or down only.
     * Space-optimized using two 1D arrays instead of full 2D table.
     *
     * @param grid m x n grid of non-negative integers
     * @return minimum sum along any valid path to bottom-right
     */
    // Time Complexity: O(m * n)
    // Space Complexity: O(n)
    public int minPathSum(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        // prefix sum for first row — only one path (all right)
        int[] prev = new int[n];
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += grid[0][i];
            prev[i] = sum;
        }

        for (int i = 1; i < m; i++) {
            int[] curr = new int[n];
            curr[0] = prev[0] + grid[i][0]; // accumulate down the first column

            for (int j = 1; j < n; j++) {
                curr[j] = Math.min(curr[j - 1], prev[j]) + grid[i][j]; // min(left, above) + cell
            }

            prev = curr;
        }

        return prev[n - 1];
    }

    // ─── Archived Approaches (for revision) ───────────────────────────────────

    /**
     * Brute force recursion — exponential, kept for approach comparison.
     * Returns Integer.MAX_VALUE for out-of-bounds as sentinel (never added to avoid
     * overflow).
     *
     * @param grid m x n grid
     * @param i    current row index
     * @param j    current column index
     * @return minimum path sum to reach (i, j) from (0, 0)
     */
    @SuppressWarnings("unused")
    private long minSumBrute(int[][] grid, int i, int j) {
        if (i < 0 || j < 0)
            return Integer.MAX_VALUE; // sentinel — out of bounds, never added
        if (i == 0 && j == 0)
            return grid[0][0];

        return Math.min(minSumBrute(grid, i - 1, j), minSumBrute(grid, i, j - 1)) + grid[i][j];
    }

    /**
     * Memoized recursion — O(m*n) time with Long dp cache.
     *
     * @param grid m x n grid
     * @param i    current row index
     * @param j    current column index
     * @param dp   memoization table
     * @return minimum path sum to reach (i, j)
     */
    @SuppressWarnings("unused")
    private long memoized(int[][] grid, int i, int j, Long[][] dp) {
        if (i < 0 || j < 0)
            return Integer.MAX_VALUE;
        if (i == 0 && j == 0)
            return grid[0][0];

        if (dp[i][j] != null)
            return dp[i][j];

        return dp[i][j] = Math.min(memoized(grid, i - 1, j, dp), memoized(grid, i, j - 1, dp)) + grid[i][j];
    }

    /**
     * Tabulated bottom-up DP — O(m*n) time and space.
     *
     * @param grid m x n grid
     * @return minimum path sum to bottom-right
     */
    @SuppressWarnings("unused")
    private int tabulated(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;

        int[][] dp = new int[m][n];

        // first row prefix sums
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += grid[0][i];
            dp[0][i] = sum;
        }

        // first column prefix sums
        sum = 0;
        for (int i = 0; i < m; i++) {
            sum += grid[i][0];
            dp[i][0] = sum;
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = Math.min(dp[i][j - 1], dp[i - 1][j]) + grid[i][j];
            }
        }

        return dp[m - 1][n - 1];
    }
}
