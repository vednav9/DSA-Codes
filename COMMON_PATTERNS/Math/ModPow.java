package Math;

public class ModPow {
    public long modPow(long a, long b, int mod) {
        long res = 1;
        a %= mod;

        while (b > 0) {
            if ((b & 1) == 1)
                res = (res * a) % mod;

            a = (a * a) % mod;
            b >>= 1;
        }
        return res;
    }
}