package dp.grids;

// Created at: 08-June-2026
// Last revised at: 08-June-2026
// Link: https://leetcode.com/problems/triangle/

import java.util.List;

/*
Problem Description:
--------------------
Statement:
    Given a triangle array, return the minimum path sum from top to bottom.
    For each step, you may move to an adjacent number of the row below.
    More formally, if you are on index j in row i, you may move to index j or j+1 in row i+1.

Example:
    Input: triangle = [[2],[3,4],[6,5,7],[4,1,8,3]]
    Output: 11
    Explanation: The minimum path sum from top to bottom is 2 + 3 + 5 + 1 = 11.

    Input: triangle = [[-10]]
    Output: -10

Constraints:
    1 <= triangle.size() <= 200
    triangle[0].size() == 1
    triangle[i].size() == triangle[i-1].size() + 1
    -10^4 <= triangle[i][j] <= 10^4
*/

/*
Approach 1: Brute Force (Pure Recursion)
-----------------------------------------
Idea:
    Recurse from (0, 0) downward. At each cell (i, j), recurse into (i+1, j) and (i+1, j+1).
    Base case: last row — return the cell value directly.
    Take minimum of both recursive paths and add current cell value.

Time Complexity:  O(2^n) — exponential overlapping recomputation
Space Complexity: O(n) — recursion stack depth (n = number of rows)

Drawbacks:
    Massive recomputation of overlapping subproblems.


Approach 2: Memoization (Top-Down DP)
---------------------------------------
Idea:
    Same recursion as brute force with Integer[][] dp cache.
    dp[i][j] stores minimum path sum from (i, j) to the last row.
    Check cache before computing.

Time Complexity:  O(n^2) — each cell computed once
Space Complexity: O(n^2) — dp table + recursion stack

Drawbacks:
    Recursion stack overhead. Full n×n table allocated.


Approach 3: Tabulation (Bottom-Up DP)
---------------------------------------
Idea:
    Start from the last row as base case (no out-of-bounds handling needed).
    For each row above, dp[i][j] = min(dp[i+1][j], dp[i+1][j+1]) + triangle[i][j].
    Answer is dp[0][0].
    Going bottom-up avoids out-of-bounds edge cases and removes the need
    for per-cell base case calls at the last row.

Time Complexity:  O(n^2)
Space Complexity: O(n^2)

Drawbacks:
    Full n×n dp table allocated even though only next row is needed.


★ Approach 4: Space Optimized (Two Arrays)
--------------------------------------------
Idea:
    prev[] represents minimum path sums from row i+1 down to the last row.
    Initialize prev[] with last row values.
    For each row above (rows-2 down to 0):
      curr[] is sized i+1 (tight allocation — triangle row i has exactly i+1 elements).
      curr[j] = min(prev[j], prev[j+1]) + triangle[i][j]
    After each iteration, prev = curr.
    Answer is prev[0].

Time Complexity:  O(n^2)
Space Complexity: O(n) — two 1D arrays, curr sized tightly per row
*/

/*
Method to Solve:
----------------
1. Initialize prev[] with all values from the last row of the triangle.
2. For each row i from rows-2 down to 0:
   a. Allocate curr[] of size i+1 (exact row size, no wasted space).
   b. For each j from 0 to i:
      curr[j] = min(prev[j], prev[j+1]) + triangle[i][j]
   c. prev = curr
3. Return prev[0].
*/

public class LC120Triangle {

    /**
     * Finds the minimum path sum from the top to the bottom of a triangle.
     * At each step, moves to adjacent index j or j+1 in the next row.
     * Iterates bottom-up so base case is the last row — no out-of-bounds handling
     * needed.
     *
     * @param triangle list of rows forming the triangle
     * @return minimum path sum from top to bottom
     */
    // Time Complexity: O(n^2)
    // Space Complexity: O(n)
    public int minimumTotal(List<List<Integer>> triangle) {
        int rows = triangle.size();

        // base case — start from last row
        int[] prev = new int[rows];
        for (int i = 0; i < rows; i++) {
            prev[i] = triangle.get(rows - 1).get(i);
        }

        for (int i = rows - 2; i >= 0; i--) {
            int[] curr = new int[i + 1]; // tight allocation — row i has exactly i+1 elements

            for (int j = 0; j <= i; j++) {
                curr[j] = Math.min(prev[j], prev[j + 1]) + triangle.get(i).get(j); // min of two adjacent below
            }

            prev = curr;
        }

        return prev[0]; // minimum path sum from top to bottom
    }

    // ─── Archived Approaches (for revision) ───────────────────────────────────

    /**
     * Tabulated bottom-up DP — O(n^2) time and space.
     *
     * @param triangle list of rows forming the triangle
     * @return minimum path sum from top to bottom
     */
    @SuppressWarnings("unused")
    private int tabulated(List<List<Integer>> triangle) {
        int rows = triangle.size();
        int[][] dp = new int[rows][rows];

        // base case — last row
        for (int i = 0; i < rows; i++) {
            dp[rows - 1][i] = triangle.get(rows - 1).get(i);
        }

        for (int i = rows - 2; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                dp[i][j] = Math.min(dp[i + 1][j], dp[i + 1][j + 1]) + triangle.get(i).get(j);
            }
        }

        return dp[0][0];
    }
}