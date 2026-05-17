package bst.medium;
// Created at: 17-May-2026

import tree.TreeNode;

// Last revised at: 17-May-2026
// Link: https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/

/*
Problem Description:
--------------------
Statement:
Given an array of integers preorder, which represents the preorder traversal of a BST,
construct and return the BST. It is guaranteed that there is always possible to find a
binary search tree with the given requirements for the given test cases.

Example:
Input:  preorder = [8, 5, 1, 7, 10, 12]
Output: [8, 5, 10, 1, 7, null, 12]

Constraints:
- 1 <= preorder.length <= 100
- 1 <= preorder[i] <= 10^8
- All values in preorder are unique.
*/

/*
Approach 1: Naive — Scan for Right Subtree Boundary
Idea:
    For each root value, linearly scan forward to find the first element greater than root.
    Everything before that index goes to the left subtree; everything after to the right.
    Recurse on both halves.

Time Complexity: O(n^2) — boundary scan is O(n) per node.

Space Complexity: O(n) — subarray slices + recursion stack.

Drawbacks:
    Linear scan per node is wasteful. BST structure already encodes where the split
    should happen — no need to search for it explicitly.

---

★ Approach 2: Bound-constrained Index Walk
Idea:
    Maintain a shared index into the preorder array and a valid range (min, max) for
    each recursive call. A node is placed at the current position only if its value
    falls strictly within the allowed range. If it doesn't fit, return null WITHOUT
    advancing the index — the value stays available for the correct ancestor's right subtree.

    The preorder property guarantees that consuming elements left-to-right while
    enforcing BST bounds naturally reconstructs the original tree in O(n) time.

    Sentinel bounds Long.MIN_VALUE / Long.MAX_VALUE avoid special-casing the root
    while keeping all int values safely within range.

Time Complexity: O(n) — each element is consumed exactly once.

Space Complexity: O(h) — recursion stack; O(log n) balanced, O(n) worst case.

Key Insight:
    Not advancing the index on a range miss is the crux. The current element isn't
    "wrong" — it just belongs to a different subtree. Leaving it in place lets the
    correct ancestor pick it up.
*/

// TreeNode definition assumed from LeetCode environment
// class TreeNode { int val; TreeNode left, right; }

/*
Method to Solve:
----------------
1. Use index[0] as a shared pointer across all recursive calls (int[] pass-by-ref trick).
2. At each call, peek at preorder[index[0]]. If out of (min, max) range, return null.
3. If in range: create the node, advance index[0], recurse left with (min, val),
   then recurse right with (val, max).
4. Entry point seeds bounds as (Long.MIN_VALUE, Long.MAX_VALUE).
*/

// Time Complexity: O(n)
// Space Complexity: O(h)

public class LC1008ConstructBSTFromPreorderTraversal {

    /**
     * Recursively builds the BST by consuming preorder elements within the allowed
     * range.
     * Does not advance the index when the current value falls outside (min, max),
     * leaving it available for the enclosing right-subtree call.
     *
     * @param preorder the preorder traversal array
     * @param min      exclusive lower bound for values valid at this position
     * @param max      exclusive upper bound for values valid at this position
     * @param index    shared index into preorder, wrapped in int[] for mutability
     * @return the root of the constructed subtree, or null if no valid node here
     */
    private TreeNode construct(int[] preorder, long min, long max, int[] index) {
        if (index[0] >= preorder.length)
            return null;

        int val = preorder[index[0]];
        // val out of BST range for this position — leave index unchanged for parent's
        // right call
        if (val <= min || val >= max)
            return null;

        TreeNode root = new TreeNode(val);
        index[0]++; // consume this element before recursing

        // left subtree: values must be in (min, val)
        root.left = construct(preorder, min, val, index);
        // right subtree: values must be in (val, max)
        root.right = construct(preorder, val, max, index);

        return root;
    }

    /**
     * Constructs a BST from its preorder traversal.
     *
     * @param preorder array representing the preorder traversal of a valid BST
     * @return root of the reconstructed BST
     */
    public TreeNode bstFromPreorder(int[] preorder) {
        // Long sentinels ensure every valid int value passes the initial range check
        return construct(preorder, Long.MIN_VALUE, Long.MAX_VALUE, new int[] { 0 });
    }
}