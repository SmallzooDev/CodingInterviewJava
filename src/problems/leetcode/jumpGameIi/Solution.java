package problems.leetcode.jumpGameIi;

/**
 * [2,3,1,1,4]
 * [2,3,0,1,4]
 */
public class Solution {
    public int jump(int[] nums) {
        if (nums.length <= 1) return 0;

        int jumps = 0;
        int currentMax = 0;
        int nextMax = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            nextMax = Math.max(nextMax, i + nums[i]);
            if (i == currentMax) {
                jumps++;
                currentMax = nextMax;
                if (currentMax >= nums.length - 1) {
                    return jumps;
                }
            }
        }

        return jumps;
    }
}
