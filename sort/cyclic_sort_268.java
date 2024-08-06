import java.util.*;
//Amazon
public class cyclic_sort_268 {
    public static void main(String[] args) {
        int[] arr = {0,1};
        sort(arr);
        int missing=sort(arr);
        System.out.println(missing);
    }

    static int sort(int[] arr) {
        int i=0;
        while(i<arr.length){
            //int correct_index=arr[i];
            if(arr[i]!=i)
            {
                swap(arr, i, arr[i]);
            }
            else{
                i++;
            }
        }

        return i;
    }

    static void swap(int[] arr, int first, int second) {
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }
}
