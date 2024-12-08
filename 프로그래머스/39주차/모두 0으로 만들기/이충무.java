import java.util.*;

class Solution {
    ArrayList<Integer> graph[];
    long answer = 0;
    boolean visited[];
    long atmp[];
    public long solution(int[] a, int[][] edges) {
        
        atmp = new long[a.length];
        for(int i=0;i<a.length;i++)
        {
            atmp[i] = a[i];
            answer+=atmp[i];
        }
        if(answer != 0) return -1;
        graph = new ArrayList[a.length];
        visited = new boolean[a.length];
        
        for(int i=0;i<a.length;i++)
        {
            graph[i] = new ArrayList<>();
        }
        
        for(int[] edge : edges)
        {
            graph[edge[0]].add(edge[1]);
            graph[edge[1]].add(edge[0]);
        }
        
        dfs(0);
        
        return answer;
    }
    
    public long dfs(int now)
    {
        visited[now] = true;
        
        for(int i=0;i<graph[now].size();i++)
        {
            int next = graph[now].get(i);
            if(visited[next]) continue;
            atmp[now] += dfs(next);
        }
        
        answer += Math.abs(atmp[now]);
        return atmp[now];
    }
}