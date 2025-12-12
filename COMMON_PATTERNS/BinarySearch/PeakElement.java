package COMMON_PATTERNS.BinarySearch;

public class PeakElement {
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (nums[mid] < nums[mid + 1])
                left = mid + 1; // go right
            else
                right = mid; // go left
        }
        return left;
    }
}