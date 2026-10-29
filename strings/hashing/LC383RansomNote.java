package strings.hashing;

// Created at: 27-October-2026
// Last revised at: 27-October-2026
// Link: https://leetcode.com/problems/ransom-note/

public class LC383RansomNote {

    /*
     * Problem Description:
     * --------------------
     * Statement:
     * Given two strings, ransomNote and magazine, return true if ransomNote
     * can be constructed using characters from magazine.
     * 
     * Each character in magazine can be used at most once.
     * 
     * Example 1:
     * Input:
     * ransomNote = "a"
     * magazine = "b"
     * 
     * Output:
     * false
     * 
     * Example 2:
     * Input:
     * ransomNote = "aa"
     * magazine = "ab"
     * 
     * Output:
     * false
     * 
     * Example 3:
     * Input:
     * ransomNote = "aa"
     * magazine = "aab"
     * 
     * Output:
     * true
     * 
     * Constraints:
     * - 1 <= ransomNote.length(), magazine.length() <= 10^5
     * - Both strings contain only lowercase English letters.
     */

    /*
     * Approach 1: Brute Force
     * 
     * Idea:
     * For each character in ransomNote, search for an unused matching
     * character in magazine. Mark matched characters as used.
     * 
     * Time Complexity:
     * O(n * m)
     * 
     * Space Complexity:
     * O(m), if a separate structure tracks used magazine characters.
     * 
     * Drawbacks:
     * Repeatedly searching the magazine is inefficient.
     */

    /*
     * Approach 2: Frequency Counter
     * 
     * Idea:
     * 1. If ransomNote is longer than magazine, return false.
     * 2. Create an integer array of size 26.
     * 3. Count the frequency of each character in magazine.
     * 4. Traverse ransomNote and check character availability.
     * 5. Return false if a required character is unavailable.
     * 6. Otherwise, decrement its frequency.
     * 7. Return true if every character is successfully matched.
     * 
     * Time Complexity:
     * O(m + n)
     * 
     * Space Complexity:
     * O(1)
     * 
     * Key Insight:
     * Since only lowercase English letters are allowed, a fixed-size
     * frequency array is sufficient.
     * 
     * Drawbacks:
     * This implementation relies on the input containing only lowercase
     * English letters.
     */

    /*
     * Method to Solve:
     * ----------------
     * 1. Compare the lengths of ransomNote and magazine.
     * 2. Initialize a frequency array of size 26.
     * 3. Count the characters available in magazine.
     * 4. Check each character required by ransomNote.
     * 5. Return false if its frequency is zero.
     * 6. Decrement the frequency after consuming a character.
     * 7. Return true when all required characters are available.
     */

    /**
     * Checks whether ransomNote can be constructed using magazine.
     *
     * @param ransomNote string that must be constructed
     * @param magazine   string containing available characters
     * @return true if construction is possible, otherwise false
     */
    public boolean canConstruct(String ransomNote, String magazine) {

        if (ransomNote.length() > magazine.length())
            return false;

        int[] freq = new int[26];

        for (int i = 0; i < magazine.length(); i++)
            freq[magazine.charAt(i) - 'a']++;

        for (int i = 0; i < ransomNote.length(); i++) {
            int index = ransomNote.charAt(i) - 'a';

            if (freq[index] == 0)
                return false;

            freq[index]--;
        }

        return true;
    }
}

// Time Complexity: O(m + n)
// Space Complexity: O(1)