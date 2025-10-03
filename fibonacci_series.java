public class Fibonacci_series {
    public static void main(String[] args) {
        int n=8;
        for(int i=0; i<=n-1;i++)
        {System.out.println(f(i));}

        fseries(8, 0, 1);
    }
    static int f(int n){
        if(n<=1){
            return n;
        }
        else{
            return f(n-1)+f(n-2);
        }
    }

    static void fseries(int n, int a, int b){
        if (n==0) {
            return;
        }
        System.out.println(a);
        fseries(n-1, b, a+b);
    }
}