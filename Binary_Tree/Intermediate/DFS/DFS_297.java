// Google, Amazon, Meta

package Binary_Tree.Intermediate.DFS;

import java.util.*;

public class DFS_297 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        List<String> list = new ArrayList<>();
        serialize(root, list);
        return String.join(",", list);
    }

    public List<String> serialize(TreeNode node, List<String> list){
        helper(node,list);
        return list;
    }
    void helper(TreeNode node, List<String> list){
        if (node==null) {
            list.add("null");
            return;
        }
        list.add(String.valueOf(node.val));

        helper(node.left, list);
        helper(node.right, list);
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        List<String> list = new LinkedList<>(Arrays.asList(data.split(",")));
        return deserializeHelper(list);
    }

    // TreeNode deserialize(List<String> list){
    //     Collection.reverse(list);
    //     TreeNode node = helper2(list);
    //     return node;
    // }
    // TreeNode helper2(List<String> list){
    //     String val=list.remove(list.size()-1);
    //     if (val.charAt(0)=='n') {
    //         return null;
    //     }
    //     TreeNode node=new TreeNode(Integer.parseInt(val));

    //     node.left=helper2(list);
    //     node.right=helper2(list);

    //     return node;
    // }

    private TreeNode deserializeHelper(List<String> list) {
        String val = list.remove(0); // Remove the first value from the list

        if (val.equals("null")) {
            return null;
        }

        TreeNode node = new TreeNode(Integer.parseInt(val)); // Create the node
        node.left = deserializeHelper(list);  // Recursively build the left child
        node.right = deserializeHelper(list); // Recursively build the right child

        return node;
    }
}