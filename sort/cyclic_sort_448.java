import java.util.*;
//Google
public class cyclic_sort_448 {
    public static void main(String[] args) {
        int[] nums = { 4, 3, 2, 7, 8, 2, 3, 1 };
        List<Integer> missingNumbers = findDisappearedNumbers(nums);
        System.out.println("Missing numbers: " + missingNumbers);
    }

    static List<Integer> findDisappearedNumbers(int[] nums) {
        int i = 0;
        while (i < nums.length) {
            int correct_index = nums[i] - 1;
            if (nums[i] != nums[correct_index]) {
                swap(nums, i, correct_index);
            } else {
                i++;
                correct_index++;
            }
        }
        // find missing numbers
        List<Integer> ans = new ArrayList<>();
        for (int index = 0; index < nums.length; index++) {
            if (nums[index] != index + 1) {
                ans.add(index + 1);
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



//Using Array


// static int[] findDisappearedNumbers(int[] nums) {
//     int i = 0;

//     // Perform cyclic sort
//     while (i < nums.length) {
//         int correctIndex = nums[i] - 1;
//         if (nums[i] != nums[correctIndex]) {
//             swap(nums, i, correctIndex);
//         } else {
//             i++;
//         }
//     }

//     // Determine the size of missing numbers
//     int missingCount = 0;
//     for (int index = 0; index < nums.length; index++) {
//         if (nums[index] != index + 1) {
//             missingCount++;
//         }
//     }

//     // Create an array for missing numbers
//     int[] missingNumbers = new int[missingCount];
//     int missingIndex = 0;

//     for (int index = 0; index < nums.length; index++) {
//         if (nums[index] != index + 1) {
//             missingNumbers[missingIndex++] = index + 1;
//         }
//     }

//     return missingNumbers;
// }