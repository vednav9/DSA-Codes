package BitManipulation;

public class TwoUnique {
    public int[] singleNumber(int[] nums) {

        // Step 1: XOR all → xor = a ^ b
        int xor = 0;
        for (int num : nums)
            xor ^= num;

        // Step 2: Find rightmost set bit
        int bit = xor & (-xor);

        // Step 3: Divide into 2 groups, XOR each
        int a = 0, b = 0;
        for (int num : nums) {
            if ((num & bit) == 0)
                a ^= num;
            else
                b ^= num;
        }

        return new int[]{a, b};
    }
}