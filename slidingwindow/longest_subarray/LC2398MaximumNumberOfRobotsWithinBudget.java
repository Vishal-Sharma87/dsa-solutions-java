package slidingwindow.longest_subarray;

// Created at: 03-November-2026
// Last revised at: 03-November-2026
// Link: https://leetcode.com/problems/maximum-number-of-robots-within-budget/

import java.util.ArrayDeque;
import java.util.Deque;

/*
Problem Description:
--------------------
You are given two 0-indexed integer arrays:
- chargeTimes: charge time of each robot.
- runningCosts: running cost of each robot.

You are also given a long integer budget.

The cost of operating a contiguous group of k robots is:

max(chargeTimes) + k * sum(runningCosts)

Return the maximum number of consecutive robots that can
be operated without exceeding the given budget.

Example 1:
----------
Input:
chargeTimes = [3, 6, 1, 3, 4]
runningCosts = [2, 1, 3, 4, 5]
budget = 25

Output: 3

Explanation:
The first three robots have a cost of:
6 + 3 * (2 + 1 + 3) = 24

Example 2:
----------
Input:
chargeTimes = [11, 12, 19]
runningCosts = [10, 8, 7]
budget = 19

Output: 0

Constraints:
------------
1 <= chargeTimes.length == runningCosts.length <= 5 * 10^4
1 <= chargeTimes[i], runningCosts[i] <= 10^5
1 <= budget <= 10^15
*/

/*
Approach 1: Brute Force

Idea:
-----
Consider every possible starting index and expand the
subarray while maintaining its maximum charge time and
running cost sum.

For every subarray, calculate:

maxChargeTime + windowSize * runningCostSum

Track the maximum valid window length.

Time Complexity:
----------------
O(n^2)

Space Complexity:
-----------------
O(1)

Drawbacks:
----------
Repeatedly examining subarrays results in quadratic time,
which is inefficient for the given constraints.
*/

/*
Approach 2: Sliding Window + Monotonic Deque

Idea:
-----
Maintain a variable-size sliding window [left, right].

Two data structures help calculate the operating cost:

1. Running Sum:
   Maintain the sum of running costs inside the window.

2. Monotonic Deque:
   Store indices in decreasing order of chargeTime.
   The front always represents the maximum chargeTime
   in the current window.

Window Expansion:
-----------------
1. Expand the window by moving right.
2. Remove indices from the deque's back while their
   chargeTime is less than or equal to the current value.
3. Insert the current index.
4. Add its running cost to the running sum.

Window Shrinking:
-----------------
Calculate:

cost = maxChargeTime + windowSize * runningCostSum

If cost exceeds the budget:
1. Remove the leftmost index from the deque if it is
   currently at the front.
2. Subtract its running cost from the running sum.
3. Increment left.
4. Recalculate the cost while the deque is non-empty.

If the deque becomes empty, the window has become empty.
The next iteration starts a new window.

Answer:
-------
Update the maximum valid window length after shrinking.

Time Complexity:
----------------
O(n)

Every index enters and leaves the deque at most once.

Space Complexity:
-----------------
O(n)

The deque stores at most n indices.

Key Insight:
------------
The monotonic deque avoids repeatedly scanning the window
to find the maximum chargeTime.

The running sum avoids repeatedly calculating the sum
of runningCosts.

Together, these allow each window expansion and shrink
to be processed in amortized constant time.
*/

/*
Method to Solve:
----------------
1. Initialize a monotonic decreasing deque and running sum.
2. Expand the window using the right pointer.
3. Maintain the deque by removing smaller or equal
   chargeTime values from its back.
4. Calculate the operating cost using the deque's front
   and the running sum.
5. Shrink the window while its cost exceeds the budget.
6. Update the maximum valid window length.
7. Return the result.
*/

public class LC2398MaximumNumberOfRobotsWithinBudget {

    /**
     * Finds the maximum number of consecutive robots
     * that can be operated within the given budget.
     *
     * @param chargeTime  charge time of each robot
     * @param runningCost running cost of each robot
     * @param budget      maximum allowed operating cost
     * @return maximum valid window length
     */
    public int maximumRobots(
            int[] chargeTime,
            int[] runningCost,
            long budget) {

        int length = chargeTime.length;

        Deque<Integer> q = new ArrayDeque<>();
        long sum = 0L;
        int result = 0;

        int left = 0;

        for (int right = 0; right < length; right++) {

            // maintain decreasing chargeTime
            while (!q.isEmpty()
                    && chargeTime[q.getLast()] <= chargeTime[right]) {
                q.removeLast();
            }

            q.addLast(right);
            sum += runningCost[right];

            long cost = chargeTime[q.getFirst()]
                    + ((right - left + 1L) * sum);

            // shrink the window until it becomes valid
            while (left <= right && cost > budget) {

                if (q.getFirst() == left)
                    q.removeFirst();

                sum -= runningCost[left];
                left++;

                if (!q.isEmpty()) {
                    cost = chargeTime[q.getFirst()]
                            + ((right - left + 1L) * sum);
                }
            }

            if (!q.isEmpty() && cost <= budget) {
                result = Math.max(right - left + 1, result);
            }
        }

        return result;
    }
}

// Time Complexity: O(n)
// Space Complexity: O(n)