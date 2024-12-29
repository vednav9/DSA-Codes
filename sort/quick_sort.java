import java.util.Arrays;

class quick_sort{
    public static void main(String[] args) {
        int[] arr = { 5, 4, 3, 2, 1, 6 };
        quicksort(arr,0, arr.length-1);
        System.out.println(Arrays.toString(arr));

        // Arrays.sort(arr);
        // System.out.println(Arrays.toString(arr));
    }

    static void quicksort(int[] arr,int l, int h){
        if(l>=h){
            return;
        }
        int s = l;
        int e = h;
        int pivot=arr[s+(e-s)/2];

        while(s<=e){
            while (arr[s]<pivot) {
                s++;
            }
            while (arr[e]>pivot) {
                e--;
            }
            if (s<=e) {
                int temp=arr[s];
                arr[s]=arr[e];
                arr[e]=temp;
                s++;
                e--;
            }
        }

        quicksort(arr,l,e);
        quicksort(arr,s,h);
    }
}