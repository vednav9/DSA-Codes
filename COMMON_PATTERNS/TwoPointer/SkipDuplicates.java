package COMMON_PATTERNS.TwoPointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// 2Sum / 3Sum / 4Sum
// Removing duplicates
// Counting unique elements
// Sorted datasets

public class SkipDuplicates {
    // Remove Duplicates from Sorted Array
    public int removeDuplicates(int[] nums) {
        int left = 0;

        for (int right = 1; right < nums.length; right++) {
            if (nums[right] != nums[left]) {
                left++;
                nums[left] = nums[right];
            }
        }

        return left + 1; // new length
    }

    // 3Sum (skip duplicates)
    public List<List<Integer>> threeSum(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();

        for (int i = 0; i < nums.length - 2; i++) {
            if (i > 0 && nums[i] == nums[i - 1])
                continue; // skip duplicates

            int left = i + 1;
            int right = nums.length - 1;

            while (left < right) {
                int sum = nums[i] + nums[left] + nums[right];

                if (sum == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    left++;
                    right--;

                    while (left < right && nums[left] == nums[left - 1])
                        left++; // skip
                    while (left < right && nums[right] == nums[right + 1])
                        right--; // skip
                } else if (sum < 0)
                    left++;
                else
                    right--;
            }
        }

        return result;
    }
}