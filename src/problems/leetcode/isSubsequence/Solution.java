package problems.leetcode.isSubsequence;

public class Solution {
    public boolean isSubsequence(String s, String t) {
        if (s.isEmpty()) return true;
        if (s.length() > t.length()) return false;

        int sPointer = 0;
        int tPointer = 0;

        while (sPointer < s.length() && tPointer < t.length()) {
            if (s.charAt(sPointer) == t.charAt(tPointer)) {
                sPointer++;
            }
            tPointer++;
        }

        return sPointer == s.length();
    }

    public boolean isSubsequence2(String s, String t) {
        if (s.isEmpty()) return true;
        if (s.length() > t.length()) return false;
        int sIndex = 0;

        for (int tIndex = 0; tIndex < t.length(); tIndex++) {
            if (s.charAt(sIndex) == t.charAt(tIndex)) {
                sIndex++;
                if (sIndex == s.length()) {
                    return true;
                }
            }
        }

        return false;
    }
}
