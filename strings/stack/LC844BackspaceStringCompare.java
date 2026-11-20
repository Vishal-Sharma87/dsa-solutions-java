package strings.stack;

// Created at: 20-November-2026
// Last revised at: 20-November-2026
// Link: https://leetcode.com/problems/backspace-string-compare/

import java.util.*;

/*
Problem Description:
--------------------
Given two strings s and t, return true if they are equal
when both are typed into empty text editors.

The '#' character represents a backspace operation.

A backspace on an empty string has no effect.

Example:
--------
Input: s = "ab#c", t = "ad#c"
Output: true

Explanation:
Both strings become "ac".

Input: s = "ab##", t = "c#d#"
Output: true

Explanation:
Both strings become empty.

Input: s = "a#c", t = "b"
Output: false

Constraints:
------------
1 <= s.length, t.length <= 200
s and t contain lowercase English letters and '#'.
*/

/*
Approach 1: Stack Simulation

Idea:
-----
1. Process both strings independently using stacks.
2. Push normal characters onto the stack.
3. For '#', pop the most recent character if available.
4. Compare the resulting stacks.

Comparison:
-----------
Both stacks store the resulting strings in reverse order.
Therefore, comparing their top elements is equivalent
to comparing their final characters.

Key Insight:
------------
Backspace removes the most recently entered character.
This naturally maps to the LIFO behavior of a stack.

Time Complexity:
----------------
O(N + M)

Each character is processed once, and the resulting
stacks are compared in linear time.

Space Complexity:
-----------------
O(N + M)

Both stacks can store all characters in their
respective strings.

Drawbacks:
---------
Requires auxiliary storage proportional to input size.
*/

/*
Method to Solve:
----------------
1. Create a stack for each input string.
2. Traverse each string and simulate backspace operations.
3. Reject immediately if the resulting stack sizes differ.
4. Compare characters from the top of both stacks.
5. Return true if both stacks become empty.
*/

public class LC844BackspaceStringCompare {

    /**
     * Compares two strings after applying backspace operations.
     *
     * @param s first input string
     * @param t second input string
     * @return true if both processed strings are equal
     */
    public boolean backspaceCompare(String s, String t) {
        Deque<Character> s1 = process(s);
        Deque<Character> s2 = process(t);

        if (s1.size() != s2.size()) {
            return false;
        }

        while (!s1.isEmpty() && s1.peek() == s2.peek()) {
            s1.pop();
            s2.pop();
        }

        return s1.isEmpty();
    }

    /**
     * Simulates backspace operations using a stack.
     *
     * @param s input string
     * @return stack containing the processed characters
     */
    private Deque<Character> process(String s) {
        Deque<Character> stack = new LinkedList<>();

        for (char ch : s.toCharArray()) {
            if (ch == '#') {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(ch);
            }
        }

        return stack;
    }

    // Time Complexity: O(N + M)
    // Space Complexity: O(N + M)
}