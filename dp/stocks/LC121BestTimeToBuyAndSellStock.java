package dp.stocks;

// Created at: 19-June-2026
// Last revised at: 19-June-2026
// Link: https://leetcode.com/problems/best-time-to-buy-and-sell-stock/

/*
Problem Description:
--------------------
Statement:
You are given an array prices where prices[i] is the price of a given stock on the ith day.

You want to maximize your profit by choosing a single day to buy one stock and a different day in the future to sell that stock.

Return the maximum profit you can achieve. If no profit is possible, return 0.

Example:
Input: prices = [7,1,5,3,6,4]
Output: 5

Input: prices = [7,6,4,3,1]
Output: 0

Constraints:
1 <= prices.length <= 10^5
0 <= prices[i] <= 10^4
*/

/*
Approach 1: Brute Force
Idea:
Try every possible buy day and every future sell day.
Compute profit for each pair and keep the maximum.

Time Complexity:
O(n²)

Space Complexity:
O(1)

Drawbacks:
Too slow for large inputs due to nested iteration.
*/

/*
Approach 2: Track Minimum Price (Optimal)
Idea:
Maintain the minimum stock price seen so far.
For every day, calculate the profit if sold today.
Update the maximum profit whenever a better profit is found.

Time Complexity:
O(n)

Space Complexity:
O(1)

Key Insight:
The best selling opportunity for a day depends only on the minimum buying price seen before it.
*/

/*
Method to Solve:
----------------
1. Initialize the first price as the minimum price.
2. Traverse the array from left to right.
3. Update the minimum price whenever a smaller value is found.
4. Otherwise compute current profit using current price - minimum price.
5. Update the maximum profit.
6. Return the maximum profit obtained.
*/

// Time Complexity: O(n)
// Space Complexity: O(1)

public class LC121BestTimeToBuyAndSellStock {

    /**
     * Finds the maximum profit from a single buy and sell transaction.
     *
     * @param prices stock prices for each day
     * @return maximum achievable profit
     */
    public int maxProfit(int[] prices) {
        int minPrice = prices[0];
        int maxProfit = 0;

        for (int i = 1; i < prices.length; i++) {

            // update minimum buying price
            if (prices[i] < minPrice) {
                minPrice = prices[i];
            } else {

                // calculate profit if sold today
                maxProfit = Math.max(maxProfit, prices[i] - minPrice);
            }
        }

        return maxProfit;
    }
}