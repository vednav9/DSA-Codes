package COMMON_PATTERNS.Sliding_Window;

public class AtMostKDistinct {
    // Longest substring with at most K distinct characters
    int longestSubstringAtMostKDistinct(String s, int k) {
        int left = 0, maxLen = 0;
        int[] freq = new int[256];
        int distinct = 0;

        for (int right = 0; right < s.length(); right++) {
            char ch = s.charAt(right);
            if (freq[ch] == 0)
                distinct++;
            freq[ch]++;

            // shrink while more than k distinct
            while (distinct > k) {
                char leftChar = s.charAt(left);
                freq[leftChar]--;
                if (freq[leftChar] == 0)
                    distinct--;
                left++;
            }

            // window [left..right] has at most k distinct
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }

    // Exactly K Distinct Trick
    // atMost(K) - atMost(K-1)

    int longestSubstringExactlyKDistinct(String s, int k) {
        return atMostKDistinct(s, k) - atMostKDistinct(s, k - 1);
    }

    int atMostKDistinct(String s, int k) {
        int n = s.length();
        int left = 0, maxLen = 0;
        int[] freq = new int[256];
        int distinct = 0;

        for (int right = 0; right < n; right++) {
            char ch = s.charAt(right);
            if (freq[ch] == 0)
                distinct++;
            freq[ch]++;

            while (distinct > k) {
                char leftChar = s.charAt(left);
                freq[leftChar]--;
                if (freq[leftChar] == 0)
                    distinct--;
                left++;
            }
            maxLen = Math.max(maxLen, right - left + 1);
        }
        return maxLen;
    }
}