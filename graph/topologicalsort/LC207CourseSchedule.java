package graph.topologicalsort;

// Created at: 24-May-2026
// Last revised at: 24-May-2026
// Link: https://leetcode.com/problems/course-schedule/

import java.util.*;

/*
Problem Description:
--------------------
Statement:
There are numCourses courses labeled 0 to numCourses - 1.
You are given prerequisites[i] = [a, b] meaning course b must be
finished before course a. Return true if it is possible to finish
all courses, false otherwise.

Example:
Input: numCourses = 2, prerequisites = [[1,0]]
Output: true
Explanation: Take course 0 first, then course 1.

Input: numCourses = 2, prerequisites = [[1,0],[0,1]]
Output: false
Explanation: Cycle — can't finish either.

Constraints:
- 1 <= numCourses <= 2000
- 0 <= prerequisites.length <= 5000
- prerequisites[i].length == 2
- 0 <= a, b < numCourses
- All pairs [a, b] are unique
*/

/*
Approach 1: DFS + Cycle Detection
Idea:
Run DFS on the graph. Track visiting (gray) and visited (black)
states per node. If DFS reaches a gray node again, a cycle exists.

Time Complexity: O(V + E)
Space Complexity: O(V + E)

Drawbacks:
Requires managing three states (unvisited, visiting, visited) and
recursive DFS which can hit stack overflow on deep graphs.

Approach 2: Kahn's Algorithm — BFS Topological Sort (Optimal)
Idea:
Build an adjacency list and in-degree array. Repeatedly process
nodes with in-degree 0 (no unmet prerequisites). Each time a node
is processed, decrement the in-degree of its neighbors. If all
numCourses nodes are eventually processed, the graph is a DAG —
no cycle exists, and all courses can be finished.

Key Insight:
A cycle means some nodes' in-degrees never reach 0 — they stay
mutually blocked. Comparing processed count to numCourses catches
this without any explicit cycle marking.

Time Complexity: O(V + E) — each node and edge visited once
Space Complexity: O(V + E) — adjacency list + in-degree array + queue
*/

/*
Method to Solve:
----------------
1. Build adjacency list: edge[1] → edge[0] (prerequisite → course).
2. Build in-degree array: increment freq[edge[0]] for every edge.
3. Enqueue all nodes with in-degree 0 (no prerequisites).
4. BFS: for each processed node, decrement neighbors' in-degrees.
   If a neighbor's in-degree hits 0, enqueue it.
5. Return processed == numCourses.
*/

// Time Complexity: O(V + E)
// Space Complexity: O(V + E)

class LC207CourseSchedule {

    /**
     * Determines if all courses can be finished given their prerequisites.
     * Uses Kahn's BFS-based topological sort to detect cycles.
     *
     * @param numCourses    total number of courses (nodes)
     * @param prerequisites edges where [a, b] means b must precede a
     * @return true if no cycle exists and all courses can be completed
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> adjList = new HashMap<>();
        int[] freq = new int[numCourses];

        for (int[] edge : prerequisites) {
            int node = edge[1];
            int nbr = edge[0];

            List<Integer> nbrs = adjList.getOrDefault(node, new ArrayList<>());
            nbrs.add(nbr);
            adjList.put(node, nbrs);

            freq[nbr]++; // nbr has one more unmet prerequisite
        }

        Deque<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (freq[i] == 0)
                q.offer(i); // no prerequisites, ready to take
        }

        int processed = 0;
        while (!q.isEmpty()) {
            processed++;
            int node = q.poll();

            for (int nbr : adjList.getOrDefault(node, Collections.emptyList())) {
                if (--freq[nbr] == 0)
                    q.offer(nbr); // all prerequisites met
            }
        }

        // cycle exists if some nodes stayed blocked (in-degree never reached 0)
        return processed == numCourses;
    }
}