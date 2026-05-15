package bst.easy;

import tree.TreeNode;

// Created at: 16-May-2026
// Last revised at: 16-May-2026
// Link: https://leetcode.com/problems/search-in-a-bst/

/*
Problem Description:
--------------------
Statement:
Given the root of a Binary Search Tree (BST) and an integer val,
find the node in the BST where the node's value equals val and
return the subtree rooted at that node. If no such node exists, return null.

Example:
Input:  root = [4,2,7,1,3], val = 2
Output: [2,1,3]

Input:  root = [4,2,7,1,3], val = 5
Output: []

Constraints:
- Number of nodes: [1, 5000]
- 1 <= Node.val <= 10^7
- root is a valid BST
- 1 <= val <= 10^7
*/

/*
Approaches:
-----------
Approach 1: Recursive DFS ★ (for simplicity)
Idea:
Use BST property to recurse only into the relevant subtree.
If val < root.val, go left. If val > root.val, go right.

Time Complexity: O(h) — h is tree height; O(log n) balanced, O(n) skewed
Space Complexity: O(h) — recursive call stack

Drawbacks:
Auxiliary stack space proportional to height. Skewed tree hits O(n) stack depth.

---

Approach 2: Iterative ★ (optimal for space)
Idea:
Same BST-guided traversal but using a pointer instead of call stack.
Move right if current < val, left otherwise. Stop when match or null.

Time Complexity: O(h)
Space Complexity: O(1) — no call stack, just a moving pointer

Key Insight:
Iterative is strictly better here — same time complexity, eliminates
auxiliary stack space entirely. Preferred in production.
*/

/*
Method to Solve:
----------------
1. If root is null or root.val matches, return root directly.
2. Initialize a mover pointer at root.
3. While mover is not null:
   a. If mover.val == val, return mover (subtree rooted here).
   b. If mover.val < val, move right; else move left.
4. Return null — val not found in the tree.
*/

class LC700SearchInABST {

    /**
     * Searches for a node with the given value in the BST
     * and returns the subtree rooted at that node.
     *
     * @param root root of the BST
     * @param val  target value to search
     * @return subtree rooted at the matching node, or null if not found
     */
    public TreeNode searchBST(TreeNode root, int val) {
        if (root == null || root.val == val)
            return root;

        // recursive — O(h) time, O(h) stack space
        // if (root.val < val) {
        // return searchBST(root.right, val);
        // } else {
        // return searchBST(root.left, val);
        // }

        // iterative — O(h) time, O(1) space
        TreeNode mover = root;
        while (mover != null) {
            if (mover.val == val)
                return mover;
            // BST property guides direction
            mover = mover.val < val ? mover.right : mover.left;
        }

        return null;
    }
}

// Time Complexity: O(h) — O(log n) balanced, O(n) skewed
// Space Complexity: O(1) — iterative, no call stack