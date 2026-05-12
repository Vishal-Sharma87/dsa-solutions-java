package tree.hard;

// Created at: 13-May-2026
// Last revised at: 13-May-2026
// Link: (GFG - problem reference)

/*
Problem:
Small helper to validate uniqueness requirements for a particular GFG problem.

Notes:
This utility replicates the provided logic: it's possible when values differ
and one of them equals 2.
*/

public class GFG_UniqueBinaryTreeRequirements {

    public boolean isPossible(int a, int b) {
        // Code here
        return (a != b) && (a == 2 || b == 2);
    }

}
