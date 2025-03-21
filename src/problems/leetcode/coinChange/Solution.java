package problems.leetcode.coinChange;

import java.util.Arrays;

public class Solution {
    // 1 ~ amount까지 각각의 금액을 전부 계산한다.
    public int coinChange(int[] coins, int amount) {
        // 각 금액을 만들 수 있는 최소 동전 개수를 저장할 배열
        int[] dp = new int[amount + 1];

        // 초기값으로 '불가능'을 의미하는 큰 값(amount+1)으로 설정
        // amount+1은 최악의 경우 모든 금액을 1원짜리로만 만들어도 amount개를 넘지 않기 때문
        Arrays.fill(dp, amount + 1);

        // 기본 조건: 0원을 만드는 데 필요한 동전 수는 0개
        dp[0] = 0;

        // 모든 금액(1부터 amount까지)에 대해 최소 동전 개수 계산
        for (int currentAmount = 1; currentAmount <= amount; currentAmount++) {
            for (int coin : coins) {
                if (currentAmount - coin >= 0) { // false면 금액 초과
                    // 두 가지 경우 중 최소값 선택:
                    // 1. 현재까지 계산된 currentAmount에 대한 최소 동전 개수
                    // 2. (currentAmount - coin)에 대한 최소 동전 개수 + 1(현재 동전)
                    // 즉 coin : coins 루프에서 처음에는 한동전만 쓰는경우, 두번째는 두 동전을 쓰는경우로 가짓수를 늘려 최적을 갱신
                    dp[currentAmount] = Math.min(dp[currentAmount], dp[currentAmount - coin] + 1);
                }
            }
        }

        // 최종 금액에 대한 결과가 초기값과 같으면 불가능한 경우이므로 -1 반환
        return dp[amount] > amount ? -1 : dp[amount];
    }
}
