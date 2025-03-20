package problems.baekjoon.p2109;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] lectures = new int[n][2];

        int maxDay = 0;

        for (int i = 0; i < n; i++) {
            String[] tokens = br.readLine().split(" ");
            int pay = Integer.parseInt(tokens[0]);
            int day = Integer.parseInt(tokens[1]);

            lectures[i][0] = pay;
            lectures[i][1] = day;
            maxDay = Math.max(maxDay, day);
        }

        Arrays.sort(lectures, (a, b) -> b[0] - a[0]);

        boolean[] visited = new boolean[maxDay + 1];
        int result = 0;

        for (int i = 0; i < n; i++) {
            int pay = lectures[i][0];
            int day = lectures[i][1];

            for (int j = day; j >= 1; j--) {
                if (!visited[j]) {
                    visited[j] = true;
                    result += pay;
                    break;
                }
            }
        }

        System.out.println(result);
    }
}
