package problems.leetcode.isomorphicStrings;

public class Solution {
    public boolean isIsomorphic(String s, String t) {
        if (s.length() != t.length()) {
            return false;
        }

        char[] sToT = new char[256];
        char[] tToS = new char[256];

        for (int i = 0; i < s.length(); i++) {
            char charS = s.charAt(i);
            char charT = t.charAt(i);

            if (sToT[charS] == 0) {
                if (tToS[charT] != 0) {
                    return false;
                }

                sToT[charS] = charT;
                tToS[charT] = charS;
            }
            else if (sToT[charS] != charT) {
                return false;
            }
        }

        return true;
    }

}
