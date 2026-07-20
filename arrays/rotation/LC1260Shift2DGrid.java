package arrays.rotation;

// Created at: 21-July-2026
// Last revised at: 21-July-2026
// Link: https://leetcode.com/problems/shift-2d-grid/

import java.util.ArrayList;
import java.util.List;

/*
Problem Description:
--------------------
Statement:
Given an m x n grid and an integer k, shift the grid k times.

In one shift operation:
1. Element at grid[i][j] moves to grid[i][j + 1].
2. Element at the end of a row moves to the beginning of the next row.
3. The last element moves to grid[0][0].

Return the shifted grid.

Example:
Input:
grid = [[1,2,3],[4,5,6],[7,8,9]], k = 1

Output:
[[9,1,2],[3,4,5],[6,7,8]]

Constraints:
- 1 <= m, n <= 50
- 1 <= m * n <= 2500
- 0 <= k <= 100
*/

/*
Approach 1: Simulate Every Shift

Idea:
Perform one shift operation exactly k times.

Time Complexity:
O(k × m × n)

Space Complexity:
O(1)

Drawbacks:
Very inefficient when k is large.
*/

/*
Approach 2: Reverse Technique (Optimal)

Idea:
Treat the matrix as a flattened array.
A right rotation by k positions can be achieved using:
1. Reverse the first part.
2. Reverse the second part.
3. Reverse the entire array.

While reversing, convert every flattened index back into
(row, column) coordinates.

Time Complexity:
O(m × n)

Space Complexity:
O(1)

Key Insight:
The matrix never needs to be flattened into another array.
Index conversion is enough to perform all swaps in-place.
*/

/*
Method to Solve:
----------------
1. Compute total number of elements.
2. Reduce k using modulo.
3. Reverse the left segment.
4. Reverse the right segment.
5. Reverse the complete grid.
6. Convert the grid into the required List<List<Integer>> format.
*/

public class LC1260Shift2DGrid {

    /**
     * Reverses elements between two flattened indices.
     *
     * @param grid  input grid
     * @param start starting flattened index
     * @param end   ending flattened index
     * @param cols  number of columns
     * @return void
     */
    private void reverse(int[][] grid, int start, int end, int cols) {
        while (start < end) {

            int startRow = start / cols;
            int startCol = start % cols;

            int endRow = end / cols;
            int endCol = end % cols;

            // swap elements
            int temp = grid[startRow][startCol];
            grid[startRow][startCol] = grid[endRow][endCol];
            grid[endRow][endCol] = temp;

            start++;
            end--;
        }
    }

    /**
     * Shifts the grid to the right by k positions.
     *
     * @param grid input matrix
     * @param k    number of shifts
     * @return shifted grid as a list
     */
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {

        int rows = grid.length;
        int cols = grid[0].length;

        int total = rows * cols;
        k %= total;

        int mid = total - k - 1;

        // rotate using three reversals
        reverse(grid, 0, mid, cols);
        reverse(grid, mid + 1, total - 1, cols);
        reverse(grid, 0, total - 1, cols);

        List<List<Integer>> answer = new ArrayList<>();

        for (int[] row : grid) {

            List<Integer> current = new ArrayList<>();

            for (int value : row) {
                current.add(value);
            }

            answer.add(current);
        }

        return answer;
    }
}

// Time Complexity: O(m × n)
// Space Complexity: O(1) (excluding output list)