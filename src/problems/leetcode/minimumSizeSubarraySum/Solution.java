package problems.leetcode.minimumSizeSubarraySum;

import javax.management.InstanceNotFoundException;
import java.util.Arrays;

public class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0, sum = 0, minLength = Integer.MAX_VALUE;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];

            while (sum >= target) {
                minLength = Math.min(minLength, right - left + 1);
                sum -= nums[left];
                left ++;
            }
        }

        return (minLength == Integer.MAX_VALUE) ? 0 : minLength;

    }

    public int minSubArrayLen2(int target, int[] nums) {
        int n = nums.length;
        int[] prefixSum = new int[n + 1];

        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }

        int minLength = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            int newTarget = target + prefixSum[i];
            int bound = Arrays.binarySearch(prefixSum, newTarget);
            if (bound < 0) bound = -bound - 1;

            if (bound <= n) {
                minLength = Math.min(minLength, bound -1);
            }
        }

        return (minLength == Integer.MAX_VALUE) ? 0 : minLength;
    }

}
