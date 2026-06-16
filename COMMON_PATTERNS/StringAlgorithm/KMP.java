package StringAlgorithm;

public class KMP {
    static int[] buildLPS(String p) {
        int n = p.length();
        int[] lps = new int[n];
        int len = 0;

        for (int i = 1; i < n; i++) {
            while (len > 0 && p.charAt(i) != p.charAt(len)) {
                len = lps[len - 1];
            }
            if (p.charAt(i) == p.charAt(len)) {
                len++;
            }
            lps[i] = len;
        }
        return lps;
    }

    static int kmp(String text, String pattern) {
        int[] lps = buildLPS(pattern);

        int i = 0, j = 0;

        while (i < text.length()) {

            if (text.charAt(i) == pattern.charAt(j)) {
                i++;
                j++;
            }

            if (j == pattern.length())
                return i - j;

            else if (i < text.length() &&
                    text.charAt(i) != pattern.charAt(j)) {
                if (j > 0)
                    j = lps[j - 1];
                else
                    i++;
            }
        }
        return -1;
    }
}