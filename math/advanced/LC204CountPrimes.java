package math.advanced;

// Created at: 24-July-2026
// Last revised at: 24-July-2026
// Link: https://leetcode.com/problems/count-primes/

/*
Problem Description:
--------------------
Statement:
Given an integer n, return the number of prime numbers that are strictly less than n.

A prime number is a natural number greater than 1 that has exactly two positive divisors:
1 and itself.

Example:

Input:
n = 10

Output:
4

Explanation:
Prime numbers less than 10 are:

2, 3, 5, 7

Hence, answer = 4.

Constraints:

0 <= n <= 5 * 10^6
*/

/*
Approach 1: Brute Force

Idea:
For every number from 2 to n - 1,
check whether it is prime by trying every possible divisor.

Count all numbers that are prime.

Time Complexity:
O(n * √n)

Space Complexity:
O(1)

Drawbacks:
The same divisibility checks are repeated for every number,
making it inefficient for larger values of n.
*/

/*
Approach 2: Sieve of Eratosthenes (Current Solution)

Idea:
Instead of checking every number individually,
pre-compute all composite numbers.

Initially, assume every number is prime.

Whenever a prime number is found,
mark all of its multiples as composite.

Numbers that remain unmarked are prime.

Why does this work?

Suppose 2 is prime.

Then

4
6
8
10
...

cannot be prime because they are divisible by 2.

Similarly,

when 3 is found to be prime,

mark

6
9
12
15
...

Continue this process until all composite numbers
have been marked.

Finally,
count every unmarked number.

Time Complexity:
O(n log log n)

Space Complexity:
O(n)

Key Insight:
Every composite number is marked by one of its prime factors,
so we never need to perform repeated primality checks.
*/

/*
Method to Solve:
----------------
1. Create a boolean array to track composite numbers.
2. Iterate from 2 to n - 1.
3. If the current number is not marked, it is prime.
4. Increase the prime count.
5. Mark all multiples of the current prime as composite.
6. Return the total count.
*/

public class LC204CountPrimes {

    /**
     * Counts all prime numbers strictly less than n.
     *
     * @param n upper limit (exclusive)
     * @return total number of prime numbers less than n
     */
    public int countPrimes(int n) {

        int primeCount = 0;

        // false -> prime candidate
        // true -> composite
        boolean[] notPrime = new boolean[n];

        for (int i = 2; i < n; i++) {

            // current number is prime
            if (!notPrime[i]) {

                primeCount++;

                // mark all multiples as composite
                for (int j = 2; j * i < n; j++) {
                    notPrime[j * i] = true;
                }
            }
        }

        return primeCount;
    }
}

// Time Complexity: O(n log log n)
// Space Complexity: O(n)