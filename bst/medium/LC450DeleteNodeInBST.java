package bst.medium;

import tree.TreeNode;

// Created at: 16-May-2026
// Last revised at: 16-May-2026
// Link: https://leetcode.com/problems/delete-node-in-a-bst/

/*
Problem Description:
--------------------
Statement:
Given the root of a BST and an integer key, delete the node with
the given key and return the updated root. If key does not exist,
return the tree unchanged. The deletion must maintain BST property.

Example:
Input:  root = [5,3,6,2,4,null,7], key = 3
Output: [5,4,6,2,null,null,7]  (or other valid BSTs)

Input:  root = [5,3,6,2,4,null,7], key = 0
Output: [5,3,6,2,4,null,7]  (key not found, unchanged)

Constraints:
- Number of nodes: [0, 10^4]
- -10^5 <= Node.val <= 10^5
- All values in the BST are unique
- -10^5 <= key <= 10^5
*/

/*
Approaches:
-----------
Approach 1: Recursive BST deletion ★
Idea:
Use BST property to locate the target node. Once found, handle one
of three structural cases:

  Case 1 — No children: return null (parent drops the node).
  Case 2 — One child: return that child (parent bypasses the node).
  Case 3 — Two children: find the inorder successor (min of right subtree),
            overwrite current node's value with it, then recursively delete
            the successor from the right subtree.

The key insight for Case 3: don't physically move the node — steal its
successor's value and let recursion handle the actual removal below.
The successor always has at most one child (no left child by definition),
so the recursive delete lands in Case 1 or Case 2, never Case 3 again.

Time Complexity: O(h) — O(log n) balanced, O(n) skewed
Space Complexity: O(h) — recursive call stack

Key Insight:
Rewiring is implicit — every recursive call returns the (possibly updated)
subtree root, and the parent rewires via `root.left = ...` or `root.right = ...`.
No explicit parent pointer tracking needed.
*/

/*
Method to Solve:
----------------
1. Base case: root is null → key not found, return null.
2. If key < root.val, recurse left and rewire root.left.
   If key > root.val, recurse right and rewire root.right.
3. key == root.val → found the node:
   a. Two children: find inorder successor value, overwrite root.val,
      delete successor from root.right recursively.
   b. Only left child: return root.left.
   c. Only right child: return root.right.
   d. No children: return null.
4. Return root at every level to propagate rewiring upward.
*/

class LC450DeleteNodeInBST {

    /**
     * Finds the minimum value in the subtree rooted at root —
     * used to locate the inorder successor during two-child deletion.
     *
     * Note: defensive null check included; caller guarantees non-null input
     * since this is only invoked when root.right exists.
     *
     * @param root root of the subtree to search
     * @return minimum node value in the subtree, or -1 if root is null
     */
    private int inorderSuccessor(TreeNode root) {
        if (root == null)
            return -1;
        TreeNode mover = root;
        while (mover.left != null)
            mover = mover.left;
        return mover.val;
    }

    /**
     * Deletes the node with the given key from the BST
     * and returns the root of the updated tree.
     *
     * @param root root of the BST
     * @param key  value of the node to delete
     * @return root of the updated BST
     */
    public TreeNode deleteNode(TreeNode root, int key) {
        if (root == null)
            return null;

        if (key == root.val) {
            // two children — replace value with inorder successor, delete it below
            if (root.left != null && root.right != null) {
                int nextBig = inorderSuccessor(root.right);
                root.val = nextBig;
                // successor has no left child, so this hits Case 1 or 2
                root.right = deleteNode(root.right, nextBig);
                return root;
            }

            // one child — bypass current node
            if (root.left != null)
                return root.left;
            if (root.right != null)
                return root.right;

            // no children — drop the node
            return null;
        }

        if (root.val < key)
            root.right = deleteNode(root.right, key);
        else
            root.left = deleteNode(root.left, key);

        return root;
    }
}

// Time Complexity: O(h) — O(log n) balanced, O(n) skewed
// Space Complexity: O(h) — recursive call stack depth
