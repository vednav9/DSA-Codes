// Meeting Rooms II (Minimum Rooms)

package MergeIntervals;

import java.util.Arrays;
import java.util.PriorityQueue;

public class SweepLine {

    public static void main(String[] args) {
        SweepLine solution = new SweepLine();

        int[][] intervals = {
                { 0, 30 },
                { 5, 10 },
                { 15, 20 }
        };

        int result = solution.minMeetingRooms(intervals);
        System.out.println("Minimum number of meeting rooms: " + result);
    }

    public int minMeetingRooms(int[][] intervals) {
        int n = intervals.length;
        int[] start = new int[n];
        int[] end = new int[n];

        for (int i = 0; i < n; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }

        Arrays.sort(start);
        Arrays.sort(end);

        int rooms = 0;
        int endPtr = 0;

        for (int i = 0; i < n; i++) {
            // New meeting starts before earliest one ends → need a room
            if (start[i] < end[endPtr]) {
                rooms++;
            } else {
                // else one meeting freed up a room
                endPtr++;
            }
        }

        return rooms;
    }

    // ANOTHER WAY - PQ
    public int minMeetingRooms2(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(intervals[0][1]);

        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= pq.peek()) {
                pq.poll();
            }
            pq.offer(intervals[i][1]);
        }
        return pq.size();
    }
}