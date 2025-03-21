package problems.baekjoon.p2629;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        ArrayList<Integer> weights = Arrays.stream(br.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toCollection(ArrayList::new));

        int m = Integer.parseInt(br.readLine());
        int[] questions = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        br.close();

        // DP 배열 초기화: dp[i][j] = i번째 추까지 사용해서 j 무게를 만들 수 있는지 여부
        boolean[][] dp = new boolean[n + 1][40001]; // 추의 최대 무게가 500이므로 최대 측정 가능 무게는 40000
        measureWeight(weights, dp, n);

        StringBuilder result = new StringBuilder();
        for (int question : questions) {
            if (question <= 40000 && dp[n][question]) {
                result.append("Y ");
            } else {
                result.append("N ");
            }
        }

        System.out.println(result.toString().trim());
    }

    public static void measureWeight(ArrayList<Integer> weights, boolean[][] dp, int n) {
        dp[0][0] = true;

        for (int i = 1; i <= n; i++) {
            int weight = weights.get(i - 1);
            for (int j = 0; j <= 40000; j++) {
                dp[i][j] |= dp[i - 1][j];

                if (j + weight <= 40000) {
                    dp[i][j + weight] |= dp[i - 1][j];
                }

                int diff = Math.abs(j - weight);
                dp[i][diff] |= dp[i - 1][j];
            }
        }
    }
}