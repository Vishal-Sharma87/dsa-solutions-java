
package recursion.hard;

// Created at: 05-May-2025
// Last revised at: 05-May-2025
// https://leetcode.com/problems/n-queens/

/*
 * Problem Description:
 * Place n queens on an n×n chessboard such that no two queens attack each other.
 * Return all distinct solutions. Each solution is a board representation where
 * 'Q' = queen and '.' = empty cell.
 *
 * Example:
 *   Input: n = 4
 *   Output: [[".Q..","...Q","Q...","..Q."],["..Q.","Q...","...Q",".Q.."]]
 *
 * Constraints:
 *   1 <= n <= 9
 */

/*
 * Approaches:
 *
 * Approach 1: Backtracking with column map + diagonal check
 *   Idea: Place queens row by row. For each row, try every column.
 *         Track occupied columns via HashMap. Check left and right diagonals
 *         explicitly by scanning upward from the current row in canPlace().
 *   Time:  O(n!) — at each row we try up to n columns, pruned by constraints
 *   Space: O(n²) — curr holds n strings of length n
 *   Key Insight: Diagonal conflicts are caught by walking up-left and up-right
 *                from the candidate cell. No need for extra diagonal sets.
 */

/*
 * Method to solve (Approach 1):
 *   1. Call function(0, n, ans, curr, map) — queenPlaced starts at 0.
 *   2. Base case: queenPlaced == n → board is complete, snapshot curr into ans.
 *   3. For each column i in [0, n):
 *      a. Skip if column i is already used (map.containsKey(i)).
 *      b. Run canPlace(i, curr, n) — checks left and right diagonals.
 *      c. If safe: mark column, add row string, recurse, then undo all changes.
 *   4. canPlace scans upward from row-1 in both diagonal directions.
 */

// Time Complexity:  O(n!)
// Space Complexity: O(n²)

import java.util.*;

public class LC51NQueens {

    /**
     * Checks if placing a queen at the given column in the next row is safe.
     * Only diagonals need checking — columns are tracked via the map.
     *
     * @param col  candidate column for the new queen
     * @param curr rows placed so far, each as a string
     * @param n    board size
     * @return true if no diagonal conflict exists
     */
    private boolean canPlace(int col, List<String> curr, int n) {
        int row = curr.size();

        // left diagonal (up-left)
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (curr.get(i).charAt(j) == 'Q')
                return false;
        }

        // right diagonal (up-right)
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (curr.get(i).charAt(j) == 'Q')
                return false;
        }

        return true;
    }

    /**
     * Backtracking helper — places queens one row at a time.
     *
     * @param queenPlaced number of queens successfully placed so far
     * @param n           board size
     * @param ans         accumulator for complete board solutions
     * @param curr        rows placed so far
     * @param map         columns already occupied by a queen
     */
    private void function(int queenPlaced, int n, List<List<String>> ans,
            List<String> curr, HashMap<Integer, Boolean> map) {
        if (queenPlaced == n) {
            ans.add(new ArrayList<>(curr));
            return;
        }

        StringBuilder stripe = new StringBuilder(".".repeat(n));
        for (int i = 0; i < n; i++) {
            if (!map.containsKey(i) && canPlace(i, curr, n)) {
                stripe.setCharAt(i, 'Q');
                map.put(i, true);
                curr.add(stripe.toString());

                function(queenPlaced + 1, n, ans, curr, map);

                // undo
                curr.remove(curr.size() - 1);
                stripe.setCharAt(i, '.');
                map.remove(i);
            }
        }
    }

    /**
     * Returns all distinct N-Queens solutions for an n×n board.
     *
     * @param n board size and number of queens to place
     * @return list of solutions, each solution being a list of n strings
     */
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>();
        if (n != 0) {
            function(0, n, ans, new ArrayList<>(), new HashMap<>());
        }
        return ans;
    }
}