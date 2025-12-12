package COMMON_PATTERNS.PrefixSum;

public class DifferenceArray {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] diff = new int[n + 1];
        for (int[] b : bookings) {
            int l = b[0] - 1;
            int r = b[1] - 1;
            int val = b[2];

            diff[l] += val;
            if (r + 1 < n) diff[r + 1] -= val;
        }
        int[] res = new int[n];
        int running = 0;
        for (int i = 0; i < n; i++) {
            running += diff[i];
            res[i] = running;
        }
        return res;
    }
}