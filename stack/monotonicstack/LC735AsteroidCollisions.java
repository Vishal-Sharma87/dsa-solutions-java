package stack.monotonicstack;

import java.util.Stack;

public class LC735AsteroidCollisions {
    // LC 735 · Asteroid Collision
    // https://leetcode.com/problems/asteroid-collision/
    //
    // Created at : 17-April-2025
    // Last revised : 17-April-2025

    /*
     * Problem Description:
     *
     * Given an array of integers `asteroids` representing asteroids in a row,
     * each asteroid moves at the same speed. Positive value = moving right,
     * negative value = moving left. The absolute value is its size.
     *
     * When two asteroids meet, the smaller one explodes. If equal, both explode.
     * Two asteroids moving in the same direction never collide.
     *
     * Return the state of the asteroids after all collisions.
     *
     * Example:
     * Input : [5, 10, -5]
     * Output: [5, 10] → -5 collides with 10 and explodes
     *
     * Input : [8, -8]
     * Output: [] → equal size, both explode
     *
     * Constraints:
     * - 2 <= asteroids.length <= 10^4
     * - -1000 <= asteroids[i] <= 1000
     * - asteroids[i] != 0
     */

    /*
     * Approach: Right-to-Left Stack Simulation ★
     *
     * Idea : Traverse right to left. Push negative asteroids (moving left)
     * onto the stack immediately — they'll fight whatever positive
     * asteroid we find to their left. When we hit a positive asteroid,
     * it battles the stack's top (negative) ones: pop smaller negatives,
     * skip equal (mutual destroy), push if no survivor on the stack blocks it.
     * Popping the stack into the result array at the end restores
     * left-to-right order.
     *
     * Time : O(n) — each asteroid is pushed and popped at most once
     * Space : O(n) — stack in the worst case holds all asteroids
     * Drawbacks : Reverse traversal is less intuitive than left-to-right;
     * Stack<Integer> boxes primitives (minor overhead vs Deque<Integer>)
     */

    /**
     * Simulates asteroid collisions and returns the surviving asteroids.
     *
     * @param asteroids array of non-zero integers; positive = right, negative =
     *                  left
     * @return array of surviving asteroids in their original relative order
     */
    public int[] asteroidCollision(int[] asteroids) {

        Stack<Integer> st = new Stack<>();

        for (int i = asteroids.length - 1; i >= 0; i--) {

            if (asteroids[i] < 0) {
                // moving left — no threat yet, queue it up for future right-movers
                st.push(asteroids[i]);
            } else {
                // moving right — fight every smaller left-mover already on the stack
                while (st.size() > 0 && st.peek() < 0 && -1 * st.peek() < asteroids[i])
                    st.pop();

                if (st.isEmpty() || st.peek() > 0) {
                    // no left-mover survived, or next one also moves right — safe to push
                    st.push(asteroids[i]);
                } else if (-1 * st.peek() == asteroids[i]) {
                    // equal size — both explode
                    st.pop();
                }
                // else st.peek() is a stronger left-mover; current positive is destroyed, skip
            }
        }

        // stack bottom → top is right → left; pop order gives left → right
        int[] result = new int[st.size()];
        for (int i = 0; i < result.length; i++) {
            result[i] = st.pop();
        }
        return result;
    }
}

// Time Complexity : O(n)
// Space Complexity : O(n)
