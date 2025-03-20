package problems.leetcode.majorityElement;

import java.util.HashMap;

public class Solution {
    public int majorityElement(int[] nums) {
        HashMap<Integer, Integer> countMap = new HashMap<>();
        for (int num : nums) {
            int count = countMap.getOrDefault(num, 0) + 1;
            countMap.put(num, count);

            if (count > nums.length / 2) {
                return num;
            }
        }

        return -1;
    }
}
