package problems.baekjoon.p21278;

import java.io.*;
import java.util.*;

// 안됨
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] counts = br.readLine().split(" ");
        int buildingCount = Integer.parseInt(counts[0]);
        int bridgeCount = Integer.parseInt(counts[1]);

        ArrayList<Integer>[] buildingMap = new ArrayList[buildingCount + 1];

        for (int i = 1; i <= buildingCount; i++) {
            buildingMap[i] = new ArrayList<>();
        }

        for (int i = 0; i < bridgeCount; i++) {
            String[] tmp = br.readLine().split(" ");
            int from = Integer.parseInt(tmp[0]);
            int to = Integer.parseInt(tmp[1]);

            buildingMap[from].add(to);
            buildingMap[to].add(from);
        }
        br.close();

        int[][] dist = new int[buildingCount + 1][buildingCount + 1];
        for (int i = 1; i <= buildingCount; i++) {
            Arrays.fill(dist[i], Integer.MAX_VALUE / 2);
            dist[i][i] = 0;
        }

        for (int i = 1; i <= buildingCount; i++) {
            for (int next : buildingMap[i]) {
                dist[i][next] = 1;
            }
        }

        for (int k = 1; k <= buildingCount; k++) {
            for (int i = 1; i <= buildingCount; i++) {
                for (int j = 1; j <= buildingCount; j++) {
                    dist[i][j] = Math.min(dist[i][j], dist[i][k] + dist[k][j]);
                }
            }
        }

        int minTotalDist = Integer.MAX_VALUE;
        int chicken1 = 0, chicken2 = 0;

        for (int i = 1; i <= buildingCount; i++) {
            for (int j = i + 1; j <= buildingCount; j++) {
                int totalDist = 0;
                for (int building = 1; building <= buildingCount; building++) {
                    totalDist += 2 * Math.min(dist[building][i], dist[building][j]);
                }

                if (totalDist < minTotalDist) {
                    minTotalDist = totalDist;
                    chicken1 = i;
                    chicken2 = j;
                }
            }
        }

        System.out.println(chicken1 + " " + chicken2 + " " + minTotalDist);
    }

}
