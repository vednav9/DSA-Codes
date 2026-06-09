package GP;
import java.util.*;

public class HuffmanCoding {

    static int huffmanCost(int[] freq){

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int f : freq)
            pq.offer(f);

        int cost = 0;

        while(pq.size() > 1){

            int a = pq.poll();
            int b = pq.poll();

            int merged = a + b;

            cost += merged;

            pq.offer(merged);
        }

        return cost;
    }
}