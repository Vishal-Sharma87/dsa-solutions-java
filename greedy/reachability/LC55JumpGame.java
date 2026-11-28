package greedy.reachability;

/*
 * Created at: 28-November-2026
 * Last revised at: 28-November-2026
 * Link: https://leetcode.com/problems/jump-game/
 */

/*
Problem Description:
--------------------
Given an integer array nums, where each element represents
the maximum jump length from that position, determine whether
you can reach the last index starting from index 0.

Example:
--------
Input: nums = [2,3,1,1,4]
Output: true

Explanation:
Jump from index 0 to index 1, then jump to index 4.

Constraints:
------------
1 <= nums.length <= 10^4
0 <= nums[i] <= 10^5
*/

/*
Approach 1: Dynamic Programming

Idea:
-----
Maintain a boolean dp array where dp[i] indicates whether
index i is reachable.

For every reachable index, mark all positions within its
jump range as reachable.

Time Complexity:
----------------
O(n^2)

Space Complexity:
-----------------
O(n)

Drawbacks:
----------
Repeatedly explores overlapping reachable ranges.
*/

/*
Approach 2: Greedy - Reachability Carry

Idea:
-----
Maintain carry representing the maximum remaining jump
distance available at the current index.

1. Decrease the previous carry by one while moving to
   the next index.
2. Compare it with the current index's jump capacity.
3. Keep whichever provides greater remaining reachability.
4. If carry becomes zero or negative before reaching the
   last index, the destination is unreachable.
5. If the iteration completes, return true.

Key Insight:
------------
Reachability can be maintained as a continuously updated
budget instead of explicitly tracking the farthest index.

The previous reachability and current jump capacity are
combined using a greedy maximum operation.

Time Complexity:
----------------
O(n)

Space Complexity:
-----------------
O(1)

Drawbacks:
----------
The carry variable must be interpreted as remaining
reachability, not the absolute farthest reachable index.
*/

/*
Method to Solve:
----------------
1. Initialize carry to zero.
2. Iterate through every index except the destination.
3. Read the maximum jump capacity at the current index.
4. Decrease the previous carry by one and compare it
   with the current jump capacity.
5. Update carry with the maximum of both values.
6. If carry becomes zero or negative, return false.
7. If the loop completes, return true.
*/

class LC55JumpGame {

    /**
     * Determines whether the last index is reachable.
     *
     * @param nums array containing maximum jump lengths
     * @return true if the last index is reachable, false otherwise
     */
    public boolean canJump(int[] nums) {

        int carry = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            int jump = nums[i];

            carry = Math.max(carry - 1, jump);

            if (carry <= 0)
                return false;
        }

        return true;
    }
}

// Time Complexity: O(n)
// Space Complexity: O(1)