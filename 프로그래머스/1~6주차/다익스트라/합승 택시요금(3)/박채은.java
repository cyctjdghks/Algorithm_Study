import java.util.*;

class Solution {
    int N;
    int[][] map;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        N = n;
        map = new int[n+1][n+1];

        for(int[] fare: fares){
            map[fare[0]][fare[1]] = fare[2];
            map[fare[1]][fare[0]] = fare[2];
        }

        int[] together = Dijkstra(s);

        int min = Integer.MAX_VALUE;
        // s -> i -> a/b
        for(int i=1;i<=N;i++){
            int[] alone = Dijkstra(i);
            int total = together[i] + alone[a] + alone[b];
            if(total < min){
                min = total;
            }
        }
        return min;
    }

    public int[] Dijkstra(int start) {
        boolean[] check = new boolean[N + 1];
        int[] dist = new int[N + 1];
        int INF = Integer.MAX_VALUE;
        Arrays.fill(dist, INF);

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        // 출발지 초기화
        pq.offer(new int[] {0, start});
        dist[start] = 0;

        while(!pq.isEmpty()){
            int[] q = pq.poll();
            int curDist = q[0]; // 출발점에서 현재 node까지의 최단 거리
            int curNode = q[1];

            // 방문한지 확인
            if(check[curNode]) continue;
            check[curNode] = true;

            // 인접한 노드 추가
            for(int i =1;i<=N;i++){
                if(map[curNode][i] == 0) continue;
                if(curDist + map[curNode][i] < dist[i]){
                    dist[i] = curDist + map[curNode][i];
                    pq.offer(new int[]{dist[i], i});
                }
            }
        }
        return dist;
    }
}