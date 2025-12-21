package COMMON_PATTERNS.Backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Subset {
    public List<List<Integer>> subsets(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        backtrack(result, new ArrayList<>(), nums, 0);
        return result;
    }

    void backtrack(List<List<Integer>> result, List<Integer> temp, int[] nums, int start) {

        result.add(new ArrayList<>(temp));

        for (int i = start; i < nums.length; i++) {
            if (i > start && nums[i] == nums[i - 1]) {
                continue;
            }
            temp.add(nums[i]);
            backtrack(result, temp, nums, i + 1);
            temp.remove(temp.size() - 1);
        }
    }
}