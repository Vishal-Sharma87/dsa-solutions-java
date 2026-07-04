package graph.undirected;

// Created at: 05-July-2026
// Last revised at: 05-July-2026
// Link: https://leetcode.com/problems/minimum-score-of-a-path-between-two-cities/

/*
Problem Description:
--------------------
Statement:
You are given a positive integer n representing n cities numbered from 1 to n.
You are given a 2D array roads where roads[i] = [ai, bi, distancei] indicates a
bidirectional road between cities ai and bi with distance distancei. The graph
is not necessarily connected.

The score of a path between two cities is the minimum distance of any road in
that path. A path is a sequence of roads between two cities — the same road can
be reused multiple times, and cities 1 and n can be revisited.

Return the minimum possible score of a path between city 1 and city n.

Example:
Input: n = 4, roads = [[1,2,9],[2,3,6],[2,4,5],[1,4,7]]
Output: 5
Explanation: Path 1 -> 2 -> 4 has score min(9,5) = 5.

Input: n = 4, roads = [[1,2,2],[1,3,4],[3,4,7]]
Output: 2
Explanation: Path 1 -> 2 -> 1 -> 3 -> 4 has score min(2,2,4,7) = 2.
Revisiting city 1 lets the edge (1,2) count toward the path.

Constraints:
2 <= n <= 1e5
1 <= roads.length <= 1e5
roads[i].length == 3
1 <= ai, bi <= n, ai != bi
1 <= distancei <= 1e4
No repeated edges. Guaranteed at least one path between 1 and n.
*/

/*
Approach 1: DFS (Recursive)
Idea:
Since roads can be reused and cities revisited, any edge in the connected
component containing city 1 can be folded into some valid path from 1 to n
(walk to the edge, use it, walk back). So the problem reduces to: find the
minimum edge weight among all edges reachable from city 1. DFS explores the
whole component, updating a running minimum for every edge encountered
(visited or not), and only recurses into a neighbor if it hasn't been visited
yet.

Time Complexity: O(V + E)
Space Complexity: O(V + E) — adjacency list + recursion stack

Drawbacks:
Recursive DFS risks a StackOverflowError on components with ~1e5 nodes in a
long chain, since Java's call stack isn't built for that depth.

Approach 2: BFS (Iterative)
Idea:
Same reduction as Approach 1 — find the min edge weight in the connected
component containing city 1. BFS avoids recursion depth issues by using an
explicit queue. Key correctness point: the score update must happen for
every edge encountered, regardless of whether the neighbor is visited.
The visited check should only gate whether a node gets pushed onto the
queue (to prevent reprocessing / infinite loop) — it must not gate whether
an edge's weight gets compared against the running minimum, or edges between
two already-visited nodes get silently skipped.

Time Complexity: O(V + E)
Space Complexity: O(V + E) — adjacency list + queue

Drawbacks:
None significant for this problem size; preferred over DFS here purely to
avoid recursion depth risk.
*/

import java.util.*;

public class LC2492MinimumScoreOfAPathBetweenTwoCities {

    private static class Edge {
        int node;
        int distance;

        Edge(int node, int distance) {
            this.node = node;
            this.distance = distance;
        }
    }

    /**
     * Builds an undirected weighted adjacency list from the roads array.
     *
     * @param n     number of cities
     * @param roads array of [cityA, cityB, distance] entries
     * @return adjacency list mapping each city to its edges
     */
    private Map<Integer, List<Edge>> buildAdjList(int n, int[][] roads) {
        Map<Integer, List<Edge>> adjList = new HashMap<>();

        for (int[] road : roads) {
            adjList.computeIfAbsent(road[0], k -> new ArrayList<>())
                    .add(new Edge(road[1], road[2]));
            adjList.computeIfAbsent(road[1], k -> new ArrayList<>())
                    .add(new Edge(road[0], road[2]));
        }

        return adjList;
    }

    /*
     * Method to Solve (DFS):
     * -----------------------
     * 1. Build adjacency list from roads.
     * 2. Start DFS from city 1, mark it visited.
     * 3. For every edge of the current node, update the running minimum
     * regardless of visited status.
     * 4. Recurse into the neighbor only if it hasn't been visited yet.
     * 5. Return the accumulated minimum after the whole component is explored.
     */

    /**
     * Finds the minimum score of a path between city 1 and city n using DFS.
     *
     * @param n     number of cities
     * @param roads array of [cityA, cityB, distance] entries
     * @return the minimum edge weight in the connected component of city 1
     */
    public int minScoreDFS(int n, int[][] roads) {
        Map<Integer, List<Edge>> adjList = buildAdjList(n, roads);
        boolean[] visited = new boolean[n + 1];
        int[] score = { Integer.MAX_VALUE };

        dfs(adjList, visited, 1, score);

        return score[0];
    }

    /**
     * Recursively explores the connected component, tracking the minimum
     * edge weight seen so far.
     *
     * @param adjList adjacency list of the graph
     * @param visited tracks which nodes have already been explored
     * @param node    current node being visited
     * @param score   single-element array acting as a mutable running minimum
     */
    private void dfs(Map<Integer, List<Edge>> adjList, boolean[] visited, int node, int[] score) {
        visited[node] = true;

        for (Edge edge : adjList.getOrDefault(node, Collections.emptyList())) {
            // update on every edge, visited or not
            score[0] = Math.min(score[0], edge.distance);

            if (!visited[edge.node]) {
                dfs(adjList, visited, edge.node, score);
            }
        }
    }

    /*
     * Method to Solve (BFS):
     * -----------------------
     * 1. Build adjacency list from roads.
     * 2. Push city 1 into the queue, mark it visited.
     * 3. Pop a node, iterate its edges:
     * - update running minimum for every edge (visited or not)
     * - push unvisited neighbors, marking them visited at push time to
     * avoid duplicate queue entries
     * 4. Repeat until queue is empty, return the running minimum.
     */

    /**
     * Finds the minimum score of a path between city 1 and city n using BFS.
     *
     * @param n     number of cities
     * @param roads array of [cityA, cityB, distance] entries
     * @return the minimum edge weight in the connected component of city 1
     */
    public int minScoreBFS(int n, int[][] roads) {
        Map<Integer, List<Edge>> adjList = buildAdjList(n, roads);

        Deque<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[n + 1];

        queue.offer(1);
        visited[1] = true;

        int score = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            int node = queue.poll();

            for (Edge edge : adjList.getOrDefault(node, Collections.emptyList())) {
                // update on every edge, visited or not
                score = Math.min(score, edge.distance);

                if (!visited[edge.node]) {
                    queue.offer(edge.node);
                    visited[edge.node] = true;
                }
            }
        }

        return score;
    }
}