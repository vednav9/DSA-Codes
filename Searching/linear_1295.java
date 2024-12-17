//1295. Find Numbers with Even Number of Digits

public class linear_1295 {
    public static void main(String[] args) {
        int[] nums = { 12, 345, 2, 6, 7896 };
        System.out.println(findNumbers(nums));
    }

    static int findNumbers(int[] nums) {
        int count = 0;
        int isEven = 0;
        for (int i = 0; i < nums.length; i++) {
            while (nums[i] != 0) {
                nums[i] /= 10;
                count++;
            }
            if (count % 2 == 0) {
                isEven++;
            }
            count = 0;
        }
        return isEven;
    }
}
