package heap.medium;

import java.util.Collections;
import java.util.PriorityQueue;

public class Basic_KthSmallestElement {

    public int kthSmallest(int[] nums, int k) {
        if (nums.length == 0 || nums.length < k) {
            return -1;
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(k + 1, Collections.reverseOrder());

        for (int val : nums) {
            maxHeap.offer(val);
            if (maxHeap.size() == k + 1) {
                maxHeap.poll();
            }
        }

        return maxHeap.poll();
    }

}
