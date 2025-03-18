package problems.leetcode.removeDuplicatesFromSortedArrayIi;

public class Solution {
    public int removeDuplicates(int[] nums) {
        if (nums.length <= 2) {
            return nums.length;
        }

        int writeIndex = 2;

        for (int readIndex = 2; readIndex < nums.length; readIndex++) {
            if (nums[readIndex] != nums[writeIndex - 2]) {
                nums[writeIndex] = nums[readIndex];
                writeIndex++;
            }
        }

        return writeIndex;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] hello = {0,0,1,1,1,1,2,3,3};
        solution.removeDuplicates(hello);
        for (int i : hello) {
            System.out.println(i);
        }
    }
}
