package dp.grids;

// Created at: 30-September-2026
// Last revised at: 30-September-2026
// Link: https://leetcode.com/problems/check-if-there-is-a-valid-parentheses-string-path/

/*
Problem Description:
--------------------
Statement:
Given an m x n grid of '(' and ')', a path from (0, 0) to (m - 1, n - 1) is valid if:
  - it moves only down or right
  - the string formed by the characters along the path is a valid parentheses string
Return true if such a path exists, otherwise false.

Example:
Input: grid = [["(","(","("],[")","(",")"],["(","(",")"],["(","(",")"]]
Output: true

Input: grid = [[")",")"],["(","("]]
Output: false

Constraints:
m == grid.length
n == grid[i].length
1 <= m, n <= 100
grid[i][j] is either '(' or ')'
*/

/*
Approach 1: Brute Force DFS
Idea:
Try every right/down path and check the bracket balance along the way.

Time Complexity:
O(2^(m + n))

Space Complexity:
O(m + n) recursion stack

Drawbacks:
Same (cell, balance) states get recomputed again and again. TLE.

Approach 2: Memoized DFS with dp[i][j][balance], balance bound = m * n
Idea:
Same cell can be reached with different open counts and their futures differ,
so state = (i, j, openCount). Cache the result per state.

Time Complexity:
O(m * n * m * n)

Space Complexity:
O(m * n * m * n)

Drawbacks:
Third dimension is sized for the worst case (m * n), ~10^8 slots for 100x100.
Gives MLE even though most of those states can never be reached.

Approach 3: Memoized DFS with bounded balance (Optimal)
Idea:
At cell (i, j) only i + j characters are consumed, so openCount <= i + j <= m + n.
Size the third dimension by reachable states, not by the total cell count.

Time Complexity:
O(m * n * (m + n))

Space Complexity:
O(m * n * (m + n))

Key Insight:
Bound the DP dimension by what is actually reachable. This cuts ~10^8 slots to ~2 * 10^6.
*/

/*
Method to Solve:
----------------
1. Reject early if start is not '(' or end is not ')', or the grid is a single cell.
2. State is (i, j, openCount), where openCount is the balance before processing grid[i][j].
3. Update the balance using the current cell: '(' adds one, ')' subtracts one.
4. Prune when the balance goes negative or the cell is out of bounds.
5. At the last cell the balance must be exactly 1, the last ')' closes it to 0.
6. Try down first, then right; cache the result in dp[i][j][openCount].
7. Size the balance dimension as m + n, since openCount can never exceed i + j.
*/
class LC2267CheckIfThereIsAValidParenthesesStringPath {

    /**
     * Checks whether the given cell lies outside the grid.
     *
     * @param row       row index
     * @param col       column index
     * @param totalRows number of rows in the grid
     * @param totalCols number of columns in the grid
     * @return true if the cell is out of bounds
     */
    boolean outOfBound(int row, int col, int totalRows, int totalCols) {
        return row < 0 || row >= totalRows || col < 0 || col >= totalCols;
    }

    /**
     * Checks if a valid path exists from the current cell to the bottom-right cell.
     *
     * @param i         current row
     * @param j         current column
     * @param openCount unmatched '(' before processing grid[i][j]
     * @param grid      input bracket grid
     * @param dp        memo table indexed by (row, col, openCount)
     * @return true if a valid path exists from this state
     */
    boolean isPathExists(int i, int j, int openCount, char[][] grid, Boolean[][][] dp) {
        if (outOfBound(i, j, grid.length, grid[0].length) || openCount < 0)
            return false;

        if (i == grid.length - 1 && j == grid[0].length - 1) {
            // last ')' must close exactly one open bracket
            return openCount == 1;
        }

        if (dp[i][j][openCount] != null)
            return dp[i][j][openCount];

        int nextCount = grid[i][j] == '(' ? openCount + 1 : openCount - 1;

        if (isPathExists(i + 1, j, nextCount, grid, dp))
            return dp[i][j][openCount] = true;

        return dp[i][j][openCount] = isPathExists(i, j + 1, nextCount, grid, dp);
    }

    /**
     * Determines whether a valid parentheses string path exists in the grid.
     *
     * @param grid grid of '(' and ')'
     * @return true if a valid path exists from top-left to bottom-right
     */
    public boolean hasValidPath(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;

        if (rows <= 1 && cols <= 1)
            return false;
        if (grid[0][0] != '(' || grid[rows - 1][cols - 1] != ')')
            return false;

        // openCount <= i + j <= rows + cols
        int maximumOpenCount = rows + cols;
        Boolean[][][] dp = new Boolean[rows + 1][cols + 1][maximumOpenCount + 1];

        return isPathExists(0, 0, 0, grid, dp);
    }

    // Time Complexity: O(m * n * (m + n))
    // Space Complexity: O(m * n * (m + n))
}