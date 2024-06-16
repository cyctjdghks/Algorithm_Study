import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n, m;
    static int[][] mat;
    static int[][] dir = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        int total = 0;
        mat = new int[n][m];
        for(int i=0;i<n;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<m;j++){
                mat[i][j] = Integer.parseInt(st.nextToken());
                if(mat[i][j] == 1){
                    total+=1;
                }
            }
        }

        int cnt = 0;
        int beforeEnd = 0;
        while(total!=0){
            int deleteNum = bfs();
            cnt+=1;
            if(total - deleteNum == 0){
                beforeEnd = total;
            }
            total = total - deleteNum;
        }

        System.out.println(cnt);
        System.out.println(beforeEnd);
    }
    public static int bfs(){
        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{0,0});
        boolean[][] visited = new boolean[n][m];
        visited[0][0] = true;
        int melted = 0;

        while(!q.isEmpty()){
            int[] cur = q.poll(); // 0인 것

            for(int i=0;i<4;i++){
                int x = cur[0] + dir[i][0];
                int y = cur[1] + dir[i][1];

                if(x>=0 && y>=0 && x<n && y<m && !visited[x][y]){
                    if(mat[x][y] == 0){
                        q.add(new int[]{x,y});
                    }
                    else if(mat[x][y] == 1){ // c인 부분
                        mat[x][y] = 0;
                        melted+=1;
                    }
                    visited[x][y] = true;
                }
            }
        }

        return melted;
    }
}