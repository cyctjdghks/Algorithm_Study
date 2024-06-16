import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int[][] mat;
    static boolean[][] check;
    static int dir[][] = {{-1,0}, {1,0}, {0,1}, {0,-1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        mat = new int[n][n];
        int max = 0;
        int result = 0;

        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<n;j++){
                mat[i][j] = Integer.parseInt(st.nextToken());
                // 최댓값 구하기
                if(max < mat[i][j]){
                    max = mat[i][j];
                }
            }
        }

        for(int height=0;height<max;height++){
            check = new boolean[n][n];
            int cnt =0;
            for(int i=0;i<n;i++){
                for(int j=0;j<n;j++){
                    if(mat[i][j] > height && !check[i][j]){ // 물에 잠기지 않는 경우
                        dfs(i,j,height);
                        cnt+=1;
                    }
                }
            }
            result = Math.max(result, cnt);
        }

        System.out.println(result);
    }

    // 안전 구역 구하기
    static void dfs(int i, int j, int height){
        check[i][j] = true;

        for(int k=0;k<4;k++){
            int dx = i+ dir[k][0];
            int dy = j+ dir[k][1];

            if(dx>=0 && dx < n && dy>=0 && dy < n){
                if(!check[dx][dy] && mat[dx][dy] > height){
                    dfs(dx, dy, height);
                }
            }
        }
    }
}