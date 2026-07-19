package stack.monotonicstack;

// Created at: 20-July-2026
// Last revised at: 20-July-2026
// Link: https://leetcode.com/problems/smallest-subsequence-of-distinct-characters/

/*
Problem Description:
--------------------
Statement:
Given a string s, return the lexicographically smallest subsequence of s
that contains every distinct character exactly once.

Example:
Input: s = "bcabc"
Output: "abc"

Input: s = "cbacdcbc"
Output: "acdb"

Constraints:
- 1 <= s.length <= 1000
- s consists of lowercase English letters.
*/

/*
Approach 1: Brute Force

Idea:
Generate every subsequence containing all distinct characters exactly once
and return the smallest lexicographically.

Time Complexity:
O(2^n)

Space Complexity:
O(2^n)

Drawbacks:
Impossible for larger input sizes.
*/

/*
Approach 2: Monotonic Stack (Optimal)

Idea:
1. Store the last occurrence of every character.
2. Traverse the string once.
3. Ignore characters already included.
4. While the current character is smaller than the stack top and the stack
   top appears again later, remove it.
5. Push the current character into the stack.

This guarantees:
- Every character appears exactly once.
- Characters remain in the smallest possible lexicographical order.

Time Complexity:
O(n)

Space Complexity:
O(1)

Key Insight:
A larger character can be removed only if it will appear again later.
*/

import java.util.*;

/*
Method to Solve:
----------------
1. Record the last occurrence of every character.
2. Maintain a monotonic increasing stack.
3. Skip already visited characters.
4. Remove larger characters that can still be used later.
5. Push the current character.
6. Build the answer from the stack.
*/

public class LC1081SmallestSubsequenceOfDistinctCharacters {

    /**
     * Returns the lexicographically smallest subsequence containing every
     * distinct character exactly once.
     *
     * @param s input string
     * @return smallest valid subsequence
     */
    public String smallestSubsequence(String s) {

        Map<Integer, Integer> lastOccurrence = new HashMap<>();

        for (int i = s.length() - 1; i >= 0; i--) {
            lastOccurrence.putIfAbsent(s.charAt(i) - 'a', i);
        }

        Deque<Integer> stack = new LinkedList<>();
        boolean[] visited = new boolean[26];

        for (int i = 0; i < s.length(); i++) {

            char current = s.charAt(i);

            if (visited[current - 'a']) {
                continue;
            }

            while (!stack.isEmpty()) {

                // cannot remove if this is the last occurrence
                if (lastOccurrence.get(stack.peek()) < i) {
                    break;
                }

                // stack is already lexicographically smaller
                if (stack.peek() < current - 'a') {
                    break;
                }

                visited[stack.pop()] = false;
            }

            stack.push(current - 'a');
            visited[current - 'a'] = true;
        }

        StringBuilder answer = new StringBuilder();

        for (int ch : stack) {
            answer.append((char) (ch + 'a'));
        }

        return answer.reverse().toString();
    }
}

// Time Complexity: O(n)
// Space Complexity: O(1)