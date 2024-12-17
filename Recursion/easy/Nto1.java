package Recursion.easy;

public class Nto1 {
    public static void main(String[] args) {
        int n=5;
        fun(n);
    }

    static void fun(int n){
        if(n==0){
            return;
        }
        System.out.println(n);// n to 1
        fun(n-1);
        System.out.println(n);// 1 to n
    }
}