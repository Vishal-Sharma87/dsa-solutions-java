package tree.hard;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

import tree.TreeNode;

// Created at: 12-May-2026
// Last revised at: 12-May-2026
// Link: https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/

/*
Problem Description:
--------------------
Statement:
Given the root of a binary tree, a target node, and an integer k, return a list
of all nodes at distance k from the target node. Distance is the number of edges
between two nodes (can travel up to parent or down to children).

Example:
        3
       / \
      5   1
     / \ / \
    6  2 0  8
      / \
     7   4

target = 5, k = 2  →  [7, 4, 1]
  - 7 and 4 are 2 edges below 5
  - 1 is 2 edges above 5 (through parent 3)

Constraints:
1 ≤ N ≤ 500
0 ≤ Node.val ≤ 500  (all values unique)
target is guaranteed to exist in the tree
0 ≤ k ≤ 1000
*/

/*
Approach 1: Convert Tree to Graph
Idea:
    Build an undirected adjacency list from the tree (parent-child edges in both
    directions). Then run a standard BFS from target up to distance k.

Time Complexity:  O(n)
Space Complexity: O(n) — adjacency list + visited set

Drawbacks:
    Extra O(n) space for the graph. Works cleanly but the graph build step feels
    redundant given the tree structure is already there.

★ Approach 2: DFS Path + Targeted BFS
Idea:
    1. DFS to find the path from root to target (stored bottom-up:
       path[0] = target, path[i] = ancestor at distance i).
    2. BFS downward from target's subtree with distance k.
    3. For each ancestor at index i in the path:
         - If k == i → the ancestor itself is a valid answer.
         - Else → BFS into the ancestor's "other" child (the one not on the path)
           with remaining distance k - i - 1.
    4. Take the union of all BFS results.

    Null nodes return 0 in BFS so missing subtrees are handled without extra checks.

Time Complexity:  O(n) — DFS visits each node once; each BFS pass is bounded by
                         the number of nodes in that subtree; total across all BFS
                         calls is still O(n)
Space Complexity: O(n) — path list + BFS queue + recursion stack
*/

/*
Method to Solve:
----------------
1. DFS to build path from root → target (bottom-up in the list).
2. BFS from path[0] (target) with distance k → captures all nodes reachable
   downward from target.
3. If k == 0, return immediately (only the target itself).
4. Walk up path from i = 1 to path.size() - 1:
     a. If k == i → add ancestor to answer and return (no subtree needed).
     b. Determine the "other" child: if node.left == previous path node,
        take node.right; else take node.left.
     c. BFS into that child with distance k - i - 1.
5. Return collected answers.
*/

class LC863AllNodesDistanceK {

    /**
     * Finds the path from {@code root} to the node with value {@code target}.
     * Nodes are added post-order (target first, then ancestors up to root),
     * so path.get(i) is the ancestor at distance i from the target.
     *
     * @param root   current node being explored
     * @param target value to search for
     * @param path   list accumulating the root-to-target path (bottom-up)
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
     * Collects all nodes exactly {@code dis} levels below {@code root}
     * via level-order traversal and appends their values to {@code ans}.
     *
     * @param root starting node for BFS
     * @param dis  number of levels to descend (0 means root itself)
     * @param ans  list receiving matching node values
     */
    private void bfs(TreeNode root, int dis, List<Integer> ans) {
        if (root == null || dis < 0)
            return;

        int level = 0;

        Deque<TreeNode> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                TreeNode node = q.poll();

                if (level == dis) {
                    ans.add(node.val);
                }

                if (node.left != null)
                    q.offer(node.left);
                if (node.right != null)
                    q.offer(node.right);
            }

            level++;
            if (level > dis)
                break; // no point going deeper
        }
    }

    /**
     * Returns all nodes at distance k from the target node in the binary tree.
     * Distance is measured in edges and can travel upward through parents.
     *
     * @param root   root of the binary tree
     * @param target the node from which distance k is measured
     * @param k      required distance
     * @return list of node values at exactly distance k from target
     */
    public List<Integer> distanceK(TreeNode root, TreeNode target, int k) {
        List<Integer> ans = new ArrayList<>();
        if (root == null)
            return ans;

        // build bottom-up path: path[i] = ancestor at distance i from target
        List<TreeNode> path = new ArrayList<>();
        if (!dfs(root, target.val, path))
            return ans;

        // all nodes at distance k inside target's own subtree
        bfs(path.get(0), k, ans);

        if (k == 0)
            return ans;

        for (int i = 1; i < path.size(); i++) {
            TreeNode node = path.get(i);
            TreeNode ignore = path.get(i - 1); // child already processed

            // ancestor itself is exactly k edges away
            if (k == i) {
                ans.add(node.val);
                return ans;
            }

            // go into the "other" child — the one not on the path to target
            TreeNode bfsNode;
            if (node.left != null && node.left == ignore) {
                bfsNode = node.right;
            } else {
                bfsNode = node.left;
            }

            // i edges to reach ancestor + 1 to enter child + (k-i-1) inside subtree = k
            int dis = k - i - 1;
            bfs(bfsNode, dis, ans);
        }

        return ans;
    }
}