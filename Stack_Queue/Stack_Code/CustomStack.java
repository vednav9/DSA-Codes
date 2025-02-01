package Stack_Code;
public class CustomStack {
    protected int[] data;
    private static final int DEFAULT_SIZE=5;
    
    int ptr=-1;

    public CustomStack(){
        this(DEFAULT_SIZE);
    }

    public CustomStack(int size){
        this.data=new int[size];
    }

    public boolean push(int item){
        if (isFull()) {
            System.out.println("Stack is Full");
            return false;
        }
        ptr++;
        data[ptr]=item;
        return true;
    }

    public int pop() throws StackException{
        if(isEmpty()){
            throw new StackException("Cannot pop from Empty Stack");
        }
        // int removed=data[ptr];
        // ptr--;
        // return removed;

        return data[ptr--];
    }

    public int peek(){
        if (isEmpty()) {
            System.out.println("Empty");
            return -1;
        }
        return data[ptr];
    }

    public boolean isFull(){
        return ptr==data.length-1; // ptr is at last index
    }

    public boolean isEmpty(){
        return ptr==-1; // ptr is at last index
    }


    public static void main(String[] args) {
        CustomStack stack=new CustomStack();

        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        System.out.println(stack.peek());
        
        try {
            stack.pop();
            System.out.println(stack.peek());
            stack.pop();
            System.out.println(stack.peek());
            stack.push(1);
            stack.push(2);
            stack.push(3);
            stack.push(4);
            System.out.println(stack.peek());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}