class Solution {
    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length ==0)
            return "";
        int start = 0;
        int end = strs[0].length() - 1;
        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (isCommonPrefix(strs, mid)) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return strs[0].substring(0, start + (end - start) / 2);

    }

    private boolean isCommonPrefix(String[] strs, int index) {
         String str = strs[0].substring(0, index + 1);
         for (int i = 1; i < strs.length; i++) {
            if (!strs[i].startsWith(str))
                return false;
         }
         return true;
    }
}
