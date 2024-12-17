package MathDSA.bitwise;

public class FindUniques {
    public static void main(String[] args) {
        int[] arr={2,3,3,4,6,4,2};
        System.out.println(ans(arr));

        // repeasting 3 times
        int[] nums={2,2,3,2,7,7,8,7,8,8};// 3
        System.out.println(result(nums));
    }

    static int ans(int[] arr){
        int unique=0;

        for(int n:arr){
            unique^=n;//XOR since 2^2^3^4^4 : order does not matter
        }             //like 2x2x3x5x2

        return unique;
    }

    static int result(int[] nums){
        int result = 0;

        // Traverse each bit position (0 to 31 for 32-bit integers)
        for (int i = 0; i < 32; i++) {
            int bitCount = 0;

            // Count the number of 1s at the i-th bit position
            for (int num : nums) {
                if ((num & (1 << i)) != 0) {
                    bitCount++;
                }
            }

            // If the bit count is not a multiple of 3, set the bit in result
            if (bitCount % 3 != 0) {
                result |= (1 << i);
            }
        }

        return result;
    }
}
 