package COMMON_PATTERNS.Sliding_Window;
import java.util.*;

public class FindAllAnagrams {

    List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s.length() < p.length())
            return result;
        
        // Assuming a-z
        int[] freqP = new int[26];
        int[] freqS = new int[26];

        // Step 1: Count frequency of characters in p and first window of s
        // p = 3, so window size = 3x
        int m = p.length();
        for (int i = 0; i < m; i++) {
            freqP[p.charAt(i) - 'a']++;
            freqS[s.charAt(i) - 'a']++;
        }

        if (Arrays.equals(freqP, freqS)) {
            result.add(0);
        }

        for (int right = m; right < s.length(); right++) {
            // add new char
            freqS[s.charAt(right) - 'a']++;

            // remove old char (left of window)
            int left = right - m;
            freqS[s.charAt(left) - 'a']--;

            if (Arrays.equals(freqP, freqS)) {
                result.add(left + 1);
            }
        }

        return result;
    }

    // ---------------------------
    // MAIN METHOD WITH INPUT
    // ---------------------------
    public static void main(String[] args) {
        FindAllAnagrams obj = new FindAllAnagrams();

        String s = "cbaebabacd";    // predefined input string
        String p = "abc";           // predefined pattern

        System.out.println("Input: s = " + s + ", p = " + p);

        List<Integer> result = obj.findAnagrams(s, p);

        System.out.println("Anagram indices: " + result);
        // "cba" at index 0 is an anagram of "abc"
        // "bac" at index 6 is also an anagram of "abc"
    }
}