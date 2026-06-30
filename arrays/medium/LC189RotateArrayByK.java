// Created at: 24-Jan-2026
// Last revised at: 24-Jan-2026
// Link: https://leetcode.com/problems/rotate-array/

package arrays.medium;

/*
Problem Description:
--------------------
Statement:

Given an integer array nums, rotate the array to the right by k steps.

Examples:

Input:
nums = [1,2,3,4,5,6,7]
k = 3

Output:
[5,6,7,1,2,3,4]

Input:
nums = [-1,-100,3,99]
k = 2

Output:
[3,99,-1,-100]

Constraints:
1 <= nums.length <= 10^5
-2^31 <= nums[i] <= 2^31 - 1
0 <= k <= 10^5
*/

/*
Approach 1: Rotate One Step at a Time

Idea:
Rotate the array by one position, k times.

Time Complexity:
O(n × k)

Space Complexity:
O(1)

Drawbacks:
Repeats the same shifting operation multiple times.
*/

/*
Approach 2: Extra Array

Idea:
Create another array.

Every element at index i moves to:

(i + k) % n

Finally, copy the temporary array back.

Time Complexity:
O(n)

Space Complexity:
O(n)

Drawbacks:
Requires an additional array.
*/

/*
Approach 3: Reversal Algorithm (Optimal)

Idea:
Normalize k first:

k = k % n

Split the array into two parts:

First Part:
0 ... n-k-1

Second Part:
n-k ... n-1

Reverse:

1. First part
2. Second part
3. Entire array

The final reversal places every element in its rotated position.

Time Complexity:
O(n)

Space Complexity:
O(1)

Key Insight:
Three reversals achieve the same effect as rotating the array without
using extra space.
*/

/*
Method to Solve:
----------------
1. Normalize k using modulo.
2. Reverse the first n-k elements.
3. Reverse the last k elements.
4. Reverse the complete array.
5. The array is now rotated by k positions.
*/

public class LC189RotateArrayByK {

    /**
     * Rotates the array to the right by k positions.
     *
     * @param nums input array
     * @param k number of rotations
     * @return void
     */
    public void solve(int[] nums, int k) {

        if (nums.length <= 1) {
            return;
        }

        int length = nums.length;

        // avoid unnecessary full rotations
        k %= length;

        int partitionIndex = length - k - 1;

        // reverse first part
        ArraysOfVishal.reverseArray(nums, 0, partitionIndex);

        // reverse second part
        ArraysOfVishal.reverseArray(nums, partitionIndex + 1, length - 1);

        // reverse complete array
        ArraysOfVishal.reverseArray(nums, 0, length - 1);
    }
}

// Time Complexity: O(n)
// Space Complexity: O(1)