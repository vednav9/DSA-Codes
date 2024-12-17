public class linear_arrayrange {
    public static void main(String[] args) {
        int[] arr={1,2,3,4,5,6};
        int t=9;
        int s=1;
        int e=5;
        int ans=linerasearch(arr,t,s,e);
        System.out.println(ans);
    }

    static int linerasearch(int[] arr, int t, int s, int e){
        
        for(int i=s;i<=e;i++){
            if(arr[i]==t){
                return i;
            }
        }

        return -1;
    }
}