package problems.baekjoon.p1092;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        String[] cranesStr = br.readLine().split(" ");
        List<Integer> cranes = new ArrayList<>();
        for (String s : cranesStr) {
            cranes.add(Integer.parseInt(s));
        }

        Collections.sort(cranes, Collections.reverseOrder());

        int m = Integer.parseInt(br.readLine());

        String[] boxesStr = br.readLine().split(" ");
        List<Integer> boxes = new ArrayList<>();
        for (String s : boxesStr) {
            boxes.add(Integer.parseInt(s));
        }

        Collections.sort(boxes, Collections.reverseOrder());

        if (!boxes.isEmpty() && !cranes.isEmpty() && boxes.get(0) > cranes.get(0)) {
            System.out.println(-1);
            return;
        }

        int time = 0;
        while (!boxes.isEmpty()) {
            int boxIndex = 0;
            int craneIndex = 0;

            while (craneIndex < cranes.size() && boxIndex < boxes.size()) {
                if (cranes.get(craneIndex) >= boxes.get(boxIndex)) {
                    boxes.remove(boxIndex);
                    craneIndex++;
                } else {
                    boxIndex++;
                }
            }

            time++;
        }

        System.out.println(time);
    }
}
