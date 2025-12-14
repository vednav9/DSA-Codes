public class Practice2 {
    public int lengthOfLongestSubstring(String s) {
        int maxLen=Integer.MIN_VALUE;
        int left=0;
        int[] freq=new int[256];

        for(int right=0;right<s.length();right++){
            char ch=s.charAt(right);
            freq[ch]++;
            while (freq[ch]>1) {
                char leftchar=s.charAt(left);
                freq[leftchar]--;
                left++;
            }
            maxLen=Math.max(maxLen,right-left+1);
        }

        return maxLen;
    }
}