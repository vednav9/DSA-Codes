package String_StringBuilder;

import java.util.*;

public class Prac {
    public static void main(String[] args) {
        String a=new String("Vedant");
        String b=new String("Vedant");
        //boolean c = (a==b);
        System.out.println(a==b);
        int[] d={5,6,7,8,9};
        System.out.println(d[0]);
        float f=453.1234f;
        System.out.println("Formated number is %2f" + f);

        System.out.println("Hello %s you are %s"+"Vedant"+"Student");

        System.out.println("Vedant" + new ArrayList<>());

        //System.out.println(new Integer(56) +""+ new ArrayList<>());

        System.out.println("a"+'a');


        //String Performance

        String series="";
        for(int i=0;i<26;i++){
            char ch=(char)('a'+i);
            series= series+ch;
        }
        System.err.println(series);


        //String Builder

        StringBuilder sb=new StringBuilder();
        for(int i=0;i<26;i++){
            char ch=(char)('a'+i);
            sb.append(ch);
        }
        System.err.println(sb.toString());

        String s="Vedant Navthale";
        System.out.println(Arrays.toString(s.toCharArray()));


        String str="abcdca";
        System.out.println(isPalindrome(str));
    }

    static boolean isPalindrome(String str){
        if(str==null ||str.length()==0){
            return true;
        }
        str =str.toLowerCase();
        for(int i=0;i<=str.length()/2;i++){
            char start=str.charAt(i);
            char end =str.charAt(str.length()-i-1);

            if(start!=end){
                return false;
            }
        }
        return true;
    }
}