import java.util.Arrays;

public class prac {
    public static void main(String[] args) {
        int[] arr = {4,5,1,2,3};
        selection(arr);
        System.out.println(Arrays.toString(arr));
    }

    static void selection(int[] arr){
        for(int i=0;i<arr.length;i++){
            int first=0;
            int last =arr.length-i-1;
            int maxIndex= getMaxIndex(arr, first, last);
            swap(arr, maxIndex, last);
        }
    }
    
    static int getMaxIndex(int[] arr, int start, int end){
        int max=start;
        for(int j=start;j<=end;j++){
            if(arr[max]<arr[j]){
                max=j;
            }
        }
        return max;
    }

    static void swap(int[] arr, int first, int second){
        int temp=arr[first];
        arr[first]=arr[second];
        arr[second]=temp;
    }
    
}