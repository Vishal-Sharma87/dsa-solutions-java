package stack.parentheses;

import java.util.Deque;
import java.util.LinkedList;

// Created at: 05-October-2026
// Last revised at: 05-October-2026
// Link: https://leetcode.com/problems/score-of-parentheses/

/*
Problem Description:
--------------------
Statement:
Given a balanced parentheses string s, return the score of the string.

The scoring rules are:
- "()" has score 1.
- AB has score A + B, where A and B are balanced parentheses strings.
- (A) has score 2 * A.

Example:
Input: s = "()"
Output: 1

Input: s = "(())"
Output: 2

Input: s = "()()"
Output: 2

Constraints:
- 2 <= s.length <= 50
- s consists only of '(' and ')'.
- s is a balanced parentheses string.
*/

/*
Approach 1: Stack

Idea:
Use a stack to keep track of open parentheses and completed scores.

For every closing parenthesis:
1. Accumulate all scores inside the matching '('.
2. If nothing was inside, the pair is "()" and contributes 1.
3. Otherwise, multiply the inner score by 2.
4. Push the calculated score back onto the stack.

At the end, sum all remaining scores to handle consecutive groups such as "()()".

Time Complexity:
O(n)

Space Complexity:
O(n)

Drawbacks:
Uses a String-based stack, which requires converting between String and int.
A typed stack such as Deque<Integer> can be cleaner, but the current
implementation is still optimal in asymptotic complexity.

Key Insight:
A closing parenthesis finalizes one complete group. The stack lets us
evaluate the innermost group first and apply the 2 * score rule when needed.
*/

/*
Method to Solve:
----------------
1. Push every opening parenthesis onto the stack.
2. When ')' is found, sum all completed scores until '(' is reached.
3. Remove the matching '('.
4. Convert the group into 1 for "()" or 2 * innerScore for "(A)".
5. Push the calculated score back onto the stack.
6. Sum the remaining scores to get the final answer.
*/

public class LC856ScoreOfParentheses {

    /**
     * Calculates the score of a balanced parentheses string.
     *
     * @param s balanced parentheses string
     * @return score of the parentheses string
     */
    public int scoreOfParentheses(String s) {
        Deque<String> stack = new LinkedList<>();

        for (char curr : s.toCharArray()) {
            if (curr == '(') {
                stack.push("(");
            } else {
                int innerScore = 0;

                while (!stack.isEmpty() && !stack.peek().equals("(")) {
                    innerScore += Integer.parseInt(stack.pop());
                }

                if (stack.isEmpty()) {
                    // defensive check for an unmatched ')'
                    throw new IllegalArgumentException("unbalanced parenthesis");
                }

                stack.pop();

                int score = innerScore == 0 ? 1 : 2 * innerScore;
                stack.push(Integer.toString(score));
            }
        }

        int finalScore = 0;

        while (!stack.isEmpty()) {
            finalScore += Integer.parseInt(stack.pop());
        }

        return finalScore;
    }
}

// Time Complexity: O(n)
// Space Complexity: O(n)
