package tree.hard;

import java.util.HashMap;
import tree.TreeNode;

// Created at: 13-May-2026
// Last revised at: 13-May-2026
// Link: https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/

/*
Problem:
Construct binary tree from inorder and postorder traversals.

Approach:
Use a HashMap to index inorder values. Iterate postorder from the end using
`it` and recursively build right then left subtrees.
*/

public class LC106ConstructBinaryTreeFromInorderPostorder {

    private int it;

    private TreeNode construct(int start, int end, int[] postorder, HashMap<Integer, Integer> indices) {
        if (start > end)
            return null;

        int index = indices.get(postorder[it]);

        TreeNode root = new TreeNode(postorder[it]);
        it--;

        root.right = construct(index + 1, end, postorder, indices);
        root.left = construct(start, index - 1, postorder, indices);

        return root;
    }

    public TreeNode buildTree(int[] inorder, int[] postorder) {
        int len = inorder.length;
        it = len - 1;
        HashMap<Integer, Integer> indices = new HashMap<>();
        for (int i = 0; i < len; i++) {
            indices.put(inorder[i], i);
        }
        return construct(0, len - 1, postorder, indices);
    }
}
