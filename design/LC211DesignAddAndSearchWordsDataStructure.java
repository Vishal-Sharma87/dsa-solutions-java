package design;

// Created at: 20-November-2026
// Last revised at: 20-November-2026
// Link: https://leetcode.com/problems/design-add-and-search-words-data-structure/

import java.util.*;

/*
Problem Description:
--------------------
Design a data structure that supports adding words and searching words.

Search supports the wildcard character '.' which can match any
single lowercase English letter.

Operations:
1. addWord(word): Adds a word to the dictionary.
2. search(word): Returns true if a matching word exists.

Example:
--------
addWord("bad")
addWord("dad")
addWord("mad")

search("pad")  -> false
search("bad")  -> true
search(".ad")  -> true
search("b..")  -> true

Constraints:
------------
1 <= word.length <= 25
Word consists of lowercase English letters.
Search pattern consists of lowercase English letters and '.'.
*/

/*
Approach 1: Segment-Based Backtracking

Idea:
-----
1. Maintain all added words in a HashSet.
2. Track minimum and maximum word lengths to reject
   impossible searches immediately.
3. Partition the search pattern into literal segments
   and individual wildcard characters.
4. Process an entire literal segment in one recursive call.
5. For each wildcard, explore all 26 possible characters.
6. At the end, check whether the generated word exists
   in the dictionary.

Key Insight:
------------
Consecutive literal characters are deterministic.
There is no need to create a separate recursive call
for every character.

Processing literal segments together reduces recursion
depth and avoids unnecessary stack frames.

Time Complexity:
----------------
O(26^W * L)

W = number of wildcards.
L = pattern length.

Space Complexity:
-----------------
O(L) auxiliary space for partitioning, StringBuilder
and recursion.

Drawbacks:
---------
Still explores exponential wildcard combinations.
Does not prune branches using dictionary prefixes.
*/

/*
Approach 2: Character-Based Backtracking

Idea:
-----
1. Maintain the same HashSet dictionary.
2. Process the search pattern one character at a time.
3. For a literal character, append it and recurse.
4. For '.', explore all 26 lowercase letters.
5. At the end, check whether the generated word
   exists in the dictionary.

Key Insight:
------------
This approach eliminates preprocessing and directly
performs character-level backtracking.

However, every literal character creates another
recursive call, even when no branching is involved.

Time Complexity:
----------------
O(26^W * L)

W = number of wildcards.
L = pattern length.

Space Complexity:
-----------------
O(L) auxiliary space for StringBuilder and recursion.

Drawbacks:
---------
1. Creates more recursive stack frames.
2. Processes deterministic characters individually.
3. Has greater recursive overhead compared to
   segment-based processing.

Performance:
------------
Can be significantly slower on long patterns with
multiple wildcards.
*/

/*
Method to Solve:
----------------
1. Store words in a HashSet.
2. Maintain minimum and maximum dictionary word lengths.
3. Reject searches whose length cannot match any stored word.
4. Partition the pattern into literal segments and wildcards.
5. Process literal segments together.
6. Explore 26 possibilities only at wildcard positions.
7. Check the generated word against the dictionary.
*/

public class LC211DesignAddAndSearchWordsDataStructure {

    private final Set<String> dictionary;
    private int maxLen;
    private int minLen;

    /**
     * Initializes the dictionary and length boundaries.
     */
    public LC211DesignAddAndSearchWordsDataStructure() {
        dictionary = new HashSet<>();
        maxLen = Integer.MIN_VALUE;
        minLen = Integer.MAX_VALUE;
    }

    /**
     * Adds a word to the dictionary.
     *
     * @param word word to insert
     * @return void
     */
    public void addWord(String word) {
        dictionary.add(word);
        maxLen = Math.max(maxLen, word.length());
        minLen = Math.min(minLen, word.length());
    }

    /**
     * Searches using segment-based backtracking.
     *
     * @param word search pattern
     * @return true if a matching word exists
     */
    public boolean search(String word) {
        if (dictionary.isEmpty()
                || word.length() > maxLen
                || word.length() < minLen) {
            return false;
        }

        List<String> parts = partitionWord(word);

        return checkSegmentBased(parts, 0, new StringBuilder());
    }

    /**
     * Processes literal segments together and branches
     * only at wildcard positions.
     *
     * @param parts    partitioned search pattern
     * @param index    current segment index
     * @param modified currently generated word
     * @return true if a matching word exists
     */
    private boolean checkSegmentBased(
            List<String> parts,
            int index,
            StringBuilder modified) {

        if (index >= parts.size()) {
            return dictionary.contains(modified.toString());
        }

        if (parts.get(index).equals(".")) {
            for (char ch = 'a'; ch <= 'z'; ch++) {
                modified.append(ch);

                if (checkSegmentBased(parts, index + 1, modified)) {
                    return true;
                }

                modified.deleteCharAt(modified.length() - 1);
            }
        } else {
            String partToAdd = parts.get(index);
            modified.append(partToAdd);

            if (checkSegmentBased(parts, index + 1, modified)) {
                return true;
            }

            int start = modified.length() - partToAdd.length();
            modified.delete(start, modified.length());

            return false;
        }

        return false;
    }

    /**
     * Processes each pattern character individually.
     *
     * @param word     original search pattern
     * @param index    current character index
     * @param modified currently generated word
     * @return true if a matching word exists
     */
    private boolean checkCharacterBased(
            String word,
            int index,
            StringBuilder modified) {

        if (index >= word.length()) {
            return dictionary.contains(modified.toString());
        }

        if (word.charAt(index) != '.') {
            modified.append(word.charAt(index));

            if (checkCharacterBased(word, index + 1, modified)) {
                return true;
            }

            modified.deleteCharAt(modified.length() - 1);
            return false;
        }

        for (char ch = 'a'; ch <= 'z'; ch++) {
            modified.append(ch);

            if (checkCharacterBased(word, index + 1, modified)) {
                return true;
            }

            modified.deleteCharAt(modified.length() - 1);
        }

        return false;
    }

    /**
     * Exposes character-based backtracking for comparison.
     *
     * @param word search pattern
     * @return true if a matching word exists
     */
    public boolean searchCharacterBased(String word) {
        if (dictionary.isEmpty()
                || word.length() > maxLen
                || word.length() < minLen) {
            return false;
        }

        return checkCharacterBased(word, 0, new StringBuilder());
    }

    /**
     * Splits a pattern into literal segments and wildcards.
     *
     * @param word original search pattern
     * @return partitioned pattern
     */
    private List<String> partitionWord(String word) {
        List<String> result = new ArrayList<>();
        StringBuilder last = new StringBuilder();

        for (char ch : word.toCharArray()) {
            if (ch == '.') {
                if (last.isEmpty()) {
                    result.add(".");
                } else {
                    result.add(last.toString());
                    result.add(".");
                    last = new StringBuilder();
                }
            } else {
                last.append(ch);
            }
        }

        if (!last.isEmpty()) {
            result.add(last.toString());
        }

        return result;
    }

    // Time Complexity: O(26^W * L) for both search approaches
    // Space Complexity: O(L) auxiliary space
}