/*
Sliding Window – Core Idea

You have an array/string and you’re interested in continuous segments (subarrays / substrings).

Instead of:
    brute force = check all subarrays → O(n²)
You:
    maintain a “window” [left…right]
    expand right to include more
    move left to remove from the front
    keep updating your answer on the fly → O(n)
*/

package COMMON_PATTERNS.Sliding_Window;

public class FixedSize {
    int maxSumSubarrayOfSizeK(int[] arr, int k) {
        int windowSum = 0;
        int maxSum = Integer.MIN_VALUE;
        int left = 0;

        for (int right = 0; right < arr.length; right++) {
            windowSum += arr[right]; // add new element

            // once window reaches size k
            if (right - left + 1 == k) {
                maxSum = Math.max(maxSum, windowSum);

                windowSum -= arr[left]; // remove element going out
                left++; // shrink from left
            }
        }
        return maxSum;
    }
}