package dp.grids;

// Created at: 08-June-2026

// Last revised at: 08-June-2026
// Link: https://leetcode.com/problems/unique-paths-ii/

/*
Problem Description:
--------------------
Statement:
    You are given an m x n integer array grid. There is a robot initially located at the
    top-left corner. The robot tries to move to the bottom-right corner. The robot can only
    move either down or right at any point in time.
    An obstacle and space are marked as 1 or 0 respectively in grid.
    A path that the robot takes cannot include any square that is an obstacle.
    Return the number of possible unique paths that the robot can take to reach the bottom-right corner.

Example:
    Input: obstacleGrid = [[0,0,0],[0,1,0],[0,0,0]]
    Output: 2
    Explanation: There is one obstacle in the middle of the 3x3 grid.
    There are two ways to reach the bottom-right corner:
        1. Right -> Right -> Down -> Down
        2. Down -> Down -> Right -> Right

    Input: obstacleGrid = [[0,1],[0,0]]
    Output: 1

Constraints:
    m == obstacleGrid.length
    n == obstacleGrid[0].length
    1 <= m, n <= 100
    obstacleGrid[i][j] is 0 or 1
*/

/*
Important Note:
---------------
This solution is a direct extension of LC62 Unique Paths (space-optimized version).
The full approach progression (Recursion → Memoization → Tabulation → Space Optimization)
applies here as well. Only the space-optimized version is implemented below since
the core DP structure is identical — obstacle cells simply contribute 0 paths.


Approach 1: Brute Force (Pure Recursion)
-----------------------------------------
Idea:
    Same as LC62 but return 0 if current cell has an obstacle.
    Recurse from (m-1, n-1) back to (0, 0).

Time Complexity:  O(2^(m+n))
Space Complexity: O(m+n) — recursion stack

Drawbacks:
    Exponential recomputation of overlapping subproblems.


Approach 2: Memoization (Top-Down DP)
---------------------------------------
Idea:
    Cache results of recursive calls in dp[row][col].
    Return 0 immediately if obstacle found at current cell.

Time Complexity:  O(m * n)
Space Complexity: O(m * n) — dp table + recursion stack

Drawbacks:
    Recursion stack overhead still present.


Approach 3: Tabulation (Bottom-Up DP)
---------------------------------------
Idea:
    Build dp[i][j] iteratively.
    Base: first row cells get 1 until an obstacle is hit (break after).
          first column cells get 1 until an obstacle is hit (break after).
    Transition: dp[i][j] = dp[i-1][j] + dp[i][j-1] if no obstacle, else 0.

Time Complexity:  O(m * n)
Space Complexity: O(m * n)

Drawbacks:
    Full 2D table allocated even though only previous row is needed.


★ Approach 4: Space Optimized (Two Arrays)
--------------------------------------------
Idea:
    Extend LC62's space optimization to handle obstacles.
    prev[] represents unique paths for the previous row.
    For each new row, build curr[] using prev[].
    If a cell has an obstacle, its path count stays 0.
    First row init: mark 1s until an obstacle is encountered, then break.
    First column per row: only 1 path if prev[0] == 1 and no obstacle at curr[0].

Time Complexity:  O(m * n)
Space Complexity: O(n)
*/

/*
Method to Solve:
----------------
1. Initialize prev[] of size n.
   Fill with 1 from left until obstacle hit, then break (first row base case).
2. For each row i from 1 to m-1:
   a. Create curr[], initialize to 0.
   b. curr[0] = 1 only if prev[0] == 1 and no obstacle at (i, 0).
   c. For each col j from 1 to n-1:
      if no obstacle at (i, j): curr[j] = curr[j-1] + prev[j]
      else: curr[j] stays 0
   d. prev = curr
3. Return prev[n-1].
*/

public class LC63UniquePathsII {

    /**
     * Counts unique paths from top-left to bottom-right in an m x n grid with
     * obstacles.
     * Cells marked 1 are obstacles; robot can only move right or down.
     * Space-optimized extension of LC62 using two 1D arrays.
     *
     * @param obstacleGrid m x n grid where 1 = obstacle, 0 = free cell
     * @return total number of unique paths avoiding obstacles
     */
    // Time Complexity: O(m * n)
    // Space Complexity: O(n)
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;

        // first row — mark 1 until obstacle blocks, break after
        int[] prev = new int[n];
        for (int i = 0; i < n; i++) {
            if (obstacleGrid[0][i] == 0)
                prev[i] = 1;
            else
                break;
        }

        for (int i = 1; i < m; i++) {
            int[] curr = new int[n];

            // first column reachable only if previous row's first col was reachable
            if (prev[0] == 1 && obstacleGrid[i][0] == 0)
                curr[0] = 1;

            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == 0)
                    curr[j] = curr[j - 1] + prev[j]; // left + above
                // obstacle — curr[j] stays 0
            }

            prev = curr;
        }

        return prev[n - 1];
    }
}