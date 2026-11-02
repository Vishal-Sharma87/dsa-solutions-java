package slidingwindow.longest_subarray;

// Created at: 02-November-2026
// Last revised at: 02-November-2026
// Link: https://leetcode.com/problems/frequency-of-the-most-frequent-element/

import java.util.Arrays;

/*
Problem Description:
--------------------
Statement:
The frequency of an element is the number of times it occurs
in an array.

You are given an integer array nums and an integer k.
In one operation, you can choose an index and increment
the element at that index by 1.

Return the maximum possible frequency of an element after
performing at most k operations.

Example:
Input: nums = [1, 2, 4], k = 5
Output: 3

Explanation:
Increment 1 two times and 2 two times.
The resulting array is [3, 4, 4].
Alternatively, increment 1 three times and 2 two times
to obtain [4, 4, 4].

Constraints:
1 <= nums.length <= 100000
1 <= nums[i] <= 100000
0 <= k <= 100000
*/

/*
Approach 1: Brute Force + Sorting

Idea:
Sort the array so that we can only consider increasing
smaller elements to match a larger element.

For every right endpoint, examine possible left endpoints
and calculate the operations required to make all elements
in that window equal to nums[right].

Keep track of the maximum valid window.

Time Complexity:
O(n^2 + n log n)

Space Complexity:
O(1) auxiliary space, excluding sorting space.

Drawbacks:
Repeatedly calculating the cost of each window makes
the approach inefficient for larger inputs.
*/

/*
Approach 2: Variable Size Sliding Window + Incremental Budget

Idea:
Sort the array so that the rightmost element of a window
can serve as the target frequency element.

Maintain:
- left and right pointers
- last: previous target value
- remaining: operations still available
- maxFreq: maximum valid window size

When expanding the window:
All existing elements must be increased from the previous
target value to the new target value.

Additional operations required:

    (right - left) * (nums[right] - last)

Subtract this cost from remaining.

If the remaining budget becomes negative, shrink the window
from the left.

Removing nums[left] restores the budget by:

    nums[right] - nums[left]

Once the window becomes valid, update maxFreq.

Time Complexity:
O(n log n) - Sorting takes O(n log n), and sliding window
operations take O(n).

Space Complexity:
O(1) auxiliary space, excluding sorting implementation space.

Key Insight:
Instead of recalculating the complete cost of making every
window element equal to nums[right], maintain the remaining
operation budget incrementally.

Since the array is sorted, increasing the target value only
requires accounting for the difference between consecutive
target values.
*/

/*
Method to Solve:
----------------
1. Sort the input array.
2. Initialize left = 0 and right = 1.
3. Maintain last as the previous target value.
4. Maintain remaining as the available operation budget.
5. Expand the window by calculating the additional cost
   required to match the new target value.
6. If remaining becomes negative, shrink the window and
   restore the budget for every removed element.
7. Update maxFreq whenever a valid window is found.
8. Return maxFreq.
*/

public class LC1838FrequencyOfTheMostFrequentElement {

    /**
     * Finds the maximum frequency achievable using at most k increments.
     *
     * @param nums input array of positive integers
     * @param k    maximum number of increment operations
     * @return maximum achievable frequency of an element
     */
    public int maxFrequency(int[] nums, int k) {
        int length = nums.length;

        Arrays.sort(nums);

        int left = 0;
        int right = 1;

        int last = nums[0];
        int maxFreq = 1;

        long remaining = k;

        while (right < length) {

            long windowSize = right - left;
            long difference = nums[right] - last;

            remaining -= windowSize * difference;

            // shrink the window until the budget is valid
            while (left < length && remaining < 0) {
                remaining += nums[right] - nums[left];
                left++;
            }

            if (remaining >= 0 && right - left + 1 > maxFreq) {
                maxFreq = right - left + 1;
            }

            last = nums[right];
            right++;
        }

        return maxFreq;
    }
}

// Time Complexity: O(n log n)
// Space Complexity: O(1) auxiliary space, excluding sorting
