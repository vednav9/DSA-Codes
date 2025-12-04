package COMMON_PATTERNS.Sliding_Window;

public class DynamicExpandShrink {
    int solve(int[] arr) {
        int left = 0, n = arr.length;
        // state: sum, map, etc.

        for (int right = 0; right < n; right++) {
            // expand window: include arr[right]

            // shrink while invalid
            while (true /* invalid condition */) {
                // remove arr[left] from state
                left++;
            }

            // now window is valid
            // update answer using [left..right]
        }
        return 0; // or ans
    }
}