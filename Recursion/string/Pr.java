package Recursion.string;

import java.util.ArrayList;

public class Pr {
    public static void main(String[] args) {
        pr("", "abc");

        ArrayList<String> ans=prList("", "abc");
        System.out.println(ans);

        System.out.println(prCount("","abc"));
    }

    static void pr(String p, String up) {
        if (up.isEmpty()) {
            System.out.println(p);
            return;
        }

        char ch = up.charAt(0);
        for (int i = 0; i <= p.length(); i++) {
            String f = p.substring(0, i); // First
            String s = p.substring(i, p.length()); // Second
            pr(f + ch + s, up.substring(1));
        }
    }

    static ArrayList<String> prList(String p, String up) {
        if (up.isEmpty()) {
            ArrayList<String> list = new ArrayList<>();
            list.add(p);
            return list;
        }

        char ch = up.charAt(0);
        ArrayList<String> ans = new ArrayList<>();
        for (int i = 0; i <= p.length(); i++) {
            String f = p.substring(0, i); // First
            String s = p.substring(i, p.length()); // Second
            ans.addAll(prList(f + ch + s, up.substring(1)));
        }
        return ans;
    }

    static int prCount(String p, String up) {
        if (up.isEmpty()) {
            return 1;
        }

        int count=0;
        char ch = up.charAt(0);
        for (int i = 0; i <= p.length(); i++) {
            String f = p.substring(0, i); // First
            String s = p.substring(i, p.length()); // Second
            count=count+ prCount(f + ch + s, up.substring(1));
        }
        return count;
    }
}
