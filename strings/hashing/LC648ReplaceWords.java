package strings.hashing;

// Created at: 19-November-2026
// Last revised at: 19-November-2026
// Link: https://leetcode.com/problems/replace-words/

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/*
Problem Description:
--------------------
Statement:
A root can be followed by other words to form a derivative word
(root "help" + "ful" = "helpful"). Given a dictionary of roots and a sentence,
replace every derivative word with the root that forms it. If a word can be
replaced by more than one root, use the shortest root.

Example:
dictionary = ["cat","bat","rat"], sentence = "the cattle was rattled by the battery"
Output: "the cat was rat by the bat"

dictionary = ["a","b","c"], sentence = "aadsfasf absbs bbab cadsfafs"
Output: "a a b c"

Constraints:
1 <= dictionary.length <= 1000
1 <= dictionary[i].length <= 100
dictionary[i] consists of only lower-case letters
1 <= sentence.length <= 10^6
sentence consists of only lower-case letters and spaces
words are separated by a single space, no leading or trailing spaces
*/

/*
Approach 1: Set of roots, check every prefix
Idea:
Put all roots in a HashSet. For each word, try prefixes of length 1, 2, 3...
and stop at the first one that is in the set (shortest root wins).

Time Complexity:
O(L^2) per word (substring copy + hash for every prefix), so O(n^2) when the
sentence is one huge word with no matching root.

Space Complexity:
O(D + n), D = total characters in the dictionary.

Drawbacks:
Keeps checking prefixes far longer than any root can be. Works, but the long
no-match word is the worst case (~700ms on LC).

Approach 2: Set of roots + cap on prefix length ★
Idea:
Same as above, but track the longest root length m and never check a prefix
longer than m. A longer prefix can't be a root.

Time Complexity:
O(m^2) per word (prefix lengths 1..m, each copied once), and a word needs at
least m characters to hit that, so at most n/m such words: O(n * m).
With m <= 100 and n <= 10^6 this is about 10^8 char operations at worst.

Space Complexity:
O(D + n)

Key Insight:
The dictionary bounds how far we ever need to look, so the cost depends on
the longest root and not on the word length (700ms -> 27ms).

Approach 3: Trie (not implemented)
Idea:
Walk the word one character at a time down a trie of the roots and stop at the
first end-of-root node. No substrings are built.

Time Complexity:
O(n) for the scan, O(D) to build.

Space Complexity:
O(D) for the trie nodes.
*/

/*
Method to Solve:
----------------
1. Add every root to a HashSet and track the longest root length.
2. Scan the sentence word by word (left = word start, right = next space).
3. For each word, try prefixes of length 1 up to min(longest root, word length).
4. First prefix found in the set is the shortest root, so use it.
5. If no prefix matches, keep the word as it is.
6. Append a space between words, none at the end.
*/
public class LC648ReplaceWords {

    /**
     * Replaces every word in the sentence with its shortest matching root.
     *
     * @param dictionary list of roots
     * @param sentence   space separated words
     * @return sentence with derivative words replaced by their roots
     */
    public String replaceWords(List<String> dictionary, String sentence) {
        Set<String> roots = buildRootSet(dictionary);
        int maxLen = longestRoot(dictionary);

        StringBuilder result = new StringBuilder();
        int n = sentence.length();
        int left = 0;

        while (left < n) {
            int right = findWordEnd(sentence, left);
            result.append(replaceWord(roots, sentence, left, right, maxLen));

            if (right < n)
                result.append(' ');
            left = right + 1;
        }

        return result.toString();
    }

    /**
     * Puts all roots in a hash set for O(1) average lookups.
     *
     * @param dictionary list of roots
     * @return set containing every root
     */
    private Set<String> buildRootSet(List<String> dictionary) {
        return new HashSet<>(dictionary);
    }

    /**
     * Finds the length of the longest root, used to cap prefix checks.
     *
     * @param dictionary list of roots
     * @return length of the longest root
     */
    private int longestRoot(List<String> dictionary) {
        int max = 0;
        for (String root : dictionary) {
            max = Math.max(max, root.length());
        }
        return max;
    }

    /**
     * Finds the end (exclusive) of the word starting at the given index.
     *
     * @param sentence input sentence
     * @param start    index where the word begins
     * @return index of the next space, or sentence length if it is the last word
     */
    private int findWordEnd(String sentence, int start) {
        int end = start;
        while (end < sentence.length() && sentence.charAt(end) != ' ')
            end++;
        return end;
    }

    /**
     * Returns the shortest root prefix of the word, or the word itself if none
     * matches.
     *
     * @param roots  set of roots
     * @param s      input sentence
     * @param left   start index of the word
     * @param right  end index (exclusive) of the word
     * @param maxLen longest root length
     * @return shortest matching root, otherwise the original word
     */
    private String replaceWord(Set<String> roots, String s, int left, int right, int maxLen) {
        // no root is longer than maxLen, so don't look further
        int limit = Math.min(maxLen, right - left);

        for (int len = 1; len <= limit; len++) {
            String prefix = s.substring(left, left + len);
            if (roots.contains(prefix))
                return prefix;
        }

        return s.substring(left, right);
    }
}

// Time Complexity: O(n * m)
// Space Complexity: O(D + n)