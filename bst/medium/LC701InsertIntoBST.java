package bst.medium;
// Created at: 16-May-2026

import tree.TreeNode;

// Last revised at: 16-May-2026
// Link: https://leetcode.com/problems/insert-into-a-binary-search-tree/

/*
Problem Description:
--------------------
Statement:
Given the root of a BST and an integer val, insert val into the BST
and return the root of the updated tree. It is guaranteed that val
does not already exist in the BST. The insertion must maintain
the BST property. Multiple valid trees may exist; return any.

Example:
Input:  root = [4,2,7,1,3], val = 5
Output: [4,2,7,1,3,5]   (5 inserted as left child of 7)

Constraints:
- Number of nodes: [0, 10^4]
- -10^8 <= Node.val <= 10^8
- All values in the BST are unique
- -10^8 <= val <= 10^8
- val does not exist in the tree
*/

/*
Approaches:
-----------
Approach 1: Recursive
Idea:
If val < root.val, recurse left and rewire root.left on the way back.
If val > root.val, recurse right and rewire root.right on the way back.
Base case: null node — create and return the new node.

Time Complexity: O(h)
Space Complexity: O(h) — recursive call stack

Drawbacks:
Call stack depth proportional to tree height. O(n) on skewed tree.

---

Approach 2: Iterative with parent tracking ★
Idea:
Walk down the tree using a mover pointer while keeping last one step behind.
When mover falls off (null), last is the parent. Attach the new node
to last.left or last.right based on value comparison.

Time Complexity: O(h)
Space Complexity: O(1) — no call stack

Key Insight:
The trailing-pointer pattern (last/mover) is the iterative equivalent
of "rewire on return" in recursion. Cleaner and avoids stack overhead.
*/

/*
Method to Solve:
----------------
1. Create the new node upfront.
2. If root is null, return the new node directly.
3. Walk with mover; last trails one step behind.
   - Go right if mover.val < val, else go left.
4. When mover is null, last is the insertion parent.
5. Attach new node to last.right if val > last.val, else last.left.
6. Return original root (tree structure unchanged above insertion point).
*/

class LC701InsertIntoBST {

    /**
     * Inserts a value into the BST and returns the updated root.
     *
     * @param root root of the BST
     * @param val  value to insert (guaranteed unique)
     * @return root of the updated BST
     */
    public TreeNode insertIntoBST(TreeNode root, int val) {
        TreeNode nodeToInsert = new TreeNode(val);

        if (root == null)
            return nodeToInsert;

        // last trails mover; when mover falls off, last is the parent
        TreeNode last = root, mover = root;
        while (mover != null) {
            last = mover;
            mover = mover.val < val ? mover.right : mover.left;
        }

        if (last.val < val)
            last.right = nodeToInsert;
        else
            last.left = nodeToInsert;

        return root;
    }
}

// Time Complexity: O(h) — O(log n) balanced, O(n) skewed
// Space Complexity: O(1)