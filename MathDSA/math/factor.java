package MathDSA.math;

import java.util.ArrayList;

public class factor {
    public static void main(String[] args) {
        factors1(20);
        factors2(20);
        factors3(20);
    }

    // O(n)
    static void factors1(int n) {
        System.out.println("O(n)");
        for (int i = 1; i <= n; i++) {
            if (n % i == 0) {
                System.out.print(i + " ");
            }
        }
    }

    // O(sqrt(n))
    static void factors2(int n) {
        System.out.println("O(sqrt(n))");
        for (int i = 1; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                if (n/i == i) {
                    System.out.print(i + " ");
                }else {
                    System.out.print(i + " " + n/i + " ");
                }
            }
        }
    }

    // both time and space with be O(sqrt(n))
    static void factors3(int n) {
        System.out.println("O(sqrt(n)) with both complexity");
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= Math.sqrt(n); i++) {
            if (n % i == 0) {
                if (n/i == i) {
                    System.out.print(i + " ");
                }else {
                    System.out.print(i + " ");
                    list.add(n/i);
                }
            }
        }
        for (int i = list.size() - 1; i >= 0; i--) {
            System.out.print(list.get(i) + " ");
        }
    }
}
