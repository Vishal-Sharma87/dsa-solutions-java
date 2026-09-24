package greedy.local_choice;

// Created at: 25-September-2026
// Last revised at: 25-September-2026
// Link: https://leetcode.com/problems/lemonade-change/

/*
Problem Description:
--------------------
Statement:
At a lemonade stand, each lemonade costs $5.

Customers pay using $5, $10, or $20 bills. You must provide the correct
change to each customer using the bills currently available.

Return true if you can provide correct change to every customer.
Otherwise, return false.

Example:
Input:
bills = [5,5,5,10,20]

Output:
true

Explanation:
- Receive $5 -> no change.
- Receive $5 -> no change.
- Receive $5 -> no change.
- Receive $10 -> give one $5.
- Receive $20 -> give one $10 and one $5.

Constraints:
- 1 <= bills.length <= 10^5
- bills[i] is either 5, 10, or 20.
*/

/*
Approach 1: Brute Force / Explicit Change Tracking

Idea:
Maintain the available bills and search for a combination that can provide
the required change for every customer.

Time Complexity:
O(n)

Space Complexity:
O(n)

Drawbacks:
Storing individual bills is unnecessary because only the counts of $5 and
$10 bills are relevant.
*/

/*
Approach 2: Greedy + Local Choice

Idea:
Track only the number of $5 and $10 bills.

For a $5 bill:
- Keep it as change for future customers.

For a $10 bill:
- Use one $5 as change.
- If no $5 is available, return false.

For a $20 bill:
- Prefer giving one $10 and one $5.
- Otherwise, give three $5 bills.
- If neither combination is possible, return false.

The $10 + $5 combination is preferred because $5 bills are more flexible
for future change.

Time Complexity:
O(n)

Space Complexity:
O(1)

Key Insight:
When receiving a $20 bill, preserve smaller denominations whenever
possible by using one $10 and one $5 instead of three $5 bills.
*/

/*
Method to Solve:
----------------
1. Maintain counts of $5 and $10 bills.
2. For a $5 bill, increment the $5 count.
3. For a $10 bill, use one $5 as change.
4. For a $20 bill, first try one $10 + one $5.
5. If that is unavailable, try three $5 bills.
6. Return false if neither option is possible.
7. Return true after serving every customer.
*/

// Time Complexity: O(n)
// Space Complexity: O(1)

public class LC860LemonadeChange {

    /**
     * Determines whether correct change can be provided to every customer.
     *
     * @param bills bills received from customers in order
     * @return true if every customer can receive correct change, otherwise false
     */
    public boolean lemonadeChange(int[] bills) {
        int fives = 0;
        int tens = 0;

        for (int bill : bills) {
            if (bill == 5) {
                fives++;
            } else if (bill == 10) {
                if (fives == 0) {
                    return false;
                }

                fives--;
                tens++;
            } else {
                // Prefer $10 + $5 to preserve $5 bills.
                if (tens > 0 && fives > 0) {
                    tens--;
                    fives--;
                } else if (fives >= 3) {
                    fives -= 3;
                } else {
                    return false;
                }
            }
        }

        return true;
    }
}