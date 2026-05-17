package bst.medium;
// Created at: 17-May-2026

// Last revised at: 17-May-2026
// Link: https://www.geeksforgeeks.org/problems/predecessor-and-successor/1

/*
Problem Description:
--------------------
Statement:
Given the root of a BST and an integer key, find the inorder predecessor and inorder
successor of the key in the BST. The key may or may not be present in the BST.

- Predecessor: the TreeNode with the largest value strictly less than key.
- Successor:   the TreeNode with the smallest value strictly greater than key.

Return an ArrayList of size 2: [predecessor TreeNode, successor TreeNode].
Use null if no predecessor or successor exists.

Example:
Input: root = [8, 1, 9, null, 4, null, 10, null, null, 3, null], key = 8
Output: [4, 9]
Explanation: 4 is the largest value < 8; 9 is the smallest value > 8.

Constraints:
- 1 <= Number of TreeNodes <= 10^5
- 1 <= TreeNode.val <= 10^6
- 1 <= key <= 10^6
*/

/*
Approach 1: Inorder Traversal
Idea:
    Perform a full inorder traversal to get a sorted list of all TreeNode values.
    Scan the list to find the element just before and just after the key.

Time Complexity: O(n) — visits every TreeNode.

Space Complexity: O(n) — stores all TreeNode values.

Drawbacks:
    Ignores BST structure entirely. O(n) time and space is unnecessary when
    the BST ordering can guide search to O(h) in both.

---

★ Approach 2: Two Independent BST Walks (Best-so-far Pattern)
Idea:
    Run two separate traversals from root — one for predecessor, one for successor.

    Predecessor (largest value < key):
    - If mover.val < key: valid candidate, record it, go RIGHT to find a larger one still < key.
    - Else (mover.val >= key): too big or equal, go LEFT toward smaller values.

    Successor (smallest value > key):
    - If mover.val > key: valid candidate, record it, go LEFT to find a smaller one still > key.
    - Else (mover.val <= key): too small or equal, go RIGHT toward larger values.

    Strict inequalities (<, >) ensure the key TreeNode itself is never recorded as either result,
    correctly handling both the key-present and key-absent cases.

Time Complexity: O(h) — h is the tree height; O(log n) balanced, O(n) skewed.

Space Complexity: O(1) — purely iterative, no recursion stack.

Key Insight:
    This is the BST floor/ceiling pattern. Record a candidate whenever the current TreeNode
    qualifies, then keep walking deeper to find a tighter match. Stop when the tree runs out.
*/

import java.util.ArrayList;
import java.util.Arrays;

import tree.TreeNode;

// TreeNode definition assumed from GFG environment
// class TreeNode { int data; TreeNode left, right; }

/*
Method to Solve:
----------------
1. Initialize result list as [null, null].
2. Walk from root for predecessor:
   - data < key  → record as candidate, go right (look for larger value still < key).
   - data >= key → go left (current and right subtree are too large).
3. Reset mover to root for successor:
   - data > key  → record as candidate, go left (look for smaller value still > key).
   - data <= key → go right (current and left subtree are too small).
4. Return [predecessor, successor].
*/

// Time Complexity: O(h)
// Space Complexity: O(1)

public class GFG_PredecessorAndSuccessor {

    /**
     * Finds the inorder predecessor and successor of a given key in a BST.
     * Returns [predecessor, successor]; either entry is null if none exists.
     *
     * @param root root of the BST
     * @param key  the reference value to find predecessor and successor for
     * @return ArrayList of size 2: index 0 = predecessor TreeNode, index 1 =
     *         successor
     *         TreeNode
     */
    public ArrayList<TreeNode> findPreSuc(TreeNode root, int key) {
        // fixed-size list; set() is safe, add() would throw
        // UnsupportedOperationException
        ArrayList<TreeNode> TreeNodes = new ArrayList<>(Arrays.asList(null, null));
        if (root == null)
            return TreeNodes;

        // --- predecessor: largest value strictly less than key ---
        TreeNode mover = root;
        while (mover != null) {
            if (mover.val < key) {
                TreeNodes.set(0, mover); // valid candidate — record it
                mover = mover.right; // go right: look for a larger value still < key
            } else {
                mover = mover.left; // too large or equal — shrink toward smaller values
            }
        }

        // --- successor: smallest value strictly greater than key ---
        mover = root;
        while (mover != null) {
            if (mover.val > key) {
                TreeNodes.set(1, mover); // valid candidate — record it
                mover = mover.left; // go left: look for a smaller value still > key
            } else {
                mover = mover.right; // too small or equal — grow toward larger values
            }
        }

        return TreeNodes;
    }
}