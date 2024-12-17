package MathDSA;

import java.util.Arrays;

public class Prac {
    public static void main(String[] args) {
        int a = 3;
        int b = 6;
        int ans = (a ^ b);

        System.out.println(ans);

        // isOdd
        int c=35234;
        System.out.println(isOdd(c));

        // Fint i th bit
        int n=3855325;
        int i =findIth(n);
        //System.out.println(Integer.toBinaryString(i));
    }

    static boolean isOdd(int c){
        return (c&1)==1;
    }

    static int findIth(int n){
        return n & (1<<6);
    }
}