package MathDSA.bitwise;

class resetBit {
    public static void main(String[] args) {
        int a =214;
        System.out.println(Integer.toBinaryString(reset(a)));
    }

    static int reset(int n){
        return n&(~(1<<(5-1)));
    }
}
