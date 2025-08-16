// Amazon ( Most Asked)

// Can be also solved by HashMap

package Binary_Tree.Intermediate.DFS;

import java.util.Arrays;

public class DFS_105 {
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

    public TreeNode buildTree(int[] preorder, int[] inorder){
        if (preorder.length==0) {
            return null;
        }

        int r=preorder[0];
        int index=0;// for inorder

        for(int i=0;i<inorder.length;i++){
            if (inorder[i]==r) {
                index=i;
            }
        }

        TreeNode node=new TreeNode(r);

        node.left=buildTree(Arrays.copyOfRange(preorder,1, index), Arrays.copyOfRange(preorder,0, index));
        node.right=buildTree(Arrays.copyOfRange(preorder,index+1, preorder.length), Arrays.copyOfRange(preorder,index+1, inorder.length));

        return node;
    }
}