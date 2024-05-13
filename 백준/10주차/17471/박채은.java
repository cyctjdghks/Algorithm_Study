import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int N;
    static int people[];
    static List<ArrayList<Integer>> graph;
    static int selected[];
    static boolean visited[];
    static int result = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());

        people = new int[N+1];
        selected = new int[N+1];
        graph = new ArrayList<>();
        st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++){
            people[i] = Integer.parseInt(st.nextToken());
            graph.add(new ArrayList<Integer>());
        }
        graph.add(new ArrayList<Integer>());

        for(int i=1;i<=N;i++){
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());
            for(int j=0;j<cnt;j++){
                int n = Integer.parseInt(st.nextToken());
                graph.get(i).add(n);
            }
        }

        // 선거구 나누기
        divide(1);

        if(result == Integer.MAX_VALUE){
            System.out.println("-1");
        }
        else{
            System.out.println(result);
        }
    }
    static void divide(int dept){
        if(dept == N+1){
            // 두 선거구끼리 인접한지 확인
            List<Integer> A = new ArrayList<>();
            List<Integer> B = new ArrayList<>();
            int totalA = 0;
            int totalB = 0;

            for(int i=1;i<=N;i++){
                if(selected[i] == 1){
                    A.add(i);
                    totalA+=people[i];
                } else if (selected[i] == 2) {
                    B.add(i);
                    totalB+=people[i];
                }
            }

            if(A.size() == 0 || B.size() == 0) return;

            if(bfs(A) && bfs(B)){
                // 인구차 구하기
                int diff = Math.abs(totalA - totalB);
                result = Math.min(result, diff);
            }
            return;
        }
        // 1 선거구로 선택
        selected[dept] = 1;
        divide(dept+1);

        // 2 선거구로 선택
        selected[dept] = 2;
        divide(dept+1);
    }

    static boolean bfs(List<Integer> list){
        Queue<Integer> que = new LinkedList<>();
        visited = new boolean[N+1];
        visited[list.get(0)] = true;
        que.add(list.get(0));

        int cnt = 1;
        while(!que.isEmpty()){
            int q = que.poll();
            for(int i=0;i<graph.get(q).size();i++){
                int node = graph.get(q).get(i);
                if(list.contains(node) && visited[node]==false){
                    cnt+=1;
                    que.add(node);
                    visited[node] = true;
                }
            }
        }

        if(cnt == list.size()){
            return true;
        }
        else{
            return false;
        }
    }
}