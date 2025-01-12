// NOT BACKTRACKING

package Recursion.backtracking;

import java.util.ArrayList;

public class Mpath {
    public static void main(String[] args) {
        // System.out.println(pathCount(3, 3));
        // pathPrint("",3, 3);
        // System.out.println(pathPrintRet("", 3, 3));
        // System.out.println(pathPrintRetDiagonal("", 3, 3));

        boolean[][] b ={
            {true,true,true},
            {true,false,true},
            {true,true,true}
        };
        pathPrintRestriction("", b, 0, 0);

    }

    static int pathCount(int r, int c){
        if (r==1||c==1) {
            return 1;
        }
        int total=0;
        total+=pathCount(r-1, c);
        total+=pathCount(r, c-1);
        // total+=pathCount(r-1, c-1);  // Diagonal

        return total;
    }

    static void pathPrint(String p,int r, int c){
        if (r==1 && c==1) {
            System.out.println(p);
            return;
        }
        if (r>1) {
            pathPrint(p+'D',r-1, c);
        }
        if (c>1) {
            pathPrint(p+'R',r, c-1);            
        }
    }

    static ArrayList<String> pathPrintRet(String p,int r, int c){
        if (r==1 && c==1) {
            ArrayList<String> list=new ArrayList<>();
            list.add(p);
            return list;
        }
        ArrayList<String> list=new ArrayList<>();
        if (r>1) {
            list.addAll(pathPrintRet(p+'D',r-1, c));
        }
        if (c>1) {
            list.addAll(pathPrintRet(p+'R',r, c-1));
        }
        return list;
    }

    static ArrayList<String> pathPrintRetDiagonal(String p,int r, int c){
        if (r==1 && c==1) {
            ArrayList<String> list=new ArrayList<>();
            list.add(p);
            return list;
        }
        ArrayList<String> list=new ArrayList<>();
        if (r>1 && c>1) {
            list.addAll(pathPrintRetDiagonal(p+'D',r-1, c-1));
        }
        if (r>1) {
            list.addAll(pathPrintRetDiagonal(p+'V',r-1, c));
        }
        if (c>1) {
            list.addAll(pathPrintRetDiagonal(p+'H',r, c-1));
        }
        return list;
    }

    static void pathPrintRestriction(String p,boolean[][] m,int r, int c){
        if (r==m.length-1 && c==m[0].length-1) {
            System.out.println(p);
            return;
        }
        if (!m[r][c]) {
            return;
        }
        if (r<m.length-1) {
            pathPrintRestriction(p+'D',m,r+1, c);
        }
        if (c<m[0].length-1) {
            pathPrintRestriction(p+'R',m,r, c+1);            
        }
    }
}