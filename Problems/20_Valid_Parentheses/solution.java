class Solution {
    public boolean isValid(String s) {
        // special case
        if (s == null || s.length() == 0)
            return true;

        // business logic
        Deque<Character> stack = new ArrayDeque<>();
        for (char ch: s.toCharArray()) {
            if (ch == '(' || ch == '[' || ch == '{') {
                stack.offerLast(ch);
            } else {
                if (stack.isEmpty())
                    return false;
                char left = stack.pollLast();
                if (ch == ')' && left == '(' || ch == ']' && left == '[' || ch == '}' && left == '{') {
                    continue;
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();

    }
}
