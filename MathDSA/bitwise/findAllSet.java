package MathDSA.bitwise;

public class findAllSet {
    public static void main(String[] args) {
        int n = 9;
        int count = 0;
        while (n > 0) {
            if ((n & 1) == 1) {
                count++;
            }
            n=n>>1;
        }
        System.out.println(count);

        int m=346;
        System.out.println(setBits(m));
    }

    static int setBits(int n) {
        int count = 0;

//        while (n > 0) {
//            count++;
//            n -= (n & -n);
//        }

        while (n > 0) {
            count++;
            n = n & (n-1);
        }

        return count;
    }
}
