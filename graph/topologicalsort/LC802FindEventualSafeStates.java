package graph.topologicalsort;

// Created at: 24-May-2026
// Last revised at: 24-May-2026
// Link: https://leetcode.com/problems/find-eventual-safe-states/

import java.util.*;

/*
Problem Description:
--------------------
Statement:
A node in a directed graph is "safe" if every path starting from
that node leads to a terminal node (a node with no outgoing edges)
or another safe node. Return all safe nodes in ascending order.

Example:
Input: graph = [[1,2],[2,3],[5],[0],[5],[],[]]
Output: [2,4,5,6]
Explanation: Nodes not on or leading into a cycle are safe.

Constraints:
- n == graph.length
- 1 <= n <= 10^4
- 0 <= graph[i].length <= n
- graph[i] is sorted in ascending order
- There are no self-loops
- Each graph[i][j] is distinct
*/

/*
Approach 1: Reverse Graph + Kahn's Algorithm
Idea:
Reverse all edges. Terminal nodes (originally out-degree 0) become
sources. Run BFS from these sources; nodes reachable in the reverse
graph are safe.

Time Complexity: O(V + E)
Space Complexity: O(V + E)

Drawbacks:
Requires explicitly building a reversed adjacency list, adding
O(V + E) extra space and a full graph reconstruction pass.

Approach 2: DFS + Cycle Detection with Memoization (Used Here)
Idea:
A node is safe if and only if it is NOT part of a cycle AND all
paths from it eventually lead to safe nodes. Run DFS tracking the
current recursion path (path[]). If DFS revisits a node on the
current path, a cycle is detected and the node is unsafe. Once a
node's safety is resolved, cache it in safe[] to avoid recomputation.

Key Insight:
visited[] + safe[] together act as a two-value memo cache:
- visited[node] = true but safe[node] = false → node is on/leads to a cycle
- visited[node] = true and safe[node] = true  → node is confirmed safe
path[] is the classic DFS "gray node" marker, reset on backtrack.

Time Complexity: O(V + E) — each node and edge visited once
Space Complexity: O(V + E) — recursion stack + three boolean arrays
*/

/*
Method to Solve:
----------------
1. For each unvisited node, run DFS.
2. In DFS: if node is on current path → cycle → return false.
          if node already visited → return cached safe[node].
3. Mark node as on-path and visited before exploring neighbors.
4. If any neighbor returns false (leads to cycle), unmark path and
   return false immediately.
5. After all neighbors pass, unmark path, mark node safe, return true.
6. Collect all nodes where safe[i] == true in ascending order.
*/

// Time Complexity: O(V + E)
// Space Complexity: O(V + E)

class LC802FindEventualSafeStates {

    /**
     * DFS to determine if a node is safe (not on or leading to a cycle).
     * Uses path[] for cycle detection and visited[]+safe[] for memoization.
     *
     * @param node    current node being explored
     * @param graph   adjacency list representation of the directed graph
     * @param safe    memoized safety result per node
     * @param path    tracks nodes on the current DFS recursion stack
     * @param visited tracks fully resolved nodes
     * @return true if node is safe, false if it leads to a cycle
     */
    private boolean dfs(int node, int[][] graph, boolean[] safe,
            boolean[] path, boolean[] visited) {
        if (path[node])
            return false; // back-edge: cycle detected
        if (visited[node])
            return safe[node]; // already resolved

        path[node] = true;
        visited[node] = true;

        for (int nbr : graph[node]) {
            if (!dfs(nbr, graph, safe, path, visited)) {
                path[node] = false; // backtrack before returning
                return false;
            }
        }

        path[node] = false; // done exploring this node's subtree
        safe[node] = true;
        return true;
    }

    /**
     * Returns all eventual safe nodes in ascending order.
     * A node is safe if every path from it terminates at a terminal node.
     *
     * @param graph adjacency list where graph[i] contains neighbors of node i
     * @return sorted list of all safe nodes
     */
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int nodes = graph.length;

        boolean[] visited = new boolean[nodes];
        boolean[] safe = new boolean[nodes];
        boolean[] path = new boolean[nodes];

        for (int i = 0; i < nodes; i++) {
            if (!visited[i])
                dfs(i, graph, safe, path, visited);
        }

        List<Integer> safeNodes = new ArrayList<>();
        for (int i = 0; i < nodes; i++) {
            if (safe[i])
                safeNodes.add(i); // graph[i].length == 0 included naturally
        }
        return safeNodes;
    }
}
