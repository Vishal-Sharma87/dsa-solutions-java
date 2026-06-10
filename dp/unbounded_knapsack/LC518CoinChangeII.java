package dp.unbounded_knapsack;

// Created at: 11-June-2026
// Last revised at: 11-June-2026
// Link: https://leetcode.com/problems/coin-change-ii/

/*
Problem Description:
--------------------
Statement:
Given an array of coin denominations and a target amount, return the number of
combinations that make up that amount. Each coin can be used an unlimited number
of times. Order does not matter — {1,5} and {5,1} count as one combination.

Example:
coins = [1, 2, 5], amount = 5
Output: 4
Combinations: {5}, {2,2,1}, {2,1,1,1}, {1,1,1,1,1}

Constraints:
- 1 <= coins.length <= 300
- 1 <= coins[i] <= 5000
- 0 <= amount <= 5000
- Answer is guaranteed to fit in a 32-bit integer (no MOD needed)
*/

/*
Key Insight — Unbounded Knapsack:
----------------------------------
Each coin has infinite supply. On include, index does NOT move forward — we stay
at the same index and reduce the amount. Include reads from curr[] (same row),
not prev[] (previous row), to allow reusing the same coin multiple times.

Difference from LC322 Coin Change:
- LC322 → minimize coin count (Math.min, sentinel BIG_NUMBER for unreachable)
- LC518 → count combinations (addition, 0 for unreachable amounts)
*/

/*
Approach 1: Brute Force (Recursion)
Idea:
At every index, make two choices:
- Include: stay at same index, reduce amount by coins[index]
- Exclude: move to previous index, amount unchanged

Base cases:
- amount == 0 → 1 valid combination found
- amount < 0  → 0 (invalid path)
- index == 0  → amount % coins[0] == 0 ? 1 : 0

Time Complexity:  O(exponential) — overlapping subproblems recomputed repeatedly
Space Complexity: O(amount) — max recursion depth
*/

/*
Approach 2: Memoization (Top-Down DP)
Idea:
Same as brute force but cache results at (index, amount).
Each unique (index, amount) state computed once.

Time Complexity:  O(n * amount)
Space Complexity: O(n * amount) — dp table + O(amount) recursion stack
*/

/*
Approach 3: Tabulation (Bottom-Up DP)
Idea:
Build dp[i][at] = number of ways to make amount at using denominations 0..i.
Base row: dp[0][at] = 1 if at is a multiple of coins[0], else 0.
Include reads from dp[index][at - coins[index]] (same row — unbounded property).

Time Complexity:  O(n * amount)
Space Complexity: O(n * amount)
*/

/*
Approach 4: Space Optimized (Two 1D Arrays with Swap)
Idea:
Since dp[i][at] depends only on dp[i-1][at] (exclude) and dp[i][at - coins[i]] (include),
maintain just prev[] and curr[] with reference swapping.

Key initializations:
- prev[0] = 1, multiples of coins[0] get 1, rest stay 0 (default)
- curr[0] = 1 per row (amount 0 always has exactly 1 way — take nothing)
- No sentinel needed — 0 correctly means "zero ways to make this amount"

Time Complexity:  O(n * amount)
Space Complexity: O(amount) — exactly two 1D arrays live at any point
*/

/*
Method to Solve:
----------------
1. Initialize prev[] for coins[0]: multiples of coins[0] get 1, rest stay 0.
2. For each index 1..n-1, fill curr[] with 0, set curr[0] = 1.
3. For each amount at 1..amount:
   - include: if at >= coins[index], read curr[at - coins[index]] (same row)
   - exclude: read prev[at]
   - curr[at] = include + exclude
4. Swap prev and curr references after each row.
5. Return prev[amount].
*/

import java.util.Arrays;

public class LC518CoinChangeII {

    /**
     * Returns the number of combinations that make up the given amount.
     * Uses space-optimized bottom-up DP with unbounded knapsack include logic
     * (include reads from curr[], not prev[], to allow coin reuse).
     *
     * @param amount target amount to achieve
     * @param coins  array of coin denominations
     * @return number of combinations that sum to amount
     */
    public int change(int amount, int[] coins) {
        int len = coins.length;

        int[] prev = new int[amount + 1];
        int[] curr = new int[amount + 1];
        int[] reference;

        // base case: only coins[0] available
        // exactly 1 way to make each multiple, 0 ways for non-multiples (default)
        prev[0] = 1;
        for (int at = coins[0]; at <= amount; at += coins[0]) {
            prev[at] = 1;
        }

        for (int index = 1; index < len; index++) {
            Arrays.fill(curr, 0);
            curr[0] = 1; // amount 0 always has exactly 1 way — take nothing

            for (int at = 1; at <= amount; at++) {
                int include = 0;

                if (at >= coins[index]) {
                    // read from curr (same row) — unbounded knapsack: reuse same coin
                    include = curr[at - coins[index]];
                }

                int exclude = prev[at];

                curr[at] = include + exclude;
            }

            // swap references — no new allocation
            reference = prev;
            prev = curr;
            curr = reference;
        }

        return prev[amount];
    }
}