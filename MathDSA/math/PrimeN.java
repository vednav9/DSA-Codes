package MathDSA.math;

public class PrimeN {
    public static void main(String[] args) {
        int n=36;
        for(int i=1;i<n;i++){
            System.out.println(i+" "+isPrime(n));
        }
    }

    static boolean isPrime(int n){
        if(n<=1){
            return false;
        }

        int c=2;
        while (c*c<=n) { // going till square root
            if(n%c==0){
                return false;
            }
            c++;
        }

        return true;
    }
}
