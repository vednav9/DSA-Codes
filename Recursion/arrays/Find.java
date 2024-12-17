package Recursion.arrays;

import java.util.ArrayList;

class Find {
    public static void main(String[] args) {
        int[] arr = { 2, 3, 1, 4, 4, 5 };
        
        System.out.println(linear(arr, 4, 0));
        System.out.println(linearIndex(arr, 4, 0));
        System.out.println(linearIndexLast(arr, 4, arr.length - 1));
        System.out.println();
        linearAllIndex(arr, 4, 0);

        linearAllIndexA1(arr, 4, 0);
        System.out.println(list1);
        System.out.println();

        ArrayList<Integer> list2=new ArrayList<>();
        linearAllIndexA2(arr, 4, 0, list2);
        System.out.println(list2);
        
        System.out.println(linearAllIndexA2(arr, 4, 0, new ArrayList<>()));;

        System.out.println();
        System.out.println();

        
        System.out.println(linearAllIndexA3(arr, 4, 0));
    }

    // return true or false
    static boolean linear(int[] arr, int target, int index) {
        if (index == arr.length) {
            return false;
        }
        return arr[index] == target || linear(arr, target, index + 1);
    }

    // return index
    static int linearIndex(int[] arr, int target, int index) {
        if (index == arr.length) {
            return -1;
        }
        if (arr[index] == target) {
            return index;
        } else {
            return linearIndex(arr, target, index + 1);
        }
    }

    // return index but search from last
    static int linearIndexLast(int[] arr, int target, int index) {
        if (index == -1) {
            return -1;
        }
        if (arr[index] == target) {
            return index;
        } else {
            return linearIndexLast(arr, target, index - 1);
        }
    }

    // return all index
    static void linearAllIndex(int[] arr, int target, int index) {
        if (index == arr.length) {
            return;
        }
        if (arr[index] == target) {
            System.out.println(index);
        }
        linearAllIndex(arr, target, index + 1);
    }

    // return all index using ArrayList
    static ArrayList<Integer> list1=new ArrayList<>();
    static void linearAllIndexA1(int[] arr, int target, int index) {
        if (index == arr.length) {
            return;
        }
        if (arr[index] == target) {
            list1.add(index);
        }
        linearAllIndexA1(arr, target, index + 1);
    }

    // return all index using ArrayList inside function
    static ArrayList<Integer> linearAllIndexA2(int[] arr, int target, int index, ArrayList<Integer> list2) {
        if (index == arr.length) {
            return list2;
        }
        if (arr[index] == target) {
            list2.add(index);
        }
        return linearAllIndexA2(arr, target, index + 1, list2);
    }

    // return all index using ArrayList inside function and not in args
    static ArrayList<Integer> linearAllIndexA3(int[] arr, int target, int index) {
        ArrayList<Integer> list = new ArrayList<>();
        
        if (index == arr.length) {
            return list;
        }
        if (arr[index] == target) {
            list.add(index);
        }
        ArrayList<Integer> ansFromBelowCalls = linearAllIndexA3(arr, target, index + 1);

        list.addAll(ansFromBelowCalls);

        return list;
    }
}
