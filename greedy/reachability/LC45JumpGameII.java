package greedy.reachability;

/*
 * Created at: 28-November-2026
 * Last revised at: 28-November-2026
 * Link: https://leetcode.com/problems/jump-game-ii/
 */

/*
Problem Description:
--------------------
Given an integer array nums, where each element represents the
maximum jump length from that position, return the minimum number
of jumps required to reach the last index.

You can assume that the last index is always reachable.

Example:
--------
Input: nums = [2,3,1,1,4]
Output: 2

Explanation:
Jump from index 0 to index 1, then jump to index 4.

Constraints:
------------
1 <= nums.length <= 10^4
0 <= nums[i] <= 1000
The last index is always reachable.
*/

/*
Approach 1: Dynamic Programming

Idea:
-----
Maintain dp[i] representing the minimum jumps required to
reach index i.

For every reachable position, relax the possible next
positions using the minimum jump count.

Time Complexity:
----------------
O(n^2)

Space Complexity:
-----------------
O(n)

Drawbacks:
----------
Repeatedly explores overlapping reachable ranges.
The greedy approach avoids this unnecessary work.
*/

/*
Approach 2: Greedy - Reachability Expansion

Idea:
-----
Treat the positions reachable using the current number of
jumps as a frontier.

1. Scan every index within the current reachable frontier.
2. Calculate the farthest position reachable from each index.
3. Maintain the maximum reachability across the frontier.
4. Once the entire frontier is processed, commit one jump
   and expand the reachable boundary.
5. If the maximum reachability does not increase, no route
   exists.
6. If the destination becomes reachable, return jumps + 1.

Key Insight:
------------
Every index within the current frontier can be reached using
the same number of jumps.

Instead of choosing an immediate next index, greedily select
the farthest boundary achievable by the entire frontier.

Time Complexity:
----------------
O(n) - Every index is processed exactly once.

Space Complexity:
-----------------
O(1) - Constant auxiliary variables.

Drawbacks:
----------
Requires careful management of the current frontier and
the next frontier. The jump count is committed only after
the current frontier has been completely explored.
*/

/*
Method to Solve:
----------------
1. Initialize jumps, reachability and index to zero.
2. Continue while the destination is outside the current
   reachable frontier.
3. Explore all indices within the current frontier and
   calculate their maximum reachable position.
4. Return immediately if the destination becomes reachable.
5. If reachability does not increase, return -1.
6. Otherwise, increment jumps and expand the frontier.
7. Return the minimum jump count.
*/

class LC45JumpGameII {

    /**
     * Calculates the minimum number of jumps required to
     * reach the last index.
     *
     * @param nums array containing maximum jump lengths
     * @return minimum jumps required, or -1 if unreachable
     */
    public int jump(int[] nums) {

        int length = nums.length;
        int jumps = 0;
        int reachability = 0;
        int index = 0;

        while (index < length - 1) {
            int maxReachability = Integer.MIN_VALUE;

            while (index <= reachability) {
                maxReachability = Math.max(
                        maxReachability,
                        Math.min(index + nums[index], length - 1));

                if (maxReachability >= length - 1)
                    return jumps + 1;

                index++;
            }

            // No route possible
            if (maxReachability == reachability)
                return -1;

            jumps++;
            reachability = maxReachability;
        }

        return jumps;
    }
}

// Time Complexity: O(n)
// Space Complexity: O(1)