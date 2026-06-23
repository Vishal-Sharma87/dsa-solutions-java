package dp.longestincreasingsubsequence;

// Created at: 24-June-2026
// Last revised at: 24-June-2026
// Link: https://www.geeksforgeeks.org/problems/printing-longest-increasing-subsequence/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/*
Problem Description:
--------------------
Statement:
Given an array of integers nums[], find and return the actual longest strictly
increasing subsequence (not just the length). If multiple LIS of the same length
exist, return any one of them.

Example:
Input:  nums = [3, 8, 14, 5, 4, 14, 2, 14, 8]
Output: [3, 8, 14]

Constraints:
- 1 <= nums.length <= 10^3
- 0 <= nums[i] <= 10^6
*/

/*
Approach 1: Brute Force (Pick / Not Pick)
-----------------------------------------
Idea:
At each index, decide to include or exclude the element.
Include only if nums[index] > prev element in chain.
Recurse over all possibilities, track the actual path, and return the longest.

Time Complexity:  O(2^n)
Space Complexity: O(n) — recursion stack

Drawbacks:
Exponential. Reconstructing the actual path adds overhead.
Only feasible for very small inputs.
*/

/*
Approach 2: DP — Tabulation + Index-based Parent Tracking (Chosen)
--------------------------------------------------------------------
Idea:
lisLen[i] = length of LIS ending at index i.
parent[i] = index of the previous element in the best chain ending at i.

For each i, scan all j < i. If nums[j] < nums[i] and extending j's chain
improves lisLen[i], update lisLen[i] = lisLen[j] + 1 and parent[i] = j.

After filling lisLen[], find the index with the max LIS length (last).
Traceback via parent[] to reconstruct the actual subsequence.

Key Design: parent[] stores indices, not values.
This correctly handles duplicate values — each element is uniquely identified
by its index, so two elements with the same value maintain separate chains.
A HashMap<value, value> approach breaks on duplicates since both entries
collide on the same key.

Time Complexity:  O(n^2)
Space Complexity: O(n)
*/

/*
Method to Solve:
----------------
1. Initialize lisLen[i] = 1 for all i (each element is its own LIS).
2. Initialize parent[i] = -1 for all i (no predecessor yet).
3. For each i from 0 to n-1:
   a. Scan all j < i.
   b. If nums[j] < nums[i] and lisLen[j] >= lisLen[i],
      set lisLen[i] = lisLen[j] + 1 and parent[i] = j.
4. Track the index of the max lisLen value as `last`.
5. Traceback from `last` via parent[] collecting nums[last] at each step.
6. Reverse the collected list and return.
*/

public class GFG_GetLIS {

    /**
     * Returns the actual longest strictly increasing subsequence.
     * Uses index-based parent tracking to correctly handle duplicate values.
     *
     * @param nums input array
     * @return one valid LIS as an ArrayList
     */
    public ArrayList<Integer> getLIS(int[] nums) {
        int len = nums.length;

        int[] lisLen = new int[len];
        int[] parent = new int[len];

        Arrays.fill(lisLen, 1); // every element is a LIS of length 1
        Arrays.fill(parent, -1); // no predecessor initially

        int maxi = 0;
        int last = -1;

        for (int i = 0; i < len; i++) {
            for (int j = 0; j < i; j++) {
                // extend j's chain only if it strictly increases and gives a longer chain
                if (nums[j] < nums[i] && lisLen[i] <= lisLen[j]) {
                    lisLen[i] = lisLen[j] + 1;
                    parent[i] = j;
                }
            }
            // track the ending index of the longest chain seen so far
            if (lisLen[i] > maxi) {
                maxi = lisLen[i];
                last = i;
            }
        }

        if (last == -1) {
            return new ArrayList<>();
        }

        // traceback via parent indices
        ArrayList<Integer> lis = new ArrayList<>();
        while (last != -1) {
            lis.add(nums[last]);
            last = parent[last];
        }

        Collections.reverse(lis);
        return lis;
    }
}

// Time Complexity: O(n^2)
// Space Complexity: O(n)