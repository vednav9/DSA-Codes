// Google

package Recursion;

import java.util.ArrayList;
import java.util.List;

public class string_17 {
    public static void main(String[] args) {
        // String up="12";
        // int digit = up.charAt(0) - '0';
        // System.out.println(digit);

        // phone("","12");

        // System.out.println(phoneArrayList("","12").size());
        // System.out.println(padCount("", "12"));

        System.out.println(letterCombinations("2"));
    }

    static void phone(String p, String up){
        if (up.isEmpty()) {
            System.out.println(p);
            return;
        }
        int digit = up.charAt(0) -'0';
        for (int i = (digit-1)*3; i < digit*3; i++) {
            char ch=(char) ('a'+i);
            phone(p+ch, up.substring(1));
        }
    }

    static ArrayList<String> phoneArrayList(String p, String up){
        if (up.isEmpty()) {
            ArrayList<String> list = new ArrayList<>();
            list.add(p);
            return list;
        }
        int digit = up.charAt(0) -'0';
        ArrayList<String> list=new ArrayList<>();
        for (int i = (digit-1)*3; i < digit*3; i++) {
            char ch=(char) ('a'+i);
            list.addAll(phoneArrayList(p+ch, up.substring(1)));
        }

        return list;
    }

    static int padCount(String p, String up) {
        if (up.isEmpty()) {
            return 1;
        }
        int count = 0;
        int digit = up.charAt(0) - '0'; // this will convert '2' into 2
        for (int i = (digit - 1) * 3; i < digit * 3; i++) {
            char ch = (char) ('a' + i);
            count = count + padCount(p + ch, up.substring(1));
        }
        return count;
    }

    

    // Leetcode

    static List<String> letterCombinations(String digits) {
        if(digits.isEmpty()){
            List<String> list= new ArrayList<>();
            return list;
        }
        List<String> list = ans(digits,"");
        return list;
    }
    static List<String> ans(String digits, String p) {
        if (digits.isEmpty()) {
            List<String> list= new ArrayList<>();
            list.add(p);
            return list;
        }
        int digit = digits.charAt(0) -'0';
        int  i=(digit-2)*3;
        if (digit>7) {
            i+=1; // Beacuse 7 is 4 digit and we want for 8,9
        }
        int len =i+3;
        if (digit==7||digit==9) {
            len+=1;
        }
        List<String> list=new ArrayList<>();
        for (int j=i; j < len; j++) {
            char ch=(char) ('a'+j);
            list.addAll(ans(digits.substring(1),p+ch));
        }

        return list;
    }
}
