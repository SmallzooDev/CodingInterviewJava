package problems.baekjoon.p7576;

import java.io.*;
import java.util.*;

public class Main {
    private static final int[] dy = {1, 0, -1, 0};
    private static final int[] dx = {0, 1, 0, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        int[][] box = new int[y][x];
        Queue<int[]> queue = new LinkedList<>();
        int unripeTomatoes = 0;

        for (int i = 0; i < y; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < x; j++) {
                box[i][j] = Integer.parseInt(st.nextToken());

                if (box[i][j] == 1) {
                    queue.offer(new int[]{i, j});
                }
                else if (box[i][j] == 0) {
                    unripeTomatoes++;
                }
            }
        }
        br.close();

        if (unripeTomatoes == 0) {
            System.out.println(0);
            return;
        }

        int days = bfs(box, queue, y, x, unripeTomatoes);
        System.out.println(days);
    }

    private static int bfs(int[][] box, Queue<int[]> queue, int y, int x, int unripeTomatoes) {
        int days = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                int[] current = queue.poll();
                int cy = current[0];
                int cx = current[1];

                for (int d = 0; d < 4; d++) {
                    int ny = cy + dy[d];
                    int nx = cx + dx[d];

                    if (ny >= 0 && ny < y && nx >= 0 && nx < x && box[ny][nx] == 0) {
                        box[ny][nx] = 1;
                        queue.offer(new int[]{ny, nx});
                        unripeTomatoes--;
                    }
                }
            }

            if (!queue.isEmpty()) {
                days++;
            }
        }

        return (unripeTomatoes == 0) ? days : -1;
    }
}