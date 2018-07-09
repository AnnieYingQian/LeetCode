class Solution {
    public int reverse(int x) {
        // define return value
        int res = 0;
        while (x != 0) {
            // get unit digit
            int unit = x % 10;
            // add each unit to temp
            int temp = res * 10 + unit;
            // if overflow exists, the new result will not equal previous one
            if (temp / 10 != res) {
                return 0;
            }
            res = temp;
            x = x / 10;
        }
        return res;
    }
}
