package tree.traversal;

import java.util.*;

import tree.TreeNode;

public class PreorderTraversal {

    /**
     * Iterative preorder using an explicit stack (root → left → right).
     * Push right first so left is popped first.
     *
     * @param root root of the binary tree
     * @return list of node values in preorder
     */
    public List<Integer> traverse(TreeNode root) {
        List<Integer> elements = new ArrayList<>();
        if (root == null)
            return elements;

        Deque<TreeNode> stack = new ArrayDeque<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            TreeNode node = stack.pop();
            elements.add(node.val);

            if (node.right != null)
                stack.push(node.right); // right first
            if (node.left != null)
                stack.push(node.left); // left on top → popped first
        }

        return elements;
    }

    /**
     * Recursive helper: root → left → right.
     *
     * @param root     current node
     * @param elements accumulator list
     */
    private void traverseRecursiveHelper(TreeNode root, List<Integer> elements) {
        if (root == null)
            return;

        elements.add(root.val);
        traverseRecursiveHelper(root.left, elements);
        traverseRecursiveHelper(root.right, elements);
    }

    /**
     * Recursive preorder traversal (root → left → right).
     *
     * @param root root of the binary tree
     * @return list of node values in preorder
     */
    public List<Integer> traverseRecursive(TreeNode root) {
        List<Integer> elements = new ArrayList<>();
        traverseRecursiveHelper(root, elements);
        return elements;
    }
}