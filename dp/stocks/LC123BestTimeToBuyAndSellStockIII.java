package dp.stocks;

import java.util.Arrays;

// Created at: 19-June-2026
// Last revised at: 19-June-2026
// Link: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iii/

/*
Problem Description:
--------------------
Statement:
You are given an array prices where prices[i] is the price of a stock on the ith day.

Find the maximum profit you can achieve by completing at most two transactions.

Note:
You may not engage in multiple transactions simultaneously.
You must sell the stock before buying again.

Example:
Input: prices = [3,3,5,0,0,3,1,4]
Output: 6

Explanation:
Buy at 0, sell at 3.
Buy at 1, sell at 4.

Example:
Input: prices = [1,2,3,4,5]
Output: 4

Example:
Input: prices = [7,6,4,3,1]
Output: 0

Constraints:
1 <= prices.length <= 10^5
0 <= prices[i] <= 10^5
*/

/*
Approach 1: Recursive DP
Idea:
At every index, decide whether to buy, sell, hold, or skip.

State:
(index, canBuy, remainingTransactions)

Time Complexity:
O(2^n)

Space Complexity:
O(n)

Drawbacks:
Contains overlapping subproblems and becomes infeasible for large inputs.
*/

/*
Approach 2: Memoization
Idea:
Cache every state:
(index, canBuy, remainingTransactions)

Time Complexity:
O(n * 2 * 3)

Space Complexity:
O(n * 2 * 3)

Key Insight:
Each state is computed only once.
*/

/*
Approach 3: Space Optimized DP
Idea:
Only the next day's states are required.

State:
canBuy = 0
holdingStock = 1

remaining:
Number of transactions still available.

Transitions:

canBuy:
max(
    buy stock,
    skip
)

holdingStock:
max(
    sell stock,
    hold
)

Time Complexity:
O(n)

Space Complexity:
O(1)

Why Better:
Eliminates the O(n) DP table while preserving all DP transitions.
*/

/*
Method to Solve:
----------------
1. Define DP states using:
   - current index
   - canBuy flag
   - remaining transactions
2. Initialize the last day's states.
3. Traverse the array from right to left.
4. For every remaining transaction count:
   - compute buy/skip state
   - compute sell/hold state
5. Store only the next day's states.
6. Return the answer starting from:
   index = 0,
   canBuy = true,
   remaining = 2.
*/

// Time Complexity: O(n)
// Space Complexity: O(1)

public class LC123BestTimeToBuyAndSellStockIII {

    /**
     * Returns the maximum profit achievable using at most two transactions.
     *
     * @param stocks stock prices by day
     * @return maximum profit
     */
    public int maxProfit(int[] stocks) {
        int len = stocks.length;

        int[][] prev = new int[3][2];
        int[][] curr = new int[3][2];

        for (int remaining = 0; remaining <= 2; remaining++) {
            prev[remaining][1] = stocks[len - 1];
        }

        for (int index = len - 2; index >= 0; index--) {

            for (int remaining = 0; remaining <= 2; remaining++) {
                Arrays.fill(curr[remaining], 0);
            }

            for (int remaining = 0; remaining <= 2; remaining++) {

                if (remaining > 0) {

                    // buy or skip
                    int buy = -stocks[index] + prev[remaining - 1][1];
                    int skip = prev[remaining][0];

                    curr[remaining][0] = Math.max(buy, skip);
                }

                // sell or hold
                int sell = stocks[index] + prev[remaining][0];
                int hold = prev[remaining][1];

                curr[remaining][1] = Math.max(sell, hold);
            }

            int[][] temp = prev;
            prev = curr;
            curr = temp;
        }

        return prev[2][0];
    }
}