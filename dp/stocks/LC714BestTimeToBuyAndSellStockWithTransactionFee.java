package dp.stocks;

// Created at: 23-June-2025
// Last revised at: 23-June-2025
// Link: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-transaction-fee/

/*
Problem Description:
--------------------
Statement:
You are given an array `stocks` where stocks[i] is the price of a stock on day i,
and an integer `fee` representing a transaction fee.
You may complete as many transactions as you like, but you need to pay the fee for each transaction.
- You may not hold more than one stock at a time.
- No cooldown period.

Find the maximum profit you can achieve.

Example:
Input:  stocks = [1, 3, 2, 8, 4, 9], fee = 2
Output: 8
Explanation: buy on day 0 (price=1), sell on day 3 (price=8, fee=2) → profit = 5
             buy on day 4 (price=4), sell on day 5 (price=9, fee=2) → profit = 3
             Total = 8

Constraints:
- 1 <= stocks.length <= 5 * 10^4
- 1 <= stocks[i] <= 10^4
- 0 <= fee <= 10^4
*/

/*
Approach 1: Space Optimized ★
-------------------------------
Idea:
Same two-state DP as LC309 but without cooldown and with a fee deducted on sell.

dp[index][0] = max profit from day `index` onwards when holding a stock (can sell).
dp[index][1] = max profit from day `index` onwards when not holding (can buy).

Since there is no cooldown, after selling today we land at index+1 in buyable state.
Fee is deducted at the point of selling.

Compressed into two rolling variables:
  tomorrowSell → dp[index+1][0]: holding state at index+1
  tomorrowBuy  → dp[index+1][1]: buyable state at index+1

Base (last day):
  tomorrowSell = stocks[len-1] - fee  (sell on last day, pay fee)
  tomorrowBuy  = 0                    (can't do anything useful)

Transitions:
  buy  = -stocks[index] + tomorrowSell   (buy today, now in holding state tomorrow)
  skip = tomorrowBuy                     (do nothing, stay buyable)
  sell = stocks[index] - fee + tomorrowBuy  (sell today with fee, back to buyable tomorrow)
  hold = tomorrowSell                    (keep holding)

Time Complexity: O(n)
Space Complexity: O(1)

Key Difference from LC309:
- No overmorrowBuy needed — sell lands at index+1, not index+2.
- Fee subtracted on sell, not on buy.
*/

/*
Method to Solve:
----------------
1. Define two states: holding (can sell) and not holding (can buy).
2. Base: last day — sell gives stocks[len-1]-fee, not holding gives 0.
3. For each day right to left:
   - buy  = -stocks[index] + tomorrowSell
   - skip = tomorrowBuy
   - sell = stocks[index] - fee + tomorrowBuy   (fee paid on sell)
   - hold = tomorrowSell
4. Update both variables after computing current day.
5. Return tomorrowBuy (buyable state at index 0 = answer).
*/

public class LC714BestTimeToBuyAndSellStockWithTransactionFee {

    /**
     * Space optimized DP using two rolling variables.
     * tomorrowSell → max profit from tomorrow onwards while holding a stock.
     * tomorrowBuy → max profit from tomorrow onwards while not holding.
     * Fee is deducted at the point of selling.
     *
     * @param stocks array of daily stock prices
     * @param fee    transaction fee per sell
     * @return maximum profit
     */
    public int maxProfit(int[] stocks, int fee) {
        int len = stocks.length;

        int tomorrowBuy = 0;
        int tomorrowSell = stocks[len - 1] - fee; // sell on last day, pay fee

        for (int index = len - 2; index >= 0; index--) {
            int buy = -stocks[index] + tomorrowSell;
            int skip = tomorrowBuy;

            int sell = stocks[index] - fee + tomorrowBuy; // pay fee on sell
            int hold = tomorrowSell;

            tomorrowBuy = Math.max(buy, skip);
            tomorrowSell = Math.max(sell, hold);
        }

        return tomorrowBuy;
    }
}