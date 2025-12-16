package COMMON_PATTERNS.MergeIntervals;

import java.util.*;

public class Main {
    public static void main(String[] args) {

        int[][] intervals = {
                { 1, 3 },
                { 2, 6 },
                { 8, 10 },
                { 15, 18 }
        };

        Arrays.sort(intervals, (a, b) -> Integer.compare(a[0], b[0]));
        for (int[] is : intervals) {
            for (int i : is) {
                System.out.println(i);
            }
        }
    }

    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a,b)->Integer.compare(a[0], b[0]));
        List<int []> res=new ArrayList<>();
        int[] curr=intervals[0];
        for (int i = 0; i < curr.length; i++) {
            int[] next=intervals[1];

            if(next[0]<=curr[1]){
                curr[1]=Math.max(next[1], curr[1]);
            }
            else{
                res.add(curr);
                curr=next;
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}