import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int R, C, K;
    static char[][] mat;
    static int[][] dir = {{-1,0}, {1,0}, {0,-1}, {0,1}};
    static boolean[][] visited;
    static int result = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        mat = new char[R][C];
        visited = new boolean[R][C];

        for(int i=0;i<R;i++){
            String s = br.readLine();
            for(int j=0;j<C;j++){
                mat[i][j] = s.charAt(j);
            }
        }

        // 출발점 visited = true
        visited[R-1][0] = true;
        dfs(R-1,0,1);
        System.out.println(result);
    }

    static void dfs(int x, int y, int k){
        if(x == 0 && y == C-1){ // 도착지점에 도달한 경우
            if(k == K){
                result+=1;
            }
            return;
        }
        // 사방 체크
        for(int i=0;i<4;i++){
            int dx = x + dir[i][0];
            int dy = y + dir[i][1];

            if(dx >=0 && dx < R && dy >=0 && dy < C && !visited[dx][dy]){
                if(mat[dx][dy] != 'T'){
                    visited[dx][dy] = true;
                    dfs(dx, dy, k+1);
                    visited[dx][dy] = false;
                }
            }
        }
    }
}