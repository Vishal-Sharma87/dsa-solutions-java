package tree.traversal;

import java.util.*;

import tree.TreeNode;

public class PostorderTraversal {

    /**
     * Recursive helper: left → right → root.
     *
     * @param root     current node
     * @param elements accumulator list
     */
    private void helper(TreeNode root, List<Integer> elements) {
        if (root == null)
            return;

        helper(root.left, elements);
        helper(root.right, elements);
        elements.add(root.val);
    }

    /**
     * Returns postorder traversal of the binary tree (left → right → root).
     *
     * @param root root of the binary tree
     * @return list of node values in postorder
     */
    public List<Integer> traverse(TreeNode root) {
        List<Integer> elements = new ArrayList<>();
        helper(root, elements);
        return elements;
    }
}