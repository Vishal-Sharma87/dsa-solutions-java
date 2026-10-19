package arrays.traversal;

// Created at: 19-October-2026
// Last revised at: 19-October-2026
// Link: https://leetcode.com/problems/find-target-indices-after-sorting-array/

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
Problem Description:
--------------------
Statement:
Given an integer array nums and an integer target, return a list
of indices where target appears in nums after sorting the array
in non-decreasing order.

The returned indices must be sorted in ascending order.

Example:
Input: nums = [1,2,5,2,3], target = 2
Output: [1,2]

Input: nums = [1,2,5,2,3], target = 3
Output: [3]

Input: nums = [1,2,5,2,3], target = 5
Output: [4]

Constraints:
- 1 <= nums.length <= 100
- 1 <= nums[i], target <= 100
*/

/*
Approach 1: Sorting and Traversal

Idea:
1. Sort the array in non-decreasing order.
2. Traverse the sorted array.
3. Add every index where nums[index] equals target.
4. Return the collected indices.

Time Complexity:
O(n log n)

Space Complexity:
O(n) auxiliary space in the general case for sorting,
depending on the sorting implementation.

Drawbacks:
Sorting is unnecessary because the required indices can be
calculated directly from element frequencies.
*/

/*
Approach 2: Counting and Index Generation

Idea:
1. Count elements smaller than target.
2. Count elements equal to target.
3. The first target index equals the count of smaller elements.
4. Generate consecutive indices for all target occurrences.
5. Return the resulting list.

Time Complexity:
O(n + k), where n is the array length and k is the
number of occurrences of target.

Space Complexity:
O(1) auxiliary space, excluding the output list.
The output list requires O(k) space.

Key Insight:
After sorting, all elements smaller than target appear first.
Therefore, the number of smaller elements determines the first
target index. Since equal elements are adjacent, all remaining
target indices are consecutive.

This approach avoids sorting and is optimal because every
input element must be examined and every result index must
be generated.
*/

/*
Method to Solve:
----------------
1. Traverse nums and count elements smaller than target.
2. During the same traversal, count occurrences of target.
3. Return an empty list if target does not exist.
4. Generate indices from smallerThanTargetCount to
   smallerThanTargetCount + equalToTargetCount - 1.
5. Return the generated indices.
*/

class LC2089FindTargetIndicesAfterSortingArray {

    /**
     * Finds the indices of target after sorting nums.
     *
     * @param nums   the input integer array
     * @param target the value whose sorted indices are required
     * @return a list of target indices in ascending order
     */
    public List<Integer> targetIndices(int[] nums, int target) {
        int smallerThanTargetCount = 0;
        int equalToTargetCount = 0;

        for (int num : nums) {
            if (num < target) {
                smallerThanTargetCount++;
            } else if (num == target) {
                equalToTargetCount++;
            }
        }

        if (equalToTargetCount == 0) {
            return Collections.emptyList();
        }

        List<Integer> targetIndices = new ArrayList<>();

        for (int index = smallerThanTargetCount; index < smallerThanTargetCount + equalToTargetCount; index++) {
            targetIndices.add(index);
        }

        return targetIndices;
    }
}

// Time Complexity: O(n + k)
// Space Complexity: O(1) auxiliary, excluding the O(k) output list