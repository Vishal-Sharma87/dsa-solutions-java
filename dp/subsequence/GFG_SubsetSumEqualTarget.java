package dp.subsequence;
// Created at: 09-June-2026

// Last revised at: 09-June-2026
// Link: https://www.geeksforgeeks.org/problems/subset-sum-problem-1611555638/1

/*
Problem Description:
--------------------
Statement:
Given an array of non-negative integers and a target sum, determine whether
there exists a subset of the array whose elements add up to exactly the target sum.

Example:
arr = [3, 34, 4, 12, 5, 2], sum = 9
Output: true
Explanation: Subset {4, 5} sums to 9.

arr = [3, 34, 4, 12, 5, 2], sum = 30
Output: false

Constraints:
1 <= arr.size() <= 200
0 <= arr[i] <= 200
1 <= sum <= 10^4
*/

/*
Approach 1: Recursion (Brute Force)
Idea:
At each index, either include the current element (reduce target) or exclude it.
Base cases: target == 0 → true; index == 0 → check if arr[0] == target.

Time Complexity: O(2^n)
Space Complexity: O(n) — recursion stack

Drawbacks:
Recomputes overlapping subproblems. TLE for large inputs.
*/

/*
Approach 2: Memoization (Top-Down DP)
Idea:
Same recursion but cache results in dp[index][target] to avoid recomputation.

Time Complexity: O(n * sum)
Space Complexity: O(n * sum) — dp table + recursion stack

Drawbacks:
Still has recursion overhead. Stack depth proportional to n.
*/

/*
Approach 3: Tabulation (Bottom-Up DP)
Idea:
Build dp[row][target] iteratively. dp[row][target] = true if any subset
of arr[0..row] sums to target.

Time Complexity: O(n * sum)
Space Complexity: O(n * sum)

Drawbacks:
Full 2D table allocation even when only previous row is needed.
*/

/*
Approach 4: Space Optimized (Two 1D Arrays)
Idea:
Since each row only depends on the previous row, maintain two 1D arrays:
prev (last computed row) and curr (current row being filled).
Swap them after each row to reuse memory.

Key fix: curr must be reset with Arrays.fill(curr, false) before each row
to clear stale values from the previous swap cycle.

Time Complexity: O(n * sum)
Space Complexity: O(sum)
*/

/*
Method to Solve:
----------------
1. Initialize prev[0] = true (empty subset sums to 0).
2. Initialize prev[arr[0]] = true if arr[0] <= sum (single element base case).
3. For each row from 1 to n-1:
   a. Reset curr to false, set curr[0] = true.
   b. For each target from 1 to sum:
      - include: prev[target - arr[row]] if target >= arr[row]
      - exclude: prev[target]
      - curr[target] = include || exclude
   c. Swap prev and curr via reference variable.
4. Return prev[sum].
*/

import java.util.Arrays;

public class GFG_SubsetSumEqualTarget {

    /**
     * Checks whether any subset of the array sums to the given target.
     * Uses space-optimized bottom-up DP with two rolling 1D arrays.
     *
     * @param arr input array of non-negative integers
     * @param sum target sum to check
     * @return true if a valid subset exists, false otherwise
     */
    public boolean isSubsetSum(int[] arr, int sum) {
        int len = arr.length;

        boolean[] prev = new boolean[sum + 1];
        boolean[] curr = new boolean[sum + 1];
        boolean[] reference;

        // base case: first element
        if (arr[0] <= sum) {
            prev[arr[0]] = true;
        }
        prev[0] = true; // empty subset

        for (int row = 1; row < len; row++) {
            // clear stale values from previous swap cycle
            Arrays.fill(curr, false);
            curr[0] = true; // empty subset always valid

            for (int target = 1; target <= sum; target++) {
                boolean include = false;
                if (target >= arr[row])
                    include = prev[target - arr[row]];

                boolean exclude = prev[target];

                curr[target] = include || exclude;
            }

            // swap arrays — reuse memory, no new allocation
            reference = prev;
            prev = curr;
            curr = reference;
        }

        return prev[sum];
    }
}

// Time Complexity: O(n * sum)
// Space Complexity: O(sum)