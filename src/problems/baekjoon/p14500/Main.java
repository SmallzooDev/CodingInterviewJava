package problems.baekjoon.p14500;

import java.io.*;
import java.util.*;


public class Main {
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};

    static int N, M;
    static int[][] paper;
    static boolean[][] visited;
    static int maxSum = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        paper = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                paper[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                visited[i][j] = true;
                dfs(i, j, 1, paper[i][j]);
                visited[i][j] = false;

                checkTShape(i, j);
            }
        }

        System.out.println(maxSum);
    }

    private static void dfs(int x, int y, int depth, int sum) {
        if (depth == 4) {
            maxSum = Math.max(maxSum, sum);
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= N || ny < 0 || ny >= M || visited[nx][ny]) {
                continue;
            }

            visited[nx][ny] = true;
            dfs(nx, ny, depth + 1, sum + paper[nx][ny]);
            visited[nx][ny] = false;
        }
    }

    private static void checkTShape(int x, int y) {
        if (x + 1 < N && y + 2 < M) {
            // 'ㅗ' 모양
            int sum = paper[x][y] + paper[x][y+1] + paper[x][y+2] + paper[x+1][y+1];
            maxSum = Math.max(maxSum, sum);
        }

        if (x + 2 < N && y + 1 < M) {
            // 'ㅏ' 모양
            int sum = paper[x][y] + paper[x+1][y] + paper[x+2][y] + paper[x+1][y+1];
            maxSum = Math.max(maxSum, sum);
        }

        if (x + 1 < N && y - 1 >= 0 && y + 1 < M) {
            // 'ㅜ' 모양
            int sum = paper[x][y] + paper[x+1][y-1] + paper[x+1][y] + paper[x+1][y+1];
            maxSum = Math.max(maxSum, sum);
        }

        if (x + 2 < N && y - 1 >= 0) {
            // 'ㅓ' 모양
            int sum = paper[x][y] + paper[x+1][y] + paper[x+2][y] + paper[x+1][y-1];
            maxSum = Math.max(maxSum, sum);
        }

        if (x - 1 >= 0 && y + 2 < M) {
            // 반전된 'ㅗ' 모양
            int sum = paper[x][y] + paper[x][y+1] + paper[x][y+2] + paper[x-1][y+1];
            maxSum = Math.max(maxSum, sum);
        }

        if (x + 2 < N && y + 1 < M) {
            // 반전된 'ㅏ' 모양 (이미 위에서 처리)
            int sum = paper[x][y] + paper[x+1][y] + paper[x+2][y] + paper[x+1][y+1];
            maxSum = Math.max(maxSum, sum);
        }

        if (x - 1 >= 0 && y - 1 >= 0 && y + 1 < M) {
            // 반전된 'ㅜ' 모양
            int sum = paper[x][y] + paper[x-1][y-1] + paper[x-1][y] + paper[x-1][y+1];
            maxSum = Math.max(maxSum, sum);
        }

        if (x + 2 < N && y + 1 < M) {
            // 반전된 'ㅓ' 모양 (이미 위에서 처리)
            int sum = paper[x][y] + paper[x+1][y] + paper[x+2][y] + paper[x+1][y+1];
            maxSum = Math.max(maxSum, sum);
        }
    }
}
