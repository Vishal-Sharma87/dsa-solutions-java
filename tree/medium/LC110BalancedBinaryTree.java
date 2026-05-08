package tree.medium;

import tree.TreeNode;

// Created at: 08-May-2026
// Last revised at: 08-May-2026
// Link: https://leetcode.com/problems/balanced-binary-tree/

/*
Problem Description:
--------------------
Statement:
Given a binary tree, determine if it is height-balanced.
A height-balanced binary tree is a binary tree in which the depth
of the two subtrees of every node never differs by more than one.

Example:
Input: root = [3,9,20,null,null,15,7]
Output: true

Input: root = [1,2,2,3,3,null,null,4,4]
Output: false

Constraints:
- The number of nodes in the tree is in the range [0, 5000].
- -10^4 <= Node.val <= 10^4
*/

/*
Approach 1: Brute Force
Idea:
For every node, independently compute the height of its left and right
subtrees and check if the difference exceeds 1. Recurse for all nodes.

Time Complexity: O(n^2) — height is recomputed from scratch at each node
Space Complexity: O(h) — recursion stack

Drawbacks:
Redundant height calculations — the same subtrees are traversed multiple
times. A node deep in the tree gets its height recomputed for every
ancestor above it.

Approach 2: Single-pass DFS with -1 Sentinel ★
Idea:
Combine the height computation and balance check into one DFS pass.
Return the actual height if the subtree is balanced, or -1 as a sentinel
to signal imbalance. Once -1 is detected anywhere, propagate it up
immediately without further work.

Time Complexity: O(n) — every node visited exactly once
Space Complexity: O(h) — recursion stack, O(log n) avg / O(n) worst

Key Insight:
The -1 sentinel short-circuits the recursion. As soon as any subtree
is unbalanced, the entire call stack unwinds with -1 — no wasted
traversal unlike the brute force approach.
*/

/*
Method to Solve (Single-pass DFS):
------------------------------------
1. Base case: null node → return 0 (height of empty tree).
2. Recurse left → leftH. If leftH == -1, return -1 immediately.
3. Recurse right → rightH. If rightH == -1, return -1 immediately.
4. If |leftH - rightH| > 1, this node is unbalanced → return -1.
5. Otherwise return 1 + max(leftH, rightH) as the height of this subtree.
6. In the public method, balanced iff checkBalance(root) != -1.
*/

// Time Complexity: O(n)
// Space Complexity: O(h)

public class LC110BalancedBinaryTree {

    /**
     * Returns the height of the subtree rooted at the given node,
     * or -1 if the subtree is not height-balanced.
     *
     * @param root current tree node
     * @return height of the balanced subtree, or -1 if unbalanced
     */
    private int checkBalance(TreeNode root) {
        if (root == null)
            return 0;

        int leftH = checkBalance(root.left);
        if (leftH == -1)
            return -1; // left already unbalanced, no point going further

        int rightH = checkBalance(root.right);
        if (rightH == -1 || Math.abs(leftH - rightH) > 1)
            return -1; // right unbalanced, or skew at this node

        return 1 + Math.max(leftH, rightH);
    }

    /**
     * Determines whether the binary tree is height-balanced.
     *
     * @param root root of the binary tree
     * @return true if balanced, false otherwise
     */
    public boolean isBalanced(TreeNode root) {
        return checkBalance(root) != -1;
    }
}