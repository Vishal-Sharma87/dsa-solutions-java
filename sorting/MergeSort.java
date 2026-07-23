package sorting;

/*
 * =========================================================
 *                      MERGE SORT
 * =========================================================
 *
 * Problem:
 * --------
 * Sort an array in ascending order using Merge Sort.
 *
 * ---------------------------------------------------------
 * Core Idea
 * ---------------------------------------------------------
 *
 * Merge Sort follows the Divide and Conquer approach.
 *
 * Instead of sorting the entire array directly,
 * it repeatedly divides the array into smaller halves.
 *
 * Once every sub-array contains only one element,
 * it starts merging them back in sorted order.
 *
 * Think of it like solving a big problem by first
 * breaking it into many small problems.
 *
 * Divide → Solve → Merge
 *
 * =========================================================
 * Why Does Merge Sort Divide?
 * =========================================================
 *
 * Sorting a large array is difficult.
 *
 * Sorting one element is trivial because
 * a single element is already sorted.
 *
 * Therefore,
 * Merge Sort keeps dividing the array until
 * every sub-array has only one element.
 *
 * Then,
 * it combines those small sorted arrays
 * to build the final sorted array.
 *
 * =========================================================
 * Dry Run
 * =========================================================
 *
 * Initial Array
 *
 * [8,3,5,4,7,6,1,2]
 *
 * -------------------------
 * Divide Phase
 * -------------------------
 *
 *                    [8,3,5,4,7,6,1,2]
 *                    /               \
 *              [8,3,5,4]          [7,6,1,2]
 *              /      \            /      \
 *           [8,3]   [5,4]      [7,6]    [1,2]
 *           /  \     /  \       /  \      /  \
 *         [8][3] [5][4] [7][6] [1][2]
 *
 * Every single element
 * is already sorted.
 *
 * -------------------------
 * Merge Phase
 * -------------------------
 *
 * [8] + [3]
 * ↓
 * [3,8]
 *
 * [5] + [4]
 * ↓
 * [4,5]
 *
 * Merge
 *
 * [3,8]
 * [4,5]
 *
 * ↓
 *
 * [3,4,5,8]
 *
 * Continue merging
 * until one sorted array remains.
 *
 * =========================================================
 * Why Does Merging Work?
 * =========================================================
 *
 * Before merging,
 * both halves are already sorted.
 *
 * Therefore,
 * we only need to compare the first
 * unprocessed element from each half.
 *
 * The smaller one must appear first
 * in the final merged array.
 *
 * Continue until one side becomes empty.
 *
 * Finally,
 * copy the remaining elements.
 *
 * Since both halves were already sorted,
 * the merged array also becomes sorted.
 *
 * =========================================================
 * Example of Merge
 * =========================================================
 *
 * Left
 *
 * [2,5,9]
 *
 * Right
 *
 * [1,4,8]
 *
 * Compare
 *
 * 2 vs 1
 *
 * Pick 1
 *
 * Compare
 *
 * 2 vs 4
 *
 * Pick 2
 *
 * Compare
 *
 * 5 vs 4
 *
 * Pick 4
 *
 * Continue...
 *
 * Result
 *
 * [1,2,4,5,8,9]
 *
 * =========================================================
 * Why Extra Array?
 * =========================================================
 *
 * While merging,
 * we cannot overwrite the original array
 * because some elements are still needed
 * for future comparisons.
 *
 * Therefore,
 * we temporarily store the merged result
 * inside another array.
 *
 * Once merging is complete,
 * we copy it back.
 *
 * =========================================================
 * Time Complexity
 * =========================================================
 *
 * Best    : O(n log n)
 * Average : O(n log n)
 * Worst   : O(n log n)
 *
 * Why?
 *
 * Dividing takes log n levels.
 *
 * Every level processes all n elements.
 *
 * Total
 *
 * n × log n
 *
 * =========================================================
 * Space Complexity
 * =========================================================
 *
 * O(n)
 *
 * Extra space is required
 * to store the merged array.
 */

public class MergeSort {

    /**
     * Sorts the given range using Merge Sort.
     *
     * Idea:
     * -----
     * Divide the array into two halves,
     * recursively sort both halves,
     * then merge them into one sorted array.
     *
     * @param nums  Input array
     * @param start Starting index
     * @param end   Ending index
     */
    public void mergeSort(int[] nums, int start, int end) {

        /*
         * Base Condition
         *
         * A sub-array containing
         * one or zero elements
         * is already sorted.
         */
        if (start >= end)
            return;

        /*
         * Find the middle index
         * to divide the array.
         */
        int mid = start + (end - start) / 2;

        /*
         * Sort the left half.
         */
        mergeSort(nums, start, mid);

        /*
         * Sort the right half.
         */
        mergeSort(nums, mid + 1, end);

        /*
         * --------------------------------------------------
         * Merge Both Sorted Halves
         * --------------------------------------------------
         *
         * At this point,
         * both halves are individually sorted.
         *
         * We now combine them into
         * one sorted array.
         */
        int[] merged = new int[end - start + 1];

        int left = start;
        int right = mid + 1;
        int index = 0;

        /*
         * Compare the smallest remaining
         * elements from both halves.
         *
         * Copy the smaller one.
         */
        while (left <= mid && right <= end) {

            if (nums[left] <= nums[right]) {
                merged[index++] = nums[left++];
            } else {
                merged[index++] = nums[right++];
            }
        }

        /*
         * Copy remaining elements
         * from the left half.
         */
        while (left <= mid) {
            merged[index++] = nums[left++];
        }

        /*
         * Copy remaining elements
         * from the right half.
         */
        while (right <= end) {
            merged[index++] = nums[right++];
        }

        /*
         * Copy the merged result
         * back into the original array.
         */
        index = start;

        for (int value : merged) {
            nums[index++] = value;
        }
    }
}