package bst.easy;

import tree.TreeNode;

// Created at: 16-May-2026
// Last revised at: 16-May-2026
// Link: https://www.geeksforgeeks.org/floor-in-binary-search-tree-bst/
//       https://www.geeksforgeeks.org/find-ceil-in-bst/

/*
Problem Description:
--------------------
Statement:
Given a BST and an integer k, find:
  - Floor: the largest value in the BST <= k
  - Ceil:  the smallest value in the BST >= k
Return -1 if no such value exists.

Example:
BST:        8
           / \
          4   12
         / \
        2   6

Floor(k=7)  → 6
Floor(k=4)  → 4
Ceil(x=5)   → 6
Ceil(x=12)  → 12

Constraints:
- 1 <= Number of TreeNodes <= 10^5
- 1 <= TreeNode.val <= 10^5
- 1 <= k, x <= 10^5
*/

/*
Approaches:
-----------
Approach 1: Iterative BST traversal ★
Idea:
Exploit BST ordering to narrow down candidates in one pass.
For floor: any TreeNode smaller than k is a candidate — go right to find a closer one.
For ceil:  any TreeNode larger than x is a candidate — go left to find a closer one.
Both algorithms are exact mirrors of each other.

Time Complexity: O(h) — O(log n) balanced, O(n) skewed
Space Complexity: O(1) — no auxiliary stack

Key Insight:
The candidate variable (floor/ceil) acts as a running best-so-far answer.
It only updates when we move in the direction that might give a better result.
Exact match short-circuits immediately.
*/

/*
Method to Solve (Floor):
------------------------
1. If root is null, return -1.
2. Track a floor variable, initialized to -1.
3. While mover is not null:
   a. If mover.val == k, return k directly.
   b. If mover.val < k, update floor = mover.val, go right (look for closer).
   c. If mover.val > k, go left (too big, can't be floor).
4. Return floor.

Method to Solve (Ceil):
-----------------------
1. If root is null, return -1.
2. Track a ceil variable, initialized to -1.
3. While mover is not null:
   a. If mover.val == x, return x directly.
   b. If mover.val > x, update ceil = mover.val, go left (look for closer).
   c. If mover.val < x, go right (too small, can't be ceil).
4. Return ceil.
*/

class GFG_FloorAndCeilInBST {

    /**
     * Finds the floor of k in the BST —
     * the largest TreeNode value less than or equal to k.
     *
     * @param root root of the BST
     * @param k    target value
     * @return floor value, or -1 if no floor exists
     */
    public int findFloor(TreeNode root, int k) {
        if (root == null)
            return -1;

        TreeNode mover = root;
        int floor = -1;

        while (mover != null) {
            if (k == mover.val)
                return k;
            if (mover.val < k) {
                // valid candidate, go right for a closer one
                floor = mover.val;
                mover = mover.right;
            } else {
                // too big, can't be floor
                mover = mover.left;
            }
        }

        return floor;
    }

    /**
     * Finds the ceil of x in the BST —
     * the smallest TreeNode value greater than or equal to x.
     *
     * @param root root of the BST
     * @param x    target value
     * @return ceil value, or -1 if no ceil exists
     */
    public int findCeil(TreeNode root, int x) {
        if (root == null)
            return -1;

        TreeNode mover = root;
        int ceil = -1;

        while (mover != null) {
            if (x == mover.val)
                return x;
            if (mover.val < x) {
                // too small, can't be ceil
                mover = mover.right;
            } else {
                // valid candidate, go left for a closer one
                ceil = mover.val;
                mover = mover.left;
            }
        }

        return ceil;
    }
}

// Time Complexity: O(h) — O(log n) balanced, O(n) skewed
// Space Complexity: O(1)
