package dp.stocks;

import java.util.Arrays;

// Created at: 19-June-2026
// Last revised at: 19-June-2026
// Link: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-iv/

/*
Problem Description:
--------------------
Statement:
You are given an integer array prices where prices[i] is the price of a stock on the ith day,
and an integer k.

You may complete at most k transactions.

Find the maximum profit you can achieve.

Note:
You must sell the stock before buying again.

Example:
Input: k = 2, prices = [2,4,1]
Output: 2

Example:
Input: k = 2, prices = [3,2,6,5,0,3]
Output: 7

Constraints:
1 <= k <= 100
1 <= prices.length <= 1000
0 <= prices[i] <= 1000
*/

/*
Approach 1: Recursive DP
Idea:
At every index decide whether to buy, sell, hold, or skip.

State:
(index, canBuy, remainingTransactions)

Time Complexity:
O(2^n)

Space Complexity:
O(n)

Drawbacks:
Contains many overlapping subproblems.
*/

/*
Approach 2: Memoization
Idea:
Cache each DP state.

State:
(index, canBuy, remainingTransactions)

Time Complexity:
O(n * k)

Space Complexity:
O(n * k)

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
Transactions still available.

Transitions:

canBuy:
max(
    buy,
    skip
)

holdingStock:
max(
    sell,
    hold
)

Time Complexity:
O(n * k)

Space Complexity:
O(k)

Why Better:
Removes the O(n * k) DP table while preserving the same transitions.
*/

/*
Method to Solve:
----------------
1. Maintain DP states for:
   - can buy
   - holding stock
2. Track the remaining transactions available.
3. Traverse from right to left.
4. For each transaction count:
   - compute buy/skip state
   - compute sell/hold state
5. Store only the next day's states.
6. Return the profit starting with:
   canBuy = true,
   remaining = k.
*/

// Time Complexity: O(n * k)
// Space Complexity: O(k)

public class LC188BestTimeToBuyAndSellStockIV {

    /**
     * Returns the maximum profit achievable using at most k transactions.
     *
     * @param k      maximum transactions allowed
     * @param stocks stock prices by day
     * @return maximum profit
     */
    public int maxProfit(int k, int[] stocks) {
        int len = stocks.length;

        int[][] prev = new int[k + 1][2];
        int[][] curr = new int[k + 1][2];

        for (int remaining = 0; remaining <= k; remaining++) {
            prev[remaining][1] = stocks[len - 1];
        }

        for (int index = len - 2; index >= 0; index--) {

            for (int remaining = 0; remaining <= k; remaining++) {
                Arrays.fill(curr[remaining], 0);
            }

            for (int remaining = 0; remaining <= k; remaining++) {

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

        return prev[k][0];
    }
}