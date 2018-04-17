class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        int start = 0;
        int end = 0;
        // Array map to remove duplicates
        int[] map = new int[128];
        int max = Integer.MIN_VALUE;
        char[] chS = s.toCharArray();

        while (end < chS.length) {
            // continuously remove elements in map and moving start pointer
            //pollFirst --> start++
            while (map[chS[end]] > 0) {
                map[chS[start++]]--;
            }
            // offerLast --> end++
            map[chS[end++]]++;
            max = Math.max(max, end - start);
        }

        return max;
    }
}
