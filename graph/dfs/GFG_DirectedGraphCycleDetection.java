package graph.dfs;

// Created at: 22-May-2026
// Last revised at: 22-May-2026
// Link: https://www.geeksforgeeks.org/problems/detect-cycle-in-a-directed-graph/1

import java.util.*;

/*
Problem Description:
--------------------
Statement:
    Given a directed graph with V vertices and a list of directed edges,
    determine whether the graph contains a cycle.

Example:
    Input:  V = 4, edges = [[0,1],[1,2],[2,3],[3,1]]
    Output: true
    Explanation: 1 → 2 → 3 → 1 forms a cycle.

    Input:  V = 4, edges = [[0,1],[1,2],[2,3]]
    Output: false

Constraints:
    - 1 <= V <= 10^4
    - 0 <= edges.length <= 10^5
    - Self-loops count as cycles
*/

/*
Approach 1: DFS with path tracking (Used here)
Idea:
    Run DFS from every unvisited node. Maintain two boolean arrays:
      - visited[]: globally marks nodes fully processed (no re-entry from future components)
      - path[]:    marks nodes on the CURRENT active DFS call stack

    If during DFS we reach a node where path[nbr] == true, we've found a back edge —
    which is the definition of a cycle in a directed graph.

    On backtracking, unset path[node] so it doesn't falsely flag other DFS branches.

Time Complexity: O(V + E) — each node and edge visited once
Space Complexity: O(V) — visited, path arrays + recursion stack

Key Insight:
    visited[] alone is NOT enough for directed graphs.
    Consider: A → B → C, A → C. Without path[], discovering C via B and then
    via A would falsely flag a cycle. path[] ensures we only flag true back edges
    within the current recursion stack.

Approach 2: Kahn's Algorithm (BFS Topological Sort)
Idea:
    A directed graph is acyclic if and only if a valid topological order exists.
    Compute in-degrees; process nodes with in-degree 0 via BFS.
    If the count of processed nodes < V, a cycle exists (some nodes were never freed).

Time Complexity: O(V + E)
Space Complexity: O(V)

Key Insight:
    Cleaner for problems that also need topological order. DFS approach is more
    intuitive when only cycle detection is needed.
*/

/*
Method to Solve:
----------------
1. Build adjacency list from edge list (directed: U → V only).
2. Initialize visited[V] = false.
3. For each unvisited node i:
   a. Create a fresh path[V] = false for this traversal.
   b. Run cycleFound(i, adjList, visited, path).
   c. If true, return true immediately.
4. Return false if no cycle found across all components.

cycleFound(node):
1. Mark visited[node] = true, path[node] = true.
2. For each neighbour:
   - If path[nbr] == true → back edge found, return true.
   - If !visited[nbr] → recurse; return true if cycle found deeper.
3. path[node] = false (backtrack).
4. Return false.
*/

// Time Complexity: O(V + E)
// Space Complexity: O(V)

public class GFG_DirectedGraphCycleDetection {

    /**
     * Builds a directed adjacency list from an edge array.
     * Each edge[i] = [U, V] represents a directed edge U → V.
     *
     * @param edgeList array of directed edges
     * @return adjacency list as a map from node → list of neighbours
     */
    private Map<Integer, List<Integer>> buildAdjList(int[][] edgeList) {
        Map<Integer, List<Integer>> adjList = new LinkedHashMap<>();
        for (int[] edge : edgeList) {
            int u = edge[0];
            int v = edge[1];
            adjList.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        }
        return adjList;
    }

    /**
     * DFS helper that detects a cycle using a current-path tracker.
     * Returns true if a back edge is found from this node.
     *
     * @param node    current node being explored
     * @param adjList adjacency list of the graph
     * @param visited globally visited nodes (prevents re-exploration across
     *                components)
     * @param path    nodes on the current DFS call stack (detects back edges)
     * @return true if a cycle is reachable from this node
     */
    private boolean cycleFound(int node,
            Map<Integer, List<Integer>> adjList,
            boolean[] visited,
            boolean[] path) {
        visited[node] = true;
        path[node] = true;

        for (int nbr : adjList.getOrDefault(node, Collections.emptyList())) {
            if (path[nbr])
                return true; // back edge — cycle confirmed

            if (!visited[nbr] && cycleFound(nbr, adjList, visited, path))
                return true;
        }

        path[node] = false; // backtrack: this node is no longer on the active path
        return false;
    }

    /**
     * Determines whether the directed graph contains a cycle.
     *
     * @param V     number of vertices (0-indexed)
     * @param edges list of directed edges as [U, V] pairs
     * @return true if a cycle exists, false otherwise
     */
    public boolean isCyclic(int V, int[][] edges) {
        Map<Integer, List<Integer>> adjList = buildAdjList(edges);

        boolean[] visited = new boolean[V];

        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                boolean[] path = new boolean[V];
                if (cycleFound(i, adjList, visited, path))
                    return true;
            }
        }

        return false;
    }
}
