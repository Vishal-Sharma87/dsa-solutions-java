package graph.shortestpath;

// Created at: 27-May-2026
// Last revised at: 27-May-2026
// Link: https://www.geeksforgeeks.org/problems/shortest-path-in-undirected-graph-having-unit-distance/1

import java.util.*;

/*
Problem Description:
--------------------
Statement:
Given an undirected graph with V vertices and a list of edges (all unit weight),
find the shortest distance from a given source node to all other vertices.
Return -1 for vertices not reachable from src.

Example:
V = 4, edges = [[0,1],[1,2],[2,3],[0,3]], src = 0
Output: [0, 1, 2, 1]

Constraints:
1 <= V <= 10^4
0 <= edges.length <= 10^4
0 <= src < V
*/

/*
Approach: BFS (Level-order traversal)
--------------------------------------
Idea:
Since all edges have unit weight, BFS naturally explores nodes in order of
increasing distance. Process level by level — all nodes at distance d are
explored before any node at distance d+1.

Time Complexity: O(V + E)
Space Complexity: O(V + E) — adjacency list + distances + visited arrays

Key Insight:
Dijkstra's is overkill for unit-weight graphs. BFS gives the same optimal
result in O(V + E) vs O((V + E) log V).
*/

/*
Method to Solve:
----------------
1. Build adjacency list from the edge list.
2. Init distances[] = -1 (unreachable by default).
3. BFS from src: set distances[src] = 0, mark visited.
4. Process level by level — capture current queue size before expanding.
5. After each level, increment the distance counter.
6. For each node polled, set distances[node] = dis, then enqueue unvisited neighbors.
*/

public class GFG_ShortestPathUndirectedGraph {

    /**
     * Returns shortest distance from src to every vertex in an undirected
     * unit-weight graph.
     *
     * @param V     number of vertices
     * @param edges list of undirected edges [u, v]
     * @param src   source vertex
     * @return distances array where distances[i] = shortest path from src to i, -1
     *         if unreachable
     */
    public int[] shortestPath(int V, int[][] edges, int src) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();

        for (int[] edge : edges) {
            adjList.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(edge[1]);
            adjList.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(edge[0]);
        }

        int dis = 0;

        int[] distances = new int[V];
        Arrays.fill(distances, -1);

        boolean[] visited = new boolean[V];
        Deque<Integer> q = new LinkedList<>();

        q.offer(src);
        visited[src] = true;
        distances[src] = 0;

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                int node = q.poll();
                distances[node] = dis;

                for (int nbr : adjList.getOrDefault(node, Collections.emptyList())) {
                    if (!visited[nbr]) {
                        q.offer(nbr);
                        visited[nbr] = true;
                    }
                }
            }

            // only increment if there's a next level to process
            if (!q.isEmpty())
                dis++;
        }

        return distances;
    }
}

// Time Complexity: O(V + E)
// Space Complexity: O(V + E)