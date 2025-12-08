package COMMON_PATTERNS.MergeIntervals;

import java.util.*;

public class MergeOverlapping {
    public int[][] merge(int[][] intervals) {
        if (intervals.length <= 1)
            return intervals;

        // Step 1: Sort intervals by start
        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));

        List<int[]> result = new ArrayList<>();

        // Start with the first interval
        int[] current = intervals[0];

        for (int i = 1; i < intervals.length; i++) {
            int[] next = intervals[i];

            // Check overlap: next.start <= current.end
            if (next[0] <= current[1]) {
                // Merge
                current[1] = Math.max(current[1], next[1]);
            } else {
                // No overlap → push current
                result.add(current);
                current = next; // move to next interval
            }
        }

        // Add last interval
        result.add(current);

        return result.toArray(new int[result.size()][]);
    }
}