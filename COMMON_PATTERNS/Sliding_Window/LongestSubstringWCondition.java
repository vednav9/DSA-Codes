package COMMON_PATTERNS.Sliding_Window;

public class LongestSubstringWCondition {
    int lengthOfLongestSubstring(String s) {
        int n = s.length();
        int left = 0, maxLen = 0;

        int[] freq = new int[256]; // frequency of chars

        for (int right = 0; right < n; right++) {
            char ch = s.charAt(right);
            freq[ch]++;

            // if we have duplicate of current char, shrink
            while (freq[ch] > 1) {
                char leftChar = s.charAt(left);
                freq[leftChar]--;
                left++;
            }

            // now window [left..right] has all unique chars
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}