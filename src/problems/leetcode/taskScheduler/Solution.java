package problems.leetcode.taskScheduler;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;

public class Solution {
    public int leastInterval(char[] tasks, int n) {
        HashMap<Character, Integer> countMap = new HashMap<>();
        for (char c : tasks) {
            countMap.put(c, countMap.getOrDefault(c, 0) +1);
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

        for (int count : countMap.values()) {
            maxHeap.offer(count);
        }

        int cycles = 0;

        while (!maxHeap.isEmpty()) {
            int[] temp = new int[n + 1];
            int i = 0;

            while (i <= n && !maxHeap.isEmpty()) {
                int count = maxHeap.poll();
                if (count > 1) {
                    temp[i] = count -1;
                }
                i++;
                cycles ++;
            }

            for (int j = 0; j < i; j++) {
                if (temp[j] > 0) {
                    maxHeap.offer(temp[j]);
                }
            }

            if (maxHeap.isEmpty()) {
                break;
            }

            if (i <= n) {
                cycles += (n + 1 - i);
            }
        }

        return cycles;
    }
}
