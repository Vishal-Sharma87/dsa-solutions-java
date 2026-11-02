package slidingwindow.longest_subarray;

// Created at: 02-November-2026
// Last revised at: 02-November-2026
// Link: https://leetcode.com/problems/maximum-erasure-value/

import java.util.HashSet;
import java.util.Set;

/*
Problem Description:
--------------------
Statement:
You are given an array of positive integers nums.
Choose a subarray containing only unique elements.
The score of a subarray is the sum of its elements.

Return the maximum possible score.

Example:
Input: nums = [4, 2, 4, 5, 6]
Output: 17

Explanation:
The subarray [2, 4, 5, 6] contains unique elements
and has the maximum sum of 17.

Constraints:
1 <= nums.length <= 100000
1 <= nums[i] <= 10000
*/

/*
Approach 1: Variable Size Sliding Window + HashSet

Idea:
Maintain a sliding window containing only unique elements.

Use a HashSet to track elements currently present
in the window and a running sum to calculate its score.

When a duplicate is encountered:
1. Shrink the window from the left.
2. Remove outgoing elements from the set.
3. Subtract their values from the running sum.
4. Continue until the duplicate is removed.

Once uniqueness is restored, include the current element
and update the maximum sum.

Time Complexity:
O(n) average - Each element enters and leaves the
window at most once.

Space Complexity:
O(n) - HashSet can store up to n unique elements.

Key Insight:
The window always maintains unique elements.
Therefore, every valid window represents a candidate
subarray, and its running sum can be evaluated in O(1).
*/

/*
Method to Solve:
----------------
1. Initialize a HashSet to maintain unique window elements.
2. Maintain left and right pointers and a running window sum.
3. Expand the window using the right pointer.
4. If a duplicate exists, shrink the window from the left
   until the duplicate is removed.
5. Add the current element and update the maximum sum.
6. Return the maximum sum.
*/

public class LC1695MaximumErasureValue {

    /**
     * Finds the maximum sum of a subarray containing unique elements.
     *
     * @param nums input array of positive integers
     * @return maximum possible sum of a unique-element subarray
     */
    public int maximumUniqueSubarray(int[] nums) {
        int length = nums.length;

        Set<Integer> exists = new HashSet<>();

        int left = 0;
        int right = 0;

        int windowSum = 0;
        int maxSum = 0;

        while (right < length) {
            // shrink the window until the duplicate is removed
            while (exists.contains(nums[right])) {
                windowSum -= nums[left];
                exists.remove(nums[left]);

                left++;
            }

            exists.add(nums[right]);
            windowSum += nums[right];

            maxSum = Math.max(maxSum, windowSum);

            right++;
        }

        return maxSum;
    }
}

// Time Complexity: O(n) average
// Space Complexity: O(n)
