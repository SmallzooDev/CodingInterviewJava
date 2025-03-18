package problems.leetcode.queueReconstructionByHeight;


import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Stream;

public class Solution {
    public int[][] reconstructQueue(int[][] people) {
        PriorityQueue<int[]> peopleQueue = new PriorityQueue<>(
                (a, b) -> a[0] != b[0] ? b[0] -a[0] : a[1] - b[1]);

        Stream.of(people).forEach(peopleQueue::offer);
        List<int[]> result = new ArrayList<>();
        while (!peopleQueue.isEmpty()) {
            int[] person = peopleQueue.poll();
            result.add(person[1], person);

        }
        return result.toArray(new int[people.length][2]);
    }

}
