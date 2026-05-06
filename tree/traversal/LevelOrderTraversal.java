package tree.traversal;

import java.util.*;

import tree.TreeNode;

public class LevelOrderTraversal {

    /**
     * Traverses the binary tree level by level (BFS).
     * Groups nodes at the same depth into individual lists.
     *
     * @param root root of the binary tree
     * @return list of levels, each containing node values at that depth
     */
    public List<List<Integer>> traverse(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null)
            return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int levelSize = queue.size(); // freeze count before expanding
            List<Integer> level = new ArrayList<>();

            while (levelSize > 0) {
                TreeNode node = queue.poll();
                level.add(node.val);

                if (node.left != null)
                    queue.offer(node.left);
                if (node.right != null)
                    queue.offer(node.right);

                levelSize--;
            }
            result.add(level);
        }

        return result;
    }
}