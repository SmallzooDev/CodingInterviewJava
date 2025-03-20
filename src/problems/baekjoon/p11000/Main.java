package problems.baekjoon.p11000;

import java.io.*;
import java.util.*;

public class Main {
    static class Schedule implements Comparable<Schedule> {
        public int start;
        public int end;

        public Schedule(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public int compareTo(Schedule o) {
            return this.start - o.start;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        List<Schedule> schedules = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            String[] inputs = br.readLine().split(" ");
            schedules.add(new Schedule(Integer.parseInt(inputs[0]), Integer.parseInt(inputs[1])));
        }

        br.close();

        Collections.sort(schedules);
        PriorityQueue<Integer> roomEndTimes = new PriorityQueue<>();

        for (Schedule schedule : schedules) {
            if (!roomEndTimes.isEmpty() && roomEndTimes.peek() <= schedule.start) {
                roomEndTimes.poll();
            }
            roomEndTimes.offer(schedule.end);
        }

        System.out.println(roomEndTimes.size());
    }
}