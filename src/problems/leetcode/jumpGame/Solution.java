package problems.leetcode.jumpGame;

public class Solution {
    public boolean canJump(int[] nums) {
        if (nums.length <= 1) return true;
        int[] visited = new int[nums.length];
        return dfs(nums, visited, 0);
    }

    public boolean canJumpGreedy(int[] nums) {
        int maxReach = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i > maxReach) return false;
            maxReach = Math.max(maxReach, i + nums[i]);
            if (maxReach >= nums.length - 1) return true;
        }

        return false;
    }

    private boolean dfs(int[] nums, int[] visited, int currentIndex) {
        if (currentIndex >= nums.length - 1) {
            return true;
        }

        visited[currentIndex] = 1;

        for (int i = 1; i <= nums[currentIndex]; i++) {
            int nextIndex = currentIndex + i;
            if (nextIndex >= nums.length) break;
            if (visited[nextIndex] == 1) continue;
            if (dfs(nums, visited, nextIndex)) {
                return true;
            }
        }

        return false;
    }
}
