package problems.nojudge;

/**
 * 1. 그래프 표현 방법
 * 2. DFS 구현
 * 3. BFS 구현
 * 4. 최단 경로 알고리즘
 */
import java.util.*;

public class GraphTemplates {

    //========================================================================
    // 1. 그래프 표현 방법
    //========================================================================

    // 1.1. 인접 리스트 - 기본
    static class Graph {
        int V;
        List<List<Integer>> adj;

        Graph(int v) {
            V = v;
            adj = new ArrayList<>(V);
            for (int i = 0; i < V; i++) {
                adj.add(new ArrayList<>());
            }
        }

        void addEdge(int u, int v) {
            adj.get(u).add(v);
            adj.get(v).add(u); // 무향 그래프의 경우. 방향 그래프면 이 줄 제거
        }
    }

    // 1.2. 인접 리스트 - 가중치 그래프
    static class WeightedGraph {
        int V;
        List<List<Edge>> adj;

        static class Edge {
            int to, weight;

            Edge(int to, int weight) {
                this.to = to;
                this.weight = weight;
            }
        }

        WeightedGraph(int v) {
            V = v;
            adj = new ArrayList<>(V);
            for (int i = 0; i < V; i++) {
                adj.add(new ArrayList<>());
            }
        }

        void addEdge(int from, int to, int weight) {
            adj.get(from).add(new Edge(to, weight));
            adj.get(to).add(new Edge(from, weight)); // 무향 그래프의 경우
        }
    }

    // 1.3. 인접 행렬
    static class MatrixGraph {
        int V;
        int[][] adj; // 또는 boolean[][] adj (간선 존재 여부만 저장)

        MatrixGraph(int v) {
            V = v;
            adj = new int[V][V]; // 모든 값 0으로 초기화 (연결 없음)
        }

        void addEdge(int u, int v, int weight) {
            adj[u][v] = weight; // 가중치가 없으면 weight = 1
            adj[v][u] = weight; // 무향 그래프의 경우
        }
    }

    //========================================================================
    // 2. 깊이 우선 탐색 (DFS)
    //========================================================================

    // 2.1. 재귀 DFS
    static void dfs(List<List<Integer>> graph, int start, boolean[] visited) {
        // 현재 노드 방문 처리
        visited[start] = true;
        System.out.print(start + " "); // 방문한 노드 출력

        // 인접 노드 탐색
        for (int next : graph.get(start)) {
            if (!visited[next]) {
                dfs(graph, next, visited);
            }
        }
    }

    // 2.2. 스택을 이용한 반복적 DFS
    static void dfsIterative(List<List<Integer>> graph, int start) {
        boolean[] visited = new boolean[graph.size()];
        Stack<Integer> stack = new Stack<>();

        stack.push(start);

        while (!stack.isEmpty()) {
            int current = stack.pop();

            if (!visited[current]) {
                visited[current] = true;
                System.out.print(current + " ");

                // 인접 노드를 스택에 추가 (역순으로)
                for (int i = graph.get(current).size() - 1; i >= 0; i--) {
                    int next = graph.get(current).get(i);
                    if (!visited[next]) {
                        stack.push(next);
                    }
                }
            }
        }
    }

    // 2.3. 2차원 그리드에서의 DFS
    static int[] dx = {-1, 1, 0, 0}; // 상하좌우
    static int[] dy = {0, 0, -1, 1};

    static void dfsGrid(int[][] grid, int x, int y, boolean[][] visited) {
        int n = grid.length;
        int m = grid[0].length;

        // 현재 위치 방문 처리
        visited[x][y] = true;

        // 상하좌우 탐색
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 범위 내에 있고, 아직 방문하지 않았으며, 유효한 셀인지 확인
            if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny] && grid[nx][ny] == 1) {
                dfsGrid(grid, nx, ny, visited);
            }
        }
    }

    // 2.4. 백트래킹으로 모든 경로 찾기
    static void findAllPaths(List<List<Integer>> graph, int current, int end,
                             List<Integer> path, List<List<Integer>> allPaths, boolean[] visited) {
        // 현재 노드 방문 처리 및 경로에 추가
        visited[current] = true;
        path.add(current);

        // 목적지에 도달한 경우
        if (current == end) {
            allPaths.add(new ArrayList<>(path)); // 현재 경로 복사하여 저장
        } else {
            // 인접 노드 탐색
            for (int next : graph.get(current)) {
                if (!visited[next]) {
                    findAllPaths(graph, next, end, path, allPaths, visited);
                }
            }
        }

        // 백트래킹 - 방문 상태 및 경로에서 제거
        visited[current] = false;
        path.remove(path.size() - 1);
    }

    // 백트래킹 메서드 호출 예시
    static List<List<Integer>> findAllPaths(List<List<Integer>> graph, int start, int end) {
        List<List<Integer>> allPaths = new ArrayList<>();
        List<Integer> path = new ArrayList<>();
        boolean[] visited = new boolean[graph.size()];

        findAllPaths(graph, start, end, path, allPaths, visited);

        return allPaths;
    }

    //========================================================================
    // 3. 너비 우선 탐색 (BFS)
    //========================================================================

    // 3.1. 기본 BFS
    static void bfs(List<List<Integer>> graph, int start) {
        boolean[] visited = new boolean[graph.size()];
        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.offer(start);

        while (!queue.isEmpty()) {
            int current = queue.poll();
            System.out.print(current + " ");

            for (int next : graph.get(current)) {
                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(next);
                }
            }
        }
    }

    // 3.2. 최단 경로 찾기 BFS
    static int shortestPath(List<List<Integer>> graph, int start, int end) {
        if (start == end) return 0;

        int n = graph.size();
        boolean[] visited = new boolean[n];
        Queue<int[]> queue = new LinkedList<>(); // {노드, 거리}

        visited[start] = true;
        queue.offer(new int[]{start, 0});

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int node = curr[0];
            int dist = curr[1];

            for (int next : graph.get(node)) {
                if (next == end) {
                    return dist + 1; // 목적지 도달
                }

                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(new int[]{next, dist + 1});
                }
            }
        }

        return -1; // 경로가 없는 경우
    }

    // 3.3. 2차원 그리드에서의 BFS
    static int bfsGrid(int[][] grid, int startX, int startY, int endX, int endY) {
        int n = grid.length;
        int m = grid[0].length;

        // 이미 방문 여부 체크
        boolean[][] visited = new boolean[n][m];

        // 상하좌우 이동 방향
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        Queue<int[]> queue = new LinkedList<>(); // {x, y, 거리}
        queue.offer(new int[]{startX, startY, 0});
        visited[startX][startY] = true;

        while (!queue.isEmpty()) {
            int[] curr = queue.poll();
            int x = curr[0];
            int y = curr[1];
            int dist = curr[2];

            // 목적지 도달
            if (x == endX && y == endY) {
                return dist;
            }

            // 상하좌우 탐색
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                // 범위 내, 미방문, 이동 가능한 셀
                if (nx >= 0 && nx < n && ny >= 0 && ny < m && !visited[nx][ny] && grid[nx][ny] == 1) {
                    visited[nx][ny] = true;
                    queue.offer(new int[]{nx, ny, dist + 1});
                }
            }
        }

        return -1; // 경로가 없는 경우
    }

    // 3.4. 0-1 BFS (가중치가 0 또는 1인 경우의 최단 경로)
    static int zeroOneBFS(List<List<WeightedGraph.Edge>> graph, int start, int end) {
        int n = graph.size();
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        // deque 사용 (가중치 0인 간선은 앞에, 가중치 1인 간선은 뒤에 추가)
        Deque<Integer> deque = new LinkedList<>();
        deque.offerFirst(start);

        while (!deque.isEmpty()) {
            int current = deque.pollFirst();

            for (WeightedGraph.Edge edge : graph.get(current)) {
                int next = edge.to;
                int weight = edge.weight;

                // 더 짧은 경로를 찾은 경우
                if (dist[current] + weight < dist[next]) {
                    dist[next] = dist[current] + weight;

                    // 가중치에 따라 앞이나 뒤에 추가
                    if (weight == 0) {
                        deque.offerFirst(next);
                    } else {
                        deque.offerLast(next);
                    }
                }
            }
        }

        return dist[end] == Integer.MAX_VALUE ? -1 : dist[end];
    }

    //========================================================================
    // 4. 최단 경로 알고리즘
    //========================================================================

    // 4.1. 다익스트라 알고리즘 (우선순위 큐 이용)
    static int[] dijkstra(List<List<WeightedGraph.Edge>> graph, int start) {
        int n = graph.size();
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        // 우선순위 큐: {노드, 거리}
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int node = curr[0];
            int distance = curr[1];

            // 이미 처리된 노드라면 스킵
            if (distance > dist[node]) continue;

            // 인접 노드 탐색
            for (WeightedGraph.Edge edge : graph.get(node)) {
                int next = edge.to;
                int weight = edge.weight;

                // 더 짧은 경로를 찾은 경우
                if (dist[node] + weight < dist[next]) {
                    dist[next] = dist[node] + weight;
                    pq.offer(new int[]{next, dist[next]});
                }
            }
        }

        return dist;
    }

    // 4.2. 다익스트라 알고리즘 (경로 추적)
    static void dijkstraWithPath(List<List<WeightedGraph.Edge>> graph, int start, int end) {
        int n = graph.size();
        int[] dist = new int[n];
        int[] prev = new int[n]; // 경로 추적용 배열

        Arrays.fill(dist, Integer.MAX_VALUE);
        Arrays.fill(prev, -1);
        dist[start] = 0;

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[1] - b[1]);
        pq.offer(new int[]{start, 0});

        while (!pq.isEmpty()) {
            int[] curr = pq.poll();
            int node = curr[0];
            int distance = curr[1];

            if (distance > dist[node]) continue;

            for (WeightedGraph.Edge edge : graph.get(node)) {
                int next = edge.to;
                int weight = edge.weight;

                if (dist[node] + weight < dist[next]) {
                    dist[next] = dist[node] + weight;
                    prev[next] = node; // 이전 노드 저장
                    pq.offer(new int[]{next, dist[next]});
                }
            }
        }

        // 경로 역추적 및 출력
        if (dist[end] == Integer.MAX_VALUE) {
            System.out.println("경로가 없습니다.");
            return;
        }

        List<Integer> path = new ArrayList<>();
        for (int at = end; at != -1; at = prev[at]) {
            path.add(at);
        }
        Collections.reverse(path);

        System.out.println("최단 거리: " + dist[end]);
        System.out.print("경로: ");
        for (int i = 0; i < path.size(); i++) {
            System.out.print(path.get(i));
            if (i < path.size() - 1) {
                System.out.print(" -> ");
            }
        }
    }

    // 4.3. 벨만-포드 알고리즘
    static class Edge {
        int from, to, weight;

        Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }

    static int[] bellmanFord(List<Edge> edges, int V, int start) {
        int[] dist = new int[V];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        // V-1번 반복
        for (int i = 0; i < V - 1; i++) {
            for (Edge edge : edges) {
                int u = edge.from;
                int v = edge.to;
                int weight = edge.weight;

                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                    dist[v] = dist[u] + weight;
                }
            }
        }

        // 음수 사이클 검사
        for (Edge edge : edges) {
            int u = edge.from;
            int v = edge.to;
            int weight = edge.weight;

            if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                System.out.println("음수 사이클이 존재합니다.");
                return null; // 또는 적절한 처리
            }
        }

        return dist;
    }

    // 4.4. 플로이드-워셜 알고리즘
    static int[][] floydWarshall(int[][] graph, int V) {
        int[][] dist = new int[V][V];

        // 초기화
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                dist[i][j] = graph[i][j];
            }
        }

        // 거쳐가는 노드 k
        for (int k = 0; k < V; k++) {
            // 출발 노드 i
            for (int i = 0; i < V; i++) {
                // 도착 노드 j
                for (int j = 0; j < V; j++) {
                    // i에서 k를 거쳐 j로 가는 경로와 직접 가는 경로 비교
                    if (dist[i][k] != Integer.MAX_VALUE && dist[k][j] != Integer.MAX_VALUE &&
                            dist[i][k] + dist[k][j] < dist[i][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                    }
                }
            }
        }

        return dist;
    }

    //========================================================================
    // 메인 메서드 (템플릿 사용 예시)
    //========================================================================

    public static void main(String[] args) {
        // 그래프 생성 예시
        int V = 5;
        Graph graph = new Graph(V);

        // 간선 추가
        graph.addEdge(0, 1);
        graph.addEdge(0, 4);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 3);
        graph.addEdge(3, 4);

        // DFS 실행
        System.out.print("DFS: ");
        boolean[] visited = new boolean[V];
        dfs(graph.adj, 0, visited);
        System.out.println();

        // BFS 실행
        System.out.print("BFS: ");
        bfs(graph.adj, 0);
        System.out.println();

        // 가중치 그래프 생성
        WeightedGraph wGraph = new WeightedGraph(V);
        wGraph.addEdge(0, 1, 10);
        wGraph.addEdge(0, 4, 5);
        wGraph.addEdge(1, 2, 1);
        wGraph.addEdge(1, 4, 2);
        wGraph.addEdge(2, 3, 4);
        wGraph.addEdge(3, 0, 7);
        wGraph.addEdge(3, 2, 6);
        wGraph.addEdge(4, 1, 3);
        wGraph.addEdge(4, 2, 9);
        wGraph.addEdge(4, 3, 2);

        // 다익스트라 알고리즘 실행
        int[] shortestDistances = dijkstra(wGraph.adj, 0);
        System.out.print("최단 거리 (0부터): ");
        for (int dist : shortestDistances) {
            System.out.print(dist + " ");
        }
        System.out.println();

        // 플로이드-워셜 예시를 위한 인접 행렬
        int INF = Integer.MAX_VALUE;
        int[][] graphMatrix = {
                {0, 10, INF, INF, 5},
                {INF, 0, 1, INF, 2},
                {INF, INF, 0, 4, INF},
                {7, INF, 6, 0, INF},
                {INF, 3, 9, 2, 0}
        };

        // 플로이드-워셜 알고리즘 실행
        int[][] allPairsShortestPaths = floydWarshall(graphMatrix, V);
        System.out.println("모든 쌍 최단 경로:");
        for (int i = 0; i < V; i++) {
            for (int j = 0; j < V; j++) {
                if (allPairsShortestPaths[i][j] == INF) {
                    System.out.print("INF ");
                } else {
                    System.out.print(allPairsShortestPaths[i][j] + " ");
                }
            }
            System.out.println();
        }
    }
}
