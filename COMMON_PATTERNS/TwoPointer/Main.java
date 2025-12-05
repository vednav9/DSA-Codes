package COMMON_PATTERNS.TwoPointer;

public class Main {
    public static void main(String[] args) {
        int[] arr={1,2,3};
        int target=0;

        int left = 0, right = arr.length - 1;
        while (left < right) {
            int sum = arr[left] + arr[right];

            if (sum == target)
            {
                /* found */
            }
            else if (sum < target)
                left++;
            else
                right--;
        }
    }
}