package sorting;

import arrays.medium.ArraysOfVishal;

/*
 * =========================================================
 *                    SELECTION SORT
 * =========================================================
 *
 * Problem:
 * --------
 * Sort an array in ascending order using Selection Sort.
 *
 * ---------------------------------------------------------
 * Core Idea
 * ---------------------------------------------------------
 *
 * Selection Sort divides the array into two parts:
 *
 * 1. Sorted Part
 * 2. Unsorted Part
 *
 * Initially,
 *
 * Sorted Part   : Empty
 * Unsorted Part : Entire Array
 *
 * During every iteration,
 * we select the smallest element from the unsorted part
 * and place it at the beginning of that unsorted part.
 *
 * As a result,
 * the sorted portion keeps growing from left to right.
 *
 * ---------------------------------------------------------
 * Why is it called Selection Sort?
 * ---------------------------------------------------------
 *
 * Because in every pass,
 * we SELECT the smallest element
 * instead of swapping elements repeatedly.
 *
 * Only one swap is performed after finding
 * the minimum element.
 *
 * =========================================================
 * Dry Run
 * =========================================================
 *
 * Initial Array
 *
 * [5, 3, 8, 4, 2]
 *
 * Pass 1
 * -------
 *
 * Find minimum from
 *
 * [5,3,8,4,2]
 *
 * Minimum = 2
 *
 * Swap with first element
 *
 * [2,3,8,4,5]
 *
 * Sorted Part
 * [2]
 *
 * ---------------------------------------------------------
 *
 * Pass 2
 *
 * Search minimum from
 *
 * [3,8,4,5]
 *
 * Minimum = 3
 *
 * Already at correct position.
 *
 * [2,3,8,4,5]
 *
 * Sorted Part
 * [2,3]
 *
 * ---------------------------------------------------------
 *
 * Pass 3
 *
 * Search minimum from
 *
 * [8,4,5]
 *
 * Minimum = 4
 *
 * Swap
 *
 * [2,3,4,8,5]
 *
 * ---------------------------------------------------------
 *
 * Pass 4
 *
 * Search minimum from
 *
 * [8,5]
 *
 * Minimum = 5
 *
 * Swap
 *
 * [2,3,4,5,8]
 *
 * Array Sorted.
 *
 * =========================================================
 * Why Does It Work?
 * =========================================================
 *
 * After every iteration,
 * one element reaches its final sorted position.
 *
 * Since the smallest remaining element is always placed
 * at the first position of the unsorted part,
 * that position never needs to be visited again.
 *
 * Example
 *
 * After first pass
 *
 * [2 | 3 8 4 5]
 *
 * 2 is guaranteed to be the smallest element
 * in the entire array.
 *
 * Therefore,
 * it never needs to move again.
 *
 * =========================================================
 * Time Complexity
 * =========================================================
 *
 * Best Case    : O(n²)
 * Average Case : O(n²)
 * Worst Case   : O(n²)
 *
 * Why?
 *
 * Even if the array is already sorted,
 * Selection Sort still searches the remaining
 * unsorted part to confirm that no smaller
 * element exists.
 *
 * =========================================================
 * Space Complexity
 * =========================================================
 *
 * O(1)
 *
 * It sorts the array in-place
 * without using any extra memory.
 */

public class SelectionSort {

    /**
     * Sorts the array using Selection Sort.
     *
     * Idea:
     * -----
     * During every iteration,
     * find the smallest element from the
     * remaining unsorted portion and place
     * it at its correct position.
     *
     * @param a Input array
     */
    public void selectionSort(int[] a) {

        int length = a.length;

        /*
         * i represents the first index
         * of the unsorted portion.
         *
         * Everything before i
         * is already sorted.
         */
        for (int i = 0; i < length; i++) {

            /*
             * Assume the current element
             * is the smallest.
             */
            int minIndex = i;

            /*
             * Search the remaining array
             * for a smaller element.
             */
            for (int j = i + 1; j < length; j++) {

                /*
                 * Found a smaller element.
                 *
                 * Update the minimum index.
                 */
                if (a[j] < a[minIndex]) {
                    minIndex = j;
                }
            }

            /*
             * Place the smallest element
             * at the beginning of the
             * unsorted portion.
             *
             * After this swap,
             * position i becomes fixed forever.
             */
            ArraysOfVishal.swap(a, i, minIndex);
        }
    }
}