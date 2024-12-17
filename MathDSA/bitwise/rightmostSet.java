package MathDSA.bitwise;

class rightmostSet {
    public static void main(String[] args) {
        int a =214;
        System.out.println(Integer.toBinaryString(find(a)));
    }

    static int find(int n){
        return n&(-n);
    }
}
// 1101 0110
// 0010 1001 : 1's
// 0010 1010 : 2's
// 0000 0010 : answer