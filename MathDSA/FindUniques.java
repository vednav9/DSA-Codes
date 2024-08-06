package MathDSA;

public class FindUniques {
    public static void main(String[] args) {
        int[] arr={2,3,3,4,6,4,2};
        System.out.println(ans(arr));
    }

    static int ans(int[] arr){
        int unique=0;

        for(int n:arr){
            unique^=n;//XOR since 2^2^3^4^4 : order does not matter
        }             //like 2x2x3x5x2

        return unique;
    }
}
 