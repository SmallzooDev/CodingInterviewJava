package problems.leetcode.bestTimeToBuyAndSellStock;

public class Solution {
    public int maxProfit(int[] prices) {
        if (prices.length == 1) return 0;

        int maxProfit = 0;
        int left = 0;
        int right = 1;

        while (right < prices.length) {
            if (prices[left] < prices[right]) {
                int currentProfit = prices[right] - prices[left];
                maxProfit = Math.max(maxProfit, currentProfit);
            } else {
                left = right;
            }
            right++;
        }

        return maxProfit;
    }
}
