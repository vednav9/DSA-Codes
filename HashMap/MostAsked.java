package HashMap;
import java.util.*;

class MostAsked {
    public static void main(String[] args) {
        int[] nums={1,3,2,5,1,3,1,5,1};
        majorityElement(nums);

        int[] arr1={7,3,9};
        int[] arr2={6,3,9,2,9,4};
        int u = union(arr1, arr2);
        System.out.println(u);
        
        System.out.println();

        System.out.println(intersection(arr1, arr2));

        HashMap<String, String> tickets=new HashMap<>();
        tickets.put("Chennai", "Bengaluru");
        tickets.put("Mumbai", "Delhi");
        tickets.put("Goa", "Chennai");
        tickets.put("Delhi", "Goa");
        String start=getStart(tickets);
        while (tickets.containsKey(start)) {
            System.out.println(start);
            start=tickets.get(start);
        }
        System.out.println(start);
    }
    
    // Majority Element more than [n/3] times
    private static void majorityElement(int[] nums){
        HashMap<Integer, Integer> map=new HashMap<>();
        int n=nums.length;
        for(int i=0;i<n;i++){
            if (map.containsKey(nums[i])) {
                map.put(nums[i], map.get(nums[i])+1);
            } else{
                map.put(nums[i], 1);
            }
        }

        for (int key : map.keySet()) {
            if (map.get(key) >3) {
                System.out.println(key);
            }
        }
    }

    // Union of 2 arrays
    private static int union(int[] arr1, int[] arr2){
        HashSet<Integer> set=new HashSet<>();
        for (int i=0;i<arr1.length;i++) {
            set.add(arr1[i]);
        }
        for (int i=0;i<arr2.length;i++) {
            set.add(arr2[i]);
        }
        return set.size();
    }

    // Intersection
    private static int intersection(int[] arr1, int[] arr2){
        HashSet<Integer> set=new HashSet<>();
        int count=0;
        for (int i=0;i<arr1.length;i++) {
            set.add(arr1[i]);
        }
        for (int i=0;i<arr2.length;i++) {
            if (set.contains(arr2[i])) {
                count++;
                set.remove(arr2[i]);
            }
        }
        return count;        
    }

    // Find Itinerary(Path) from Tickets - City Travel Path - (Topological Sort)
    static String getStart(HashMap<String,String> tick){
        HashMap<String,String> revMap=new HashMap<>();
        for (String key : tick.keySet()) {
            // key -> key; val -> tick.get(key)
            revMap.put(tick.get(key), key);
        }
        for (String key : tick.keySet()) {
            if (revMap.containsKey(key)) {
                return key;
            }
        }

        return null;
    }

    // Subarray sum equal to K
    static void subArray(int k){
    }
}