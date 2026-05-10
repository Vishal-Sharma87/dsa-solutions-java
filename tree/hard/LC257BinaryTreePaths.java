package tree.hard;

// Created at: 11-May-2025
// Last revised at: 11-May-2025
// Link: https://leetcode.com/problems/binary-tree-paths/

import java.util.ArrayList;
import java.util.List;

import tree.TreeNode;

/*
Problem Description:
--------------------
Statement:
Given the root of a binary tree, return all root-to-leaf paths in any order.
A leaf is a node with no children.

Example:
Input: root = [1,2,3,null,5]
Output: ["1->2->5","1->3"]

Input: root = [1]
Output: ["1"]

Constraints:
- The number of nodes in the tree is in the range [1, 100].
- -100 <= Node.val <= 100
*/

/*
Approach 1: DFS with Path String Accumulation
----------------------------------------------
Idea:
Do a preorder DFS. Carry the current path string down as we recurse.
At every leaf, the accumulated string is a complete root-to-leaf path — add it to the result.

Time Complexity: O(N * H)
- We visit every node once: O(N).
- At each leaf, we copy the accumulated string of length O(H) into the list.
- In the worst case (skewed tree), H = N, so overall O(N^2).

Space Complexity: O(H)
- Recursion stack depth is O(H).
- String at each level is O(H) but those are intermediate and GC'd.
- Result list holds at most O(N) strings, each of length O(H).

Key Insight:
Pass the path string by value — Java strings are immutable, so each recursive
call gets its own copy automatically. No need for explicit backtracking.
*/

/*
Method to Solve:
----------------
1. Start DFS from root with an empty path string.
2. At each node, append the node's value to the current path.
3. If the node is a leaf, record the path and return.
4. Otherwise, append "->" and recurse into left and right children.
*/

// Time Complexity: O(N * H)
// Space Complexity: O(H) recursion stack; O(N * H) for result strings

public class LC257BinaryTreePaths {

    /**
     * Recursively builds all root-to-leaf paths via DFS.
     *
     * @param root  current tree node
     * @param path  path string accumulated so far (without trailing "->")
     * @param paths list collecting complete root-to-leaf path strings
     */
    private void collectPaths(TreeNode root, String path, List<String> paths) {
        if (root == null)
            return;

        // append current node
        String current = path + root.val;

        // leaf: complete path found
        if (root.left == null && root.right == null) {
            paths.add(current);
            return;
        }

        // go deeper with separator
        collectPaths(root.left, current + "->", paths);
        collectPaths(root.right, current + "->", paths);
    }

    /**
     * Returns all root-to-leaf paths of the binary tree as strings.
     *
     * @param root root of the binary tree
     * @return list of path strings in "val1->val2->...->leaf" format
     */
    public List<String> binaryTreePaths(TreeNode root) {
        List<String> paths = new ArrayList<>();
        collectPaths(root, "", paths);
        return paths;
    }
}