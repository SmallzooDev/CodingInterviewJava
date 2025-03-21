package problems.leetcode.climbingStairs;

public class Solution {
    public int climbStairs(int n) {
        if (n == 1) return 1;
        int[] dp = new int[n + 1];
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i < n + 1; i++) {
            int curr = dp[i - 1] + dp[i - 2];
            dp[i] = curr;
        }

        return dp[n];
    }
}
