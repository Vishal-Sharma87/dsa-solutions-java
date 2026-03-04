package binarysearch.onAnswers;

/*
 * PROBLEM: Book Allocation (GFG / Leetcode variant)
 * =====================================================
 * Given an array `books[]` where books[i] = number of pages in the i-th book,
 * and `k` students, allocate books to students such that:
 *   1. Each student gets at least one book.
 *   2. Each book is assigned to exactly one student.
 *   3. Books are assigned contiguously (a student gets a continuous subarray).
 *   4. The MAXIMUM pages assigned to any single student is MINIMIZED.
 *
 * APPROACH: Binary Search on Answer
 * =====================================================
 * Instead of trying every possible allocation, we binary search on the ANSWER
 * itself — i.e., we search over the possible values of "maximum pages per student".
 *
 * KEY INSIGHT:
 *   - The answer (max pages) lies in the range:
 *       low  = max(books[i])   → minimum possible, at least one student must read
 *                                 the largest book
 *       high = sum(books[i])   → maximum possible, one student reads everything
 *   - For a given `mid` (candidate max pages), we can GREEDILY check if it is
 *     feasible to allocate all books to at most k students without any student
 *     exceeding `mid` pages.
 *
 * GREEDY CHECK (canAllocateBooks):
 *   - Go left to right. Keep assigning books to the current student.
 *   - As soon as adding the next book exceeds `mid`, assign it to the next student.
 *   - Count total students needed. If count <= k, the allocation is feasible.
 *
 * BINARY SEARCH DIRECTION (Minimization Pattern):
 *   - If feasible   → mid might be the answer, but try smaller → high = mid - 1
 *   - If infeasible → mid is too small, need more pages       → low  = mid + 1
 *
 * TIME COMPLEXITY : O(N * log(sum - max))
 *   - log(sum - max) iterations of binary search
 *   - O(N) for each greedy check
 * SPACE COMPLEXITY: O(1)
 */
public class GFG_BookAllocation {

    /**
     * Greedy check: Can we allocate all books to at most k students
     * such that no student reads more than `pages` pages?
     *
     * @param pages the candidate maximum page limit per student
     * @param books array of page counts for each book
     * @param k     number of students
     * @return true if allocation is feasible within the page limit, false otherwise
     */
    public boolean canAllocateBooks(long pages, int[] books, int k) {
        int len = books.length;

        // Edge case: more students than books → can't give every student at least one
        // book
        if (len < k)
            return false;

        int assignedStudents = 0;
        int i = 0;

        while (i < len) {
            // Start assigning books to the next student
            assignedStudents++;

            // Early exit: already using more students than allowed
            if (assignedStudents > k)
                return false;

            int j = i;
            int pageSum = 0;

            // Keep giving books to the current student as long as the page limit holds
            while (j < len && pageSum + books[j] <= pages) {
                pageSum += books[j];
                j++;
            }

            // Move to the next unassigned book
            i = j;
        }

        // All books assigned within k students and the page limit
        return true;
    }

    /**
     * Finds the minimum possible value of the maximum pages
     * assigned to any student when books are optimally allocated.
     *
     * @param books array of page counts for each book
     * @param k     number of students
     * @return minimum possible maximum pages, or -1 if allocation is impossible
     */
    public int findPages(int[] books, int k) {
        int len = books.length;

        // Edge case: more students than books → impossible to assign at least one book
        // each
        if (len < k)
            return -1;

        // low = max(books) → a student must read at least the largest single book
        // high = sum(books) → worst case, one student reads everything
        int low = 0, high = 0, mid;

        for (int val : books) {
            low = Math.max(low, val);
            high += val;
        }

        // Stores the best (minimum) feasible answer found so far
        int minPages = -1;

        // Binary search on the answer space [max(books), sum(books)]
        while (low <= high) {
            mid = low + (high - low) / 2; // avoids integer overflow vs (low+high)/2

            if (canAllocateBooks(mid, books, k)) {
                // mid is feasible → record it and try to minimize further (search left)
                minPages = mid;
                high = mid - 1;
            } else {
                // mid is too restrictive → increase the page limit (search right)
                low = mid + 1;
            }
        }

        return minPages;
    }
}