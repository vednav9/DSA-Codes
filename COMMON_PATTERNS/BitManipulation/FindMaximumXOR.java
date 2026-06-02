package BitManipulation;

class TrieNode {
    TrieNode[] child = new TrieNode[2];
}

class FindMaximumXOR {

    public int findMaximumXOR(int[] nums) {
        TrieNode root = new TrieNode();

        // insert numbers
        for (int num : nums) {
            TrieNode node = root;
            for (int i = 31; i >= 0; i--) {
                int bit = (num >> i) & 1;
                if (node.child[bit] == null)
                    node.child[bit] = new TrieNode();
                node = node.child[bit];
            }
        }

        int max = 0;

        // find max XOR
        for (int num : nums) {
            TrieNode node = root;
            int curr = 0;

            for (int i = 31; i >= 0; i--) {
                int bit = (num >> i) & 1;

                if (node.child[1 - bit] != null) {
                    curr |= (1 << i);
                    node = node.child[1 - bit];
                } else {
                    node = node.child[bit];
                }
            }
            max = Math.max(max, curr);
        }

        return max;
    }
}