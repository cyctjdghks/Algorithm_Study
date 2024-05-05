import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

public class Main {
    static int n, m;
    static int[][] map;
    static boolean[][] visited;
    static int x1, y1, x2, y2;
    static int[][] dir = {{-1, 0}, {1, 0}, {0, 1}, {0, -1}};

    static class Point{
        int x;
        int y;
        int cnt;
        public Point(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        visited = new boolean[n][m];

        st = new StringTokenizer(br.readLine());
        x1 = Integer.parseInt(st.nextToken()) -1;
        y1 = Integer.parseInt(st.nextToken()) -1;
        x2 = Integer.parseInt(st.nextToken()) -1;
        y2 = Integer.parseInt(st.nextToken()) -1;

        String line;
        for(int i=0;i<n;i++){
            line = br.readLine();
            for(int j=0;j<m;j++){
                char c = line.charAt(j);
                if(c=='0' || c== '1'){
                    map[i][j] = c - '0';
                }
                else{
                    continue;
                }
            }
        }
        // 초코바를 가진 친구의 위치
        map[x2][y2] = 1;

        System.out.println(bfs());
    }
    static int bfs(){
        ArrayDeque<Point> queue = new ArrayDeque<>();
        // 시작점
        queue.add(new Point(x1, y1,0));
        visited[x1][y1] = true;

        int curX, curY;
        while(!queue.isEmpty()){
            Point p = queue.poll();
            if(p.x == x2 && p.y==y2){
                return p.cnt;
            }
            // 상하좌우 탐색
            for(int i=0;i<4;i++){
                curX = p.x + dir[i][0];
                curY = p.y + dir[i][1];

                // 범위를 초과하는 경우
                if(curX < 0 || curY < 0 || curX >=n || curY >= m){
                    continue;
                }

                if(visited[curX][curY]) continue;
                visited[curX][curY] = true;

                // 1을 만날 때까지 전진
                if(map[curX][curY] == 0){
                    queue.offerFirst(new Point(curX, curY, p.cnt));
                }
                else{
                    queue.offerLast(new Point(curX, curY, p.cnt+1));
                }
            }
        }
        return -1;
    }
}