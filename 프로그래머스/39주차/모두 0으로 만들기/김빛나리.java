import java.util.*;

class Solution {
    long answer = 0;
    boolean[] visited;
    // a를 long으로 변환하기 위한 배열
    long[] long_a;
    // 각 index에 연결된 정점
    ArrayList<Integer>[] list;
    
    public long solution(int[] a, int[][] edges) {
        visited = new boolean[a.length];
        long_a = new long[a.length];
        list = new ArrayList[a.length];
        
        long sum = 0;
        for(int i=0;i<a.length;i++) {
            sum += a[i];
            list[i] = new ArrayList<>();
            long_a[i] = a[i];
        }
        
        // 모든 점들의 가중치값의 합이 0이 아닌 경우, 0으로 만드는 것이 불가능
        if(sum != 0) return -1;
        
        for(int i=0;i<edges.length;i++) {
            list[edges[i][0]].add(edges[i][1]);
            list[edges[i][1]].add(edges[i][0]);
        }

        // 0번째 정점부터 탐색
        dfs(0);
        
        return answer;
    }
    
    long dfs(int v) {
        visited[v] = true;
        
        // 해당하는 정점의 list 값 가져오기
        for(int i=0;i<list[v].size();i++) {
            int next = list[v].get(i);
            
            if(!visited[next]) long_a[v] += dfs(next);
        }

        answer += Math.abs(long_a[v]);

        return long_a[v];
    }
}