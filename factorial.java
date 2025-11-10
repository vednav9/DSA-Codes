public class Factorial {
    public static void main(String[] args) {
        int n=5;
        long fact=1;
        for(int i=1; i<=n; i++) {
            fact *= i; // Calculate factorial iteratively
            System.out.println("Factorial of " + i + " is: " + fact);
        }
    }
    
    static long fact(int n){
        long fact=1;
        if (n<=1) {
            return 1;
        }
        return n*fact(n-1);
    }
}