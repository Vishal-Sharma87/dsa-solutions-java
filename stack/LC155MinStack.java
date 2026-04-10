
package stack;
// Link: https://leetcode.com/problems/min-stack/

// Time Complexity: O(1) for all operations
// Space Complexity: O(n)
// Created at: 11 - April - 2025
// Last revised at: 11 - April - 2025

import java.util.Stack;

/*
 * Problem Description:
 * Design a stack that supports push, pop, top, and retrieving the minimum
 * element in constant time.
 *
 * Implement the MinStack class:
 *   - MinStack()         — initializes the stack object
 *   - void push(int val) — pushes val onto the stack
 *   - void pop()         — removes the element on top
 *   - int top()          — gets the top element
 *   - int getMin()       — retrieves the minimum element in the stack
 *
 * Example:
 *   MinStack minStack = new MinStack();
 *   minStack.push(-2);
 *   minStack.push(0);
 *   minStack.push(-3);
 *   minStack.getMin(); // returns -3
 *   minStack.pop();
 *   minStack.top();    // returns 0
 *   minStack.getMin(); // returns -2
 *
 * Constraints:
 *   - -2^31 <= val <= 2^31 - 1
 *   - pop, top, getMin will always be called on non-empty stacks (per LC guarantee)
 *   - At most 3 * 10^4 calls will be made
 */

/*
 * Approaches:
 *
 * Approach 1 — Auxiliary min-stack
 *   Idea: Maintain a second stack that tracks the current minimum at each level.
 *         On push, push min(current, newVal) to the aux stack.
 *   Time: O(1) all ops
 *   Space: O(2n) — two stacks
 *   Drawback: Extra stack doubles memory usage.
 *
 * Approach 2 — Pair-per-node (chosen) ★
 *   Idea: Each stack node stores both its value and the running minimum at that
 *         point. getMin() just peeks the top's min field — no second stack needed.
 *   Time: O(1) all ops
 *   Space: O(n) — one stack, each node carries an extra int
 *   Key Insight: The minimum can only change when you push or pop, so encoding
 *                it per node is sufficient and avoids a parallel data structure.
 */

class Pair {
    int val;
    int min;

    Pair(int val, int min) {
        this.val = val;
        this.min = min;
    }
}

public class LC155MinStack {

    private final Stack<Pair> st;

    /** Initializes the stack. */
    public LC155MinStack() {
        this.st = new Stack<>();
    }

    /**
     * Pushes val onto the stack, recording the running minimum.
     *
     * @param val the value to push
     */
    public void push(int val) {
        if (st.isEmpty()) {
            st.push(new Pair(val, val));
        } else {
            // carry forward the smaller of current min and new value
            st.push(new Pair(val, Math.min(st.peek().min, val)));
        }
    }

    /**
     * Removes the top element from the stack.
     *
     * @throws RuntimeException if the stack is empty
     */
    public void pop() {
        if (st.isEmpty())
            throw new RuntimeException("Stack is empty");

        st.pop();
    }

    /**
     * Returns the value of the top element without removing it.
     *
     * @return top element's value
     * @throws RuntimeException if the stack is empty
     */
    public int top() {
        if (st.isEmpty())
            throw new RuntimeException("Stack is empty");

        return st.peek().val;
    }

    /**
     * Returns the minimum element currently in the stack in O(1).
     *
     * @return current minimum value
     * @throws RuntimeException if the stack is empty
     */
    public int getMin() {
        if (st.isEmpty())
            throw new RuntimeException("Stack is empty");

        // minimum at this depth is pre-computed on every push
        return st.peek().min;
    }
}