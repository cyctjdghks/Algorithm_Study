import java.util.*;

public class BOJ_9370 {

    static final int INF = 10_000_000;
    static int N, M, T;
    static int S, G, H;
    static int[][] graph;
    static int[] dist;
    static boolean[] visited;
    static List<Integer> candidates;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int testCases = sc.nextInt();

        for (int tc = 0; tc < testCases; tc++) {
            N = sc.nextInt();
            M = sc.nextInt();
            T = sc.nextInt();

            graph = new int[N + 1][N + 1];
            dist = new int[N + 1];
            visited = new boolean[N + 1];
            candidates = new ArrayList<>();

            for (int i = 0; i <= N; i++) {
                Arrays.fill(graph[i], INF);
            }
            Arrays.fill(dist, INF);

            S = sc.nextInt();
            G = sc.nextInt();
            H = sc.nextInt();

            for (int i = 0; i < M; i++) {
                int u = sc.nextInt();
                int v = sc.nextInt();
                int w = sc.nextInt() * 2;
                graph[u][v] = graph[v][u] = w;
            }

            graph[G][H]--;
            graph[H][G]--;

            for (int i = 0; i < T; i++) {
                candidates.add(sc.nextInt());
            }

            dijkstra(S);

            Collections.sort(candidates);
            StringBuilder result = new StringBuilder();
            for (int candidate : candidates) {
                if (dist[candidate] % 2 == 1) {
                    result.append(candidate).append(" ");
                }
            }

            System.out.println(result.toString().trim());
        }

        sc.close();
    }

    private static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node currentNode = pq.poll();
            int current = currentNode.end;

            if (visited[current]) continue;

            visited[current] = true;

            for (int i = 1; i <= N; i++) {
                if (!visited[i] && dist[i] > dist[current] + graph[current][i]) {
                    dist[i] = dist[current] + graph[current][i];
                    pq.offer(new Node(i, dist[i]));
                }
            }
        }
    }

    static class Node implements Comparable<Node> {
        int end, weight;

        public Node(int end, int weight) {
            this.end = end;
            this.weight = weight;
        }

        @Override
        public int compareTo(Node o) {
            return this.weight - o.weight;
        }
    }
}
