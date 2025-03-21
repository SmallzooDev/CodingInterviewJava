package problems.leetcode.houseRobber;

import java.util.ArrayList;

public class Solution {
    static class House {
        public int robbedMax;
        public int unRobbedMax;

        public House(int robbedMax, int unRobbedMax) {
            this.robbedMax = robbedMax;
            this.unRobbedMax = unRobbedMax;
        }
    }

    public int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }

        if (nums.length == 1) {
            return nums[0];
        }

        ArrayList<House> robbedResult = new ArrayList<>();
        robbedResult.add(new House(nums[0], 0));

        for (int i = 1; i < nums.length; i++) {
            House former = robbedResult.get(i - 1);
            int robCurrent = nums[i] + former.unRobbedMax;
            int skipCurrent = Math.max(former.robbedMax, former.unRobbedMax);

            robbedResult.add(new House(robCurrent, skipCurrent));
        }

        House lastHouse = robbedResult.get(nums.length - 1);
        return Math.max(lastHouse.robbedMax, lastHouse.unRobbedMax);
    }

    public int robSimple(int[] nums) {
        if (nums.length == 0) return 0;
        if (nums.length == 1) return nums[0];

        int[] dp = new int[nums.length];

        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for (int i = 2; i < nums.length; i++) {
            // dp[i-2] + nums[i] -> robCurrent
            // dp[i-1] -> skipCurrent
            dp[i] = Math.max(nums[i] + dp[i - 2], dp[i - 1]);
        }

        return dp[nums.length - 1];
    }
}