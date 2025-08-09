import java.util.Arrays;

public class binary_744 {
    public static void main(String[] args) {
        char[] arr = {'a','b','c','d'};
        char target = 'a';
        System.out.println(nextGreatestLetter(arr, target));
    }

    public static char nextGreatestLetter(char[] letters, char target) {
        int start = 0;
        int end = letters.length - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (target < letters[mid]) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }
        return letters[start % letters.length];
    }

}
