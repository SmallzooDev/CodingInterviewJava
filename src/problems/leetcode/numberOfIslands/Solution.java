package problems.leetcode.numberOfIslands;

import java.util.Arrays;

public class Solution {
    private final int[] dy = {1, 0, -1, 0};
    private final int[] dx = {0, 1, 0, -1};
    private int yLen;
    private int xLen;

    public int numIslands(char[][] grid) {
        int result = 0;
        yLen = grid.length;
        xLen = grid[0].length;

        for (int i = 0; i < yLen; i++) {
            for (int j = 0; j < xLen; j++) {
                if (grid[i][j] == '1') {
                    result++;
                    dfs(grid, i, j);
                }
            }
        }
        return result;
    }

    private void dfs(char[][] grid, int y, int x) {
        if (y < 0 || x < 0 || y >= yLen || x >= xLen || grid[y][x] == '0') {
            return;
        }

        // 방문 표시
        grid[y][x] = '0';

        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];
            dfs(grid, ny, nx);
        }
    }
}
