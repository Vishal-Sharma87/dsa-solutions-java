package binarysearch.on2DMatrix;

public class LC74SortedMatrixSearch {

    // Created at:9 march 2026
    // Last revised at:9 march 2026

    // link :https://leetcode.com/problems/search-a-2d-matrix/

    /*
     * Problem Description -> statement, example, constraints
     * 74. Search a 2D Matrix
     * Medium
     * 
     * You are given an m x n integer matrix matrix with the following two
     * properties:
     * 
     * Each row is sorted in non-decreasing order.
     * The first integer of each row is greater than the last integer of the
     * previous row.
     * 
     * Given an integer target, return true if target is in matrix or false
     * otherwise.
     * 
     * You must write a solution in O(log(m * n)) time complexity.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 3
     * Output: true
     * 
     * Example 2:
     * 
     * Input: matrix = [[1,3,5,7],[10,11,16,20],[23,30,34,60]], target = 13
     * Output: false
     * 
     * 
     * 
     * Constraints:
     * 
     * m == matrix.length
     * n == matrix[i].length
     * 1 <= m, n <= 100
     * -104 <= matrix[i][j], target <= 104
     * 
     * 
     */

    // Method to solve the Problem
    public boolean searchMatrix(int[][] matrix, int target) {

        /*
         * APPROCH
         * we can map each cell of the matrix to a number from
         * 0(first value) as low, to rows*cols -1 (last value) as high
         * 
         * and caluclate mid as usual
         * 
         * cell value -> current row, current col calcalation
         * 
         * row = mid / columns
         * col = mid % columns
         * 
         * and then typical binary search
         */

        int rows = matrix.length;
        if (rows <= 0)
            return false;

        int cols = matrix[0].length;

        int low = 0, high = rows * cols - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            // calculate row and column of current cell corroseponding to cell value(0-based
            // indexing)
            int r = mid / cols;
            int c = mid % cols;

            int val = matrix[r][c];

            if (val == target)
                return true;

            if (val > target) {
                // target is smaller then the current cell value-> search in right
                high = mid - 1;
            } else {
                // target is greater then the current cell value-> search in left
                low = mid + 1;
            }
        }

        return false;

    }

    // Time Complexity: O(log (rows * cols))
    // Space Complexity: constant

}
