class binary_33 {
    public static void main(String[] args) {
        int[] arr={10,12,2,4,5,7,8,9};
        int target=10;
        int num=search(arr,target);
        System.out.println(num);
    }
    static int search(int[] arr, int target) {
        int start=0;
        int end=arr.length-1;
        while(start<=end){
            int mid=start+(end-start)/2;
            if(arr[mid]==target){
                return mid;
            }
            if(arr[start]<=arr[mid]){   // {5,6,7,8,9,1,2}
                if(arr[start]<=target&& target<arr[mid]){
                    end=mid-1;
                }
                else{
                    start=mid+1;
                }
            }
            else{   // {10,12,2,4,5,7,8,9}
            
                if(target<=arr[end]&&arr[mid]<target){
                    start=mid+1;
                }
                else{
                    end=mid-1;
                }
            }
        }
        return -1;
    }
}