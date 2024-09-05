import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    static ArrayList<int[]>[] dest;
    static int[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int i=0;i<T;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
    		int m = Integer.parseInt(st.nextToken());
    		int t = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
    		int s = Integer.parseInt(st.nextToken());
    		int g = Integer.parseInt(st.nextToken());
    		int h = Integer.parseInt(st.nextToken());
            
    		dest = new ArrayList[n + 1];
            graph = new int[n + 1];

            for(int j=1;j<=n;j++) {
                dest[j] = new ArrayList<>();
            }
            Arrays.fill(graph, 100000000);

            for(int j=1;j<=m;j++) {
                st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                int d = 2 * Integer.parseInt(st.nextToken());

                if ((a == g && b == h) || (a == h && b == g)) d -= 1;

                dest[a].add(new int[] {b, d});
                dest[b].add(new int[] {a, d});
            }

            dijkstra(s);

            ArrayList<Integer> result = new ArrayList<>();

            for(int j=0;j<t;j++) {
                int x = Integer.parseInt(br.readLine());

                if(graph[x] % 2 != 0) result.add(x);
            }

            Collections.sort(result);

            for(Integer re : result) {
                bw.write(re+" ");
            }

            bw.write("\n");
        }

        bw.close();
        br.close();
    }

    public static void dijkstra(int start) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            return a[1] - b[1];
        });

        graph[start] = 0;
        pq.add(new int[] {start, 0});

        while(!pq.isEmpty()) {
            int[] cur = pq.poll();
            int node = cur[0];
            int weight = cur[1];

            if (weight > graph[node]) continue;

            for(int[] next: dest[node]) {
                int cost = weight + next[1];

                if (graph[next[0]] > cost) {
                    graph[next[0]] = cost;
                    pq.add(new int[] {next[0], cost});
                }
            }
        }
    }
}
