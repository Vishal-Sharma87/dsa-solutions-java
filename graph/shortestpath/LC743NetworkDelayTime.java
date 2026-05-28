package graph.shortestpath;

// Created at: 29-May-2026
// Last revised at: 29-May-2026
// Link: https://leetcode.com/problems/network-delay-time/

/*
Problem Description:
--------------------
Statement:
You are given a network of n nodes labeled from 1 to n.

Each directed edge times[i] = [u, v, w] represents:
- source node u
- destination node v
- travel time w

A signal starts from node k.

Return the minimum time required for all nodes to receive the signal.
If any node cannot be reached, return -1.

Example:
Input:
times = [[2,1,1],[2,3,1],[3,4,1]]
n = 4
k = 2

Output:
2

Explanation:
- Node 1 receives signal in 1 unit.
- Node 3 receives signal in 1 unit.
- Node 4 receives signal in 2 units.

Constraints:
- 1 <= n <= 100
- 1 <= times.length <= 6000
- times[i].length == 3
*/

/*
Approach 1: Dijkstra's Algorithm using Min Heap
Idea:
- Build adjacency list for directed graph.
- Use min heap to always process node with minimum current travel time.
- Relax edges whenever a shorter path is found.
- Store shortest time needed to reach every node.

Time Complexity:
O((V + E) log V)

Space Complexity:
O(V + E)

Key Insight:
Dijkstra works because all edge weights are positive.
Using min heap ensures shortest available path gets processed first.
*/

/*
Method to Solve:
----------------
1. Build adjacency list from input edges.
2. Initialize minimum time array with infinity.
3. Start Dijkstra traversal from source node k.
4. Process nodes from min heap:
   - skip outdated states
   - relax neighboring edges
5. Find maximum shortest-path value.
6. If any node remains unreachable, return -1.
*/

import java.util.*;

public class LC743NetworkDelayTime {

    /**
     * Stores current node and time used.
     */
    private static class Info {
        int node;
        int timeUsed;

        public Info(int node, int timeUsed) {
            this.node = node;
            this.timeUsed = timeUsed;
        }
    }

    /**
     * Stores adjacent node and edge weight.
     */
    private static class EdgeInfo {
        int node;
        int time;

        public EdgeInfo(int node, int time) {
            this.node = node;
            this.time = time;
        }
    }

    /**
     * Finds minimum time required for all nodes
     * to receive the signal.
     *
     * @param times directed edges with weights
     * @param n     total nodes
     * @param k     starting node
     * @return network delay time
     */
    public int networkDelayTime(int[][] times, int n, int k) {

        Map<Integer, List<EdgeInfo>> adjList = buildGraph(times);

        int[] timeNeeded = initializeTimeArray(n, k);

        PriorityQueue<Info> minHeap = new PriorityQueue<>((a, b) -> a.timeUsed - b.timeUsed);

        minHeap.offer(new Info(k, 0));

        List<EdgeInfo> emptyList = new ArrayList<>();

        while (!minHeap.isEmpty()) {

            Info info = minHeap.poll();

            // skip outdated state
            if (info.timeUsed > timeNeeded[info.node]) {
                continue;
            }

            for (EdgeInfo edge : adjList.getOrDefault(info.node, emptyList)) {

                int newTime = info.timeUsed + edge.time;

                // relax edge
                if (timeNeeded[edge.node] > newTime) {

                    timeNeeded[edge.node] = newTime;

                    minHeap.offer(
                            new Info(edge.node, newTime));
                }
            }
        }

        return findMaximumDelay(timeNeeded);
    }

    /**
     * Builds adjacency list.
     *
     * @param times graph edges
     * @return adjacency list
     */
    private Map<Integer, List<EdgeInfo>> buildGraph(int[][] times) {

        Map<Integer, List<EdgeInfo>> adjList = new LinkedHashMap<>();

        for (int[] edge : times) {

            int u = edge[0];
            int v = edge[1];
            int t = edge[2];

            // u → v
            adjList
                    .computeIfAbsent(u, list -> new ArrayList<>())
                    .add(new EdgeInfo(v, t));
        }

        return adjList;
    }

    /**
     * Initializes shortest time array.
     *
     * @param n      total nodes
     * @param source starting node
     * @return initialized array
     */
    private int[] initializeTimeArray(int n, int source) {

        int[] timeNeeded = new int[n + 1];

        Arrays.fill(timeNeeded, Integer.MAX_VALUE);

        timeNeeded[source] = 0;

        return timeNeeded;
    }

    /**
     * Finds final network delay time.
     *
     * @param timeNeeded shortest path array
     * @return maximum shortest path
     */
    private int findMaximumDelay(int[] timeNeeded) {

        int minDelayTime = -1;

        for (int i = 1; i < timeNeeded.length; i++) {

            int time = timeNeeded[i];

            // unreachable node
            if (time == Integer.MAX_VALUE) {
                return -1;
            }

            minDelayTime = Math.max(minDelayTime, time);
        }

        return minDelayTime;
    }
}

// Time Complexity: O((V + E) log V)
// Space Complexity: O(V + E)