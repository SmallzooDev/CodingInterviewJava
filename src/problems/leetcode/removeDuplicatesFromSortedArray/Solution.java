package problems.leetcode.removeDuplicatesFromSortedArray;

public class Solution {
    public int removeDuplicates(int[] nums) {
        int writeIndex = 1;
        int current = nums[0];
        for (int readIndex = 1; readIndex < nums.length; readIndex++) {
            if (nums[readIndex] != current) {
                current = nums[readIndex];
                nums[writeIndex] = nums[readIndex];
                writeIndex++;
            }
        }
        return writeIndex;
    }
}
