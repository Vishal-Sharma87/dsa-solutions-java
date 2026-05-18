package graph.traversal;
// Created at: 19-May-2026

// Last revised at: 19-May-2026
// Time Complexity: O(V + E) — each node and edge visited once
// Space Complexity: O(V) — visited array + recursive call stack depth

/*
 * DFS — Undirected Graph Traversal
 * ----------------------------------
 * Performs recursive DFS traversal on an undirected graph.
 * Handles both connected and disconnected graphs via a 0..n-1 outer loop.
 *
 * Output format:
 *   0
 *   1
 *   4
 *   ...  ← nodes printed in DFS order as they are first visited
 *
 * Key design decisions:
 *   - visited[node] is marked true before processing neighbors, not after.
 *     This prevents re-entering a node via a back-edge or cross-edge in cycles.
 *   - Early return guard `if (visited[node]) return` at the top of the recursive
 *     method keeps each recursive branch self-contained — no pre-check needed
 *     before each neighbor call.
 *   - adjList.getOrDefault(node, new ArrayList<>()) safely handles isolated nodes
 *     not present in the adjacency list, returning an empty neighbor list.
 *   - Recursive approach: clean and readable for DSA scope.
 *     For very deep graphs (chain of thousands of nodes), iterative DFS
 *     with an explicit stack avoids JVM stack overflow.
 */

/*
 * Method to Solve:
 * ----------------
 * 1. Convert edgeList to adjacency list via EdgeListToAdjacencyList.convert().
 * 2. Initialize visited[] of size n.
 * 3. Iterate nodes 0..n-1 — covers isolated nodes and disconnected components.
 * 4. For each node, call traverseUndirectedDfs():
 *    a. If already visited, return immediately.
 *    b. Mark node visited, print it.
 *    c. Recurse into each unvisited neighbor.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import graph.EdgeListToAdjacencyList;

public class DFS {

    /**
     * Performs recursive DFS traversal on an undirected graph.
     * Prints nodes in DFS visit order.
     * Handles disconnected graphs — all nodes 0..n-1 are guaranteed to be visited.
     *
     * @param edgeList 2D array where each row [U, V] is an undirected edge
     * @param n        total number of nodes (0 to n-1)
     */
    public static void traverseUndirected(int[][] edgeList, int n) {
        Map<Integer, List<Integer>> adjList = EdgeListToAdjacencyList.convert(edgeList);

        boolean[] visited = new boolean[n];

        System.out.println("Printing DFS");

        // covers isolated nodes and disconnected components
        for (int node = 0; node < n; node++) {
            traverseUndirectedDfs(node, adjList, visited);
        }
    }

    /**
     * Recursive DFS helper. Visits a node, marks it, then recurses into neighbors.
     * Returns immediately if node is already visited — handles cycles and
     * back-edges.
     *
     * @param node    current node being visited
     * @param adjList adjacency list of the graph
     * @param visited boolean array tracking visited nodes
     */
    private static void traverseUndirectedDfs(int node, Map<Integer, List<Integer>> adjList, boolean[] visited) {
        if (visited[node])
            return;

        visited[node] = true;
        System.out.println(node);

        for (int nbr : adjList.getOrDefault(node, new ArrayList<>())) {
            traverseUndirectedDfs(nbr, adjList, visited);
        }
    }
}