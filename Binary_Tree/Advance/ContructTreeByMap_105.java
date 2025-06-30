package Binary_Tree.Advance;

import java.util.HashMap;

public class ContructTreeByMap_105 {
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public TreeNode buildTree(int[] preorder, int[] inorder) {
        HashMap<Integer,Integer> map=new HashMap<>();
        for (int i : inorder) {
            map.put(inorder[i],i);
        }
        int[] index={0};
        return helper(preorder,inorder,0,preorder.length-1,map, index);
    }

    private TreeNode helper(int[] preorder, int[] inorder, int left, int right, HashMap<Integer, Integer> map, int[] index) {
        if (left>right) {
            return null;
        }

        int current =preorder[index[0]];
        index[0]++;
        TreeNode node =new TreeNode(current);
        if (left==right) {
            return node;
        }

        node.left=helper(preorder, inorder, left, index[0]-1, map, index);
        node.right=helper(preorder, inorder, index[0]+1, right, map, index);

        return node;
    }
    
}
