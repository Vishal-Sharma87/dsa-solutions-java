package graph.bfs;

// Created at: 22-May-2026
// Last revised at: 22-May-2026
// Link: https://leetcode.com/problems/word-ladder/

import java.util.*;

/*
Problem Description:
--------------------
Statement:
    A transformation sequence from beginWord to endWord using a dictionary wordList is a
    sequence beginWord → s1 → s2 → ... → sk such that:
      - Every adjacent pair differs by exactly one letter.
      - Every si (except beginWord) is in wordList.
    Given beginWord, endWord, and wordList, return the number of words in the shortest
    transformation sequence, or 0 if no such sequence exists.

Example:
    Input:  beginWord = "hit", endWord = "cog",
            wordList = ["hot","dot","dog","lot","log","cog"]
    Output: 5
    Explanation: "hit" → "hot" → "dot" → "dog" → "cog"

Constraints:
    - 1 <= beginWord.length <= 10
    - endWord.length == beginWord.length
    - 1 <= wordList.length <= 5000
    - wordList[i].length == beginWord.length
    - All strings consist of lowercase English letters
    - beginWord != endWord
    - All words in wordList are unique
*/

/*
Approach 1: Brute Force BFS with visited set rebuilt each time
Idea:
    Try all possible one-letter mutations from each word; check membership in wordList.
    Track visited to avoid revisiting. No real optimisation over standard BFS here —
    just noting this as the baseline before the set-mutation trick.

Time Complexity: O(m^2 * n) — m = word length, n = wordList size; string construction + set lookup
Space Complexity: O(n) — queue and set

Drawbacks:
    Building a full graph of neighbours upfront is O(n^2 * m) and wasteful when
    the alphabet-mutation trick already gives O(m * 26) per word.

Approach 2: BFS with alphabet mutation + hash set (Optimal)
Idea:
    Instead of pre-building an adjacency graph, mutate each character of the current
    word through all 26 letters and check if the result is in the dictionary set.
    Process BFS level by level — the level count at which endWord is first dequeued
    is the shortest path length.
    Remove words from the set as they are discovered to prevent revisits.

Time Complexity: O(m * 26 * n) → O(m * n)
Space Complexity: O(n) — queue + set

Key Insight:
    Removing from the set on first discovery is safe here (unlike Word Ladder II)
    because we only need the shortest length, not all shortest paths.
    Every word need only be enqueued once.
*/

/*
Method to Solve:
----------------
1. Load wordList into a HashSet; return 0 immediately if endWord not present.
2. Remove beginWord from set (avoids cycling back to start).
3. Offer beginWord to queue. Set level counter to 0.
4. BFS level by level:
   a. Increment level at the start of each level.
   b. Dequeue current word. If it equals endWord, return level.
   c. Mutate each character through 'a'–'z'. If mutated word is in set,
      enqueue it and remove from set.
5. If queue empties without finding endWord, return 0.
*/

// Time Complexity: O(m * n) where m = word length, n = wordList size
// Space Complexity: O(n)

public class LC127WordLadder {

    /**
     * Returns the length of the shortest transformation sequence from beginWord to
     * endWord.
     * Each step must change exactly one character and the result must be in the
     * wordList.
     *
     * @param beginWord starting word
     * @param endWord   target word
     * @param wordList  valid intermediate words
     * @return length of shortest sequence, or 0 if unreachable
     */
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        int len = beginWord.length();

        Set<String> set = new HashSet<>(wordList);
        if (!set.contains(endWord))
            return 0;

        set.remove(beginWord);

        Deque<String> q = new LinkedList<>();
        q.offer(beginWord);

        int level = 0;

        while (!q.isEmpty()) {
            level++;
            int size = q.size();

            while (size-- > 0) {
                String curr = q.poll();

                if (curr.equals(endWord))
                    return level;

                char[] word = curr.toCharArray();

                for (int i = 0; i < len; i++) {
                    char original = word[i];

                    for (char ch = 'a'; ch <= 'z'; ch++) {
                        if (ch == original)
                            continue;

                        word[i] = ch;
                        String next = new String(word);

                        if (set.contains(next)) {
                            q.offer(next);
                            set.remove(next); // mark visited immediately — safe for single shortest path
                        }
                    }

                    word[i] = original; // restore before next position
                }
            }
        }
        return 0;
    }
}
