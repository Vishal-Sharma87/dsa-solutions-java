package sorting;

/*
 * =========================================================
 *                    INSERTION SORT
 * =========================================================
 *
 * Problem:
 * --------
 * Sort an array in ascending order using Insertion Sort.
 *
 * ---------------------------------------------------------
 * Core Idea
 * ---------------------------------------------------------
 *
 * Insertion Sort builds the sorted array one element
 * at a time.
 *
 * It assumes that the left portion of the array
 * is already sorted.
 *
 * During every iteration,
 * one element is picked from the unsorted part
 * and inserted into its correct position
 * inside the sorted part.
 *
 * It works exactly like arranging playing cards
 * in your hand.
 *
 * =========================================================
 * Dry Run
 * =========================================================
 *
 * Initial Array
 *
 * [5,3,4,1,2]
 *
 * Initially
 *
 * Sorted   : [5]
 * Unsorted : [3,4,1,2]
 *
 * ----------------------------------
 * Pass 1
 * ----------------------------------
 *
 * Current = 3
 *
 * Shift 5
 *
 * [5,5,4,1,2]
 *
 * Insert 3
 *
 * [3,5,4,1,2]
 *
 * ----------------------------------
 * Pass 2
 * ----------------------------------
 *
 * Current = 4
 *
 * Shift 5
 *
 * [3,5,5,1,2]
 *
 * Insert 4
 *
 * [3,4,5,1,2]
 *
 * ----------------------------------
 * Pass 3
 * ----------------------------------
 *
 * Current = 1
 *
 * Shift
 *
 * 5
 * 4
 * 3
 *
 * Insert
 *
 * [1,3,4,5,2]
 *
 * Continue until the entire
 * array becomes sorted.
 *
 * =========================================================
 * Why Does It Work?
 * =========================================================
 *
 * After every iteration,
 *
 * indices
 *
 * 0 ... i
 *
 * are always sorted.
 *
 * The next element is simply inserted
 * into that sorted portion.
 *
 * Larger elements are shifted
 * one position right
 * until the correct position
 * becomes available.
 *
 * =========================================================
 * Time Complexity
 * =========================================================
 *
 * Best    : O(n)
 * Average : O(n²)
 * Worst   : O(n²)
 *
 * =========================================================
 * Space Complexity
 * =========================================================
 *
 * O(1)
 */

public class InsertionSort {

    /**
     * Sorts the array using Insertion Sort.
     *
     * Idea:
     * -----
     * Grow the sorted portion
     * one element at a time.
     *
     * For every new element,
     * shift larger elements to the right
     * and insert the current element
     * into its correct position.
     *
     * @param array Input array
     */
    public void insertionSort(int[] array) {

        /*
         * Start from the second element.
         *
         * The first element is already
         * considered sorted.
         */
        for (int i = 1; i < array.length; i++) {

            /*
             * Element to be inserted
             * into the sorted portion.
             */
            int current = array[i];

            /*
             * Start comparing from
             * the last element
             * of the sorted portion.
             */
            int j = i - 1;

            /*
             * Shift larger elements
             * one position to the right.
             */
            while (j >= 0 && array[j] > current) {

                array[j + 1] = array[j];
                j--;
            }

            /*
             * Insert the current element
             * into its correct position.
             */
            array[j + 1] = current;
        }
    }
}