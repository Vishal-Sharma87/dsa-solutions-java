package dp.unbounded_knapsack;

// Created at: 11-June-2026
// Last revised at: 11-June-2026
// Link: https://leetcode.com/problems/coin-change/

/*
Problem Description:
--------------------
Statement:
Given an array of coin denominations and a target amount, return the minimum number
of coins needed to make up that amount. If the amount cannot be made up by any
combination of coins, return -1. Each coin can be used an unlimited number of times.

Example:
coins = [1, 5, 6, 9], amount = 11
Output: 2
Explanation: 5 + 6 = 11 → 2 coins

Constraints:
- 1 <= coins.length <= 12
- 1 <= coins[i] <= 2^31 - 1
- 0 <= amount <= 10^4
*/

/*
Key Insight — Unbounded Knapsack:
----------------------------------
Unlike 0/1 knapsack where each element is used at most once, here each coin has
infinite supply. This means on include, the index does NOT move forward — we stay
at the same index and reduce the amount. This is the defining property of
Unbounded Knapsack.

Consequence in space-optimized DP:
- 0/1 knapsack include reads from prev[] (previous index row)
- Unbounded knapsack include reads from curr[] (same index row)
  → allows reusing the same coin multiple times
*/

/*
Approach 1: Brute Force (Recursion)
Idea:
At every index, make two choices:
- Include: stay at same index, reduce amount by coins[index], add 1 to coin count
- Exclude: move to previous index, amount unchanged

Base cases:
- amount == 0 → 0 coins needed
- amount < 0  → BIG_NUMBER (invalid path)
- index == 0  → amount % coins[0] == 0 ? amount / coins[0] : BIG_NUMBER

Time Complexity:  O(exponential) — overlapping subproblems recomputed repeatedly
Space Complexity: O(amount) — max recursion depth is amount (when coins[i] = 1)
*/

/*
Approach 2: Memoization (Top-Down DP)
Idea:
Same as brute force but cache results at (index, amount) to avoid recomputation.
Each unique (index, amount) state computed once.

Time Complexity:  O(n * amount) — n * amount unique states, O(1) per state
Space Complexity: O(n * amount) — dp table + O(amount) recursion stack
*/

/*
Approach 3: Tabulation (Bottom-Up DP)
Idea:
Build dp[i][at] = min coins using denominations 0..i to make amount at.
Initialize dp[i][0] = 0 for all i (amount 0 always achievable with 0 coins).
Base row: dp[0][at] = at / coins[0] if divisible, else BIG_NUMBER.
Include reads from dp[index][at - coins[index]] (same row — unbounded property).

Time Complexity:  O(n * amount)
Space Complexity: O(n * amount)
*/

/*
Approach 4: Space Optimized (Two 1D Arrays with Swap)
Idea:
Since dp[i][at] depends only on dp[i-1][at] (exclude) and dp[i][at - coins[i]] (include),
maintain just prev[] and curr[] with reference swapping.

Key initializations per row:
- Arrays.fill(curr, BIG_NUMBER) — unreachable by default
- curr[0] = 0 — amount 0 always needs 0 coins (inner loop starts at at=1, so explicit)

BIG_NUMBER = 100_000_000:
- Safe sentinel for "unreachable" — adding 1 won't overflow Integer.MAX_VALUE
- Final check: if prev[amount] == BIG_NUMBER → return -1

Time Complexity:  O(n * amount)
Space Complexity: O(amount) — exactly two 1D arrays live at any point
*/

/*
Method to Solve:
----------------
1. Handle amount == 0 early → return 0.
2. Initialize prev[] for coins[0]: multiples get at/coins[0], rest stay BIG_NUMBER.
3. For each index 1..n-1, fill curr[] fresh (BIG_NUMBER), set curr[0] = 0.
4. For each amount at 1..amount:
   - include: if at >= coins[index], read curr[at - coins[index]] + 1 (same row)
   - exclude: read prev[at]
   - curr[at] = min(include, exclude)
5. Swap prev and curr references after each row.
6. Return prev[amount] == BIG_NUMBER ? -1 : prev[amount].
*/

import java.util.Arrays;

public class LC322CoinChange {

    private static final int BIG_NUMBER = 100_000_000;

    /**
     * Returns the minimum number of coins needed to make up the given amount.
     * Uses space-optimized bottom-up DP with unbounded knapsack include logic
     * (include reads from curr[], not prev[], to allow coin reuse).
     *
     * @param coins  array of coin denominations
     * @param amount target amount to achieve
     * @return minimum coins needed, or -1 if amount cannot be made up
     */
    public int coinChange(int[] coins, int amount) {
        if (amount == 0)
            return 0;

        int len = coins.length;

        int[] prev = new int[amount + 1];
        int[] curr = new int[amount + 1];
        int[] reference;

        // base case: only coins[0] available
        // non-multiples stay BIG_NUMBER (unreachable)
        Arrays.fill(prev, BIG_NUMBER);
        prev[0] = 0;

        for (int at = coins[0]; at <= amount; at += coins[0]) {
            prev[at] = at / coins[0];
        }

        for (int index = 1; index < len; index++) {
            Arrays.fill(curr, BIG_NUMBER);
            curr[0] = 0; // amount 0 always achievable with 0 coins; loop starts at at=1

            for (int at = 1; at <= amount; at++) {
                int includeCoins = BIG_NUMBER;

                if (at >= coins[index]) {
                    // read from curr (same row) — unbounded knapsack: reuse same coin
                    includeCoins = curr[at - coins[index]] + 1;
                }

                int excludeCoins = prev[at];

                curr[at] = Math.min(includeCoins, excludeCoins);
            }

            // swap references — no new allocation
            reference = prev;
            prev = curr;
            curr = reference;
        }

        return prev[amount] == BIG_NUMBER ? -1 : prev[amount];
    }
}