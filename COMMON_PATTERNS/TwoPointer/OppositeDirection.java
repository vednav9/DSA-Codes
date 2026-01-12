package COMMON_PATTERNS.TwoPointer;

public class OppositeDirection {
    // Used when:
    // Array/string is sorted
    // You want pairs, triplets, or check palindrome

    // Pair Sum in Sorted Array (Two Sum II)
    public int[] twoSumSorted(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left < right) {
            int sum = arr[left] + arr[right];

            if (sum == target) {
                return new int[] { left, right };
            } else if (sum < target) {
                left++; // need larger number
            } else {
                right--; // need smaller number
            }
        }

        return new int[] { -1, -1 };
    }

    // Check Palindrome
    public boolean isPalindrome(String s) {
        int left = 0, right = s.length() - 1;

        while (left < right) {
            if (s.charAt(left) != s.charAt(right))
                return false;
            left++;
            right--;
        }
        return true;
    }
}