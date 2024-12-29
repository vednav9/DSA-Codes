package Recursion.string;

class stream {
    public static void main(String[] args) {
        skip("","baccad");

        System.out.println(skip("baccad"));

        System.out.println(skipApple("bacapplcdah"));

        System.out.println(skipAppNotApple("bacapplcdah"));
    }

    // 1st way

    static void skip(String p, String up){ // p - processed, up - unprocessed
        if(up.isEmpty()){
            System.out.println(p);
            return;
        }

        char ch=up.charAt(0);
        if (ch=='a') {
            skip(p, up.substring(1));
        }
        else{
            skip(p+ch, up.substring(1));
        }

    }

    static String skip(String up) {
        if (up.isEmpty()) {
            return "";
        }

        char ch = up.charAt(0);

        if (ch == 'a') {
            return skip(up.substring(1));
        } else {
            return ch + skip(up.substring(1));
        }
    }

    // 2nd way

    static String skipApple(String up){ // p - processed, up - unprocessed
        if(up.isEmpty()){
            return "";
        }

        if (up.startsWith("apple")) {
            return skipApple(up.substring(5));// skip letter, apple = 5 letter
        }
        else{
            return up.charAt(0) + skipApple(up.substring(1));
        }

    }

    static String skipAppNotApple(String up){ // p - processed, up - unprocessed
        if(up.isEmpty()){
            return "";
        }

        if (up.startsWith("app") && !up.startsWith("apple")) {
            return skipApple(up.substring(3));// skip letter, apple = 5 letter
        }
        else{
            return up.charAt(0) + skipApple(up.substring(1));
        }

    }
}
