import java.util.Arrays;
public class binary_744 {
    public static void main(String[] args) {
        int[] arr = { 5, 7, 7, 7, 8, 10 };
        int target = 7;
        int[] series=binary1(arr, target);
        System.out.println(Arrays.toString(series));
    }

    static int[] binary1(int[] arr, int target) {
        int[] series = { -1, -1 };

        series[0] = binary2(arr, target, true);
        series[1] = binary2(arr, target, false);
        return series;
    }

    static int binary2(int[] arr, int target, boolean firstIndex) {
        int start = 0;
        int end = arr.length - 1;
        int ans=-1;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (arr[mid] < target) {
                start = mid + 1;
            } else if (arr[mid] > target) {
                end = mid - 1;
            }
            else{
                // { 5, 7, 7, 7, 8, 10 }
                ans=mid;
                if(firstIndex){
                    end = mid - 1;
                }
                else{
                    start = mid + 1;
                }
            }
        }

        return ans;
    }
}
