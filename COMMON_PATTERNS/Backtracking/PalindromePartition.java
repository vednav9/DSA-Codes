package Backtracking;

import java.util.*;

public class PalindromePartition {
    public List<List<String>> partition(String s) {
        List<List<String>> result = new ArrayList<>();
        backtrack(s, 0, new ArrayList<>(), result);
        return result;
    }

    public void backtrack(String s, int start, List<String> path, List<List<String>> result) {
        // base case
        if(start==s.length()){
            result.add(new ArrayList<>(path));
        }

        // try all cuts
        for (int i = start; i < s.length(); i++) {
            if (isPalindrome(s, start, i)) {
                path.add(s.substring(start, i+1));
                backtrack(s, i+1, path, result);
                path.remove(path.size()-1);
            }
        }
    }

    private boolean isPalindrome(String s, int l, int r){
        while (l<r) {
            if (s.charAt(l)!=s.charAt(r)) {
                return false;
            }
            l++;
            r--;
        }
        return true;
    }
}