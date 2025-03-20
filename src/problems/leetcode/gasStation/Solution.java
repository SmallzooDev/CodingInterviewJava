package problems.leetcode.gasStation;

import java.util.Arrays;

public class Solution {
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int n = gas.length;

        for (int start = 0; start < n; start++) {
            int tank = 0;
            boolean isSuccess = true;

            for (int i = 0; i < n; i++) {
                int current = (start + i) % n;
                tank += gas[current] - cost[current];

                if (tank < 0) {
                    isSuccess = false;
                    break;
                }
            }

            if (isSuccess) {
                return start;
            }
        }

        return -1;
    }

    public int canCompleteCircuit2(int[] gas, int[] cost) {
        if (Arrays.stream(gas).sum() < Arrays.stream(cost).sum()) return -1;
        int tank = 0;
        int start = 0;
        for (int i = 0; i < gas.length; i++) {
            if (tank + gas[i] - cost[i] < 0) {
                start = i + 1;
                tank = 0;
            } else {
                tank += gas[i] - cost[i];
            }
        }

        return start;
    }
}
