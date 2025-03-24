import java.util.*;

class Solution {
    List<List<Integer>> graph = new ArrayList<>();
    boolean[] visited;
    int[][] dp;
    public int solution(int n, int[][] lighthouse) {
        int answer = 0;

        for(int i=0; i<=n; i++){
            graph.add(new ArrayList<>());
        }

        for(int[] l:lighthouse){
            graph.get(l[0]).add(l[1]);
            graph.get(l[1]).add(l[0]);
        }

        visited = new boolean[n+1];
        dp = new int[n+1][2];
        dfs(1);

        answer = Math.min(dp[1][0], dp[1][1]);

        return answer;
    }

    public void dfs(int node){
        visited[node] = true;

        dp[node][0] = 0;
        dp[node][1] = 1;

        for(int i=0; i<graph.get(node).size(); i++){
            int child = graph.get(node).get(i);

            if(visited[child]) continue;

            dfs(child);

            dp[node][0] += dp[child][1];
            dp[node][1] += Math.min(dp[child][0], dp[child][1]);

        }
    }

}