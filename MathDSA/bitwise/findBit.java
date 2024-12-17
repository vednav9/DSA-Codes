package MathDSA.bitwise;

class findBit {
    public static void main(String[] args) {
        int a =214;
        System.out.println(Integer.toBinaryString(find(a)));
    }

    static int find(int n){
        return n&(1<<(5-1));
    }
}
