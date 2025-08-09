public class linear_1672 {
    public static void main(String[] args) {
        int[][] acc = { { 1, 2, 3, 11, 1 }, { 3, 2, 1, 9 } };
        int ans = maximumWealth(acc);
        System.out.println(ans);
    }

    static int maximumWealth(int[][] accounts) {
        int ans = 0;
        for (int p = 0; p <accounts.length-1; p++) {
            int sum = 0;
            for (int a = 0; a < accounts[p].length-1; a++) {
                sum += accounts[p][a];
            }
            if (sum > ans) {
                ans = sum;
            }
        }

        // for (int[] p : accounts) {
        //     int sum = 0;
        //     for (int a : p) {
        //         sum += a;
        //     }
        //     if (sum > ans) {
        //         ans = sum;
        //     }

        // }

        return ans;
    }
}
