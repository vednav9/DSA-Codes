package COMMON_PATTERNS.TwoPointer;

// Partitioning (like Quicksort)
// Removing elements (filtering)
// Moving zeros
// Sorting colors (Dutch National Flag)

public class InPlace {
    // Move Zeroes (LeetCode 283)
    public void moveZeroes(int[] nums) {
        int left = 0; // pointer for non-zero

        for (int right = 0; right < nums.length; right++) {
            if (nums[right] != 0) {
                int temp = nums[left];
                nums[left] = nums[right];
                nums[right] = temp;
                left++;
            }
        }
    }

    // Sort Colors (Dutch National Flag) — BEST example of in-place two pointers
    public void sortColors(int[] nums) {
        int low = 0, mid = 0, high = nums.length - 1;

        while (mid <= high) {
            if (nums[mid] == 0) {
                swap(nums, low, mid);
                low++;
                mid++;
            } else if (nums[mid] == 1) {
                mid++;
            } else {
                swap(nums, mid, high);
                high--;
            }
        }
    }

    public void swap(int[] nums, int left, int right) {
        int temp = nums[left];
        nums[left] = nums[right];
        nums[right] = temp;
    }
}