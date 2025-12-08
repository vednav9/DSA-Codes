package COMMON_PATTERNS.MergeIntervals;

import java.util.ArrayList;
import java.util.List;

public class EraseInterval {
    public List<int[]> removeInterval(int[][] intervals, int[] toRemove) {
        List<int[]> result = new ArrayList<>();

        int rStart = toRemove[0];
        int rEnd = toRemove[1];

        for (int[] interval : intervals) {
            int start = interval[0];
            int end = interval[1];

            // No overlap
            if (end <= rStart || start >= rEnd) {
                result.add(interval);
            } else {
                // Left part remaining
                if (start < rStart) {
                    result.add(new int[] { start, rStart });
                }
                // Right part remaining
                if (end > rEnd) {
                    result.add(new int[] { rEnd, end });
                }
            }
        }

        return result;
    }
}