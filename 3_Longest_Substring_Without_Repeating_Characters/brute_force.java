// Time Limit Exceeded

class Solution {
    public int lengthOfLongestSubstring(String s) {
        int length = s.length();
        // define return value
        int ans = 0;

        // business logic
        for (int i = 0; i < length; i++) {
            for (int j = 0; j < length; j++) {
                if (isUnique(s, i, j)) {
                    ans = Math.max(ans, j - i + 1);
                } else {
                    break;
                }
            }
        }

        // return value
        return ans;
    }

    private boolean isUnique(String s, int start, int end) {
        Set<Character> set = new HashSet<>();
        for (int i = start; i < end + 1; i++) {
            Character ch = s.charAt(i);
            if (set.contains(ch)) {
                return false;
            }
            set.add(ch);
        }
        return true;
    }
}
