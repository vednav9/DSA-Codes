package StringAlgorithm;

public class RabinKarp {
    public int rabinKarp(String text, String pattern) {

        int n = text.length(), m = pattern.length();
        int base = 256, mod = 1000000007;

        long pHash = 0, tHash = 0, power = 1;

        for (int i = 0; i < m; i++) {
            pHash = (pHash * base + pattern.charAt(i)) % mod;
            tHash = (tHash * base + text.charAt(i)) % mod;
            power = (power * base) % mod;
        }

        for (int i = 0; i <= n - m; i++) {

            if (pHash == tHash) {
                if (text.substring(i, i + m).equals(pattern))
                    return i;
            }

            if (i < n - m) {
                tHash = (tHash * base - text.charAt(i) * power % mod + mod) % mod;
                tHash = (tHash + text.charAt(i + m)) % mod;
            }
        }
        return -1;
    }
}