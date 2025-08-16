package HashMap;
import java.util.HashSet;
import java.util.Iterator;
public class HashSetCode {
    public static void main(String[] args) {

        // Create
        HashSet<Integer> set =new HashSet<>();

        // Insert
        set.add(1);
        set.add(2);
        set.add(3);
        set.remove(1);
    
        //search - contain
        if(set.contains(1)){
            System.out.println("yes");
        }

        // print
        System.out.println(set);

        // iterator
        Iterator it=set.iterator();
        // hasNext, next

        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }
}