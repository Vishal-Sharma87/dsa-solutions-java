package graph.shortestpath;

// Created at: 27-May-2026
// Last revised at: 27-May-2026
// Link: https://www.geeksforgeeks.org/problems/shortest-path-in-weighted-undirected-graph/1

import java.util.*;

/*
Problem Description:
--------------------
Statement:
Given a DAG with V vertices, E directed weighted edges, and source always at vertex 0,
find the shortest distance from vertex 0 to every other vertex.
Return -1 for vertices unreachable from source.

Example:
V = 4, E = 2, edges = [[0,1,2],[0,2,1]]
Output: [0, 2, 1, -1]

Constraints:
1 <= V <= 10^3
1 <= E <= V*(V-1)/2
0 <= edge weight <= 10^5
*/

/*
Approach: Dijkstra's Algorithm
-------------------------------
Idea:
Use a min-heap to always process the node with the currently known smallest distance.
For each node popped, relax all outgoing edges. Stale heap entries (where a better
path was already found) are pruned with a continue guard to avoid unnecessary work.

Time Complexity: O((V + E) log V) — each node/edge may push to the heap once; heap ops are log V
Space Complexity: O(V + E) — adjacency list + distances array + heap

Key Insight:
Without the stale-entry guard (`if (dis > distances[node]) continue`), the algorithm
still produces correct results because stale entries can't update neighbors to worse values.
But skipping them early avoids redundant neighbor traversals — important for dense graphs.

Note: Although this is a DAG, Dijkstra's works here because all edge weights are non-negative.
For general DAGs you could also use topological sort + relaxation in O(V + E), but
Dijkstra's is the standard approach taught alongside this problem.
*/

/*
Method to Solve:
----------------
1. Build a directed adjacency list: node → [(neighbor, weight)].
2. Init distances[] = -1 (unreachable). Set distances[0] = 0.
3. Push (node=0, dist=0) into a min-heap ordered by distance.
4. Poll from the heap. If popped distance > distances[node], it's stale — skip.
5. Relax all outgoing edges: if distances[nbr] == -1 or new dist is better, update and push.
6. Repeat until heap is empty.
*/

public class GFG_ShortestPathDAG {

    private static class Pair {
        int node;
        int distance;

        public Pair(int n, int d) {
            node = n;
            distance = d;
        }
    }

    /**
     * Returns shortest distances from vertex 0 to all other vertices in a weighted
     * DAG.
     *
     * @param V     number of vertices
     * @param E     number of edges
     * @param edges list of directed edges [u, v, weight]
     * @return distances array where distances[i] = shortest path from 0 to i, -1 if
     *         unreachable
     */
    public int[] shortestPath(int V, int E, int[][] edges) {
        Map<Integer, List<Pair>> adjList = new HashMap<>();

        for (int[] edge : edges) {
            int node = edge[0];
            int nbr = edge[1];
            int weight = edge[2];
            adjList.computeIfAbsent(node, k -> new ArrayList<>()).add(new Pair(nbr, weight));
        }

        int[] distances = new int[V];
        Arrays.fill(distances, -1);

        PriorityQueue<Pair> minHeap = new PriorityQueue<>((x, y) -> x.distance - y.distance);

        distances[0] = 0;
        minHeap.offer(new Pair(0, 0));

        List<Pair> emptyList = new ArrayList<>();

        while (!minHeap.isEmpty()) {
            Pair p = minHeap.poll();
            int node = p.node;
            int dis = p.distance;

            // stale entry — a shorter path to this node was already processed
            if (dis > distances[node])
                continue;

            for (Pair pair : adjList.getOrDefault(node, emptyList)) {
                int nbr = pair.node;
                int edgeDis = dis + pair.distance;

                if (distances[nbr] == -1 || distances[nbr] > edgeDis) {
                    distances[nbr] = edgeDis;
                    minHeap.offer(new Pair(nbr, edgeDis));
                }
            }
        }

        return distances;
    }
}

// Time Complexity: O((V + E) log V)
// Space Complexity: O(V + E)