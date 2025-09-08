// import java.util.*;

public class Practice2 {
    // Reverse a string without using built-in functions.

    public static void main(String[] args) {
        System.out.println(16&1);
        
    }

    public static boolean isPalindrome(String s) {
        int i=0;
        s=s.strip();
        int j=s.length()-1;

        while (i<j) {
            if (s.charAt(i)==s.charAt(j)) {
                i++;
                j--;
            }
            else if ((s.charAt(i)==':') || (s.charAt(i)==',')) {
                i++;
            }
            else if ((s.charAt(j)==':') || (s.charAt(j)==',')) {
                j--;
            }
            else{
                return false;
            }
        }
        return true;
    }
}