package COMMON_PATTERNS.Sliding_Window;

import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowMaximum {
    int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        if (n == 0)
            return new int[0];

        int[] result = new int[n - k + 1];
        Deque<Integer> dq = new LinkedList<>(); // will store indices

        int resIdx = 0; // result index

        for (int i = 0; i < n; i++) {
            // 1) remove indices that are out of this window
            while (!dq.isEmpty() && dq.peekFirst() <= i - k) {
                dq.pollFirst();
            }

            // 2) maintain decreasing order: remove smaller elements from back
            while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) {
                dq.pollLast();
            }

            // 3) add current index
            dq.offerLast(i);

            // 4) starting from i >= k-1, window is valid; record max
            if (i >= k - 1) {
                result[resIdx++] = nums[dq.peekFirst()];
            }
        }

        return result;
    }
}