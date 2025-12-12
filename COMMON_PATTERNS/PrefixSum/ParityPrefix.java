package COMMON_PATTERNS.PrefixSum;

public class ParityPrefix {
    public int countEvenSumSubarrays(int[] nums) {
        int even = 1; // prefix = 0 is even
        int odd = 0;

        int prefix = 0;
        int count = 0;

        for (int n : nums) {
            prefix += n;

            if (prefix % 2 == 0) {
                count += even;
                even++;
            } else {
                count += odd;
                odd++;
            }
        }
        return count;
    }
}