package dp.stocks;

// Created at: 23-June-2025
// Last revised at: 23-June-2025
// Link: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/

/*
Problem Description:
--------------------
Statement:
You are given an array `stocks` where stocks[i] is the price of a stock on day i.
You may complete as many transactions as you like with the following restriction:
- After you sell your stock, you cannot buy stock on the next day (cooldown of 1 day).
- You may not hold more than one stock at a time.

Find the maximum profit you can achieve.

Example:
Input:  stocks = [1, 2, 3, 0, 2]
Output: 3
Explanation: buy on day 0, sell on day 1, cooldown on day 2, buy on day 3, sell on day 4 → profit = (2-1) + (2-0) = 3

Constraints:
- 1 <= stocks.length <= 5000
- 0 <= stocks[i] <= 1000
*/

/*
Approach 1: Brute Force (Recursion)
------------------------------------
Idea:
At every index, track whether we can buy or not (canBuy flag).
- If canBuy: either buy today or skip.
- If holding (canSell): either sell today (skip next day for cooldown) or hold.
Explore all paths and return max profit.

Time Complexity: O(2^n) — two choices at every index
Space Complexity: O(n) — recursion stack depth

Drawbacks:
Exponential time due to overlapping subproblems. Same (index, canBuy) state recomputed multiple times.
*/

/*
Approach 2: Tabulation (Bottom-Up DP)
---------------------------------------
Idea:
dp[index][0] = max profit from day `index` onwards when holding a stock (can sell).
dp[index][1] = max profit from day `index` onwards when not holding (can buy).

Fill from right to left.
Base: dp[len-1][0] = stocks[len-1] (sell on last day if holding), dp[len-1][1] = 0.
Extra row dp[len] added to handle cooldown out-of-bound access (index+2) without if checks.

Transitions:
  dp[index][1] = max(-stocks[index] + dp[index+1][0], dp[index+1][1])  // buy or skip
  dp[index][0] = max(stocks[index] + dp[index+2][1], dp[index+1][0])   // sell (cooldown) or hold

Time Complexity: O(n)
Space Complexity: O(n)

Key Insight:
Sizing the table as [len+1][2] avoids a separate boundary check for the cooldown jump (index+2).
dp[len][...] defaults to 0 — no profit possible beyond last day.
*/

/*
Approach 3: Space Optimized ★
-------------------------------
Idea:
At any index, transitions only look at index+1 and index+2.
So we only need three variables instead of the full table:
  tomorrowSell  → dp[index+1][0]: holding state at index+1
  tomorrowBuy   → dp[index+1][1]: buyable state at index+1
  overmorrowBuy → dp[index+2][1]: buyable state at index+2 (needed after cooldown)

Fill from right to left, updating variables after computing current day's values.

Time Complexity: O(n)
Space Complexity: O(1)

Key Insight:
cooldown makes index+2 a dependency — requires tracking one extra variable (overmorrowBuy)
compared to problems without cooldown.
*/

/*
Method to Solve:
----------------
1. Define two states: holding a stock (can sell) and not holding (can buy).
2. At the last day: profit if holding = stocks[len-1], profit if not holding = 0.
3. For each day right to left:
   - buy   = -stocks[index] + tomorrowSell   (buy today, now holding tomorrow)
   - skip  = tomorrowBuy                     (do nothing, stay in buyable state)
   - sell  = stocks[index] + overmorrowBuy   (sell today, cooldown, buyable at index+2)
   - hold  = tomorrowSell                    (keep holding into tomorrow)
4. Update variables: shift overmorrow ← tomorrow ← current before next iteration.
5. Return tomorrowBuy (buyable state at index 0 = answer).
*/

public class LC309BestTimeToBuyAndSellStockWithCooldown {

    /**
     * Brute force recursive solution exploring all buy/sell/skip/hold choices.
     *
     * @param stocks array of daily stock prices
     * @param index  current day
     * @param canBuy true if not currently holding a stock
     * @return maximum profit from current day onwards
     */
    public int brute(int[] stocks, int index, boolean canBuy) {
        if (index >= stocks.length)
            return 0;

        // last day: only profit possible is selling if holding
        if (index == stocks.length - 1)
            return !canBuy ? stocks[index] : 0;

        int maxProfit;
        if (canBuy) {
            int buy = -stocks[index] + brute(stocks, index + 1, false);
            int skip = brute(stocks, index + 1, true);
            maxProfit = Math.max(buy, skip);
        } else {
            int sell = stocks[index] + brute(stocks, index + 2, true); // cooldown: skip index+1
            int hold = brute(stocks, index + 1, false);
            maxProfit = Math.max(sell, hold);
        }

        return maxProfit;
    }

    /**
     * Tabulation solution using a 2D DP table.
     * Column 0: holding state (can sell). Column 1: buyable state (can buy).
     * Extra row (len+1) handles cooldown boundary without if checks.
     *
     * @param stocks array of daily stock prices
     * @return maximum profit
     */
    public int tabulation(int[] stocks) {
        int len = stocks.length;
        int[][] dp = new int[len + 1][2];

        // base: last day, sell if holding
        dp[len - 1][0] = stocks[len - 1];

        for (int index = len - 2; index >= 0; index--) {
            int buy = -stocks[index] + dp[index + 1][0];
            int skip = dp[index + 1][1];

            int sell = stocks[index] + dp[index + 2][1]; // cooldown jump
            int hold = dp[index + 1][0];

            dp[index][1] = Math.max(buy, skip);
            dp[index][0] = Math.max(sell, hold);
        }

        return dp[0][1];
    }

    /**
     * Space optimized solution using three rolling variables.
     * tomorrowSell → holding state at index+1
     * tomorrowBuy → buyable state at index+1
     * overmorrowBuy → buyable state at index+2 (cooldown dependency)
     *
     * @param stocks array of daily stock prices
     * @return maximum profit
     */
    public int maxProfit(int[] stocks) {
        int len = stocks.length;

        int overmorrowBuy = 0;
        int tomorrowBuy = 0;
        int tomorrowSell = stocks[len - 1]; // sell on last day if holding

        for (int index = len - 2; index >= 0; index--) {
            int buy = -stocks[index] + tomorrowSell;
            int skip = tomorrowBuy;

            int sell = stocks[index] + overmorrowBuy; // cooldown: land at index+2
            int hold = tomorrowSell;

            // shift variables before overwriting
            overmorrowBuy = tomorrowBuy;
            tomorrowBuy = Math.max(buy, skip);
            tomorrowSell = Math.max(sell, hold);
        }

        return tomorrowBuy;
    }
}