package Math;
import java.util.*;

public class SieveofEratosthenes {
    public List<Integer> sieve(int n) {
        boolean[] prime = new boolean[n + 1];
        Arrays.fill(prime, true);

        prime[0] = prime[1] = false;

        for (int i = 2; i * i <= n; i++) {
            if (prime[i]) {
                for (int j = i * i; j <= n; j += i) {
                    prime[j] = false;
                }
            }
        }

        List<Integer> res = new ArrayList<>();
        for (int i = 2; i <= n; i++) {
            if (prime[i])
                res.add(i);
        }
        return res;
    }
}