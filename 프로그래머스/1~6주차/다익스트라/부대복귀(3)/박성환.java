import java.util.*;

class Solution {
    // 강철부대가 위치한 지역을 포함한 총지역의 수 n
    // 3 ≤ n ≤ 100,000
    // 두 지역을 왕복할 수 있는 길 정보를 담은 2차원 정수 배열 roads
    // 2 ≤ roads.length ≤ 500,000
    // 각 부대원이 위치한 서로 다른 지역들을 나타내는 정수 배열 sources
    // 1 ≤ sources.length ≤ 500
    // 철부대의 지역 destination
    // 1 ≤ destination ≤ n
    
    static final int MAX = 100001; // n * 1 + 1
    static ArrayList<ArrayList<Node>> map;
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        
        map = new ArrayList<>();
        for(int i = 0; i <= n; i++) {
            map.add(new ArrayList<>());
        }
        
        for(int[] road : roads) {
            map.get(road[0]).add(new Node(road[1], 1));
            map.get(road[1]).add(new Node(road[0], 1));
        }
        
        int[] dijk = new int[n+1];
        
        Arrays.fill(dijk, MAX);
        
        dijk = dijkstra(destination, dijk);
        
        for(int i = 0; i < sources.length; i++) {
            answer[i] = dijk[sources[i]] == MAX ? -1 : dijk[sources[i]];
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
        
        public Node(int index, int cost) {
            this.index = index;
            this.cost = cost;
        }
        
        @Override
        public int compareTo(Node node) {
            return this.cost - node.cost;
        }
    }
}