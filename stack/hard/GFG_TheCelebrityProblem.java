package stack.hard;

import java.util.Stack;

// Created at: 23-04-2025
// Last revised at: 23-04-2025
// Link: https://www.geeksforgeeks.org/problems/the-celebrity-problem/1
// Time Complexity: O(n)
// Space Complexity: O(n) — stack

/*
    Problem Description:
    Given an n×n matrix where matrix[i][j] = 1 means person i knows person j,
    find the celebrity — the one person who:
      - is known by everyone else, AND
      - knows nobody else.
    Return their index, or -1 if no celebrity exists.

    Approach: Stack-based elimination
    - Push all candidates onto the stack.
    - Repeatedly pop two candidates A and B:
        - If A knows B → A can't be the celebrity, push B back.
        - Otherwise     → B can't be the celebrity, push A back.
    - One candidate survives elimination. Verify them with two full passes:
        1. They must not know anyone.
        2. Everyone else must know them.
*/

class GFG_TheCelebrityProblem {

    /**
     * Finds the celebrity in a party of n people using stack-based elimination.
     *
     * @param matrix n×n adjacency matrix; matrix[i][j] = 1 means i knows j
     * @return index of the celebrity, or -1 if none exists
     */
    public int celebrity(int[][] matrix) {
        int len = matrix.length;
        if (len == 0)
            return -1;

        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < len; i++)
            st.push(i);

        // Elimination round — narrow down to one candidate
        while (st.size() > 1) {
            int A = st.pop();
            int B = st.pop();

            // A knows B → A is out; otherwise B is out
            if (matrix[A][B] == 1) {
                st.push(B);
            } else {
                st.push(A);
            }
        }

        int candidate = st.pop();

        // Verify: candidate must not know anyone
        for (int j = 0; j < len; j++) {
            if (candidate != j && matrix[candidate][j] == 1)
                return -1;
        }

        // Verify: everyone must know the candidate
        for (int j = 0; j < len; j++) {
            if (candidate != j && matrix[j][candidate] == 0)
                return -1;
        }

        return candidate;
    }
}