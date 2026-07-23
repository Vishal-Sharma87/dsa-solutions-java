package sorting;

import arrays.medium.ArraysOfVishal;

/*
 * =========================================================
 *                       QUICK SORT
 * =========================================================
 *
 * Problem:
 * --------
 * Sort an array in ascending order using Quick Sort.
 *
 * ---------------------------------------------------------
 * Core Idea
 * ---------------------------------------------------------
 *
 * Quick Sort is a Divide and Conquer algorithm.
 *
 * Instead of sorting the whole array at once, it repeatedly:
 *
 * 1. Picks one element as Pivot.
 * 2. Places the pivot in its correct sorted position.
 * 3. Recursively sorts the left half.
 * 4. Recursively sorts the right half.
 *
 * Once a pivot reaches its correct position,
 * it will never move again.
 *
 * =========================================================
 * Why does this implementation work?
 * =========================================================
 *
 * Unlike the common Lomuto or Hoare partition schemes,
 * this implementation first finds where the pivot SHOULD be.
 *
 * Steps:
 *
 * 1. Choose Pivot
 * 2. Count smaller elements
 * 3. Find Pivot's Correct Index
 * 4. Move Pivot there
 * 5. Balance remaining elements
 * 6. Recurse on left and right halves
 *
 * This makes the algorithm easier to understand because
 * we know exactly where the pivot belongs before partitioning.
 *
 * =========================================================
 * Dry Run
 * =========================================================
 *
 * Initial Array
 *
 * [7,2,1,8,6,3,5,4]
 *
 * Pivot = 7
 *
 * Smaller Elements
 *
 * 2
 * 1
 * 6
 * 3
 * 5
 * 4
 *
 * Count = 6
 *
 * Therefore,
 *
 * Pivot Index = Start + Count
 *             = 0 + 6
 *             = 6
 *
 * Swap pivot with index 6
 *
 * [5,2,1,8,6,3,7,4]
 *
 * Now balance both sides
 *
 * [5,2,1,4,6,3,7,8]
 *
 * Now recursively sort
 *
 * Left
 * [5,2,1,4,6,3]
 *
 * Right
 * [8]
 *
 * Continue until every sub-array has
 * one or zero elements.
 *
 * =========================================================
 * Time Complexity
 * =========================================================
 *
 * Best    : O(n log n)
 * Average : O(n log n)
 * Worst   : O(n²)
 *
 * Space Complexity
 *
 * Average : O(log n)
 * Worst   : O(n)
 */

public class QuickSort {

    /**
     * Sorts the given range using Quick Sort.
     *
     * Why recursion?
     * --------------
     * Once the pivot reaches its correct position,
     * the left and right halves become completely independent.
     *
     * Therefore,
     * instead of sorting the whole array again,
     * we simply solve the same problem for both halves.
     *
     * @param array Input array
     * @param start Starting index
     * @param end   Ending index
     */
    public void quickSort(int[] array, int start, int end) {

        /*
         * Base Condition
         *
         * If the range has one or zero elements,
         * it is already sorted.
         */
        if (start >= end)
            return;

        /*
         * --------------------------------------------------
         * STEP 1
         * Choose Pivot
         * --------------------------------------------------
         *
         * We choose the first element.
         *
         * Example
         *
         * [7,2,1,8,6,3,5,4]
         *
         * Pivot = 7
         */
        int pivot = array[start];

        /*
         * --------------------------------------------------
         * STEP 2
         * Find Correct Pivot Index
         * --------------------------------------------------
         *
         * Count how many elements are smaller than pivot.
         *
         * If six elements are smaller,
         * pivot must appear after those six elements.
         */
        int pivotIndex = getCorrectPivotIndex(array, start, end);

        /*
         * --------------------------------------------------
         * STEP 3
         * Move Pivot
         * --------------------------------------------------
         *
         * Place the pivot at its final position.
         *
         * After this swap,
         * the pivot will never move again.
         */
        ArraysOfVishal.swap(array, start, pivotIndex);

        /*
         * --------------------------------------------------
         * STEP 4
         * Balance Left and Right
         * --------------------------------------------------
         *
         * After placing the pivot,
         * some larger elements may still be on the left,
         * and some smaller elements may still be on the right.
         *
         * We now fix only those misplaced elements.
         */
        makeArrayBalanced(array, start, end, pivotIndex);

        /*
         * --------------------------------------------------
         * STEP 5
         * Sort Left Half
         * --------------------------------------------------
         */
        quickSort(array, start, pivotIndex - 1);

        /*
         * --------------------------------------------------
         * STEP 6
         * Sort Right Half
         * --------------------------------------------------
         */
        quickSort(array, pivotIndex + 1, end);
    }

    /**
     * Rearranges the remaining elements around the pivot.
     *
     * Goal
     * ----
     * Left side -> Smaller values
     * Right side -> Larger values
     *
     * Uses two pointers.
     *
     * Left Pointer
     * Searches for a value greater than pivot.
     *
     * Right Pointer
     * Searches for a value smaller than pivot.
     *
     * Once both are found,
     * swap them.
     *
     * @param array      Input array
     * @param left       Left pointer
     * @param right      Right pointer
     * @param pivotIndex Final pivot position
     */
    private void makeArrayBalanced(int[] array, int left, int right, int pivotIndex) {

        int pivot = array[pivotIndex];

        while (left < pivotIndex && right > pivotIndex) {

            /*
             * Left value is too large.
             * Right value is too small.
             *
             * Both are misplaced.
             *
             * Swap them.
             */
            if (array[left] > pivot && array[right] < pivot) {

                ArraysOfVishal.swap(array, left, right);

                left++;
                right--;
            }

            /*
             * Left value is incorrect.
             *
             * Right value already belongs
             * on the right side.
             *
             * Continue searching from right.
             */
            else if (array[left] > pivot) {

                right--;
            }

            /*
             * Left value already belongs
             * on the left side.
             *
             * Move forward.
             */
            else {

                left++;
            }
        }
    }

    /**
     * Finds the final position of the pivot.
     *
     * Logic
     * -----
     * Count how many elements are smaller
     * than the pivot.
     *
     * If count = 4
     *
     * Then exactly four elements must
     * appear before the pivot.
     *
     * Therefore
     *
     * Pivot Index = start + count
     *
     * Example
     *
     * [7,2,1,8,6,3,5,4]
     *
     * Smaller elements
     *
     * 2
     * 1
     * 6
     * 3
     * 5
     * 4
     *
     * Count = 6
     *
     * Pivot Index = 0 + 6 = 6
     *
     * @param array Input array
     * @param start Starting index
     * @param end   Ending index
     * @return Correct pivot index
     */
    private int getCorrectPivotIndex(int[] array, int start, int end) {

        int pivot = array[start];

        int count = 0;

        for (int i = start + 1; i <= end; i++) {

            if (array[i] < pivot)
                count++;
        }

        return start + count;
    }
}