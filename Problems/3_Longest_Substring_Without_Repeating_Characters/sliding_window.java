class Solution {
    public int lengthOfLongestSubstring(String s) {
        if (s == null || s.length() == 0) {
            return 0;
        }

        Deque<Character> queue = new ArrayDeque<>();
        Set<Character> set = new HashSet<>();
        int max = Integer.MIN_VALUE;

        for (char ch: s.toCharArray()) {
            // if duplicated, remove until unique
            while(!set.add(ch)) {
                set.remove(queue.pollFirst());
            }

            queue.offerLast(ch);
            set.add(ch);

            max = Math.max(max, queue.size());

        }

        return max;
    }
}
