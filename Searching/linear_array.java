public class linear_array {
    public static void main(String[] args) {
        int[] arr={1,2,3,4,5,6};
        int t=4;
        int ans=linerasearch(arr,t);
        System.out.println(ans);
    }

    static int linerasearch(int[] arr, int t){

        for(int i=0;i<=arr.length;i++){
            if(arr[i]==t){
                return i;
            }
        }

        return 0;
    }
}
