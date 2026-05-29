package graph.topologicalsort;

// Created at: 24-May-2026
// Last revised at: 24-May-2026
// Link: https://leetcode.com/problems/course-schedule-ii/

import java.util.*;

/*
Problem Description:
--------------------
Statement:
There are numCourses courses labeled 0 to numCourses - 1.
Given prerequisites[i] = [a, b] meaning course b must be finished
before course a, return any valid ordering of courses to finish all
of them. If it's impossible (cycle exists), return an empty array.

Example:
Input: numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
Output: [0,1,2,3] or [0,2,1,3]
Explanation: Multiple valid topological orderings exist.

Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: []
Explanation: Cycle detected — impossible to finish.

Constraints:
- 1 <= numCourses <= 2000
- 0 <= prerequisites.length <= 5000
- prerequisites[i].length == 2
- All pairs [a, b] are unique
*/

/*
Approach 1: DFS-based Topological Sort + Cycle Detection
Idea:
Use DFS with 3 states:
0 -> unvisited
1 -> visiting
2 -> visited

If a DFS reaches a node already in the visiting state,
a cycle exists and no valid ordering is possible.

Push nodes into a stack after all descendants are processed.
The stack order forms the topological ordering.

Time Complexity: O(V + E)
Space Complexity: O(V + E)

Drawbacks:
Uses recursion, which may cause stack overflow for very deep graphs.

Approach 2: Kahn's Algorithm — BFS Topological Sort (Optimal)
Idea:
Extend LC207's cycle detection: instead of just counting processed
nodes, record them in order into topoOrder[]. The BFS processing
sequence is itself a valid topological ordering — every node is
dequeued only after all its prerequisites have been processed.

Key Insight:
BFS naturally produces a valid topological order because a node
enters the queue only when its in-degree hits 0, meaning all its
prerequisites are already in topoOrder before it.

Time Complexity: O(V + E)
Space Complexity: O(V + E)
*/

/*
Method to Solve:
----------------

DFS Topological Sort:
1. Build adjacency list.
2. Run DFS from every unvisited node.
3. Detect cycles using 3-state visitation.
4. Push nodes into stack after processing descendants.
5. Pop stack to obtain topological order.
6. Return empty array if a cycle exists.

Kahn's Algorithm:
1. Build adjacency list and in-degree array.
2. Enqueue all nodes with in-degree 0.
3. BFS through the graph.
4. Append processed nodes to topoOrder.
5. Reduce neighbors' in-degrees.
6. If all nodes are processed, return topoOrder.
7. Otherwise return int[0].
*/

// Time Complexity: O(V + E)
// Space Complexity: O(V + E)

class LC210CourseScheduleII {

    /**
     * Returns a valid course ordering that satisfies all prerequisites.
     * Uses Kahn's BFS topological sort; returns empty array if a cycle exists.
     *
     * @param numCourses    total number of courses (nodes)
     * @param prerequisites edges where [a, b] means b must precede a
     * @return valid topological order, or int[0] if impossible
     */
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        int[] indegree = new int[numCourses];

        for (int[] edge : prerequisites) {
            int node = edge[1];
            int nbr = edge[0];

            List<Integer> nbrs = adjList.getOrDefault(node, new ArrayList<>());
            nbrs.add(nbr);
            adjList.put(node, nbrs);

            indegree[nbr]++;
        }

        Deque<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0)
                q.offer(i);
        }

        int[] topoOrder = new int[numCourses];
        int processed = 0;

        while (!q.isEmpty()) {
            int node = q.poll();
            topoOrder[processed++] = node; // BFS order = valid topo order

            for (int nbr : adjList.getOrDefault(node, Collections.emptyList())) {
                if (--indegree[nbr] == 0)
                    q.offer(nbr);
            }
        }

        // cycle means some nodes never unblocked
        return processed == numCourses ? topoOrder : new int[0];
    }

    /**
     * Returns a valid course ordering using DFS topological sort.
     *
     * @param numCourses    total number of courses
     * @param prerequisites prerequisite relationships
     * @return valid topological ordering or empty array if cycle exists
     */
    public int[] findOrderDFS(int numCourses, int[][] prerequisites) {

        Map<Integer, List<Integer>> adjList = new HashMap<>();

        for (int[] edge : prerequisites) {
            adjList.computeIfAbsent(edge[1], k -> new ArrayList<>())
                    .add(edge[0]);
        }

        int[] state = new int[numCourses];
        Deque<Integer> stack = new LinkedList<>();

        for (int course = 0; course < numCourses; course++) {

            if (state[course] == 0) {

                if (dfs(course, adjList, state, stack)) {
                    return new int[0];
                }
            }
        }

        int[] topoOrder = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            topoOrder[i] = stack.pop();
        }

        return topoOrder;
    }

    /**
     * DFS traversal for topological sort with cycle detection.
     *
     * @param node    current node
     * @param adjList graph adjacency list
     * @param state   visitation state array
     * @param stack   stores topological ordering
     * @return true if cycle detected, otherwise false
     */
    private boolean dfs(
            int node,
            Map<Integer, List<Integer>> adjList,
            int[] state,
            Deque<Integer> stack) {

        state[node] = 1;

        for (int nbr : adjList.getOrDefault(node, Collections.emptyList())) {

            // back edge found
            if (state[nbr] == 1) {
                return true;
            }

            // visit unprocessed node
            if (state[nbr] == 0 && dfs(nbr, adjList, state, stack)) {
                return true;
            }
        }

        state[node] = 2;
        stack.push(node);

        return false;
    }
}
