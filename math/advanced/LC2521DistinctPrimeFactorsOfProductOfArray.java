package math.advanced;

// Created at: 19-July-2026
// Last revised at: 19-July-2026
// Link: https://leetcode.com/problems/distinct-prime-factors-of-product-of-array/

/*
Problem Description:
--------------------
Statement:
Given an integer array nums, return the number of distinct prime factors in the
product of all elements of the array.

Example:
Input: nums = [2,4,3,7,10,6]
Output: 4

Input: nums = [2,4,8,16]
Output: 1

Constraints:
- 1 <= nums.length <= 10^4
- 2 <= nums[i] <= 1000
*/

/*
Approach 1: Compute Product Then Factorize

Idea:
Multiply all numbers together and then find the distinct prime factors of the
result.

Time Complexity:
Not practical

Space Complexity:
O(1)

Drawbacks:
The product quickly exceeds integer and even long limits, making this approach
infeasible.
*/

/*
Approach 2: Factorize Each Number Individually (Optimal)

Idea:
Prime factorize every element using trial division. Store each discovered prime
factor in a HashSet. Since a set keeps only unique values, the final size gives
the number of distinct prime factors.

Time Complexity:
O(n * √m)
where m is the maximum value in nums.

Space Complexity:
O(k)
where k is the number of distinct prime factors.

Key Insight:
The prime factors of the overall product are simply the union of the prime
factors of each individual element.
*/

import java.util.HashSet;
import java.util.Set;

public class LC2521DistinctPrimeFactorsOfProductOfArray {

    /*
     * Method to Solve:
     * ----------------
     * 1. Create a set to store distinct prime factors.
     * 2. Factorize every number using trial division.
     * 3. Add every discovered prime factor to the set.
     * 4. If a prime factor greater than √n remains, add it.
     * 5. Return the size of the set.
     */

    /**
     * Returns the number of distinct prime factors in the product
     * of all array elements.
     *
     * @param nums input array
     * @return number of distinct prime factors
     */
    public int distinctPrimeFactors(int[] nums) {
        Set<Integer> primeFactors = new HashSet<>();

        for (int num : nums) {
            collectPrimeFactors(num, primeFactors);
        }

        return primeFactors.size();
    }

    /**
     * Collects all prime factors of a number.
     *
     * @param number       input number
     * @param primeFactors set storing distinct prime factors
     * @return void
     */
    private void collectPrimeFactors(int number, Set<Integer> primeFactors) {

        // remove all occurrences of each prime factor
        for (int divisor = 2; divisor * divisor <= number; divisor++) {
            while (number % divisor == 0) {
                primeFactors.add(divisor);
                number /= divisor;
            }
        }

        // remaining number is prime
        if (number > 1) {
            primeFactors.add(number);
        }
    }
}

// Time Complexity: O(n * √m)
// Space Complexity: O(k)