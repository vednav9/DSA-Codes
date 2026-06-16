package StringAlgorithm;

public class TrieSearching {
    class TrieNode {
        TrieNode[] child = new TrieNode[26];
        boolean isEnd;
    }

    class Trie {

        TrieNode root = new TrieNode();

        public void insert(String word) {
            TrieNode node = root;

            for (char c : word.toCharArray()) {
                int i = c - 'a';
                if (node.child[i] == null)
                    node.child[i] = new TrieNode();
                node = node.child[i];
            }
            node.isEnd = true;
        }

        public boolean search(String word) {
            TrieNode node = root;

            for (char c : word.toCharArray()) {
                int i = c - 'a';
                if (node.child[i] == null)
                    return false;
                node = node.child[i];
            }
            return node.isEnd;
        }
    }
}