package math.advanced;

// Created at: 22-July-2026
// Last revised at: 22-July-2026
// Link: https://www.geeksforgeeks.org/problems/all-divisors-of-a-number/1

/*
Problem Description:
--------------------
Statement:
Given a positive integer n, return all of its divisors in increasing order.

Example:
Input:
n = 36

Output:
1 2 3 4 6 9 12 18 36

Constraints:
1 <= n <= 10^8
*/

/*
Approach 1: Brute Force

Idea:
Iterate from 1 to n and check whether each number divides n.

Time Complexity:
O(n)

Space Complexity:
O(1)

Drawbacks:
Performs unnecessary checks, making it inefficient for large values of n.
*/

/*
Approach 2: Square Root Traversal (Optimized)

Idea:
Every divisor smaller than √n has a complementary divisor greater than √n.
Iterate only up to √n, store the smaller divisor immediately, and push the
complementary divisor onto a stack. Finally, pop the stack to obtain the
remaining divisors in increasing order.

Time Complexity:
O(√n)

Space Complexity:
O(√n)

Key Insight:
Divisors always occur in pairs. Using a stack preserves the sorted order
without requiring an additional sorting step.
*/

/*
Method to Solve:
----------------
1. Traverse from 1 to √n.
2. If the current number divides n, store it.
3. Push its complementary divisor when it is different.
4. Append all complementary divisors by popping the stack.
5. Return the complete list.
*/

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;

class GFG_AllDivisorsOfANumber {

    /**
     * Returns all divisors of the given number in increasing order.
     *
     * @param n positive integer
     * @return list containing all divisors in sorted order
     */
    public ArrayList<Integer> getDivisors(int n) {

        ArrayList<Integer> divisors = new ArrayList<>();
        Deque<Integer> complementaryDivisors = new LinkedList<>();

        // find divisor pairs
        for (int divisor = 1; divisor * divisor <= n; divisor++) {

            if (n % divisor == 0) {

                divisors.add(divisor);

                // avoid duplicate for perfect squares
                if (divisor != n / divisor) {
                    complementaryDivisors.push(n / divisor);
                }
            }
        }

        // append larger divisors
        while (!complementaryDivisors.isEmpty()) {
            divisors.add(complementaryDivisors.pop());
        }

        return divisors;
    }
}

// Time Complexity: O(√n)
// Space Complexity: O(√n)