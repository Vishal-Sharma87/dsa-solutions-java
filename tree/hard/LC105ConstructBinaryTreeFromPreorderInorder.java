package tree.hard;

import java.util.HashMap;
import tree.TreeNode;

// Created at: 13-May-2026
// Last revised at: 13-May-2026
// Link: https://leetcode.com/problems/construct-binary-tree-from-preorder-and-inorder-traversal/

/*
Problem:
Construct binary tree from preorder and inorder traversals.

Approach:
Use a HashMap to map inorder values to indices. Iterate through preorder
using an index `it` to pick roots, then recursively construct left/right
subtrees using index ranges from inorder.
*/

public class LC105ConstructBinaryTreeFromPreorderInorder {

    private int it;

    private TreeNode construct(int start, int end, int[] preorder, HashMap<Integer, Integer> indices) {
        if (start > end)
            return null;

        int index = indices.get(preorder[it]);

        TreeNode root = new TreeNode(preorder[it]);
        it++;

        root.left = construct(start, index - 1, preorder, indices);
        root.right = construct(index + 1, end, preorder, indices);

        return root;
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        int len = preorder.length;
        it = 0;
        HashMap<Integer, Integer> indices = new HashMap<>();
        for (int i = 0; i < len; i++) {
            indices.put(inorder[i], i);
        }
        return construct(0, len - 1, preorder, indices);
    }
}
