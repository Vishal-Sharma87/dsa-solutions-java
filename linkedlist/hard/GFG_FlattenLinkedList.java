package linkedlist.hard;

public class GFG_FlattenLinkedList {

    class FlatNode {

        int data;
        FlatNode next; // horizontal — moves across top-level heads

        FlatNode bottom; // vertical — moves down a column

        FlatNode(int data) {
            this.data = data;
            this.next = null;
            this.bottom = null;
        }
    }

    // Created at: 26 - March - 2026
    // Last revised at: 26 - March - 2026
    // GFG Link: https://www.geeksforgeeks.org/problems/flattening-a-linked-list/1
    // Time Complexity: O(n * m) — n horizontal FlatNodes, m average bottom-chain
    // length
    // Space Complexity: O(n) — recursion stack depth proportional to horizontal
    // length

    /*
     * FlatNode Declaration:
     * This problem uses a 2D linked list where each FlatNode carries two pointers:
     * - next : points to the next FlatNode in the horizontal (top-level) list
     * - bottom : points to the next FlatNode in the vertical (bottom-chain) list
     * All bottom-chains are individually sorted in ascending order.
     *
     * 
     *
     * ─────────────────────────────────────────────────────────────
     * Problem Description:
     * Given the head of a multi-level linked list where:
     * - The top-level list is connected via 'next' pointers (sorted ascending).
     * - Each top-level FlatNode may have a 'bottom' chain (also sorted ascending).
     * Flatten the entire structure into a single sorted linked list
     * connected only via 'bottom' pointers. Return its head.
     *
     * Example:
     * Input:
     * 5 -> 10 -> 19 -> 28
     * | | | |
     * 7 20 22 35
     * | | |
     * 8 50 40
     * | |
     * 30 45
     *
     * Output: 5 -> 7 -> 8 -> 10 -> 19 -> 20 -> 22 -> 28 -> 30 -> 35 -> 40 -> 45 ->
     * 50
     * (connected via bottom pointers, all next pointers become null)
     *
     * Constraints:
     * 1 <= number of top-level FlatNodes (n) <= 50
     * 1 <= length of each bottom chain (m) <= 20
     * 1 <= FlatNode.data <= 10^4
     *
     * ─────────────────────────────────────────────────────────────
     * Approaches:
     *
     * 1. Collect + Sort
     * Idea : Traverse every FlatNode in the 2D structure, collect all values
     * into a list, sort it, and rebuild a bottom-linked list.
     * Time : O(n * m * log(n * m))
     * Space : O(n * m)
     * Drawback: Ignores pre-sorted structure of each chain; wasteful sort.
     *
     * 2. Iterative Pairwise Merge
     * Idea : Maintain a running merged list. Traverse top-level FlatNodes
     * left to right, merging each bottom-chain into the running result.
     * Time : O(n * m)
     * Space : O(1)
     * Drawback: Correct but slightly harder to reason about chain boundaries
     * without a dummy head; iterative merge accumulates results
     * left-to-right which can cause repeated traversal of earlier FlatNodes.
     *
     * 3. ★ Recursive Right-to-Left Merge
     * Idea : Recursively flatten root.next first (right-to-left post-order),
     * which collapses all right-side FlatNodes into one sorted bottom-chain.
     * Then merge root's own bottom-chain with that result using a
     * classic sorted-merge routine (mergeUsingBottom).
     * Time : O(n * m)
     * Space : O(n) — recursion stack, one frame per top-level FlatNode
     * Key Insight: By the time we merge at any level, root.next is already
     * a fully flattened sorted chain — so every merge step is
     * a simple O(m) two-pointer sorted merge, not a re-sort.
     * ─────────────────────────────────────────────────────────────
     */

    /**
     * Merges two sorted bottom-chains into a single sorted bottom-chain.
     * Uses a dummy head FlatNode to simplify edge-case handling at the start.
     *
     * @param rootA head of the first sorted bottom-chain
     * @param rootB head of the second sorted bottom-chain
     * @return head of the merged sorted bottom-chain
     */
    public FlatNode mergeUsingBottom(FlatNode rootA, FlatNode rootB) {

        // Base cases: if either chain is empty, return the other
        if (rootA == null)
            return rootB;
        if (rootB == null)
            return rootA;

        // Dummy head avoids a special case for attaching the very first FlatNode
        FlatNode dummyHead = new FlatNode(-1);
        FlatNode tail = dummyHead;

        FlatNode moverA = rootA;
        FlatNode moverB = rootB;

        // Standard two-pointer merge on sorted chains — compare and link smaller
        while (moverA != null && moverB != null) {

            if (moverA.data < moverB.data) {
                tail.bottom = moverA; // attach smaller FlatNode from chain A
                moverA = moverA.bottom; // advance A down its chain
            } else {
                tail.bottom = moverB; // attach smaller FlatNode from chain B
                moverB = moverB.bottom; // advance B down its chain
            }
            tail = tail.bottom; // advance tail to the newly attached FlatNode
        }

        // Attach whichever chain still has remaining FlatNodes
        if (moverA != null)
            tail.bottom = moverA;
        if (moverB != null)
            tail.bottom = moverB;

        // Return merged chain, skipping the sentinel dummy head
        return dummyHead.bottom;
    }

    /**
     * Flattens a multi-level linked list (connected via 'next' and 'bottom'
     * pointers)
     * into a single sorted list connected only via 'bottom' pointers.
     * Processes the top-level list right-to-left so each recursive return
     * provides a ready-to-merge sorted chain.
     *
     * @param root the head of the multi-level linked list
     * @return the head of the fully flattened sorted bottom-chain
     */
    public FlatNode flatten(FlatNode root) {

        // Base case: empty list — nothing to flatten
        if (root == null)
            return root;

        // Recursively flatten everything to the right first;
        // after this call, root.next points to a fully sorted bottom-chain
        root.next = flatten(root.next);

        // Merge root's own bottom-chain with the already-flattened right portion
        return mergeUsingBottom(root, root.next);
    }
}
