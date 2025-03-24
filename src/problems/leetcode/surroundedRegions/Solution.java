package problems.leetcode.surroundedRegions;

import java.util.Arrays;

public class Solution {
    private static int[] dy = {1, 0, -1, 0};
    private static int[] dx = {0, 1, 0, -1};

    public void solve(char[][] board) {
        int[][] visited = new int[board.length][board[0].length];
        for (int[] ints : visited) {
            Arrays.fill(ints, 0);
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                if (i == 0 || i == board.length - 1 || j == 0 || j == board[0].length - 1) {
                    if (board[i][j] == 'O') {
                        masking(board, visited, i, j);
                    }
                }
            }
        }

        for (int i = 1; i < board.length - 1; i++) {
            for (int j = 1; j < board[0].length - 1; j++) {
                if (board[i][j] == 'O' && visited[i][j] == 0) {
                    board[i][j] = 'X';
                }
            }
        }

    }

    void masking(char[][] board, int[][] visited, int y, int x) {
        if (visited[y][x] != 0) return;
        visited[y][x] = 1;
        for (int i = 0; i < 4; i++) {
            int ny = y + dy[i];
            int nx = x + dx[i];

            if (ny < 0 || nx < 0 || ny >= board.length || nx >= board[0].length) {
                continue;
            }

            if (visited[ny][nx] != 1 && board[ny][nx] == 'O') {
                masking(board, visited, ny, nx);
            }
        }
    }
}
