package graph;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
// Created at: 19-May-2026
// Last revised at: 19-May-2026
// Time Complexity: O(V + E) — V nodes touched, E edges each processed twice
// Space Complexity: O(V + E) — adjacency list stores all nodes and edges

/*
 * Utility: EdgeListToAdjacencyList
 * ---------------------------------
 * Converts an undirected edge list into an adjacency list representation.
 *
 * Input:
 *   edgeList → [[1, 2], [1, 4], [2, 3], [2, 4]]
 *   n        → total number of nodes (0 to n-1)
 *
 * Output:
 *   LinkedHashMap<Integer, List<Integer>>
 *   1 → [2, 4]
 *   2 → [1, 3, 4]
 *   3 → [2]
 *   4 → [1, 2]
 *
 * Note:
 *   - Only nodes that appear in at least one edge are present in the returned map.
 *   - Isolated nodes (valid nodes with no edges) are NOT included.
 *     The caller is responsible for handling isolated nodes via a 0..n-1 loop.
 *   - Self-loop edges (U == V) will add the node to its own neighbor list twice.
 *     Standard DSA problems use simple graphs — self-loops are not expected.
 *   - LinkedHashMap is used to preserve insertion order for deterministic traversal.
 *   - The n parameter is intentionally unused here; it exists for caller-side use
 *     (visited array sizing, outer loop bounds).
 */

/*
 * Method to Solve:
 * ----------------
 * 1. Return empty map immediately if edgeList has no edges.
 * 2. For each edge [U, V]:
 *    a. Get or create U's neighbor list, add V, put back.
 *    b. Get or create V's neighbor list, add U, put back.
 * 3. Return the populated adjacency list.
 */

/**
 * Converts an undirected edge list to an adjacency list.
 * Only nodes with at least one edge are included in the output map.
 * Isolated node handling is the caller's responsibility.
 */
public class EdgeListToAdjacencyList {

    /**
     * Converts an undirected edge list into a LinkedHashMap adjacency list.
     * Insertion order is preserved via LinkedHashMap for deterministic traversal.
     *
     * @param edgeList 2D array where each row [U, V] represents an undirected edge
     * @param n        total number of nodes (0 to n-1); unused internally, present
     *                 for caller context
     * @return adjacency list mapping each node to its list of neighbors;
     *         empty map if edgeList has no edges
     */
    public static Map<Integer, List<Integer>> convert(int[][] edgeList) {
        Map<Integer, List<Integer>> adjList = new LinkedHashMap<>();

        if (edgeList.length == 0)
            return adjList;

        for (int[] edge : edgeList) {
            int U = edge[0];
            int V = edge[1];

            // U → V
            List<Integer> neighboursOfU = adjList.getOrDefault(U, new ArrayList<>());
            neighboursOfU.add(V);
            adjList.put(U, neighboursOfU);

            // V → U
            List<Integer> neighboursOfV = adjList.getOrDefault(V, new ArrayList<>());
            neighboursOfV.add(U);
            adjList.put(V, neighboursOfV);
        }

        return adjList;
    }
}