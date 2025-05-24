package HashMap;
import java.util.*;
public class HashMapCodee {
    public static void main(String[] args) {
        HashMap<String,Integer> map=new HashMap<>();

        // Insertion
        map.put("USA", 130);
        map.put("London", 129);
        map.put("India", 120);

        System.out.println(map);

        // Search
        if (map.containsKey("USA")) {
            System.out.println("USA");
        }
        if (map.containsValue(130)) {
            System.out.println(130);
        }

        // Get
        System.out.println(map.get("USA"));
        System.out.println(map.get("Japan"));

        int arr[]={12,15,18};
        for(int val:arr){
            System.out.print(val+" ");
        }
        System.out.println();
        System.out.println();

        for (Map.Entry<String,Integer> i : map.entrySet()) {
            System.out.println(i.getKey());
            System.out.println(i.getValue());
        }

        Set<String> keys=map.keySet();
        for (String key : keys) {
            System.out.println(key+" "+map.get(key));
        }
    }
}