package Recursion;

public class binary_search {
    public static void main(String[] args) {
        int[] n = { 1, 2, 3, 4, 5, 6, 7, 8, 9 };
        int target = 3;
        int index = binary_recur(n, target, 0, n.length - 1);
        System.out.println(index);
    }

    static int binary_recur(int[] n, int target, int s, int e) {

        if(s>e){
            return -1;
        }
        int mid = s + (e - s) / 2;
        if (target > n[mid]) {
            return binary_recur(n, target, mid+1, e);
        } else if (target < n[mid]) {
            return binary_recur(n, target, s, mid-1);//e = mid - 1;
        } else {
            return mid;
        }

    }
}
