import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static class Node implements Comparable<Node>{
        int end,cost;

        public Node(int end,int cost){
            this.end=end;
            this.cost=cost;
        }
        @Override
        public int compareTo(Node o){
            return cost - o.cost;
        }
    }
    static int n,m,t,s,g,h;
    static ArrayList<Node>[] map;
    static int[] destination;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());

        for(int k=0;k<T;k++){
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());//교차로
            m = Integer.parseInt(st.nextToken());//도로
            t = Integer.parseInt(st.nextToken());//목적지 후보
            st = new StringTokenizer(br.readLine());
            s = Integer.parseInt(st.nextToken());//예술가들의 출발지
            g = Integer.parseInt(st.nextToken());
            h = Integer.parseInt(st.nextToken());
            map = new ArrayList[n+1];
            for(int i=1;i<=n;i++) {
                map[i] = new ArrayList<Node>();
            }
            for(int i=0;i<m;i++){
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                map[start].add(new Node(end,cost));
                map[end].add(new Node(start,cost));
            }

            destination = new int[t];
            for(int i=0;i<t;i++){
                st = new StringTokenizer(br.readLine());
                int X = Integer.parseInt(st.nextToken());
                destination[i] = X;
            }

            int[] distFromStart = dijkstra(s);

            int[] distFromG = dijkstra(g);

            int[] distFromH = dijkstra(h);

            Arrays.sort(destination);
            for(int i=0;i<t;i++){
                int minVal = Math.min(distFromStart[g] + distFromG[h] + distFromH[destination[i]], distFromStart[h] + distFromH[g] + distFromG[destination[i]]);
                if(distFromStart[destination[i]] == minVal){
                    System.out.print(destination[i] + " ");
                }
            }
            System.out.println();
        }




    }
    static int[] dijkstra(int start){
        PriorityQueue<Node> queue = new PriorityQueue<>();

        int[] dist = new int[n+1];
        Arrays.fill(dist,Integer.MAX_VALUE);

        boolean[] visited = new boolean[n + 1];

        queue.add(new Node(start, 0));
        dist[start] = 0;

        while(!queue.isEmpty()){
            Node curNode = queue.poll();
            int cur = curNode.end;

            if(visited[cur] == true) continue;
            visited[cur] = true;

            for(Node node : map[cur]){
                if(dist[node.end] > dist[cur] + node.cost){
                    dist[node.end] = dist[cur] + node.cost;
                    queue.add(new Node(node.end, dist[node.end]));
                }
            }
        }
        return dist;
    }


}
