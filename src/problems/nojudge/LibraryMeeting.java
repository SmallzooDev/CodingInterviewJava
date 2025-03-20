package problems.nojudge;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class LibraryMeeting {
    public int getSchedule() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        PriorityQueue<Meeting> pq = new PriorityQueue<>();
        int result = 0;

        int count = Integer.parseInt(br.readLine());
        for (int i = 0; i < count; i++) {
            String[] tokens = br.readLine().split(" ");
            int start = Integer.parseInt(tokens[0]);
            int end = Integer.parseInt(tokens[1]);
            pq.offer(new Meeting(start, end));
        }

        int lastMeetingEndUpTime = 0;
        while (!pq.isEmpty()) {
            Meeting newMeeting = pq.poll();
            if (newMeeting.start >= lastMeetingEndUpTime) {
                lastMeetingEndUpTime = newMeeting.end;
                result++;
            }
        }

        return result;
    }


    static class Meeting implements Comparable<Meeting> {
        public int start;
        public int end;

        public Meeting(int start, int end) {
           this.start = start;
           this.end = end;
        }

        @Override
        public int compareTo(Meeting meeting) {
            return this.end - meeting.end;
        }
    }
}
