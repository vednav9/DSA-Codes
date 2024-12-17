package Recursion.easy;

public class sumOfDigit {
    public static void main(String[] args) {
        System.out.println(fun(235));
    }
    static int fun(int n){
        if(n==0){
            return 0;
        }
        return(n%10)+fun(n/10);
    }
}