package graph.topologicalsort;

// Created at: 26-May-2026
// Last revised at: 26-May-2026
// Link: https://neetcode.io/problems/foreign-dictionary

import java.util.*;

/*
Problem Description:
--------------------
Statement:
You are given a list of strings `words` from a foreign language's dictionary,
where the words are sorted lexicographically according to that language's alphabet.
Derive the order of characters in that alphabet and return it as a string.
If the order is invalid (contradictory constraints), return "".
If multiple valid orderings exist, return any one.

Example:
Input:  words = ["wrt","wrf","er","ett","rftt"]
Output: "wertf"

Input:  words = ["z","x"]
Output: "zx"

Input:  words = ["z","x","z"]
Output: "" (z cannot come before and after x simultaneously)

Constraints:
- 1 <= words.length <= 100
- 1 <= words[i].length <= 100
- words[i] contains only lowercase English letters
*/

/*
Approach 1: BFS Topological Sort on Character Graph
----------------------------------------------------
Idea:
    Compare every adjacent word pair (words[w], words[w+1]).
    At the first mismatching character, we know c1 → c2 in the ordering.
    This builds a directed graph of character ordering constraints.
    Run BFS topological sort (Kahn's algorithm) over this graph.
    If all characters are processed, return the ordering.
    If a cycle exists, return "".

    Key correctness decisions:
    - Mark all characters present upfront (not during comparison) to avoid
      missing characters that only appear after the differing position.
    - Use a Set for adjacency to prevent the same edge c1→c2 from inflating
      indegree[c2] when multiple word pairs produce the same constraint.
    - Return "" immediately if words[w+1] is a prefix of words[w] (invalid input).

Time Complexity:
    O(N * L) — N pairs of words, each comparison up to L characters.
    Topo sort runs in O(V + E) where V ≤ 26 and E ≤ 26*25, effectively O(1).

Space Complexity:
    O(1) — present[], indegree[] are fixed size 26.
    O(E) for adjacency list where E ≤ 650 (26 * 25 max directed edges).
*/

/*
Method to Solve:
----------------
1. Mark all characters across all words as present.
2. For each adjacent word pair, find the first mismatching character.
   - Add directed edge c1 → c2 (using Set to avoid duplicates).
   - If words[w+1] is exhausted before a mismatch but words[w] is not → return "".
3. Run BFS topological sort (Kahn's algorithm):
   - Seed the queue with all present characters that have indegree 0.
   - Process each character, decrement neighbors' indegrees.
   - Enqueue neighbors whose indegree drops to 0.
4. If all present characters were processed, return the order. Otherwise return "".
*/

// Time Complexity: O(N * L)
// Space Complexity: O(E) where E ≤ 650

class LC269ForeignDictionary {

    /**
     * Runs BFS topological sort over the character constraint graph.
     *
     * @param adjList  directed graph mapping character index → set of dependent
     *                 character indices
     * @param present  boolean flags for characters that appear in any word
     * @param indegree incoming edge count per character index
     * @return valid character ordering, or "" if a cycle is detected
     */
    private String topoSort(Map<Integer, Set<Integer>> adjList, boolean[] present, int[] indegree) {
        Deque<Integer> q = new LinkedList<>();

        int toProcess = 0;
        for (int i = 0; i < 26; i++) {
            if (present[i]) {
                if (indegree[i] == 0) {
                    q.offer(i);
                }
                toProcess++;
            }
        }

        StringBuilder order = new StringBuilder();
        while (!q.isEmpty()) {
            int num = q.poll();
            order.append((char) (num + 'a'));

            for (int nbr : adjList.getOrDefault(num, Collections.emptySet())) {
                indegree[nbr]--;
                if (indegree[nbr] == 0) {
                    q.offer(nbr);
                }
            }
        }

        // cycle detected if not all characters were processed
        return order.length() == toProcess ? order.toString() : new String();
    }

    /**
     * Derives the character ordering of a foreign alphabet from a sorted word list.
     *
     * @param words array of strings sorted in foreign lexicographic order
     * @return any valid character ordering, or "" if constraints are contradictory
     *         or invalid
     */
    public String foreignDictionary(String[] words) {
        int len = words.length;

        boolean[] present = new boolean[26];
        int[] indegree = new int[26];

        // mark every character present before any comparison
        for (String word : words) {
            for (char ch : word.toCharArray()) {
                present[ch - 'a'] = true;
            }
        }

        Map<Integer, Set<Integer>> adjList = new HashMap<>();

        for (int w = 0; w < len - 1; w++) {
            char[] w1 = words[w].toCharArray();
            char[] w2 = words[w + 1].toCharArray();

            int i = 0, j = 0;
            while (i < w1.length && j < w2.length) {
                char c1 = w1[i];
                char c2 = w2[j];
                if (c1 != c2) {
                    Set<Integer> neighbors = adjList.computeIfAbsent(c1 - 'a', k -> new HashSet<>());
                    if (neighbors.add(c2 - 'a')) { // skip if edge already exists
                        indegree[c2 - 'a']++;
                    }
                    break;
                }
                i++;
                j++;
            }

            // w2 is a strict prefix of w1 — impossible ordering
            if (i < w1.length && j == w2.length) {
                return "";
            }
        }

        return topoSort(adjList, present, indegree);
    }
}
