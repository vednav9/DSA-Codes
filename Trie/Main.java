// Google, Microsoft, Amazon

package Trie;

public class Main {
    static class Node {
        Node[] children;
        boolean eow; // End of word

        public Node() {
            children = new Node[26];
            eow = false;
        }
    }

    static Node root=new Node();

    // Insert in Trie - Google, Microsoft, Amazon
    public static void insert(String word) {
        Node curr = root;  // Use this as the root node
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';

            if (curr.children[idx] == null) {
                curr.children[idx] = new Node();
            }

            curr = curr.children[idx];
        }
        curr.eow = true;  // Mark end of word after loop
    }


    public static boolean search(String word) {
        Node curr = root;
        for (int i = 0; i < word.length(); i++) {
            int idx = word.charAt(i) - 'a';

            if (curr.children[idx] == null) {
                return false;
            }

            curr = curr.children[idx];
        }
        return curr.eow;  // Only return true if it's end of a word
    }


    // Google
    // Work Break Problem
    public static boolean wordBreak(String key) {
        if (key.length() == 0) {
            return true;
        }

        for (int i = 1; i <= key.length(); i++) {
            String firstPart = key.substring(0, i);
            String secPart = key.substring(i);
            if (search(firstPart) && wordBreak(secPart)) {
                return true;
            }
        }
        return false;
    }


    // Prefix
    public static boolean startsWith(String prefix) {
        Node curr = root;
        for (int i = 0; i < prefix.length(); i++) {
            int idx = prefix.charAt(i) - 'a';
            if (curr.children[idx] == null) {
                return false;
            }
            curr = curr.children[idx];
        }
        return true;
    }

    
    // Google, Microsoft
    // Collect Unique SubStrings
    public static int countNode(Node root){
        if (root==null) {
            return 0;
        }
        int count=0;
        for (int i = 0; i < 26; i++) {
            if (root.children[i]!=null) {
                count+=countNode(root.children[i]);
            }
        }
        return count+1;
    }


    // Longest Word with all Prefixes
    public static String ans="";
    public static void longestWord(Node root, StringBuilder temp){
        if (root==null) {
            return;
        }
        for (int i = 0; i < 26; i++) {
            if (root.children[i]!=null&&root.children[i].eow==true) {
                temp.append((char)(i+'a'));
                if (temp.length()> ans.length()) {
                    ans=temp.toString();
                }
                longestWord(root.children[i], temp);
                temp.deleteCharAt(temp.length()-1);
            }
        }
    }

    public static void main(String[] args) {
        // Google - Work Break Problems
        String word[] = { "i", "like", "app", "apple", "mobile" };
        String key = "ilikeapple";
        for (int index = 0; index < word.length; index++) {
            insert(word[index]);
        }
        System.out.println(wordBreak(key));


        // Starts with
        String words[] = { "apple", "app", "mango", "man", "mobile" };
        String prefix = "app";
        for (int index = 0; index < words.length; index++) {
            insert(word[index]);
        }
        System.out.println(startsWith(prefix));


        // Collect Unique SubStrings
        String str="ababa";
        for (int i = 0; i < str.length(); i++) {
            String suffix=str.substring(i);
            insert(suffix);
        }
        System.out.println(countNode(root));


        // Longest Word with all Prefixes
        String prefixes[]={"a","banana","app","appl","ap","apply","apple"};
        for (int i = 0; i < prefixes.length; i++) {
            insert(prefixes[i]);
        }
        longestWord(root, new StringBuilder(""));
        System.out.println(ans);
    }
}