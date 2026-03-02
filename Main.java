import arrays.ArraysOfVishal;
import binarysearch.BinarySearch;
import binarysearch.LC88RotatedSortedArraySearch2;
import binarysearch.on1DArrays.FindIndexOfTarget;
import binarysearch.on1DArrays.LC34FindFirstAndLastOccurrences;
import sorting.MergeSort;

public class Main {
    public static void main(String[] args) {

        int[] a = new int[] { 1, 1, 1, 1, 1, 1, 1, 1, 1, 1 };

        int target = -1;
        if (new LC88RotatedSortedArraySearch2().exists(a, 2)) {
            System.out.println("True");
        }
    }
}
