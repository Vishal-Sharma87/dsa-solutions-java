package binarysearch;

public class LC162PeakElementInSortedArray {

    // Created at: 3 march 2026
    // Last revised at: 3 march 2026

    // link :https://leetcode.com/problems/find-peak-element/description/

    /*
     * Problem Description -> statement, example, constraints
     * 162. Find Peak Element
     * Medium
     * 
     * A peak element is an element that is strictly greater than its neighbors.
     * 
     * Given a 0-indexed integer array nums, find a peak element, and return its
     * index. If the array contains multiple peaks, return the index to any of the
     * peaks.
     * 
     * You may imagine that nums[-1] = nums[n] = -∞. In other words, an element is
     * always considered to be strictly greater than a neighbor that is outside the
     * array.
     * 
     * You must write an algorithm that runs in O(log n) time.
     * 
     * 
     * 
     * Example 1:
     * 
     * Input: nums = [1,2,3,1]
     * Output: 2
     * Explanation: 3 is a peak element and your function should return the index
     * number 2.
     * Example 2:
     * 
     * Input: nums = [1,2,1,3,5,6,4]
     * Output: 5
     * Explanation: Your function can return either index number 1 where the peak
     * element is 2, or index number 5 where the peak element is 6.
     * 
     * 
     * Constraints:
     * 
     * 1 <= nums.length <= 1000
     * -231 <= nums[i] <= 231 - 1
     * nums[i] != nums[i + 1] for all valid i.
     * 
     */

    // Method to solve the Problem
    public int findPeakElement(int[] nums) {

        /*
         * The problem gurantees atleast one peak
         * the approach is simple check the slope
         * (gurantees no two consecutive elements are same)
         * 
         * 
         * case 1: mid is a peak i.e., 1,5,3
         * 5 is mid and is peak
         * return
         * mid index
         * 
         * case 2: incresing slope i.e., 1,2,3
         * a peak must be in right portion of the arrray
         * 
         * case 3/ default case: decresing slope or dip of values
         * i.e., 5,4,3 or 5,4,9
         * 
         * must be a peak at left of the portion
         * NOTE: the problem demands either of the peak index not the greatest(in terms
         * of index or value) of the peak index
         */
        int low = 0, mid, high = nums.length - 1;

        while (low <= high) {

            mid = low + (high - low) / 2;

            // peak check
            boolean peak = true;
            if (mid - 1 >= low && nums[mid] < nums[mid - 1])
                peak = false;
            if (mid + 1 <= high && nums[mid] < nums[mid + 1])
                peak = false;
            if (peak)
                return mid;

            if (mid - 1 >= low && nums[mid - 1] > nums[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    // Time Complexity: O(logN)
    // SPace Complexity: constant
}
