package binarysearch;

public class LC540SingleElementInSortedArray {

    // CAN BE MORE READABLE AND SIMPLE CODE

    // Created at:3 march 2026
    // Last revised at: 3 march 2026

    // link
    // :https://leetcode.com/problems/single-element-in-a-sorted-array/description/

    /*
     * Problem Description -> statement, example, constraints
     * 540. Single Element in a Sorted Array
     * Medium
     * You are given a sorted array consisting of only integers where every element
     * appears exactly twice, except for one element which appears exactly once.
     * 
     * Return the single element that appears only once.
     * 
     * Your solution must run in O(log n) time and O(1) space.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: nums = [1,1,2,3,3,4,4,8,8]
     * Output: 2
     * Example 2:
     * 
     * Input: nums = [3,3,7,7,10,11,11]
     * Output: 10
     * 
     * 
     * Constraints:
     * 
     * 1 <= nums.length <= 105
     * 0 <= nums[i] <= 105
     * 
     */

    // Method to solve the Problem
    public int singleNonDuplicate(int[] nums) {

        /*
         * APPROCH
         * single element will always occurs at even index,
         * before single element every pair starts at even then odd
         * after single element every pair starts at odd then even
         */

        int low = 0, mid, high = nums.length - 1;

        while (low < high) {

            mid = low + (high - low) / 2;
            if (mid % 2 == 1) {
                // mid is at odd index
                if (nums[mid] != nums[mid - 1]) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            } else {
                // mid is at even index (mid can be single element)
                if (nums[mid] != nums[mid - 1]) {
                    // discard left part
                    low = mid;
                } else {
                    high = mid - 2;
                }
            }

        }
        return nums[low];
    }

    // Time Complexity: O(logN)
    // SPace Complexity: constant
}
