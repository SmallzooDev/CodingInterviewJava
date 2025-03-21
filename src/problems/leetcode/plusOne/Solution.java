package problems.leetcode.plusOne;

public class Solution {
    public int[] plusOne(int[] digits) {
        if (digits[digits.length - 1] + 1 < 10) {
            digits[digits.length - 1] = digits[digits.length - 1] + 1;
            return digits;
        }

        int carry = 1;
        digits[digits.length - 1] = 0;

        for (int i = digits.length - 2; i >= 0; i--) {
            if (carry < 1) break;

            int sum = digits[i] + carry;
            if (sum > 9) {
                digits[i] = 0;
                carry = 1;
            } else {
                digits[i] = sum;
                carry = 0;
            }
        }

        if (carry > 0) {
            int[] result = new int[digits.length + 1];
            result[0] = 1;
            return result;
        }

        return digits;
    }
}
