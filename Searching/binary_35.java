public class binary_35 {
    public static void main(String[] args) {
        int[] arr={3,4,5,6};
        int target=9;
        System.out.println(binary(arr,target));
    }

    static int binary(int[] arr,int target){
        int start=0;
        int end=arr.length-1;
        while (start<=end) {
            int mid=start+(end-start)/2;
            
            if(arr[mid]==target){
                return arr[mid];
            }
            else if(arr[mid]<target){
                start=mid+1;
            }
            else{
                end=mid-1;
            }
        }

        return start;
    }
}
