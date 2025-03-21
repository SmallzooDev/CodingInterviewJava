package problems.baekjoon.p2294;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int count = Integer.parseInt(s[0]);
        int n = Integer.parseInt(s[1]);
        int[] coins = new int[count];

        for (int i = 0; i < count; i++) {
            coins[i] = Integer.parseInt(br.readLine());
        }
        br.close();

        int[] dp = new int[n + 1];
        Arrays.fill(dp, n + 1);

        dp[0] = 0;
        for (int current = 1; current <= n; current++) {
            for (int coin : coins) {
                if (current - coin >= 0) {
                    dp[current] = Math.min(dp[current], dp[current - coin] + 1);
                }
            }
        }

        int result = dp[n] > n ? -1 : dp[n];
        System.out.println(result);
    }
}
