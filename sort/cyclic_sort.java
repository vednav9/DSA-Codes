import java.util.*;

public class cyclic_sort {
    public static void main(String[] args) {
        int[] arr = {3,5,2,1,4};
        sort(arr);
        System.out.println(Arrays.toString(arr));
    }

    static void sort(int[] arr) {
        int i=0;
        while(i<arr.length){
            int correct_index=arr[i]-1;
            if(arr[i]!=arr[correct_index])
            {
                swap(arr, i, correct_index);
            }
            else{
                i++;
            }
        }

        // We not take for loop because, in 1st pass index at 0 element might not be at it's correct place

        // for(int i=0;i<arr.length;i++){
        //     int correct_index=arr[i]-1;
        //     if(arr[i]!=arr[correct_index])
        //     {
        //         swap(arr, i, correct_index);
        //     }
        // }
    }

    static void swap(int[] arr, int first, int second) {
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }
}