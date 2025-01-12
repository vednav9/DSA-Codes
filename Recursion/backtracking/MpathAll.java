package Recursion.backtracking;

import java.util.Arrays;

public class MpathAll {
    public static void main(String[] args) {
        boolean[][] b = {
                { true, true, true },
                { true, true, true },
                { true, true, true }
        };
        //allPath("", b, 0, 0);
        int[][] path = new int[b.length][b[0].length];
        allPathPrint("", b, 0, 0, path, 1);
    }

    static void allPath(String p, boolean[][] m, int r, int c) {
        if (r == m.length - 1 && c == m[0].length - 1) {
            System.out.println(p);
            return;
        }
        if (!m[r][c]) {
            return;
        }

        // I am considering this block in my path
        m[r][c] = false;
        if (r < m.length - 1) {
            allPath(p + 'D', m, r + 1, c);
        }
        if (c < m[0].length - 1) {
            allPath(p + 'R', m, r, c + 1);
        }
        if (r > 0) {
            allPath(p + 'U', m, r - 1, c);
        }
        if (c > 0) {
            allPath(p + 'L', m, r, c - 1);
        }
        // this line is where the function will be over
        // so before the function gets removed, also remove the changes that were made
        // by that function
        m[r][c] = true;
    }

    static void allPathPrint(String p, boolean[][] m, int r, int c, int[][] path, int step) {
        if (r == m.length - 1 && c == m[0].length - 1) {
            path[r][c]=step;
            for (int[] arr : path) {
                System.out.println(Arrays.toString(arr));
            }
            System.out.println(p);
            System.out.println();
            return;
        }
        if (!m[r][c]) {
            return;
        }

        // I am considering this block in my path
        m[r][c] = false;
        path[r][c]=step;
        if (r < m.length - 1) {
            allPathPrint(p + 'D', m, r + 1, c, path, step+1);
        }
        if (c < m[0].length - 1) {
            allPathPrint(p + 'R', m, r, c + 1, path, step+1);
        }
        if (r > 0) {
            allPathPrint(p + 'U', m, r - 1, c, path, step+1);
        }
        if (c > 0) {
            allPathPrint(p + 'L', m, r, c - 1, path, step+1);
        }
        // this line is where the function will be over
        // so before the function gets removed, also remove the changes that were made
        // by that function
        m[r][c] = true;
        path[r][c]=0;
    }
}