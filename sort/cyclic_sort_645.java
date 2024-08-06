import java.util.ArrayList;
import java.util.List;

public class cyclic_sort_645 {
    public static void main(String[] args) {
        
    }

    static int[] findErrorNums(int[] nums) {
        int i=0;
        while(i<nums.length){
            int correct_index=nums[i]-1;
            if(nums[i]!=nums[correct_index]){
                swap(nums,i,correct_index);
            }
            else{
                i++;
            }
        }
        //find missing numbers
        for(int index=0;index<nums.length;index++){
            if(nums[index]!=index+1){//1 to n
                return new int[]{nums[index],index+1};
            }
        }
        return new int[]{-1,-1};
    }

    static void swap(int[] arr, int first, int second) {
        int temp = arr[first];
        arr[first] = arr[second];
        arr[second] = temp;
    }
}
