package problems.leetcode.wordBreak;

import java.util.*;

public class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);

        // dp[i]는 s의 처음부터 i번째 문자까지의 부분 문자열이
        // wordDict의 단어들로 분할 가능한지 여부를 나타냄
        boolean[] dp = new boolean[s.length() + 1];

        // 빈 문자열은 항상 분할 가능 (기본 케이스)
        dp[0] = true;

        for (int end = 1; end <= s.length(); end++) {
            // 현재 위치에서 가능한 모든 시작점 검사
            for (int start = 0; start < end; start++) {
                if (dp[start] && wordSet.contains(s.substring(start, end))) {
                    dp[end] = true;
                    break;
                }
            }
        }

        // 전체 문자열이 분할 가능한지 결과 반환
        return dp[s.length()];
    }

    public boolean wordBreakOptimized(String s, List<String> wordDict) {
        Set<String> wordSet = new HashSet<>(wordDict);
        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        // 최대 단어 길이 계산
        int maxWordLength = 0;
        for (String word : wordDict) {
            maxWordLength = Math.max(maxWordLength, word.length());
        }

        for (int end = 1; end <= s.length(); end++) {
            // 최대 단어 길이를 고려하여 시작점 제한
            for (int start = end - 1; start >= 0 && start >= end - maxWordLength; start--) {
                if (dp[start] && wordSet.contains(s.substring(start, end))) {
                    dp[end] = true;
                    break;
                }
            }
        }

        return dp[s.length()];
    }
}