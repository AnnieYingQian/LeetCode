class Solution {
    public boolean isPalindrome(int x) {
        if (x < 0)
            return false;
        if (x == 0)
            return true;
        int res = 0;
        int number = x;
        while (number != 0) {
            int unit = number % 10;
            res = res * 10 + unit;
            number = number / 10;
        }

        if (res == x) {
            return true;
        } else {
            return false;
        }
    }
}
