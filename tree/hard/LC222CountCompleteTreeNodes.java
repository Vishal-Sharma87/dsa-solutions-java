package tree.hard;

import tree.TreeNode;

// Created at: 13-May-2026
// Last revised at: 13-May-2026
// Link: https://leetcode.com/problems/count-complete-tree-nodes/

/*
Problem:
Count the number of nodes in a complete binary tree.

Approach:
Use the height of the leftmost and rightmost paths to detect full subtrees.
If heights are equal the subtree is perfect and node count is (1<<h)-1.
Otherwise recurse on both sides.

Time Complexity: O((log n)^2) in worst-case for complete tree
Space Complexity: O(h) recursion stack
*/

public class LC222CountCompleteTreeNodes {

    private int count(TreeNode root) {
        if (root == null)
            return 0;

        int lh = 1;
        TreeNode mover = root.left;
        while (mover != null) {
            lh++;
            mover = mover.left;
        }
        mover = root.right;

        int rh = 1;
        while (mover != null) {
            rh++;
            mover = mover.right;
        }

        if (lh == rh)
            return (1 << lh) - 1;
        return 1 + count(root.left) + count(root.right);
    }

    public int countNodes(TreeNode root) {
        return count(root);
    }
}
