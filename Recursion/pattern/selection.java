package Recursion.pattern;

import java.util.Arrays;

public class selection {
    public static void main(String[] args) {
        int[] arr = { 5, 3, 4, 1, 2 };
        s(arr, arr.length, 0, 0);
        System.out.println(Arrays.toString(arr));
    }

    static void s(int[] arr, int r, int c, int max) {
        if (r == 0) {
            return;
        }
        if (c < r) {
            if (arr[c] > arr[max]) {
                s(arr, r, c + 1, c);
            } else {
                s(arr, r, c + 1, max);
            }
        } else {
            int temp = arr[max];
            arr[max] = arr[r - 1];
            arr[r - 1] = temp;
            s(arr, r - 1, 0, 0);
        }
    }
}
