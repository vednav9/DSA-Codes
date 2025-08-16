import java.util.*;

public class TwoStack {

    static int twoStacks(int x, int[] a, int[] b) {
        return twoStacks(x, a, b, 0, 0, 0);
    }

    private static int twoStacks(int x, int[] a, int[] b, int sum, int i, int j) {
        if (sum > x) return 0;

        int count = i + j;

        int max = count;

        if (i < a.length)
            max = Math.max(max, twoStacks(x, a, b, sum + a[i], i + 1, j));

        if (j < b.length)
            max = Math.max(max, twoStacks(x, a, b, sum + b[j], i, j + 1));

        return max;
    }

    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int t = s.nextInt();
        for (int i = 0; i < t; i++) {
            int n = s.nextInt();
            int m = s.nextInt();
            int x = s.nextInt();
            int[] a = new int[n];
            int[] b = new int[m];
            for (int j = 0; j < n; j++) {
                a[j] = s.nextInt();
            }
            for (int j = 0; j < m; j++) {
                b[j] = s.nextInt();
            }
            System.out.println(twoStacks(x, a, b));
        }
    }
}