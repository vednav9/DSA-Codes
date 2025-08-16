package HashMap;

import java.util.*;

public class HuffmanCoding {

    // Node class representing each character and its frequency in the tree
    static class HuffmanNode {
        char ch; // character
        int freq; // frequency
        HuffmanNode left, right; // left and right child in the tree

        // Constructor for leaf nodes
        HuffmanNode(char ch, int freq) {
            this.ch = ch;
            this.freq = freq;
        }

        // Constructor for internal nodes
        HuffmanNode(int freq, HuffmanNode left, HuffmanNode right) {
            this.ch = '\0'; // null character since it's not a leaf
            this.freq = freq;
            this.left = left;
            this.right = right;
        }
    }

    // Step 1: Count frequency of each character in the input string
    public static Map<Character, Integer> buildFrequencyMap(String text) {
        Map<Character, Integer> freqMap = new HashMap<>();

        for (char ch : text.toCharArray()) {
            // Increase count for each character
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }

        return freqMap;
    }

    // Step 2: Build the Huffman tree using a min-heap (priority queue)
    public static HuffmanNode buildHuffmanTree(Map<Character, Integer> freqMap) {
        // Priority queue (min-heap) sorted by frequency
        PriorityQueue<HuffmanNode> pq = new PriorityQueue<>(Comparator.comparingInt(n -> n.freq));

        // Create a leaf node for each character and add to the queue
        for (Map.Entry<Character, Integer> entry : freqMap.entrySet()) {
            pq.offer(new HuffmanNode(entry.getKey(), entry.getValue()));
        }

        // Combine nodes until only one remains (the root)
        while (pq.size() > 1) {
            HuffmanNode left = pq.poll();  // node with lowest frequency
            HuffmanNode right = pq.poll(); // node with second lowest

            // Create a new internal node with combined frequency
            HuffmanNode parent = new HuffmanNode(left.freq + right.freq, left, right);

            pq.offer(parent); // Add the combined node back to the queue
        }

        return pq.poll(); // The final node is the root of the Huffman Tree
    }

    // Step 3: Generate Huffman codes by traversing the tree
    public static void generateCodes(HuffmanNode root, String code, Map<Character, String> huffmanCode) {
        if (root == null)
            return;

        // If it's a leaf node, store the code
        if (root.left == null && root.right == null) {
            huffmanCode.put(root.ch, code);
        }

        // Traverse left with '0' and right with '1'
        generateCodes(root.left, code + '0', huffmanCode);
        generateCodes(root.right, code + '1', huffmanCode);
    }

    // Step 4: Encode the original string using the Huffman codes
    public static String encode(String text, Map<Character, String> huffmanCode) {
        StringBuilder sb = new StringBuilder();

        for (char ch : text.toCharArray()) {
            sb.append(huffmanCode.get(ch)); // append binary code for each character
        }

        return sb.toString(); // return the final encoded string
    }

    // Step 5 (optional): Decode the binary string back to original text
    public static String decode(String encodedStr, HuffmanNode root) {
        StringBuilder result = new StringBuilder();
        HuffmanNode current = root;

        for (char bit : encodedStr.toCharArray()) {
            // Traverse the tree based on current bit
            current = (bit == '0') ? current.left : current.right;

            // If we reach a leaf node, append character and reset to root
            if (current.left == null && current.right == null) {
                result.append(current.ch);
                current = root;
            }
        }

        return result.toString(); // return decoded string
    }

    // Main method to run the example
    public static void main(String[] args) {
        String text = "huffman coding example"; // Input string

        // Build frequency map from input text
        Map<Character, Integer> freqMap = buildFrequencyMap(text);

        // Build Huffman Tree from frequency map
        HuffmanNode root = buildHuffmanTree(freqMap);

        // Map to store the Huffman codes
        Map<Character, String> huffmanCode = new HashMap<>();

        // Generate Huffman codes by traversing the tree
        generateCodes(root, "", huffmanCode);

        // Display the Huffman codes
        System.out.println("Huffman Codes: " + huffmanCode);

        // Encode the original string using Huffman codes
        String encoded = encode(text, huffmanCode);
        System.out.println("Encoded: " + encoded);

        // Decode the encoded string back to original
        String decoded = decode(encoded, root);
        System.out.println("Decoded: " + decoded);
    }
}