import java.util.LinkedList;

class InbuiltLL {
    public static void main(String[] args) {
        LinkedList<String> list=new LinkedList<String>();

        list.addFirst("a");
        list.addFirst("is");
        System.out.println(list);

        list.addLast("list");
        System.out.println(list);

        list.addFirst("this");
        System.out.println(list);

        list.removeFirst();

        // list.deleteFirst();
        // System.out.println(list);

        // list.deleteLast();
        // System.out.println(list);

        System.out.println(list.size());
    }
}