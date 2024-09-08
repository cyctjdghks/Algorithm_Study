import java.util.*;

class Solution {
    
    static final int MAX = 20000001; // 200 * 100000 + 1
    static ArrayList<ArrayList<Node>> map;
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        // 지점 개수 n, 3 <= n <= 200
        // 출발지점 s
        // A의 도착지점 a
        // B의 도착지점 b
        // 1 <= f <= 100,000
        
        int answer = Integer.MAX_VALUE;
        
        map = new ArrayList<>();
        for(int i = 0; i <= n; i++) {
            map.add(new ArrayList<>());
        }
        
        // 각 노드 별 시작과 끝 선언
        for(int[] fare : fares) {
            map.get(fare[0]).add(new Node(fare[1], fare[2]));
            map.get(fare[1]).add(new Node(fare[0], fare[2]));
        }
        
        int[] dijkA = new int[n+1]; // A 지점 기준
        int[] dijkB = new int[n+1]; // B 지점 기준
        int[] dijk = new int[n+1];  // S 지점 기준
        
        Arrays.fill(dijkA, MAX);
        Arrays.fill(dijkB, MAX);
        Arrays.fill(dijk, MAX);
        
        dijkA = dijkstra(a, dijkA);
        dijkB = dijkstra(b, dijkB);
        dijk = dijkstra(s, dijk);
        
        for(int i = 1; i <= n; i++) {
            answer = Math.min(answer, dijkA[i] + dijkB[i] + dijk[i]);
        }
        
        return answer;
    }
    
    public int[] dijkstra(int start, int[] costs) {
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));
        costs[start] = 0;
        
        while(!pq.isEmpty()) {
            Node now = pq.poll();
            int nIndex = now.index;
            int nCost = now.cost;
            
            if(nCost > costs[nIndex]) continue;
            
            ArrayList<Node> nodes = map.get(nIndex);
            for(Node node : nodes) {
                int cost = costs[nIndex] + node.cost;
                
                if(cost < costs[node.index]) {
                    costs[node.index] = cost;
                    pq.offer(new Node(node.index, cost));
                }
            }
        }
        
        return costs;
    }
    
    class Node implements Comparable<Node> {
        int index;
        int cost;
        
        public Node (int index, int cost) {
            this.index = index;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Node n) {
            return this.cost - n.cost;
        }
    }
}