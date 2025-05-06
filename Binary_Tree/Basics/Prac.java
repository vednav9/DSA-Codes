package Binary_Tree.Basics;

class Prac {
    public class Node{
        int data;
        int startInterval;
        int endInterval;
        Node left;
        Node right;

        public Node(int startInterval, int endInterval){
            this.startInterval= startInterval;
            this.endInterval= endInterval;
        }
    }
    public Node root;

    public Prac(int[] arr){
        this.root=constructTree(arr, 0, arr.length-1);
    }

    public Node constructTree(int[] arr, int start, int end){
        if (start==end) {
            Node leaf=new Node(start, end);
            leaf.data = arr[start];
            return leaf;
        }

        int mid=(start+end)/2;
        Node node=new Node(start,end);
        
        node.left= this.constructTree(arr, start, mid);
        node.right=this.constructTree(arr, mid+1, end);
        node.data=node.left.data+node.right.data;

        return node;
    }

    public void display(){
        display(this.root);
    }

    public void display(Node node){

        String str = "";
  
        if(node.left != null) {
          str = str + "Interval=[" + node.left.startInterval + "-" + node.left.endInterval + "] and data: " + node.left.data + " => "; 
        } else {
          str = str + "No left child";
        }
    
        // for current node
        str = str + "Interval=[" + node.startInterval + "-" + node.endInterval + "] and data: " + node.data + " <= "; 
        
        if(node.right != null) {
          str = str + "Interval=[" + node.right.startInterval + "-" + node.right.endInterval + "] and data: " + node.right.data; 
        } else {
          str = str + "No right child";
        }
    
        System.out.println(str + '\n');
    
        // call recursion
        if(node.left != null) {
          display(node.left);
        }
    
        if(node.right != null) {
          display(node.right);
        }
    }

    public static void main(String[] args) {
        int[] arr={3, 8, 6, 7, -2, -8, 4, 9};
        Prac tree=new Prac(arr);
        tree.display();
    }
}