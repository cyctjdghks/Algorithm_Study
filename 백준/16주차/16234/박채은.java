import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, L, R;
    static int[][] mat;
    static int[][] dir = {{-1,0}, {1,0}, {0,-1}, {0,1}};
    static boolean[][] visited;
    static List<Node> union;

    public static class Node{
        int x;
        int y;
        public Node(int x, int y){
            this.x = x;
            this.y = y;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        mat = new int[n][n];

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                mat[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        // 더 이상의 인구이동이 없을 때까지 반복
        while (true){
            boolean moving = false;
            visited = new boolean[n][n];
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(!visited[i][j]) {
                        int sum = bfs(i, j);
                        if(union.size() > 1) { // 인구 이동 시작
                            moving = true;
                            startMove(sum);
                        }
                    }
                }
            }
            if(!moving) break;
            cnt+=1;
        }

        System.out.println(cnt);
    }

    public static void startMove(int sum){
        int people = sum / union.size();
        for(Node n : union){
            mat[n.x][n.y] = people;
        }
    }

    public static int bfs(int x, int y){
        Queue<Node> q = new LinkedList<>();
        visited[x][y] = true;
        q.add(new Node(x,y));

        union = new ArrayList<>();
        union.add(new Node(x,y));

        int sum = mat[x][y]; // 연합의 인구수
        while(!q.isEmpty()){
            Node cur = q.poll();
            for(int i=0;i<4;i++){
                int dx = cur.x + dir[i][0];
                int dy = cur.y + dir[i][1];

                if(dx >=0 && dx < n && dy >=0 && dy < n && !visited[dx][dy]){
                    if(Math.abs(mat[dx][dy] - mat[cur.x][cur.y]) >= L && Math.abs(mat[dx][dy] - mat[cur.x][cur.y]) <= R){
                        visited[dx][dy] = true;
                        sum+= mat[dx][dy];
                        q.add(new Node(dx ,dy));
                        union.add(new Node(dx, dy));
                    }
                }
            }
        }
        return sum;
    }
}