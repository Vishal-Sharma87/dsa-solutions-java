package dp.stocks;

// Created at: 19-June-2026
// Last revised at: 19-June-2026
// Link: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-ii/

/*
Problem Description:
--------------------
Statement:
You are given an integer array prices where prices[i] is the price of a stock on the ith day.

On each day, you may decide to buy and/or sell the stock. You can hold at most one stock at any time. However, you can buy it and then immediately sell it on the same day.

Find and return the maximum profit you can achieve.

Example:
Input: prices = [7,1,5,3,6,4]
Output: 7

Explanation:
Buy on day 2 (price = 1), sell on day 3 (price = 5), profit = 4.
Buy on day 4 (price = 3), sell on day 5 (price = 6), profit = 3.
Total profit = 7.

Example:
Input: prices = [1,2,3,4,5]
Output: 4

Example:
Input: prices = [7,6,4,3,1]
Output: 0

Constraints:
1 <= prices.length <= 3 * 10^4
0 <= prices[i] <= 10^4
*/

/*
Approach 1: Recursive DP
Idea:
At every index, decide whether to buy/sell or skip based on the current state.

State:
(index, holdingStock)

Time Complexity:
O(2^n)

Space Complexity:
O(n)

Drawbacks:
Explores overlapping subproblems repeatedly.
*/

/*
Approach 2: Memoization
Idea:
Store results for every (index, holdingStock) state.

Time Complexity:
O(n)

Space Complexity:
O(n)

Key Insight:
Each state is computed only once.
*/

/*
Approach 3: Space Optimized DP
Idea:
Only the next day's states are required while computing the current day.

State:
canBuy = 0
holdingStock = 1

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
Removes the DP table while preserving the same transitions.
*/

/*
Method to Solve:
----------------
1. Start from the last day.
2. Maintain profits for two states:
   - holding a stock
   - not holding a stock
3. For every day:
   - compute buy/skip decision
   - compute sell/hold decision
4. Update previous states.
5. Return the profit when starting without any stock.
*/

// Time Complexity: O(n)
// Space Complexity: O(1)

public class LC122BestTimeToBuyAndSellStockII {

    /**
     * Returns the maximum profit achievable with unlimited transactions.
     *
     * @param stocks stock prices by day
     * @return maximum profit
     */
    public int maxProfit(int[] stocks) {
        int len = stocks.length;

        int prevHolding = stocks[len - 1];
        int prevCanBuy = 0;

        int currHolding = 0;
        int currCanBuy = 0;

        for (int index = len - 2; index >= 0; index--) {

            // buy or skip
            int buy = -stocks[index] + prevHolding;
            int skip = prevCanBuy;

            currCanBuy = Math.max(buy, skip);

            // sell or hold
            int sell = stocks[index] + prevCanBuy;
            int hold = prevHolding;

            currHolding = Math.max(sell, hold);

            prevHolding = currHolding;
            prevCanBuy = currCanBuy;
        }

        return prevCanBuy;
    }
}