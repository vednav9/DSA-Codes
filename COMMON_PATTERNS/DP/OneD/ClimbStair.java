package DP.OneD;

public class ClimbStair {
    public int jump(int[] nums) {
        int jumps = 0, end = 0, far = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            far = Math.max(far, i + nums[i]);

            if (i == end) {
                jumps++;
                end = far;
            }
        }
        return jumps;
    }
}