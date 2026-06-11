package dp.unbounded_knapsack;

// Created at: 12-June-2026
// Last revised at: 12-June-2026
// Link: https://www.geeksforgeeks.org/problems/knapsack-with-duplicate-items4201/1

/*
Problem Description:
--------------------
Statement:
to get the maximum total value in the knapsack.
Given the weights and values of N items, put these items in a knapsack of capacity W

Unlike 0/1 Knapsack, each item can be chosen any number of times.

Example:
Input:
N = 4
W = 8
val[] = {6, 1, 7, 7}
wt[] = {1, 3, 4, 5}

Output:
48

Explanation:
Choose the first item 8 times.

Constraints:
1 <= N <= 1000
1 <= W <= 1000
1 <= wt[i], val[i] <= 1000
*/

/*
Approach 1: Recursive + Memoization
Idea:
For every item, either:
1. Pick the current item and stay at the same index (since reuse is allowed).
2. Skip the current item and move to the previous index.

Store computed states (index, capacity) to avoid recomputation.

Time Complexity:
O(N * W)

Space Complexity:
O(N * W) + O(N)

Drawbacks:
Uses recursion stack and extra DP table.
*/

/*
Approach 2: Space Optimized Tabulation
Idea:
Use two 1D arrays:
1. prev[] stores answers using previous items.
2. curr[] stores answers including the current item.

For unbounded knapsack:
include = value[index] + curr[capacity - weight[index]]

Using curr[] allows the same item to be reused multiple times.

Time Complexity:
O(N * W)

Space Complexity:
O(W)

Key Insight:
For unbounded knapsack, the include transition must use the current row
instead of the previous row.
*/

/*
Method to Solve:
----------------
1. Initialize the base row using item 0.
2. For every remaining item:
   - Calculate include profit using the current row.
   - Calculate exclude profit using the previous row.
   - Store the maximum.
3. Swap current and previous rows after each item.
4. Return the answer stored at capacity W.
*/

// Time Complexity: O(N * W)
// Space Complexity: O(W)

import java.util.Arrays;

public class GFG_UnboundedKnapsack {

    /**
     * Finds the maximum obtainable value in an unbounded knapsack.
     *
     * @param val values of items
     * @param wt weights of items
     * @param capacity knapsack capacity
     * @return maximum achievable profit
     */
    public int knapSack(int[] val, int[] wt, int capacity) {
        int n = val.length;

        int[] prev = new int[capacity + 1];
        int[] curr = new int[capacity + 1];

        // initialize using the first item
        for (int cp = wt[0]; cp <= capacity; cp++) {
            prev[cp] = (cp / wt[0]) * val[0];
        }

        for (int index = 1; index < n; index++) {
            Arrays.fill(curr, 0);

            for (int cp = 1; cp <= capacity; cp++) {

                int include = 0;

                if (cp >= wt[index]) {
                    // reuse current item
                    include = val[index] + curr[cp - wt[index]];
                }

                int exclude = prev[cp];

                curr[cp] = Math.max(include, exclude);
            }

            int[] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[capacity];
    }
}