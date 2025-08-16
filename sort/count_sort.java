import java.util.Arrays;
import java.util.HashMap;

class count_sort {

    public static void countSort(int[] arr){
        if (arr==null||arr.length<=1) {
            return;
        }

        int max=arr[0];
        for(int num:arr){
            if (num>max) {
                max=num;
            }
        }
        int[] countArray=new int[max+1];
        for (int num : arr) {
            countArray[num]++;
        }
        int index=0;
        for (int i=0;i<=max;i++) {
            while (countArray[i]>0) {
                arr[index]=i;
                index++;
                countArray[i]--;
            }
        }
    }

    static void countSortHash(int[] arr){
        if (arr==null||arr.length<=1) {
            return;
        }

        int max=Arrays.stream(arr).max().getAsInt();
        int min=Arrays.stream(arr).min().getAsInt();
        HashMap<Integer,Integer> countMap=new HashMap<>();
        for (int num : arr) {
            countMap.put(num, countMap.getOrDefault(num,0)+1);
        }
        int index=0;
        for (int i = min; i <=max; i++) {
            int count=countMap.getOrDefault(i, 0);
            for(int j=0;j<count;j++){
                arr[index]=i;
                index++;
            }
        }
    }



    public static void main(String[] args) {
        int[] arr={6,3,10,9,2,4,9,7};
        countSortHash(arr);
        System.out.println(Arrays.toString(arr));
    }
}