// Meeting Rooms II (Minimum Rooms)

package MergeIntervals;
import java.util.Arrays;

public class SweepLine {

    public static void main(String[] args) {
        SweepLine solution = new SweepLine();

        int[][] intervals = {
            {0, 30},
            {5, 10},
            {15, 20}
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
}