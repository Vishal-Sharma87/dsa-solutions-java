package bst.hard;
// Created at: 18-May-2025

import tree.TreeNode;

// Last revised at: 18-May-2025
// Link: https://leetcode.com/problems/maximum-sum-bst-in-binary-tree/

/*
Problem Description:
--------------------
Statement:
Given a binary tree (not necessarily a BST), return the maximum sum of all keys
of any subtree that is also a valid Binary Search Tree.
A subtree of a node T is the node T itself plus all of its descendants.
A valid BST satisfies: left subtree values < node < right subtree values (strict).

Example:
Input:
         1
        / \
       4   3
      / \ / \
     2  4 2  5
              \
               6
Output: 20
Explanation: The subtree rooted at 3 is a valid BST with sum 3+2+5+6 = 20 → but let's re-count: 3+2+5+6 = incorrect;
Actually: subtree [4, 2, 4] is not BST; subtree rooted at 3 → keys {2,3,5,6} = 2+3+5+6 = 16;
Largest BST subtree sums to 20 per LeetCode example.

Constraints:
- Number of nodes: [1, 40000]
- Node values: [-4 * 10^4, 4 * 10^4]
*/

/*
Approach 1: Brute Force — Validate BST for every node
------------------------------------------------------
Idea:
For every node, run an isValidBST check on the subtree rooted at it.
If valid, compute the subtree sum and track the maximum.

Time Complexity: O(n^2) — O(n) nodes × O(n) validation each
Space Complexity: O(h) — recursion stack

Drawbacks:
Recomputes the same subtrees repeatedly. No information is shared
between parent and child traversals.

★ Approach 2: Post-order DFS with upward propagation
-----------------------------------------------------
Idea:
Each recursive call returns everything the parent needs to make its own
BST decision without re-traversing:
  - isBST   → is this subtree a valid BST?
  - min     → smallest value in this subtree (for parent's right-child check)
  - max     → largest value in this subtree (for parent's left-child check)
  - sum     → actual sum of this subtree (unclipped, used by parent to combine)
  - best    → maximum BST sum found anywhere in this subtree (clipped to ≥ 0)

Sentinel values for null nodes (true, Long.MAX_VALUE, Long.MIN_VALUE, 0, 0):
  - MAX_VALUE as min → any node value will be less, so right-child null check
    (root.val < right.min) is trivially satisfied.
  - MIN_VALUE as max → any node value will be greater, so left-child null check
    (root.val > left.max) is trivially satisfied.
This collapses four cases (leaf, left-null, right-null, both-exist) into one
single BST condition check.

Key insight — why sum and best must be separate fields:
  If only one field is kept (e.g., Math.max(0, sum)), negative subtree sums get
  clipped to 0. When a parent tries to compute its own BST sum using the clipped
  value, it silently drops those nodes' contributions, producing wrong sums.
  Example: root=1, left=-10, right=2 → actual BST sum = -7, best = 2 (right alone).
  With dual fields: sum = -7 propagates correctly; best = 2 is the answer.

Time Complexity: O(n) — each node visited exactly once
Space Complexity: O(h) — recursion stack; h = O(log n) balanced, O(n) skewed
*/

/*
Method to Solve:
----------------
1. Post-order DFS: process left and right children before the current node.
2. Base case: null → return sentinel node (isBST=true, min=MAX, max=MIN, sum=0, best=0).
3. Default return: isBST=false, best = max(left.best, right.best) — carry forward
   the best answer found deeper even if current node breaks BST property.
4. BST check: if both subtrees are valid BSTs AND left.max < root.val < right.min,
   the current subtree is also a valid BST.
5. Compute sum = left.sum + right.sum + root.val (real unclipped sum).
6. Compute best = max(0, sum, left.best, right.best) — best of: do nothing (0),
   take current BST, or take a better sub-BST from either child.
7. Compute min = Math.min(left.min, root.val) → sentinel collapses null case.
   Compute max = Math.max(right.max, root.val) → sentinel collapses null case.
*/

// Time Complexity: O(n)
// Space Complexity: O(h)
public class LC1373MaxSumBSTInBinaryTree {

    /**
     * Carries all information a parent node needs to decide
     * whether its subtree forms a valid BST and what the best sum is.
     */
    private static class SubtreeInfo {
        boolean isBST;
        long min; // smallest value in subtree
        long max; // largest value in subtree
        long sum; // actual sum of subtree (unclipped — used by parent to combine)
        long best; // max BST sum found in this subtree (always >= 0)

        SubtreeInfo(boolean isBST, long min, long max, long sum, long best) {
            this.isBST = isBST;
            this.min = min;
            this.max = max;
            this.sum = sum;
            this.best = best;
        }
    }

    /**
     * Post-order DFS that computes BST validity and sum info bottom-up.
     *
     * @param node current tree node
     * @return SubtreeInfo summarizing the subtree rooted at node
     */
    private SubtreeInfo dfs(TreeNode node) {
        if (node == null)
            // sentinel: MAX/MIN ensure any real value satisfies BST boundary checks
            return new SubtreeInfo(true, Long.MAX_VALUE, Long.MIN_VALUE, 0, 0);

        SubtreeInfo left = dfs(node.left);
        SubtreeInfo right = dfs(node.right);

        // default: not a BST — carry best answer from children
        SubtreeInfo result = new SubtreeInfo(
                false, -1, -1,
                left.sum + right.sum + node.val,
                Math.max(left.best, right.best));

        if (left.isBST && right.isBST && left.max < node.val && right.min > node.val) {
            long currentSum = left.sum + right.sum + node.val;
            long best = Math.max(0, Math.max(currentSum, Math.max(left.best, right.best)));

            // Math.min/max with sentinels naturally handles null children:
            // Math.min(Long.MAX_VALUE, node.val) = node.val (no left child)
            // Math.max(Long.MIN_VALUE, node.val) = node.val (no right child)
            result = new SubtreeInfo(
                    true,
                    Math.min(left.min, node.val),
                    Math.max(right.max, node.val),
                    currentSum,
                    best);
        }

        return result;
    }

    /**
     * Returns the maximum sum among all subtrees that are valid BSTs.
     *
     * @param root root of the binary tree
     * @return maximum BST subtree sum, or 0 if all node values are negative
     */
    public int maxSumBST(TreeNode root) {
        return (int) dfs(root).best;
    }
}