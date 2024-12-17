package MathDSA.bitwise;

class setBit {
    public static void main(String[] args) {
        int a =214;
        System.out.println(Integer.toBinaryString(set(a)));
    }

    static int set(int n){
        return n|(1<<(4-1));
    }
}
