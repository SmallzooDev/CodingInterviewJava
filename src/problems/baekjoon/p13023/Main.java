package problems.baekjoon.p13023;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] counts = br.readLine().split(" ");

        int humanCount = Integer.parseInt(counts[0]);
        int relationCount = Integer.parseInt(counts[1]);

        List<List<Integer>> humanGraph = new ArrayList<>();
        for (int i = 0; i < humanCount; i++) {
            humanGraph.add(new ArrayList<>());
        }

        for (int i = 0; i < relationCount; i++) {
            String[] relation = br.readLine().split(" ");
            int from = Integer.parseInt(relation[0]);
            int to = Integer.parseInt(relation[1]);

            humanGraph.get(from).add(to);
            humanGraph.get(to).add(from);
        }

        br.close();

        int isExist = 0;
        for (int i = 0; i < humanCount; i++) {
            boolean[] visited = new boolean[humanCount];
            if (dfs(humanGraph, visited, i, 0)) {
                isExist = 1;
                break;
            }
        }

        System.out.println(isExist);
    }

    private static boolean dfs(List<List<Integer>> graph, boolean[] visited, int current, int depth) {
        if (depth == 4) {
            return true;
        }

        visited[current] = true;

        for (int next : graph.get(current)) {
            if (!visited[next]) {
                if (dfs(graph, visited, next, depth + 1)) {
                    return true;
                }
            }
        }


        visited[current] = false;
        return false;
    }
}