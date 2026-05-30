package graph.mst;

// Created at: 31-May-2026
// Last revised at: 31-May-2026
// Link: https://www.geeksforgeeks.org/problems/minimum-spanning-tree/1

/*
Problem Description:
--------------------
Statement:
Given a weighted, undirected, and connected graph with V vertices and a list of edges,
find the sum of weights of the edges present in its Minimum Spanning Tree (MST).

Example:
Input:
V = 3
edges = {{0,1,5}, {1,2,3}, {0,2,1}}

Output:
4

Explanation:
Pick edges (0-2) and (1-2).
Total MST weight = 1 + 3 = 4.

Constraints:
1 <= V <= 10^3
1 <= E <= 10^5
0 <= edgeWeight <= 10^6
*/

/*
Approach 1: Kruskal's Algorithm
Idea:
- Sort all edges by weight.
- Use Disjoint Set Union (DSU) to avoid cycles.
- Pick the smallest valid edge until V - 1 edges are selected.

Time Complexity:
O(E log E)

Space Complexity:
O(V)

Drawbacks:
Requires sorting all edges and maintaining a DSU structure.
*/

/*
Approach 2: Prim's Algorithm (Min Heap) [Implemented]
Idea:
- Start from any node (node 0).
- Continuously pick the minimum weight edge that connects
  an unvisited vertex to the growing MST.
- Use a min heap to efficiently retrieve the next best edge.
- Mark vertices as processed and expand the frontier.

Time Complexity:
O(E log E)

Space Complexity:
O(E)

Key Insight:
At every step, the minimum edge crossing the cut between
visited and unvisited vertices is guaranteed to be part of the MST.
*/

/*
Method to Solve:
----------------
1. Build an adjacency list from the edge list.
2. Mark node 0 as part of the MST.
3. Push all edges of node 0 into a min heap.
4. Repeatedly pick the minimum weight edge.
5. Skip it if the destination node is already processed.
6. Otherwise include the edge weight in the MST sum.
7. Add all outgoing edges of the newly connected node.
8. Stop after all vertices become part of the MST.
*/

import java.util.*;

public class PrimsAlgorithm {

    private static class Info {
        int v;
        int w;

        Info(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }

    /**
     * Returns the total weight of the Minimum Spanning Tree.
     *
     * @param V     number of vertices
     * @param edges edge list where each edge is represented as {u, v, weight}
     * @return MST weight sum
     */
    public int spanningTree(int V, int[][] edges) {

        Map<Integer, List<List<Integer>>> adjList = new HashMap<>();

        for (int[] edge : edges) {

            List<Integer> e1 = new ArrayList<>();
            e1.add(edge[1]);
            e1.add(edge[2]);
            adjList.computeIfAbsent(edge[0], k -> new ArrayList<>()).add(e1);

            List<Integer> e2 = new ArrayList<>();
            e2.add(edge[0]);
            e2.add(edge[2]);
            adjList.computeIfAbsent(edge[1], k -> new ArrayList<>()).add(e2);
        }

        PriorityQueue<Info> pq = new PriorityQueue<>((a, b) -> a.w - b.w);

        for (List<Integer> edge : adjList.getOrDefault(0, Collections.emptyList())) {
            pq.offer(new Info(edge.get(0), edge.get(1)));
        }

        boolean[] processed = new boolean[V];
        processed[0] = true;

        int mstSum = 0;
        int nodeConnected = 1;

        while (!pq.isEmpty()) {

            Info info = pq.poll();

            // skip already connected nodes
            if (processed[info.v]) {
                continue;
            }

            processed[info.v] = true;
            mstSum += info.w;

            nodeConnected++;

            if (nodeConnected == V) {
                break;
            }

            // expand frontier
            for (List<Integer> edge : adjList.getOrDefault(info.v, Collections.emptyList())) {

                pq.offer(
                        new Info(
                                edge.get(0),
                                edge.get(1)));
            }
        }

        return mstSum;
    }
}

// Time Complexity: O(E log E)
// Space Complexity: O(E)