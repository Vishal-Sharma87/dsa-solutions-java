package dp.subsequence;

// Created at: 10-June-2026
// Last revised at: 10-June-2026
// Link: https://www.geeksforgeeks.org/problems/partitions-with-given-difference/1

/*
Problem Description:
--------------------
Statement:
Given an array of non-negative integers and a difference D, count the number of ways
to partition the array into two subsets S1 and S2 such that S1 - S2 = D.
Since the answer can be large, return it modulo 1e9 + 7.

Example:
arr = [5, 2, 6, 4], diff = 3
Output: 1
S1 = {5, 6}, S2 = {2, 4} → 11 - 7 = 4 ❌
S1 = {5, 4}, S2 = {2, 6} → 9 - 8 = 1 ❌
S1 = {2, 6, 4}, S2 = {5} → 12 - 5 = 7 ❌
S1 = {6, 4}, S2 = {5, 2} → 10 - 7 = 3 ✓

Constraints:
- 1 <= arr.length <= 500
- 0 <= arr[i] <= 100
- 0 <= diff <= total sum of array
- Array elements are non-negative
- diff is guaranteed non-negative by GFG
*/

/*
Key Derivation:
---------------
Let S1 + S2 = totalSum and S1 - S2 = diff.
Adding both:  2 * S1 = totalSum + diff  →  S1 = (totalSum + diff) / 2

So the problem reduces to: count subsets with sum = (totalSum + diff) / 2
which is exactly the "Count Subsets with Given Sum" problem.

Base Case Checks (before DP):
1. diff > totalSum       → impossible, return 0
2. (totalSum + diff) % 2 == 1 → S1 won't be integer, return 0
*/

/*
Approach: Reduce + Space Optimized DP
Idea:
Derive target S1 = (totalSum + diff) / 2 using the mathematical reduction.
Apply base case guards, then delegate to perfectSum() — count subsets with sum S1.
perfectSum() uses two 1D arrays with reference swapping for O(target) space.

Time Complexity:  O(n * target) where target = (totalSum + diff) / 2
Space Complexity: O(target) — exactly two 1D arrays live at any point

Key insight on zeros in perfectSum:
nums[0] = 0 → both include and exclude contribute to sum 0 → prev[0] = 2
Initialization handles this: prev[t] = (t - nums[0] == 0 ? 1 : 0) + (t == 0 ? 1 : 0)
*/

/*
Method to Solve:
----------------
1. Compute totalSum of array.
2. Guard: if diff > totalSum or (totalSum + diff) is odd → return 0.
3. Compute requiredSum = (totalSum + diff) / 2.
4. Count subsets with sum = requiredSum using space-optimized DP.
5. Return count % MOD.
*/

public class GFG_CountPartitionsWithGivenDifference {

    /**
     * Counts the number of partitions of arr into two subsets
     * such that their difference equals diff.
     *
     * @param arr  array of non-negative integers
     * @param diff required difference between the two subset sums
     * @return count of valid partitions modulo 1e9+7
     */
    public int countPartitions(int[] arr, int diff) {
        int sum = 0;

        for (int val : arr)
            sum += val;

        // S1 = (sum + diff) / 2 must be a valid integer
        if (diff > sum || ((sum + diff) & 1) == 1)
            return 0;

        int requiredSum = (sum + diff) / 2;
        return new GFG_CountSubsetsWithGivenSum().perfectSum(arr, requiredSum);
    }
}