package GP;

import java.util.*;

public class MaxEvents {
    public int maxEvents(int[][] events) {
        Arrays.sort(events, (a, b) -> a[0] - b[0]);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        int day = 0, i = 0, n = events.length, count = 0;

        while (i < n || !minHeap.isEmpty()) {

            if (minHeap.isEmpty()) {
                day = events[i][0];
            }

            while (i < n && events[i][0] == day) {
                minHeap.offer(events[i][1]);
                i++;
            }

            while (!minHeap.isEmpty() && minHeap.peek() < day) {
                minHeap.poll();
            }

            if (!minHeap.isEmpty()) {
                minHeap.poll();
                count++;
                day++;
            }
        }

        return count;
    }
}
