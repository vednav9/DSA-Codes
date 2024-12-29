package Recursion.string;

import java.util.ArrayList;

class SubSeq {
    public static void main(String[] args) {
        //sub("", "abc");
        //System.out.println(subseqRet("","abc"));
        //subASCII("", "abc");
        System.out.println(subseqASCIIRet("","abc"));
    }

    static void sub(String p, String up){
        if(up.isEmpty()){
            System.out.println(p);
            return;
        }
        char ch=up.charAt(0);

        sub(p+ch, up.substring(1));
        sub(p, up.substring(1));
    }

    // using ArrayList
    
    static ArrayList<String> subseqRet(String p, String up){
        if(up.isEmpty()){
            ArrayList<String> list = new ArrayList<>();
            list.add(p);
            return list;
        }
        
        char ch=up.charAt(0);

        ArrayList<String> left = subseqRet(p+ch, up.substring(1));
        ArrayList<String> right = subseqRet(p, up.substring(1));

        left.addAll(right);
        return left;
    }

    static void subASCII(String p, String up){
        if(up.isEmpty()){
            System.out.println(p);
            return;
        }
        char ch=up.charAt(0);

        subASCII(p+ch, up.substring(1));
        subASCII(p, up.substring(1));
        subASCII(p+(ch+0), up.substring(1));
    }

    // using ArrayList
    
    static ArrayList<String> subseqASCIIRet(String p, String up){
        if(up.isEmpty()){
            ArrayList<String> list = new ArrayList<>();
            list.add(p);
            return list;
        }
        
        char ch=up.charAt(0);

        ArrayList<String> first = subseqASCIIRet(p+ch, up.substring(1));
        ArrayList<String> second = subseqASCIIRet(p, up.substring(1));
        ArrayList<String> third = subseqASCIIRet(p + (ch+0), up.substring(1));

        first.addAll(second);
        first.addAll(third);
        return first;
    }
}