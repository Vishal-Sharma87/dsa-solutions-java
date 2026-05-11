package tree.hard;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import tree.TreeNode;

// Created at: 12-May-2026
// Last revised at: 12-May-2026
// Link: https://www.geeksforgeeks.org/problems/burning-tree/1

/*
Problem Description:
--------------------
Statement:
Given a binary tree and a target TreeNode value, fire starts at the target TreeNode
at time 0. Every second, fire spreads to all adjacent TreeNodes (parent, left child,
right child). Return the minimum time required to burn the entire tree.

Example:
            1
           / \
          2   3
         / \
        4   5

target = 2
  t=0: TreeNode 2 burns
  t=1: TreeNodes 4, 5, 1 burn (children of 2 + parent 1)
  t=2: TreeNode 3 burns (child of 1)
  Answer: 2

Constraints:
1 ≤ N ≤ 10^5
1 ≤ TreeNode.val ≤ 10^5  (all values unique)
Target TreeNode is guaranteed to exist in the tree
*/

/*
Approach 1: Parent Mapping + Multi-source BFS
Idea:
    Build a parent map for all TreeNodes via a DFS/BFS pass.
    Then run a standard BFS from the target treating the tree as an undirected
    graph (left child, right child, parent all count as neighbors).
    Track visited TreeNodes; the last level reached is the answer.

Time Complexity:  O(n)
Space Complexity: O(n) — parent map + visited set + BFS queue

Drawbacks:
    Extra O(n) space for parent map. Two separate passes (map build + BFS).
    Clean but wasteful when the tree structure already gives us what we need.

★ Approach 2: DFS Ancestor Path + Targeted BFS
Idea:
    1. DFS to find the path from root to target (bottom-up):
       path[0] = target, path[i] = ancestor at distance i.
    2. BFS downward from target — measures how deep fire burns inside
       target's own subtree. bfs(target) - 1 is the burn time
       (bfs returns level count; subtract 1 because target itself
       burns at t=0, not t=1).
    3. For each ancestor at index i (fire reaches it at time i):
         - BFS into the ancestor's "other" subtree (the side not on the
           path toward target).
         - Burn time through that direction = i + bfs(otherChild).
           (i to reach ancestor + bfs depth into its other subtree)
    4. Answer is the maximum across all directions.

    Null TreeNodes return 0 in BFS so missing subtrees are handled without
    extra null checks.

Time Complexity:  O(n) — DFS is O(n); each BFS is bounded by its subtree
                         size; total across all BFS calls is still O(n)
Space Complexity: O(n) — path list + BFS queue + recursion stack
*/

/*
Method to Solve:
----------------
1. DFS to build path from root → target (bottom-up in the list).
2. BFS from path[0] (target) → returns depth of target's subtree.
   Initial time = bfs(target) - 1.
3. Walk up path from i = 1 to path.size() - 1:
     a. Identify the "other" child: if TreeNode.left == previous path TreeNode,
        take TreeNode.right; else take TreeNode.left.
     b. currTime = i + bfs(otherChild).
        - i     : time for fire to travel up to this ancestor
        - bfs() : depth of the other subtree (0 if null → only ancestor itself)
     c. time = max(time, currTime).
4. Return time.
*/

class GFG_BurningTree {

    /**
     * Finds the path from {@code root} to the TreeNode with value {@code target}.
     * TreeNodes are added post-order (target first, then ancestors up to root),
     * so path.get(i) is the ancestor at distance i from the target.
     *
     * @param root   current TreeNode being explored
     * @param target value to search for
     * @param path   list accumulating the path bottom-up
     * @return true if target was found in this subtree
     */
    private boolean dfs(TreeNode root, int target, List<TreeNode> path) {
        if (root == null)
            return false;
        if (root.val == target) {
            path.add(root);
            return true;
        }

        boolean found = dfs(root.left, target, path);
        if (found) {
            path.add(root);
            return true;
        }
        if (dfs(root.right, target, path)) {
            path.add(root);
            return true;
        }

        return false;
    }

    /**
     * Returns the number of levels in the subtree rooted at {@code root}
     * (equivalently, the height + 1). A null root returns 0; a single
     * TreeNode returns 1.
     *
     * <p>
     * Used to compute burn time: bfs(TreeNode) - 1 gives the number of
     * seconds needed to burn every TreeNode in that subtree starting from TreeNode
     * itself at t=0.
     *
     * @param root root of the subtree to measure
     * @return level count (depth) of the subtree
     */
    private int bfs(TreeNode root) {
        if (root == null)
            return 0;

        int level = 0;

        Deque<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            level++; // count this level before processing it
            int size = q.size();

            for (int i = 0; i < size; i++) {
                TreeNode TreeNode = q.poll();

                if (TreeNode.left != null)
                    q.offer(TreeNode.left);
                if (TreeNode.right != null)
                    q.offer(TreeNode.right);
            }
        }

        return level;
    }

    /**
     * Returns the minimum time (in seconds) to burn the entire binary tree
     * when fire starts at the TreeNode with value {@code target}.
     *
     * @param root   root of the binary tree
     * @param target value of the TreeNode where fire starts
     * @return minimum seconds to burn the entire tree; -1 if target not found
     */
    public int minTime(TreeNode root, int target) {
        if (root == null)
            return -1;

        // build bottom-up path: path[i] = ancestor at distance i from target
        List<TreeNode> path = new ArrayList<>();
        if (!dfs(root, target, path))
            return -1;

        // burn time inside target's own subtree (target burns at t=0, hence -1)
        int time = bfs(path.get(0)) - 1;

        for (int i = 1; i < path.size(); i++) {
            TreeNode TreeNode = path.get(i);
            TreeNode ignore = path.get(i - 1); // child already covered

            // go into the "other" child — the one not on the path to target
            TreeNode bfsTreeNode;
            if (TreeNode.left != null && TreeNode.left == ignore) {
                bfsTreeNode = TreeNode.right;
            } else {
                bfsTreeNode = TreeNode.left;
            }

            // i seconds to reach ancestor + bfs depth into its other subtree
            // bfs(null) = 0 → currTime = i, correctly capturing just the ancestor
            int currTime = i + bfs(bfsTreeNode);
            time = Math.max(time, currTime);
        }

        return time;
    }
}