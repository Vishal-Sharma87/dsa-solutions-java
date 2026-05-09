package tree.view;

// Created at: 10-May-2026
// Last revised at: 10-May-2026
// Link: https://leetcode.com/problems/binary-tree-right-side-view/

import java.util.*;

import tree.TreeNode;

/*
Problem Description:
--------------------
Statement:
    Given the root of a binary tree, imagine yourself standing on the right side of it.
    Return the values of the nodes you can see, ordered from top to bottom.
    A node is visible from the right if it is the rightmost node at its depth level.

Example:
    Input:
           1
         /   \
        2     3
         \     \
          5     4

    Output: [1, 3, 4]

    (At level 2, node 5 exists but 4 is further right — only 4 is visible)

Constraints:
    - Number of nodes: [0, 100]
    - -100 ≤ Node.val ≤ 100
*/

/*
Approaches:
-----------
Approach 1: BFS (level-order traversal)

Idea:
    Process nodes level by level using a queue. For each level, the last node
    dequeued is the rightmost visible node — add it to the result.

Time Complexity: O(N)
Space Complexity: O(N) — queue holds up to one full level at a time (worst case N/2 nodes)

Drawbacks:
    Higher space usage. Queue can hold O(N) nodes for wide trees.

---

Approach 2: Right-first DFS with level-size gate   ← implemented

Idea:
    DFS where the right subtree is always visited before the left. Track `level`
    (depth from root). The result list acts as a level-seen tracker — when
    `level == list.size()`, we are visiting this depth for the first time, meaning
    the current node is the rightmost at that level. Add it and move on.
    Left subtree still gets visited because some right-side-visible nodes may
    come from the left when the right subtree is shorter.

Time Complexity: O(N) — every node is visited exactly once
Space Complexity: O(H) — recursion stack depth equals tree height
                  O(log N) balanced, O(N) worst-case skewed

Key Insight:
    `level == values.size()` elegantly replaces a separate visited-levels set.
    It works because the list grows by exactly 1 per new level, and DFS visits
    levels top-down — so size always equals the number of levels seen so far.
*/

/*
Method to Solve:
----------------
1. DFS with right child before left child at every node.
2. Pass `level` (0-indexed depth) and the result list into the recursion.
3. At each node: if `level == list.size()`, this is the first (rightmost) node
   at this depth — add its value.
4. Recurse right first, then left. Left subtree nodes only add to the list if
   the right subtree didn't already fill that level.
5. Return the list after the DFS completes.
*/

// Time Complexity: O(N)
// Space Complexity: O(H)

public class LC199BinaryTreeRightSideView {

    /**
     * Performs right-first DFS, recording the first node seen at each level.
     * Since right is visited before left, the first node at any level is
     * always the rightmost one visible from the right side.
     *
     * @param root   current node being visited
     * @param level  depth of the current node (root = 0)
     * @param values running result list; its size doubles as a "levels seen"
     *               counter
     */
    private void fillRightView(TreeNode root, int level, List<Integer> values) {
        if (root == null)
            return;

        // first visit to this level → rightmost node at this depth
        if (level == values.size()) {
            values.add(root.val);
        }

        fillRightView(root.right, level + 1, values); // right first
        fillRightView(root.left, level + 1, values); // left only fills uncovered levels
    }

    /**
     * Returns the right-side view of a binary tree from top to bottom.
     *
     * @param root root of the binary tree
     * @return list of values visible from the right side, top to bottom
     */
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> values = new ArrayList<>();
        if (root == null)
            return values;

        fillRightView(root, 0, values);

        return values;
    }
}