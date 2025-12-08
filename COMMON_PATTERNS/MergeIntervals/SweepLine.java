// Meeting Rooms II (Minimum Rooms)

package COMMON_PATTERNS.MergeIntervals;
import java.util.Arrays;

public class SweepLine {
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
}