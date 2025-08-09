import java.util.Arrays;

public class linear_array {
    public static void main(String[] args) {
        int[] arr1={1,2,3,4,5,6};
        int t=5;
        int ans1=linerasearch(arr1,t);
        System.out.println(ans1);
        int[][] arr2={{23,24,1},{1,3,5},{7,8,9},{10,11,12}};
        int[] ans2=linerasearch(arr2,t);
        System.out.println(Arrays.toString(ans2));

    }

    static int linerasearch(int[] arr, int t){
        for(int i=0;i<=arr.length-1;i++){
            if(arr[i]==t){
                return i;
            }
        }
        return -1;
    }

    static int[] linerasearch(int[][] arr, int t){
        for(int i=0;i<=arr.length-1;i++){
            for(int j=0;j<=arr[i].length-1;j++){
            if(arr[i][j]==t){
                return new int[]{i,j};
            }
        }
        }
        return new int[]{-1,-1};
    }
}
