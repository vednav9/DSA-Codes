package MathDSA;

public class PrimeN {
    public static void main(String[] args) {
        int n=9;
        for(int i=2;i<n;i++){
            if(n%i==0){
                System.out.println("Not Prime");
                break;
            }
            else{
                System.out.println("Prime");
                break;
            }
        }
    }
}
