// Created at: 12-January-2026
// Last revised at: 12-January-2026
// Link: https://leetcode.com/problems/single-number/

package arrays.easy;

/*
Problem Description:
--------------------
Statement:
Given a non-empty integer array nums, every element appears exactly twice
except for one element. Return that single element.

You must implement a solution with linear runtime complexity and use
only constant extra space.

Example:
Input: nums = [2,2,1]
Output: 1

Input: nums = [4,1,2,1,2]
Output: 4

Input: nums = [1]
Output: 1

Constraints:
- 1 <= nums.length <= 3 * 10^4
- -3 * 10^4 <= nums[i] <= 3 * 10^4
- Every element appears twice except one.
*/

/*
Approach 1: Brute Force

Idea:
For every element, count its occurrences by traversing the entire array.
Return the element whose frequency is one.

Time Complexity:
O(n²)

Space Complexity:
O(1)

Drawbacks:
Repeated counting makes the solution inefficient.
*/

/*
Approach 2: HashMap

Idea:
Store the frequency of every element using a HashMap.
Return the element with frequency one.

Time Complexity:
O(n)

Space Complexity:
O(n)

Key Insight:
Extra memory avoids repeated counting.
*/

/*
Approach 3: XOR (Optimal)

Idea:
Use the XOR operator on every element.

Properties:
- x ^ x = 0
- x ^ 0 = x
- XOR is associative and commutative.

All duplicate elements cancel each other, leaving only the unique element.

Time Complexity:
O(n)

Space Complexity:
O(1)

Key Insight:
Pairwise XOR eliminates duplicates without requiring extra memory.
*/

/*
Method to Solve:
----------------
1. Initialize the answer as 0.
2. Traverse the array.
3. XOR every element with the current answer.
4. Return the remaining value.
*/

public class LC136SIngleNumber {

    /**
     * Returns the element that appears only once.
     *
     * @param nums input array
     * @return single occurring element
     */
    public int singleNumber(int[] nums) {

        int single = 0;

        for (int num : nums) {
            // cancel duplicate numbers
            single ^= num;
        }

        return single;
    }
}

// Time Complexity: O(n)
// Space Complexity: O(1)