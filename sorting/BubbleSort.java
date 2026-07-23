package sorting;

import arrays.medium.ArraysOfVishal;

/*
 * =========================================================
 *                     BUBBLE SORT
 * =========================================================
 *
 * Problem:
 * --------
 * Sort an array in ascending order using Bubble Sort.
 *
 * ---------------------------------------------------------
 * Core Idea
 * ---------------------------------------------------------
 *
 * Bubble Sort repeatedly compares two adjacent elements.
 *
 * If they are in the wrong order,
 * swap them.
 *
 * By continuously swapping adjacent elements,
 * the largest element "bubbles" towards the end
 * of the array after every pass.
 *
 * ---------------------------------------------------------
 * Why is it called Bubble Sort?
 * ---------------------------------------------------------
 *
 * Just like an air bubble rises to the surface of water,
 * the largest element gradually moves to the end
 * of the array through adjacent swaps.
 *
 * Only neighboring elements are compared.
 *
 * =========================================================
 * Dry Run
 * =========================================================
 *
 * Initial Array
 *
 * [5, 1, 4, 2, 8]
 *
 * -------------------------
 * Pass 1
 * -------------------------
 *
 * Compare 5 and 1
 * Swap
 *
 * [1,5,4,2,8]
 *
 * Compare 5 and 4
 * Swap
 *
 * [1,4,5,2,8]
 *
 * Compare 5 and 2
 * Swap
 *
 * [1,4,2,5,8]
 *
 * Compare 5 and 8
 *
 * No Swap
 *
 * Largest element (8)
 * reaches its final position.
 *
 * -------------------------
 * Pass 2
 * -------------------------
 *
 * Compare only till index 2.
 *
 * [1,2,4,5,8]
 *
 * Now,
 * 5 is also fixed.
 *
 * -------------------------
 * Pass 3
 * -------------------------
 *
 * [1,2,4,5,8]
 *
 * No swaps occur.
 *
 * This means
 * the array is already sorted.
 *
 * Stop immediately.
 *
 * =========================================================
 * Why Does It Work?
 * =========================================================
 *
 * Every adjacent comparison fixes local disorder.
 *
 * After one complete pass,
 * the largest remaining element
 * reaches its correct position.
 *
 * Therefore,
 *
 * After Pass 1
 * Last element is sorted.
 *
 * After Pass 2
 * Last two elements are sorted.
 *
 * After Pass 3
 * Last three elements are sorted.
 *
 * The sorted portion grows
 * from right to left.
 *
 * =========================================================
 * Optimization
 * =========================================================
 *
 * A boolean variable "swapped"
 * is used to detect whether any
 * swap happened during a pass.
 *
 * If no swaps occur,
 * every adjacent pair is already
 * in correct order.
 *
 * Therefore,
 * the entire array is sorted.
 *
 * We stop immediately instead of
 * performing unnecessary passes.
 *
 * This improves the best-case time
 * complexity from O(n²) to O(n).
 *
 * =========================================================
 * Time Complexity
 * =========================================================
 *
 * Best Case    : O(n)
 * (Already Sorted)
 *
 * Average Case : O(n²)
 *
 * Worst Case   : O(n²)
 *
 * =========================================================
 * Space Complexity
 * =========================================================
 *
 * O(1)
 *
 * Bubble Sort is an in-place sorting algorithm.
 */

public class BubbleSort {

    /**
     * Sorts the given array using Bubble Sort.
     *
     * Idea:
     * -----
     * Repeatedly compare adjacent elements.
     * Swap them if they are in the wrong order.
     *
     * After every pass,
     * the largest remaining element
     * reaches its correct position.
     *
     * @param a Input array
     */
    public void bubbleSort(int[] a) {

        int length = a.length;

        /*
         * Each pass fixes one element
         * at the end of the array.
         */
        for (int i = 0; i < length; i++) {

            /*
             * Tracks whether any swap
             * happened in this pass.
             */
            boolean swapped = false;

            /*
             * No need to visit the last i elements
             * because they are already sorted.
             */
            for (int j = 0; j < length - i - 1; j++) {

                /*
                 * Adjacent elements are
                 * in the wrong order.
                 *
                 * Swap them.
                 */
                if (a[j] > a[j + 1]) {

                    ArraysOfVishal.swap(a, j, j + 1);
                    swapped = true;
                }
            }

            /*
             * No swaps mean the array
             * is already sorted.
             *
             * Stop early.
             */
            if (!swapped) {
                break;
            }
        }
    }
}