package TreeAlgorithm;

public class FenwickTree {

    int[] bit;
    int n;

    FenwickTree(int n) {
        this.n = n;
        bit = new int[n+1];
    }

    void update(int i, int val) {
        while (i <= n) {
            bit[i] += val;
            i += i & (-i);
        }
    }

    int sum(int i) {
        int s = 0;
        while (i > 0) {
            s += bit[i];
            i -= i & (-i);
        }
        return s;
    }
}