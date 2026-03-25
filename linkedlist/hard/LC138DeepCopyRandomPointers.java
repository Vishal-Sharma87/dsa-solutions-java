package linkedlist.hard;

public class LC138DeepCopyRandomPointers {

    class Node {
        public int val;
        Node next;
        Node random;

        public Node(int x) {
            this.val = x;
            this.next = null;
            this.random = null;
        }
    }
    // Created at: 26 - March - 2026
    // Last revised at: 26 - March - 2026
    // LeetCode link: https://leetcode.com/problems/copy-list-with-random-pointer/
    // Time Complexity: O(n)
    // Space Complexity: O(1) — excluding the output list

    /*
     * Problem Description:
     * Given a linked list where each node has a 'next' pointer and a 'random'
     * pointer (which may point to any node in the list or null), construct a
     * deep copy of the list and return its head.
     * The deep copy must consist of entirely new nodes — no original node should
     * appear in the returned list.
     *
     * Example:
     * Input : [[7,null],[13,0],[11,4],[10,2],[1,0]]
     * (each pair is [val, random_index])
     * Output: A deep copy with identical structure and random linkage.
     *
     * Constraints:
     * 0 <= number of nodes <= 1000
     * -10^4 <= node.val <= 10^4
     * random points to some node in the list or null.
     *
     * ─────────────────────────────────────────────────────────────
     * Approaches:
     *
     * 1. HashMap (Original → Copy)
     * Idea : First pass: create all copy nodes and store a mapping
     * original → copy in a HashMap. Second pass: use the map to
     * wire up next and random pointers on every copy node.
     * Time : O(n)
     * Space : O(n) — HashMap holds n entries
     * Drawback: O(n) extra space for the map; straightforward but not optimal.
     *
     * 2. ★ Interleaved Clone (Weave + Fix Random + Unweave)
     * Idea : Three-pass in-place approach with O(1) extra space:
     *
     * Pass 1 — Weave:
     * Insert each node's clone immediately after it in the original list.
     * Original: A -> B -> C becomes A -> A' -> B -> B' -> C -> C'
     * This makes every original node's clone reachable via node.next,
     * eliminating the need for a HashMap.
     *
     * Pass 2 — Fix Random:
     * For each original node, its clone's random = original.random.next
     * (since the clone of any node X is always X.next after weaving).
     *
     * Pass 3 — Unweave:
     * Separate the interleaved list back into the original list and the
     * cloned list by relinking next pointers.
     *
     * Time : O(n)
     * Space : O(1)
     * Key Insight: Weaving clones directly after originals creates a temporary
     * implicit map (original.next = its clone) without any
     * auxiliary data structure.
     * ─────────────────────────────────────────────────────────────
     */

    /**
     * Constructs a deep copy of a linked list where each node contains
     * an additional random pointer that may reference any node or null.
     *
     * @param head the head node of the original linked list
     * @return the head of the deep-copied linked list
     */
    public Node copyRandomList(Node head) {

        // Edge case: empty list — nothing to copy
        if (head == null)
            return null;

        // ── Pass 1: Weave ────────────────────────────────────────────────────────
        // Insert a clone of each node immediately after the original node.
        // After this pass: original.next == its clone, for every node.
        Node mover = head;
        while (mover != null) {
            Node newNode = new Node(mover.val);
            newNode.next = mover.next; // clone points to original's successor
            mover.next = newNode; // original now points to its clone
            mover = newNode.next; // advance to the next original node
        }

        // ── Pass 2: Fix Random Pointers ──────────────────────────────────────────
        // For each original node, its clone sits at original.next.
        // The clone of any random target X is X.next — use this to wire randoms.
        mover = head;
        while (mover != null) {
            Node nextOriginal = mover.next.next; // the next original node

            if (mover.random != null)
                mover.next.random = mover.random.next; // clone's random = clone of original's random

            mover = nextOriginal; // advance to the next original node
        }

        // ── Pass 3: Unweave ──────────────────────────────────────────────────────
        // Separate the interleaved list back into the original and cloned lists.
        mover = head;
        Node randomHead = mover.next; // head of the deep-copied list

        while (mover != null && mover.next != null) {
            Node nextNode = mover.next.next; // next original node (or null)

            if (nextNode != null)
                mover.next.next = nextNode.next; // clone's next points to next clone

            mover.next = nextNode; // restore original's next pointer
            mover = nextNode; // advance to next original node
        }

        return randomHead;
    }
}