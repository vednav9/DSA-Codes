package Stack_Code;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class Inbuild {
    public static void main(String[] args) {
        Stack<Integer> stack =new Stack<>();
        //System.out.println(stack.push(1));
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);

        System.out.println(stack.pop());

        System.out.println();

        Queue<Integer> queue=new LinkedList<>();
        queue.add(1);
        queue.add(2);
        queue.add(3);
        queue.add(4);
        queue.add(5);

        System.out.println(queue.peek());

        // Dequeue  -  (Used in trees)

        Deque<Integer> deque=new ArrayDeque<>();
        deque.add(1);
        deque.addLast(1);


    }
}
