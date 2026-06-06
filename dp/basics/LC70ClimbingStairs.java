package dp.basics;

// Created at: 07-June-2025
// Last revised at: 07-June-2025
// Link: https://leetcode.com/problems/climbing-stairs/

/*
Problem Description:
--------------------
Statement:
You are climbing a staircase. It takes n steps to reach the top.
Each time you can either climb 1 or 2 steps.
In how many distinct ways can you climb to the top?

Example:
Input: n = 3
Output: 3
Explanation: 1+1+1, 1+2, 2+1

Constraints:
1 <= n <= 45
*/

/*
Approach 1: Brute Force (Pure Recursion)
Idea:
At each step, we have two choices — take 1 step or take 2 steps.
Total ways to reach n = ways to reach (n-1) + ways to reach (n-2).
Explore all possibilities recursively.

Time Complexity: O(2^n) — each call branches into two
Space Complexity: O(n) — recursion call stack depth

Drawbacks:
Same subproblems recomputed repeatedly.
fib(3) gets solved multiple times in fib(5) call tree.

---

Approach 2: Memoization (Top-Down DP)
Idea:
Same recursion as brute force, but store results of already-solved subproblems.
Before computing, check if dp[n] is already filled.

Time Complexity: O(n) — each subproblem solved once
Space Complexity: O(n) — dp array + call stack

Key Insight:
Eliminates redundant recomputation. Still top-down — solves only what's needed.

---

Approach 3: Tabulation (Bottom-Up DP)
Idea:
Build the solution iteratively from base cases.
dp[i] = dp[i-1] + dp[i-2]
Initialize dp[0] = 1, dp[1] = 1 and fill up to n.

Time Complexity: O(n)
Space Complexity: O(n) — dp array

Key Insight:
No recursion overhead. Fills all subproblems even if not needed, but cleaner for most cases.

---

Approach 4: Space Optimized
Idea:
dp[i] only depends on dp[i-1] and dp[i-2].
No need to store the full array — just keep two variables.

Time Complexity: O(n)
Space Complexity: O(1)

Key Insight:
Rolling variable trick. Shift window forward each iteration.
*/

/*
Method to Solve (Space Optimized):
------------------------------------
1. Initialize oneStepWays = 1 (ways to reach step 0 — base case)
   and twoStepWays = 0 (no step before step 0)
2. For each step i from 1 to n:
   a. Compute ithStepWays = oneStepWays + twoStepWays
   b. Shift: twoStepWays = oneStepWays
   c. Shift: oneStepWays = ithStepWays
3. Return oneStepWays
*/

public class LC70ClimbingStairs {

    /**
     * Brute force — explores all paths recursively.
     * Included as reference; not submitted.
     *
     * @param n number of steps
     * @return total distinct ways to reach step n
     */
    public int climbStairsBrute(int n) {
        if (n < 0)
            return 0;
        if (n == 0)
            return 1;

        return climbStairsBrute(n - 1) + climbStairsBrute(n - 2);
    }

    /**
     * Memoized recursion — caches already solved subproblems.
     * Call this from climbStairsMemo after initializing dp array.
     *
     * @param n  current step to solve
     * @param dp memoization array of size n+1
     * @return total distinct ways to reach step n
     */
    private int memoizedClimbStairs(int n, Integer[] dp) {
        if (n < 0)
            return 0;
        if (n == 0)
            return 1;

        if (dp[n] != null)
            return dp[n];

        return dp[n] = memoizedClimbStairs(n - 1, dp) + memoizedClimbStairs(n - 2, dp);
    }

    /**
     * Entry point for memoized approach.
     *
     * @param n number of steps
     * @return total distinct ways to reach step n
     */
    public int climbStairsMemo(int n) {
        Integer[] dp = new Integer[n + 1];
        return memoizedClimbStairs(n, dp);
    }

    /**
     * Bottom-up tabulation — builds solution from base cases up to n.
     *
     * @param n number of steps
     * @return total distinct ways to reach step n
     */
    public int climbStairsTabulation(int n) {
        Integer[] dp = new Integer[n + 1];

        dp[0] = 1;
        dp[1] = 1;

        for (int i = 2; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        return dp[n];
    }

    /**
     * Space optimized — only tracks the last two step counts.
     * Main submitted solution.
     *
     * @param n number of steps
     * @return total distinct ways to reach step n
     */
    public int climbStairs(int n) {
        int oneStepWays = 1; // ways to reach step 0
        int twoStepWays = 0; // nothing before step 0

        for (int i = 1; i <= n; i++) {
            int ithStepWays = oneStepWays + twoStepWays;

            // shift the window forward
            twoStepWays = oneStepWays;
            oneStepWays = ithStepWays;
        }

        return oneStepWays;
    }
}

// Time Complexity: O(n)
// Space Complexity: O(1)