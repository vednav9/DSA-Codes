package COMMON_PATTERNS.BinarySearch;

public class KthSmallestPairDistance {
    public int splitArray(int[] nums, int m) {
        int left = 0, right = 0;
        for (int n : nums) {
            left = Math.max(left, n);
            right += n;
        }

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (canSplit(nums, m, mid))
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }

    boolean canSplit(int[] nums, int m, int maxAllowed) {
        int count = 1;
        int sum = 0;

        for (int n : nums) {
            if (sum + n > maxAllowed) {
                count++;
                sum = n;

                if (count > m)
                    return false;
            } else {
                sum += n;
            }
        }
        return true;
    }
}