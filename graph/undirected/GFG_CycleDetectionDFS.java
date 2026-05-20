package graph.undirected;

// Created at: 21-May-2026
// Last revised at: 21-May-2026
// Link: https://www.geeksforgeeks.org/problems/detect-cycle-in-an-undirected-graph/1

/*
Problem Description:
--------------------
Statement:
Given an undirected graph with V vertices (0-indexed) and a list of edges,
determine whether the graph contains a cycle.

Example:
Input:  V = 5, edges = [[0,1],[1,2],[2,0],[3,4]]
Output: true
Explanation: Nodes 0→1→2→0 form a cycle.

Input:  V = 4, edges = [[0,1],[1,2],[2,3]]
Output: false
Explanation: Linear chain — no cycle.

Constraints:
- 1 <= V <= 10^5
- 1 <= E <= 10^5
- The graph is undirected and may be disconnected (forest).
*/

/*
Approach 1: BFS with Parent Tracking
--------------------------------------
Idea:
Run BFS from every unvisited node. For each dequeued node, check its
neighbours. If a neighbour is visited and isn't the direct parent → cycle.
Track parent via a parent[] array.

Time Complexity: O(V + E)
Space Complexity: O(V + E) — adjacency list + visited + parent array + queue

Drawbacks:
Slightly more space than DFS due to the explicit parent[] array and BFS queue.
Also less natural to express recursively.

Approach 2: DFS with Parent Passing ★
---------------------------------------
Idea:
Run DFS from every unvisited node, passing the parent node at each recursive
call. For each neighbour:
- Unvisited  → recurse deeper, propagate true if cycle found below.
- Visited and not the direct parent → back-edge detected → cycle exists.

Cleaner than BFS: no parent array needed — parent is carried as a method
parameter through the call stack.

Time Complexity: O(V + E)
Space Complexity: O(V + E) — adjacency list O(V+E) + visited O(V) + recursion stack O(V)

Key Insight:
Every edge (u, v) appears twice in the adjacency list (u→v and v→u).
Without parent tracking, the return edge v→u would always look like
a back-edge, causing a false positive on every edge.
*/

/*
Method to Solve:
----------------
1. Convert edge list to adjacency list.
2. Maintain a visited[] array of size V.
3. For each unvisited node, call dfs(node, parent=-1):
   a. Mark current node visited.
   b. For each neighbour:
      - If unvisited → recurse with current node as parent;
        return true if recursion signals a cycle.
      - If visited and not the direct parent → return true.
4. If no DFS call returns true, return false.
*/

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import graph.EdgeListToAdjacencyList;

// Time Complexity: O(V + E)
// Space Complexity: O(V + E)
public class GFG_CycleDetectionDFS {

    /**
     * DFS traversal from a single source node to detect a cycle
     * within one connected component.
     *
     * @param nd      current node being visited
     * @param parent  node from which we arrived at nd (-1 for the source)
     * @param adjList adjacency list of the graph
     * @param visited shared visited array across all DFS calls
     * @return true if a cycle is reachable from nd, false otherwise
     */
    private boolean dfs(int nd, int parent, Map<Integer, List<Integer>> adjList, boolean[] visited) {
        visited[nd] = true;

        for (int nbr : adjList.getOrDefault(nd, new ArrayList<>())) {
            if (!visited[nbr]) {
                if (dfs(nbr, nd, adjList, visited)) // cycle found deeper
                    return true;
            } else if (parent != nbr) {
                // visited neighbour that isn't our parent → back edge
                return true;
            }
        }
        return false;
    }

    /**
     * Detects whether any cycle exists in the given undirected graph.
     * Handles disconnected graphs by launching DFS from every unvisited node.
     *
     * @param V     number of vertices (0-indexed)
     * @param edges edge list where each element is [u, v]
     * @return true if the graph contains at least one cycle, false otherwise
     */
    public boolean isCycle(int V, int[][] edges) {
        Map<Integer, List<Integer>> adjList = EdgeListToAdjacencyList.convert(edges);
        boolean[] visited = new boolean[V];

        for (int nd = 0; nd < V; nd++) {
            if (!visited[nd]) { // new component
                if (dfs(nd, -1, adjList, visited))
                    return true;
            }
        }
        return false;
    }
}