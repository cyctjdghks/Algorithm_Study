import java.util.*;

class Solution {
    List<Integer>[] graph;
    int[][] dp;
    boolean[] visited;

    public int solution(int n, int[][] lighthouse) {
        graph = new ArrayList[n + 1];
        dp = new int[n + 1][2];
        visited = new boolean[n + 1];

        // 그래프 초기화
        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int[] edge : lighthouse) {
            int a = edge[0];
            int b = edge[1];
            graph[a].add(b);
            graph[b].add(a);
        }

        dfs(1); // 루트 노드는 1로 설정

        // 루트를 켜거나 안 켤 수 있으므로 둘 중 최솟값 선택
        return Math.min(dp[1][0], dp[1][1]);
    }

    private void dfs(int node) {
        visited[node] = true;
        dp[node][0] = 0; // node를 켜지 않은 경우
        dp[node][1] = 1; // node를 켠 경우

        for (int child : graph[node]) {
            if (!visited[child]) {
                dfs(child);
                dp[node][0] += dp[child][1]; // 내가 꺼져 있으면 자식은 반드시 켜야 한다
                dp[node][1] += Math.min(dp[child][0], dp[child][1]); // 내가 켜져 있으면 자식은 자유롭게
            }
        }
    }
}
