public class fibonacci_series {
    public static void main(String[] args) {
        int n=8;
        for(int i=0; i<=n-1;i++)
        {System.out.println(f(i));}
    }
    static int f(int n){
        if(n<=1){
            return n;
        }
        else{
            return f(n-1)+f(n-2);
        }
    }
}