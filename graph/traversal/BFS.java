package graph.traversal;

// Created at: 19-May-2026
// Last revised at: 19-May-2026
// Time Complexity: O(V + E) — each node and edge processed once
// Space Complexity: O(V) — queue and visited array scale with node count

/*
 * BFS — Undirected Graph Traversal
 * ----------------------------------
 * Performs level-order BFS traversal on an undirected graph.
 * Handles both connected and disconnected graphs via a 0..n-1 outer loop.
 *
 * Output format:
 *   --- component ---   ← printed once per connected component
 *   0 1 2               ← level 0
 *   3 4                 ← level 1
 *   ...
 *
 * Key design decisions:
 *   - Visited is marked before enqueue, not after dequeue.
 *     Marking after dequeue allows the same node to be enqueued multiple
 *     times by different neighbors before it is processed — causing duplicates.
 *   - Level boundary tracked via queue size snapshot at the start of each level.
 *     This avoids sentinel nodes and cleanly separates levels without extra state.
 *   - adjList.getOrDefault(u, new ArrayList<>()) safely handles isolated nodes
 *     that are not present in the adjacency list.
 */

/*
 * Method to Solve:
 * ----------------
 * 1. Convert edgeList to adjacency list via EdgeListToAdjacencyList.convert().
 * 2. Initialize visited[] of size n and an empty queue.
 * 3. Iterate nodes 0..n-1 — covers isolated nodes and disconnected components.
 * 4. For each unvisited node, mark it as a new component, enqueue it, mark visited.
 * 5. While queue is not empty:
 *    a. Snapshot current queue size → number of nodes at this level.
 *    b. Poll and print each node at the current level.
 *    c. For each unvisited neighbor, enqueue and mark visited immediately.
 *    d. Print a line break after all nodes at this level are processed.
 */

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import graph.EdgeListToAdjacencyList;

public class BFS {

    /**
     * Performs level-order BFS traversal on an undirected graph.
     * Prints each level on a new line and separates disconnected
     * components with a visual marker.
     *
     * @param edgeList 2D array where each row [U, V] is an undirected edge
     * @param n        total number of nodes (0 to n-1)
     */
    public static void traverseUndirected(int[][] edgeList, int n) {
        Map<Integer, List<Integer>> adjList = EdgeListToAdjacencyList.convert(edgeList);

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];

        System.out.println("Printing BFS");

        for (int node = 0; node < n; node++) {
            if (!visited[node]) {
                System.out.println("--- component ---");

                queue.offer(node);
                visited[node] = true; // mark before enqueue, not after dequeue

                while (!queue.isEmpty()) {
                    int size = queue.size(); // snapshot current level count

                    for (int i = 0; i < size; i++) {
                        int u = queue.poll();
                        System.out.print(u + " ");

                        for (int nbr : adjList.getOrDefault(u, new ArrayList<>())) {
                            if (!visited[nbr]) {
                                queue.offer(nbr);
                                visited[nbr] = true;
                            }
                        }
                    }
                    System.out.println(); // next level on new line
                }
            }
        }
    }
}