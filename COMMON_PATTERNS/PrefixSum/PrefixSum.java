package COMMON_PATTERNS.PrefixSum;

public class PrefixSum {
    static int[] prefix;

    public static void PrefixSumCode(int[] arr) {
        prefix = new int[arr.length];
        prefix[0] = arr[0];

        for (int i = 1; i < arr.length; i++) {
            prefix[i] = prefix[i - 1] + arr[i];
        }
    }

    public static int rangeSum(int left, int right) {
        if (left == 0)
            return prefix[right];
        return prefix[right] - prefix[left - 1];
    }

    public static void main(String[] args) {
        int[] arr = { 2, 4, 1, 3 };
        PrefixSumCode(arr);

        System.out.println(rangeSum(1, 3));
    }
}