package COMMON_PATTERNS.TwoPointer;

import java.util.Arrays;

public class PairSum {
    public int countPairs(int[] nums, int target) {
        Arrays.sort(nums);
        int left = 0, right = nums.length - 1;
        int count = 0;

        while (left < right) {
            int sum = nums[left] + nums[right];
            if (sum == target) {
                count++;
                left++;
                right--;
            } else if (sum < target) {
                left++;
            } else {
                right--;
            }
        }

        return count;
    }

    public static void main(String[] args) {
        PairSum ps = new PairSum();

        int[] nums = {1, 5, 7, -1, 5};
        int target = 6;

        int result = ps.countPairs(nums, target);

        System.out.println("Number of pairs with sum " + target + " = " + result);
    }
}