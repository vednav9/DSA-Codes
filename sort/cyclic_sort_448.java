import java.util.*;
//Google
public class cyclic_sort_448 {
    public static void main(String[] args) {
        
    }

    static List<Integer> findDisappearedNumbers(int[] nums) {
        int i=0;
        while(i<nums.length){
            int correct_index=nums[i]-1;
            if(nums[i]!=nums[correct_index]){
                swap(nums,i,correct_index);
            }
            else{
                i++;
                correct_index++;
            }
        }
        //find missing numbers
        List<Integer> ans=new ArrayList<>();
        for(int index=0;index<nums.length;index++){
            if(nums[index]!=index+1){
                ans.add(index+1);
            }
        }

        return ans;
    }

    static void swap(int[] nums, int first, int second) {
        int temp = nums[first];
        nums[first] = nums[second];
        nums[second] = temp;
    }
}
