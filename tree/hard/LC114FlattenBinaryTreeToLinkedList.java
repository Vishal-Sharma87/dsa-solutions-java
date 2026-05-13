package tree.hard;

import tree.TreeNode;

// Created at: 14-May-2026
// Last revised at: 14-May-2026
// Link: https://leetcode.com/problems/flatten-binary-tree-to-linked-list/

/*
Problem Description:
--------------------
Statement:
Given the root of a binary tree, flatten the tree into a "linked list"
in-place using the same TreeNode class. The right pointer of each node
should point to the next node in the flattened list (pre-order traversal
order), and the left pointer of every node must be null.

Example:
Input:
        1
       / \
      2   5
     / \   \
    3   4   6

Output: 1 -> 2 -> 3 -> 4 -> 5 -> 6 (right pointers, all lefts null)

Constraints:
- The number of nodes in the tree is in the range [0, 2000].
- -100 <= Node.val <= 100
*/

/*
Approach 1: Brute Force
Idea:
Do a pre-order traversal, collect all nodes in a list,
then rewire their right pointers sequentially and null out lefts.

Time Complexity: O(n)
Space Complexity: O(n) — extra list to store all nodes

Drawbacks:
Needs extra space proportional to node count.
*/

/*
Approach 2: Recursive In-Place (used here) ★
Idea:
Recursively flatten the left and right subtrees independently,
then stitch them together in pre-order order:
  root → flattened_left → flattened_right

This avoids any auxiliary list.

Time Complexity: O(n) — each node visited once; tail traversal adds
                 at most O(n) total across all recursive calls (amortized).
Space Complexity: O(h) — recursion stack where h is the tree height.
                  O(log n) for balanced, O(n) worst case for skewed.

Key Insight:
Save root.right before overwriting it, flatten left and hang it on
root.right, walk to the tail of that new right chain, then attach
the saved (now flattened) right subtree there.
*/

/*
Method to Solve:
----------------
1. Base case: if root is null, return null.
2. Save root.right into a local variable (next) before it gets overwritten.
3. Recursively flatten root.left and attach the result to root.right.
4. Walk the right chain from root until the tail (mover.right == null).
5. Attach the recursively flattened saved right subtree to the tail.
6. Set root.left = null.
7. Return root (head of the flattened chain for this subtree).
*/

class LC114FlattenBinaryTreeToLinkedList {

    /**
     * Recursively flattens the subtree rooted at the given node
     * into a right-skewed linked list in pre-order.
     *
     * @param root root of the current subtree to flatten
     * @return the head of the flattened linked list (same as input root)
     */
    private TreeNode convertToLinkedList(TreeNode root) {
        if (root == null)
            return null;

        // save right before overwriting it
        TreeNode next = root.right;

        root.right = convertToLinkedList(root.left);

        // walk to tail of the newly attached left chain
        TreeNode mover = root;
        while (mover.right != null)
            mover = mover.right;

        // attach flattened right subtree at the tail
        mover.right = convertToLinkedList(next);

        root.left = null;
        return root;
    }

    /**
     * Flattens the binary tree rooted at root into a linked list in-place.
     * Uses pre-order traversal order (root → left → right).
     *
     * @param root root of the binary tree to flatten
     */
    // Time Complexity: O(n)
    // Space Complexity: O(h) — recursion stack, h = tree height
    public void flatten(TreeNode root) {
        convertToLinkedList(root);
    }
}
