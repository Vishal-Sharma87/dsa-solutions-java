package tree.medium;

import java.util.ArrayList;
import java.util.Collections;

import tree.TreeNode;

// Created at: 09-May-2026
// Last revised at: 09-May-2026
// Link: https://www.geeksforgeeks.org/problems/boundary-traversal-of-binary-tree/1

/*
Problem Description:
--------------------
Statement:
Given a binary tree, return the boundary nodes of the tree in anti-clockwise direction
starting from the root. Boundary includes the left boundary (excluding leaf), all leaf nodes
left to right, and the right boundary (excluding leaf), in reverse.

Example:
Input:
        1
       / \
      2   3
     / \   \
    4   5   6
           / \
          7   8

Output: [1, 2, 4, 5, 7, 8, 6, 3]

Constraints:
- 1 <= Number of nodes <= 10^5
- 1 <= Node.val <= 10^5
*/

/*
Approach 1: Three-Pass DFS ★
Idea:
Split boundary into three independent parts and collect separately:
1. Left boundary — from root.left downward, go left first, right only when left is null. Stop at leaves.
2. Leaf nodes — full DFS, collect all leaves left to right.
3. Right boundary — from root.right downward, go right first, left only when right is null. Stop at leaves.
Reverse the right boundary before merging so it reads bottom-up.

Time Complexity: O(n) — each node visited at most once across all three passes
Space Complexity: O(n) — recursion stack O(h) + output lists O(n)

Key Insight:
Left and right boundaries intentionally exclude leaves — the base case
`root.left == null && root.right == null` handles this cleanly without extra flags.
Right boundary is collected top-down and reversed at the end.
*/

/*
Method to Solve:
----------------
1. Add root first. Return early if single node.
2. extractLeft from root.left — go left, fallback to right only if left is null.
3. extractLeaf from root — full DFS, collect all leaves.
4. extractRight from root.right — go right, fallback to left only if right is null.
5. Reverse right boundary.
6. Merge: left + leaf + right into boundary.
*/

// Time Complexity: O(n)
// Space Complexity: O(n)

class GFG_BoundaryTraversal {

    /**
     * Collects left boundary nodes top-down, excluding leaves.
     *
     * @param root current node
     * @param left list to collect into
     */
    private void extractLeft(TreeNode root, ArrayList<Integer> left) {
        if (root == null || (root.left == null && root.right == null))
            return;

        left.add(root.val);

        extractLeft(root.left, left);
        // hug the boundary — go right only when left is absent
        if (root.left == null)
            extractLeft(root.right, left);
    }

    /**
     * Collects all leaf nodes left to right via full DFS.
     *
     * @param root current node
     * @param leaf list to collect into
     */
    private void extractLeaf(TreeNode root, ArrayList<Integer> leaf) {
        if (root == null)
            return;

        if (root.left == null && root.right == null) {
            leaf.add(root.val);
            return;
        }

        extractLeaf(root.left, leaf);
        extractLeaf(root.right, leaf);
    }

    /**
     * Collects right boundary nodes top-down, excluding leaves.
     *
     * @param root  current node
     * @param right list to collect into
     */
    private void extractRight(TreeNode root, ArrayList<Integer> right) {
        if (root == null || (root.left == null && root.right == null))
            return;

        right.add(root.val);

        extractRight(root.right, right);
        // hug the boundary — go left only when right is absent
        if (root.right == null)
            extractRight(root.left, right);
    }

    /**
     * Returns boundary traversal of a binary tree in anti-clockwise order.
     *
     * @param root root of the binary tree
     * @return boundary nodes starting from root
     */
    ArrayList<Integer> boundaryTraversal(TreeNode root) {
        ArrayList<Integer> boundary = new ArrayList<>();
        if (root == null)
            return boundary;

        boundary.add(root.val);
        if (root.left == null && root.right == null)
            return boundary;

        ArrayList<Integer> left = new ArrayList<>();
        ArrayList<Integer> leaf = new ArrayList<>();
        ArrayList<Integer> right = new ArrayList<>();

        extractLeft(root.left, left);
        extractLeaf(root, leaf);
        extractRight(root.right, right);

        // right was collected top-down, reverse for bottom-up merge
        Collections.reverse(right);

        left.forEach(boundary::add);
        leaf.forEach(boundary::add);
        right.forEach(boundary::add);

        return boundary;
    }
}