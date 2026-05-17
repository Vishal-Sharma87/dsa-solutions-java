package bst.medium;

// Created at: 17-May-2026
// Last revised at: 17-May-2026
// Link: https://leetcode.com/problems/validate-binary-search-tree/

/*
Problem Description:
--------------------
Statement:
Given the root of a binary tree, determine if it is a valid Binary Search Tree (BST).
A valid BST satisfies: the left subtree contains only nodes with values strictly less than
the node's value, the right subtree contains only nodes with values strictly greater than
the node's value, and both subtrees must also be valid BSTs.

Example:
Input: root = [5, 1, 4, null, null, 3, 6]
Output: false
Explanation: Node 4 in the right subtree has 3 and 6 as children. 3 < 5, so BST is invalid.

Constraints:
- The number of nodes is in the range [1, 10^4].
- -2^31 <= Node.val <= 2^31 - 1
*/

/*
Approach 1: Inorder Traversal
Idea:
    Perform an inorder traversal (left → root → right). In a valid BST, this produces
    a strictly increasing sequence. Track the previously visited value and fail if the
    current value is not strictly greater.

Time Complexity: O(n) — visits every node once.

Space Complexity: O(h) — recursion stack, where h is the tree height.

Drawbacks:
    Requires maintaining external state (previous value). Also needs a wrapper object
    or class field to pass state across recursive calls in Java.

---

★ Approach 2: Post-order with (min, max) Range Propagation
Idea:
    Traverse post-order. Each call returns a Node(small, big) representing the actual
    minimum and maximum values in that subtree. This naturally validates the BST property
    bottom-up: a subtree is valid only if its max (for left child) < root.val AND its
    min (for right child) > root.val.

    Sentinel design:
    - null return       → absent node (no constraint to check)
    - Node(null, null)  → invalid BST detected in this subtree (propagate failure up)
    - Node(s, b)        → valid subtree with actual min=s, max=b

Time Complexity: O(n) — every node visited exactly once.

Space Complexity: O(h) — recursion stack depth.

Key Insight:
    Propagating actual (min, max) values instead of bounds avoids the classic pitfall of
    only checking parent-child pairs. It naturally catches violations like a node in the
    right subtree being smaller than an ancestor further up the tree.
*/

import tree.TreeNode;

// TreeNode definition assumed from LeetCode environment
// class TreeNode { int val; TreeNode left, right; }

/*
Method to Solve:
----------------
1. Base case: null node returns null (no range to report, no violation).
2. Recurse left. If left returned Node(null, null), propagate failure.
   If left's max >= root.val, BST property violated — return Node(null, null).
3. Recurse right. If right returned Node(null, null), propagate failure.
   If right's min <= root.val, BST property violated — return Node(null, null).
4. Both subtrees valid. Return a Node whose small is left.small (or root.val if no left)
   and big is right.big (or root.val if no right).
5. In isValidBST: null result = empty tree = valid. Non-null result = valid iff small != null.
*/

// Time Complexity: O(n)
// Space Complexity: O(h)

public class LC98ValidateBinarySearchTree {

    // Tracks the actual min and max value in a subtree.
    // Node(null, null) is the invalid-BST sentinel.
    private static class Range {
        Integer small;
        Integer big;

        Range(Integer small, Integer big) {
            this.small = small;
            this.big = big;
        }
    }

    /**
     * Post-order traversal that computes the (min, max) range for each subtree.
     * Returns null for absent nodes, Range(null, null) for an invalid subtree,
     * and Range(min, max) for a valid subtree.
     *
     * @param root current node being evaluated
     * @return Range representing validity and bounds, or null if node is absent
     */
    private Range computeRange(TreeNode root) {
        if (root == null)
            return null;

        Range left = computeRange(root.left);
        // left subtree invalid, or its max violates BST with root
        if (left != null && (left.small == null || left.big >= root.val)) {
            return new Range(null, null);
        }

        Range right = computeRange(root.right);
        // right subtree invalid, or its min violates BST with root
        if (right != null && (right.small == null || right.small <= root.val)) {
            return new Range(null, null);
        }

        // leaf node
        if (left == null && right == null)
            return new Range(root.val, root.val);

        // only right child exists
        if (left == null) {
            return new Range(Math.min(right.small, root.val), Math.max(root.val, right.big));
        }

        // only left child exists
        if (right == null) {
            return new Range(Math.min(left.small, root.val), Math.max(root.val, left.big));
        }

        // both children valid — expand range from left.small to right.big
        return new Range(left.small, right.big);
    }

    /**
     * Validates whether the given binary tree satisfies all BST properties.
     *
     * @param root root of the binary tree
     * @return true if the tree is a valid BST, false otherwise
     */
    public boolean isValidBST(TreeNode root) {
        Range result = computeRange(root);
        // null means empty tree (valid); non-null with small != null means valid BST
        return result == null || result.small != null;
    }
}