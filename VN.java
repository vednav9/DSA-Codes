import java.util.Arrays;
import java.util.Scanner;

public class VN {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int x = sc.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
            }
            int maxStreak = 0;
            for (int i = 0; i < n; i++) {
                int[] b = a.clone();
                b[i] *= x;
                maxStreak = Math.max(maxStreak, calculateStreak(b));
            }
            maxStreak = Math.max(maxStreak, calculateStreak(a)); // Check without modification as well
            System.out.println(maxStreak);
        }
        sc.close();
    }

    private static int calculateStreak(int[] arr) {
        int maxStreak = 0;
        for (int i = 0; i < arr.length; i++) {
            int currentStreak = 0;
            for (int j = i; j < arr.length; j++) {
                boolean nonDecreasing = true;
                for (int k = i + 1; k <= j; k++) {
                    if (arr[k] < arr[k - 1]) {
                        nonDecreasing = false;
                        break;
                    }
                }
                if (nonDecreasing) {
                    currentStreak = j - i + 1;
                } else {
                  break; //Optimization: If it's not non-decreasing, no need to check further
                }
            }
            maxStreak = Math.max(maxStreak, currentStreak);
        }
        return maxStreak;
    }
}