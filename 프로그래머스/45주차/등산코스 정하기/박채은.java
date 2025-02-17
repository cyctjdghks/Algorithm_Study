import java.util.*;

class Solution {
    public List<List<Node>> graph;
    public int[] Gates;
    public int[] Summits;
    public class Node{
        int e;
        int w;

        Node(int e, int w){
            this.e = e;
            this.w = w;
        }
    }

    public int[] solution(int n, int[][] paths, int[] gates, int[] summits) {
        Gates = gates;
        Summits = summits;

        graph = new ArrayList<>();
        for(int i=0;i<n+1;i++){
            graph.add(new ArrayList<>());
        }

        // 1. 출입구에 연결된 양방향 등산로 -> 출입구에서 다른 지점으로만 이동 가능한 단방향 등산로
        // 2. 산봉우리 연결된 양방향 등산로 -> 다른 지점에서 산봉우리로만 이동 가능한 단방향 등산로
        for(int[] path: paths){
            int from = path[0];
            int to = path[1];
            int w = path[2];

            if(isGate(from) || isSummits(to)){
                graph.get(from).add(new Node(to, w));
            }
            else if(isGate(to) || isSummits(from)){
                graph.get(to).add(new Node(from, w));
            }
            else{
                graph.get(from).add(new Node(to, w));
                graph.get(to).add(new Node(from, w));
            }
        }
        return dijkstra(n);
    }

    public int[] dijkstra(int n) {
        Queue<Node> que = new LinkedList<>();
        int intensity[] = new int[n+1];

        Arrays.fill(intensity, Integer.MAX_VALUE);

        for(int gate:Gates){
            que.add(new Node(gate, 0));
            intensity[gate] = 0;
        }

        while(!que.isEmpty()){
            Node cur = que.poll();

            if(cur.w > intensity[cur.e]) continue;

            for(int i=0;i<graph.get(cur.e).size();i++){
                Node to = graph.get(cur.e).get(i);

                int dist = Math.max(intensity[cur.e], to.w);
                if (intensity[to.e] > dist) {
                    intensity[to.e] = dist;
                    que.add(new Node(to.e, dist));
                }
            }
        }

        int result = Integer.MAX_VALUE;
        int min = Integer.MAX_VALUE;

        Arrays.sort(Summits);

        for(int summit : Summits){
            if(intensity[summit] < min){
                result = summit;
                min = intensity[summit];
            }
        }

        return new int[]{result, min};
    }


    public boolean isGate(int e){
        for(int gate: Gates){
            if(gate == e){
                return true;
            }
        }
        return false;
    }

    public boolean isSummits(int e){
        for(int summit: Summits){
            if(summit == e){
                return true;
            }
        }
        return false;
    }
}