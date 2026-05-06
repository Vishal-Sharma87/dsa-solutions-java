package tree.traversal;

import java.util.*;

import tree.TreeNode;

public class InorderTraversal {

    /**
     * Recursive helper: left → root → right.
     *
     * @param root     current node
     * @param elements accumulator list
     */
    private void helper(TreeNode root, List<Integer> elements) {
        if (root == null)
            return;

        helper(root.left, elements);
        elements.add(root.val);
        helper(root.right, elements);
    }

    /**
     * Returns inorder traversal of the binary tree (left → root → right).
     *
     * @param root root of the binary tree
     * @return list of node values in inorder
     */
    public List<Integer> traverse(TreeNode root) {
        List<Integer> elements = new ArrayList<>();
        helper(root, elements);
        return elements;
    }
}