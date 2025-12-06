package COMMON_PATTERNS.TwoPointer;

public class Main {
    public static void main(String[] args) {
        // Opposite direction (sorted array)
        int[] arr = { 1, 2, 3 };
        int target = 0;

        int left = 0, right = arr.length - 1;
        while (left < right) {
            int sum = arr[left] + arr[right];

            if (sum == target) {
                /* found */
            } else if (sum < target)
                left++;
            else
                right--;
        }

        // Same direction (slow/fast)
        int slow = 0, fast = 0;
        while (fast < arr.length) {
            // move fast pointer
            // move slow pointer conditionally
            fast++;
        }

        // Skip duplicates
        while (left < right && arr[left] == arr[left - 1])
            left++;
        while (left < right && arr[right] == arr[right + 1])
            right--;

        // In-place partitioning
        int l = 0, r = arr.length - 1;
        while (left < right) {
            if (true /* condition */)
                left++;
            else
                swap(left, right--);
        }

    }

    private static void swap(int left, int right) {
        return;
    }
}