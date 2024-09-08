import java.util.*;

class Solution {
    List<Integer>[] graph;
    int[] cost;
    int N;
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        graph = new List[n+1];
        int result[] = new int[sources.length];

        // graph 생성
        for(int i=0;i<=n;i++){
            graph[i] = new ArrayList<>();
        }
        for(int[] r : roads){
            graph[r[0]].add(r[1]);
            graph[r[1]].add(r[0]);
        }

        N = n;
        cost = new int[n+1];
        Arrays.fill(cost, Integer.MAX_VALUE);

        // 도착지를 출발지로 봄
        Dijkstra(destination);

        for(int i=0;i<sources.length;i++){
            result[i] = cost[sources[i]];
            if(result[i] == Integer.MAX_VALUE){
                result[i] = -1;
            }
        }
        return result;
    }
    public void Dijkstra(int dest){
        Queue<Integer> q = new LinkedList<>();
        q.add(dest);
        cost[dest] = 0;

        while(!q.isEmpty()){
            int curNode = q.poll();
            int size = graph[curNode].size();
            for(int i=0;i<size;i++){
                int nextNode = graph[curNode].get(i);
                if(cost[nextNode] > cost[curNode] + 1){
                    cost[nextNode] = cost[curNode] + 1;
                    q.add(nextNode);
                }
            }
        }
    }
}