// Amazon

package Recursion;

public class recursion_1155 {
    public static void main(String[] args) {
        int n = 30;  // Number of dice
        int k = 30;  // Faces on each die
        int target = 500;  // Target sum
        
        System.out.println(numRollsToTarget(n, k, target));
    }

    static int numRollsToTarget(int n, int k, int target) {
        // int MOD = 1000000007;
        if (target == 0 && n == 0) {
            return 1;  // There is exactly one way to achieve sum 0 with 0 dice.
        }
        // If there are no dice left or target becomes negative, return 0
        if (n == 0 || target < 0) {
            return 0;
        }
        int ways = 0;
        // Try all dice faces (1 to k)
        for (int i = 1; i <= k; i++) {
            // Recurse by reducing the number of dice and the target by the value of current dice face
            ways += numRollsToTarget(n - 1, k, target - i);
            // n=1,k=6,t=6 - way=0
            // n=0,k=6,t=5
            // 0

            // n=1,k=6,t=5
            // n=0,k=6,t=4
            // 0

            // n=1,k=6,t=4
            // n=0,k=6,t=3
            // 0
        }
        return ways;
    }
}
