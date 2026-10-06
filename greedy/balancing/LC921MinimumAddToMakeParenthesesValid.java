package greedy.balancing;

// Created at: 06-October-2026
// Last revised at: 06-October-2026
// Link: https://leetcode.com/problems/minimum-add-to-make-parentheses-valid/

/*
Problem Description:
--------------------
Statement:
Given a string s consisting of '(' and ')', return the minimum number of parentheses
that must be added to make the string valid.

A valid parentheses string must have every opening parenthesis matched with a
corresponding closing parenthesis, with correct ordering.

Example:
Input:  s = "())"
Output: 1

Input:  s = "((("
Output: 3

Constraints:
1 <= s.length <= 1000
s[i] is '(' or ')'.
*/

/*
Approach 1: Greedy / Balance Tracking

Idea:
Maintain the number of currently unmatched opening parentheses.

- For '(' increase the open count.
- For ')' match an available opening parenthesis if possible.
- If no opening parenthesis is available, this ')' is unmatched and must
  eventually be paired with an added '('.

At the end:
- open = unmatched '(' that need closing ')'.
- closeEncountered = unmatched ')' that need opening '('.

The answer is their sum.

Time Complexity:
O(n)

Space Complexity:
O(1)

Drawbacks:
None for this problem. This is the optimal one-pass solution.

Key Insight:
Every unmatched closing parenthesis requires one added opening parenthesis,
while every remaining unmatched opening parenthesis requires one added
closing parenthesis.
*/

/*
Method to Solve:
----------------
1. Initialize open to track unmatched '('.
2. Initialize closeEncountered to track unmatched ')'.
3. Traverse the string.
4. For '(' increment open.
5. For ')', match it with an available opening parenthesis if possible.
6. Otherwise, count it as an unmatched closing parenthesis.
7. Return open + closeEncountered.
*/

// Time Complexity: O(n)
// Space Complexity: O(1)

class LC921MinimumAddToMakeParenthesesValid {

    /**
     * Finds the minimum number of parentheses needed to make the string valid.
     *
     * @param s input string containing '(' and ')'
     * @return minimum number of parentheses that must be added
     */
    public int minAddToMakeValid(String s) {
        int open = 0;
        int closeEncountered = 0;

        for (char curr : s.toCharArray()) {
            if (curr == '(') {
                open++;
            } else {
                if (open != 0) {
                    open--;
                } else {
                    closeEncountered++;
                }
            }
        }

        return open + closeEncountered;
    }
}