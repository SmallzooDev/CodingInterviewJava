package problems.leetcode.validPalindrome;

public class Solution {
    public boolean isPalindrome(String s) {
        StringBuilder result = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (Character.isLetterOrDigit(c)) result.append(Character.toLowerCase(c));
        }

        return result.toString().contentEquals(result.reverse());
    }
}
