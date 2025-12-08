package COMMON_PATTERNS.PrefixSum;

import java.util.HashMap;
import java.util.Map;

public class HashMapPrefixSum {
    public int subarraySum(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1); // important for subarrays starting at index 0

        int prefix = 0;
        int count = 0;

        for (int num : nums) {
            prefix += num;

            if (map.containsKey(prefix - k)) {
                count += map.get(prefix - k);
            }

            map.put(prefix, map.getOrDefault(prefix, 0) + 1);
        }

        return count;
    }
}