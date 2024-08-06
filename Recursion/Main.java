package Recursion;

public class Main {
    public static void main(String[] args) {
        int ans=print1(44);
        System.out.println(ans);
    }

    static int print1(int n){
        if(n<2){
            return n;
        }
        return print1(n-1)+print1(n-2);
    }
}
