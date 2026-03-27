package recursion.basics;

import java.util.Stack;

public class GFG_SortStack {
    // Created at: 28 - March - 2026
    // Last revised at: 28 - March - 2026
    // LeetCode link: https://www.geeksforgeeks.org/problems/sort-a-stack/1
    // Time Complexity: O(n²)
    // Space Complexity: O(n)

    /*
     * Problem Description:
     * Given a stack, sort it in descending order (largest element on top)
     * using recursion. No loops allowed.
     *
     * Example:
     * Input: stack = [3, 1, 4, 2] (top → 2)
     * Output: stack = [4, 3, 2, 1] (top → 4... wait, largest on top)
     * stack = [1, 2, 3, 4] (top → 4)
     *
     * Constraints:
     * - 1 <= size of stack <= 100
     * - Elements can be negative
     *
     * -----------------------------------------------------------------------
     * Approaches:
     *
     * 1. Using auxiliary stack
     * Idea: Pop all elements, push them into a second stack in sorted order.
     * Time: O(n²)
     * Space: O(n) for the auxiliary stack
     * Drawback: Uses extra space.
     *
     * ★ 2. Recursive (implemented)
     * Idea: sortStack peels the top off, recursively sorts what remains,
     * then calls insertInSortedStack to drop the element into the
     * right position. insertInSortedStack works similarly — remove
     * elements until it finds the right spot, insert, then restore.
     * Time: O(n²) — n insertions, each O(n) in the worst case
     * Space: O(n) — call stack depth
     * Key Insight: The call stack itself acts as temporary storage,
     * replacing the need for any auxiliary data structure.
     */

    /**
     * Inserts val into its correct position in an already-sorted stack,
     * maintaining descending order (largest on top).
     *
     * @param st  the sorted stack to insert into
     * @param val the value to insert
     */
    public void insertInSortedStack(Stack<Integer> st, int val) {

        // val belongs on top
        if (st.isEmpty() || st.peek() <= val) {
            st.push(val);
            return;
        }

        // current top is bigger, so hold it aside and go deeper
        int data = st.pop();
        insertInSortedStack(st, val);

        // restore
        st.push(data);
    }

    /**
     * Sorts the stack in descending order (largest element on top)
     * using recursion only — no loops or auxiliary data structures.
     *
     * @param st the stack to sort in place
     */
    public void sortStack(Stack<Integer> st) {

        if (st.isEmpty())
            return;

        int data = st.pop();

        // sort the rest first
        sortStack(st);

        // drop data into its correct spot
        insertInSortedStack(st, data);
    }
}
