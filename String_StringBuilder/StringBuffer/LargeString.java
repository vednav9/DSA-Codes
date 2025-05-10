package String_StringBuilder.StringBuffer;

public class LargeString {
    public static void main(String[] args) {
        StringBuffer buffer=new StringBuffer();
        buffer.append("Hello");

        String str=buffer.toString();
        System.out.println(str);
    }
}