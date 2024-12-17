package Recursion.easy;

public class Recursion_1342 {
    public static void main(String[] args) {
        System.out.println(numberOfSteps(14));//6
    }

    static int numberOfSteps(int num){
        return helper(num,0);
    }

    static int helper(int num, int steps){
        if(num==0){
            return steps;
        }
        if(num%2==0){
            return helper(num/2,steps+1);
        }
        return helper(num-1,steps+1);
    }
}