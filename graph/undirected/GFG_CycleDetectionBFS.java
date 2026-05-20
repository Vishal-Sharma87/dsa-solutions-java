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
Approach 1: BFS with Parent Tracking ★
---------------------------------------
Idea:
Run BFS from every unvisited node (to cover all components).
For each node dequeued, iterate over its neighbours.
- If a neighbour is unvisited → mark visited, set parent, enqueue.
- If a neighbour is visited AND it is NOT the direct parent of the
  current node → a back-edge exists → cycle detected.

The parent array records who enqueued each node so we can
distinguish a legitimate parent-edge from a real back-edge.

Time Complexity:
O(V + E) — every node and edge is processed at most once.

Space Complexity:
O(V + E) — adjacency list O(V+E) + visited array O(V) + parent array O(V) + BFS queue O(V).

Key Insight:
In an undirected graph, every edge appears in both directions in the
adjacency list. Without parent tracking we would falsely detect a cycle
every time we see the edge we just came from.
*/

/*
Method to Solve:
----------------
1. Convert edge list to adjacency list.
2. Maintain a visited[] array of size V.
3. For each unvisited node, call bfs():
   a. Initialise a parent[] array filled with -1 for each BFS call.
   b. Mark source visited, enqueue it.
   c. For each dequeued node, scan its neighbours:
      - Unvisited  → set parent, mark visited, enqueue.
      - Visited and not the direct parent → return true (cycle found).
4. If no BFS call returns true, return false.
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;

import graph.EdgeListToAdjacencyList;

// Time Complexity: O(V + E)
// Space Complexity: O(V + E)
public class GFG_CycleDetectionBFS {

    /**
     * BFS traversal from a single source node to detect a cycle
     * within one connected component.
     *
     * @param nd      source node for this BFS
     * @param V       total number of vertices (used to size parent array)
     * @param adjList adjacency list of the graph
     * @param visited shared visited array across all BFS calls
     * @return true if a cycle is found in this component, false otherwise
     */
    private boolean bfs(int nd, int V, Map<Integer, List<Integer>> adjList, boolean[] visited) {
        Queue<Integer> q = new LinkedList<>();
        int[] parent = new int[V];
        Arrays.fill(parent, -1);

        visited[nd] = true;
        q.offer(nd);

        while (!q.isEmpty()) {
            int node = q.poll();

            for (int nbr : adjList.getOrDefault(node, new ArrayList<>())) {
                if (!visited[nbr]) {
                    q.offer(nbr);
                    parent[nbr] = node;
                    visited[nbr] = true;
                } else if (parent[node] != nbr) {
                    // visited neighbour that isn't our parent → back edge
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Detects whether any cycle exists in the given undirected graph.
     * Handles disconnected graphs by launching BFS from every unvisited node.
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
                if (bfs(nd, V, adjList, visited))
                    return true;
            }
        }
        return false;
    }
}