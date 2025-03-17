package problems.leetcode.numberOfIslands;

import java.util.Arrays;

public class Solution {
    private final int[] dy = {1, 0, -1, 0};
    private final int[] dx = {0, 1, 0, -1};
    private int[][] visited;
    private int yLen;
    private int xLen;

    public int numIslands(char[][] grid) {
        int result = 0;
        yLen = grid.length;
        xLen = grid[0].length;
        visited = new int[yLen][xLen];

        for (int[] row : visited) {
            Arrays.fill(row, 0);
        }

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

    public void dfs(char[][] grid, int y, int x) {
        if (grid[y][x] == '0') {
            return;
        }

        grid[y][x] = '0';
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (ny < 0 || nx < 0 || ny >= yLen || nx >= xLen || visited[ny][nx] == 1) {
                continue;
            }

            visited[ny][nx] = 1;
            dfs(grid, ny, nx);
        }
    }
}
