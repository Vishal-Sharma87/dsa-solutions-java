package hashing.lookups;

// Created at: 26-September-2026
// Last revised at: 26-September-2026
// Link: https://leetcode.com/problems/evaluate-the-bracket-pairs-of-a-string/

/*
Problem Description:
--------------------
Statement:
Given a string s containing lowercase English letters and bracket pairs,
replace every "(key)" with its corresponding value from the knowledge list.
If a key is not present in knowledge, replace it with "?".

Example:
s = "Hi(name)"
knowledge = [["name", "bob"]]

Output:
"Hi bob"

Constraints:
- s contains lowercase English letters and parentheses.
- Each bracket pair contains a key.
- knowledge contains unique keys.
*/

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LC1807EvaluateTheBracketPairsOfAString {

    /*
     * Approach 1: Brute Force
     * 
     * Idea:
     * For every bracketed key, search the knowledge list linearly
     * to find its corresponding value.
     * 
     * Time Complexity:
     * O(n * k), where n is the string length and k is the number of
     * knowledge entries.
     * 
     * Space Complexity:
     * O(1), excluding the output.
     * 
     * Drawbacks:
     * Repeatedly scanning knowledge makes lookups expensive.
     */

    /*
     * Approach 2: HashMap Lookup
     * 
     * Idea:
     * Store every key-value pair in a HashMap.
     * Scan the string and extract each key between '(' and ')'.
     * Use HashMap lookup to replace the key with its value.
     * 
     * Time Complexity:
     * O(n + K), where n is the string length and K is the total size
     * of the knowledge entries.
     * 
     * Space Complexity:
     * O(K) for the HashMap.
     * 
     * Key Insight:
     * Convert repeated key searches into O(1) average-time lookups
     * using hashing.
     */

    /*
     * Method to Solve:
     * ----------------
     * 1. Store all knowledge key-value pairs in a HashMap.
     * 2. Scan the string from left to right.
     * 3. When '(' is found, locate the matching ')'.
     * 4. Extract the key between the brackets.
     * 5. Replace it with the mapped value or "?" if absent.
     * 6. Append normal characters directly to the result.
     */

    /**
     * Evaluates all bracket pairs using the given key-value knowledge.
     *
     * @param s         the input string containing bracket pairs
     * @param knowledge list of key-value pairs
     * @return the evaluated string
     */
    public String evaluate(String s, List<List<String>> knowledge) {
        Map<String, String> pair = new HashMap<>();

        for (List<String> entry : knowledge) {
            String key = entry.get(0);
            String value = entry.get(1);
            pair.put(key, value);
        }

        StringBuilder result = new StringBuilder();
        int i = 0;

        while (i < s.length()) {
            if (s.charAt(i) == '(') {
                int j = i + 1;

                while (s.charAt(j) != ')') {
                    j++;
                }

                String key = s.substring(i + 1, j);
                result.append(pair.getOrDefault(key, "?"));
                i = j + 1;
            } else {
                result.append(s.charAt(i));
                i++;
            }
        }

        return result.toString();
    }
}

// Time Complexity: O(n + K)
// Space Complexity: O(K)