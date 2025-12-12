package COMMON_PATTERNS.BinarySearch;

public class FirstLast {
    public int[] searchRange(int[] nums, int target) {

        int[] ans = { -1, -1 };
        // check for first occurrence if target first
        ans[0] = search(nums, target, true);// first element
        if (ans[0] != -1) {
            ans[1] = search(nums, target, false);// last element
        }
        return ans;
    }

    int search(int[] nums, int target, boolean findStartIndex) {
        int ans = -1;
        int start = 0;
        int end = nums.length - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (target < nums[mid]) {
                end = mid - 1;
            } else if (target > nums[mid]) {
                start = mid + 1;
            } else {
                // potential ans found
                ans = mid;
                if (findStartIndex) {
                    end = mid - 1;// for first
                } else {
                    start = mid + 1;// for last
                }
            }
        }
        return ans;
    }
}