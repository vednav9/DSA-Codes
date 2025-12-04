package COMMON_PATTERNS.Sliding_Window;

public class VariableSize {
    int slidingWindowGeneric(int[] arr) {
        int left = 0;
        // some state: sum, freq map, etc.

        for (int right = 0; right < arr.length; right++) {
            // 1) include arr[right] into window

            // 2) while window is INVALID → shrink from left
            while (true /* condition invalid using current state */) {
                // remove arr[left] from state
                left++;
            }

            // 3) here window [left..right] is VALID
            // update answer if needed (length, sum, etc.)
        }
        return 0; // or answer
    }

    int longestSubarraySumAtMostK(int[] arr, int K) {
        int left = 0;
        int sum = 0;
        int best = 0;

        for (int right = 0; right < arr.length; right++) {
            // 1) include arr[right]
            sum += arr[right];

            // 2) shrink window while INVALID (sum > K)
            while (sum > K) {
                sum -= arr[left];
                left++;
            }

            // 3) window [left..right] is VALID → update answer
            best = Math.max(best, right - left + 1);
        }
        return best;
    }
}