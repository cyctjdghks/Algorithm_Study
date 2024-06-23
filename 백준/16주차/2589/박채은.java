import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int R, C;
    static char[][] mat;
    static int[][] dir = {{-1,0}, {1,0}, {0,-1}, {0,1}};
    static boolean[][] visited;
    static int result = 0;

    public static class Node{
        int x;
        int y;
        int cnt;
        public Node(int x, int y, int cnt){
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        mat = new char[R][C];

        for(int i=0;i<R;i++){
            String s = br.readLine();
            for(int j=0;j<C;j++){
                mat[i][j] = s.charAt(j);
            }
        }

        // 육지인 모든 정점에서 bfs를 실행
        for(int i=0;i<R;i++){
            for(int j=0;j<C;j++){
                if(mat[i][j] == 'L'){
                    visited = new boolean[R][C];
                    result = Math.max(result, bfs(i, j));
                }
            }
        }

        System.out.println(result);
    }

    public static int bfs(int x, int y){ // 시작점
        Queue<Node> q = new LinkedList<>();
        visited[x][y] = true;
        q.add(new Node(x,y, 0));
        int maxCnt = 0;

        // 최단거리 구하기
        while(!q.isEmpty()){
            Node cur = q.poll();
            for(int i=0;i<4;i++){
                int dx = cur.x + dir[i][0];
                int dy = cur.y + dir[i][1];

                if(dx >=0 && dx < R && dy >=0 && dy < C && !visited[dx][dy]){
                    if(mat[dx][dy] == 'L'){
                        visited[dx][dy] = true;
                        q.add(new Node(dx ,dy , cur.cnt +1));
                        maxCnt = Math.max(maxCnt, cur.cnt + 1);
                    }
                }
            }
        }
        return maxCnt;
    }
}