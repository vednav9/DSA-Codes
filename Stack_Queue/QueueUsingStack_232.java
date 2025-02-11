// Google

import java.util.Stack;

public class QueueUsingStack_232 {
    private Stack<Integer> in_stack;
    private Stack<Integer> out_stack;

    public QueueUsingStack_232() {
        in_stack = new Stack<>();
        out_stack = new Stack<>();
    }

    private void transfer() {
        while (!in_stack.isEmpty()) {
            out_stack.push(in_stack.pop());
        }
    }

    public void push(int x) {
        in_stack.push(x);
    }

    public int pop() {
        if (out_stack.isEmpty()) {
            transfer();
        }
        return out_stack.pop();
    }

    public int peek() {
        if (out_stack.isEmpty()) {
            transfer();
        }
        return out_stack.peek();
    }

    public boolean empty() {
        return in_stack.isEmpty() && out_stack.isEmpty();
    }

    public static void main(String[] args) {
        // QueueUsingStack obj = new QueueUsingStack();
        // obj.push(1);
        // int param_2 = obj.pop();
        // int param_3 = obj.peek();
        // boolean param_4 = obj.empty();
    }
}