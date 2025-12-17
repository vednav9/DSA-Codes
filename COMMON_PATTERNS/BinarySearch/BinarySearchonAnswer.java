package COMMON_PATTERNS.BinarySearch;

public class BinarySearchonAnswer {
    public int minEatingSpeed(int[] piles, int h) {
        int left = 1, right = 0;
        for (int p : piles)
            right = Math.max(right, p);

        while (left < right) {
            int mid = left + (right - left) / 2;

            if (canEat(piles, h, mid))
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }

    boolean canEat(int[] piles, int h, int k) {
        long time = 0;
        for (int p : piles) {
            time += (p + k - 1) / k; // ceil(p/k)
        }
        return time <= h;
    }
}