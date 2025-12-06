package COMMON_PATTERNS.FastSlow;

public class HappyNumber {
    public boolean isHappy(int n) {
        int slow = n;
        int fast = next(n);

        while (fast != 1 && slow != fast) {
            slow = next(slow);
            fast = next(next(fast));
        }

        return fast == 1;
    }

    private int next(int n) {
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return sum;
    }

}