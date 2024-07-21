import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// 비트마스킹을 이용한 DP
public class Main {
    static int N;
    static int[][] matrix;
    static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 도시의 수
        N = Integer.parseInt(br.readLine());
        matrix = new int[N][N];

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());

            for(int j=0;j<N;j++) {
                matrix[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // d[i][j] = 현재 있는 도시가 i, 이미 방문한 도시들의 집합이 j
        dp = new int[N][(1 << N) - 1];
        for (int i=0;i<N;i++) {
            Arrays.fill(dp[i], -1);
        }
            
        bw.write(dfs(0, 1)+"\n");
        bw.close();
    }

    public static int dfs(int now, int visit) {
        // 모든 도시를 지난 경우
        if(visit == (1 << N) - 1) {
            // 현재 도시에서 출발 도시 경로가 없는 경우
            if(matrix[now][0] == 0) return Integer.MAX_VALUE;
            return matrix[now][0];
        }

        // 이미 방문한 도시인 경우
        if(dp[now][visit] != -1) return dp[now][visit];
        
        dp[now][visit] = Integer.MAX_VALUE;

        for(int i=0;i<N;i++) {
            // 현재 도시에서 방문하지 않는 i 도시로 가는 경로가 있는 경우
            if((visit & (1 << i)) == 0 && matrix[now][i] != 0) {
                int result = dfs(i, visit | (1 << i));

                if(result != Integer.MAX_VALUE && matrix[now][i] != Integer.MAX_VALUE) 
                    dp[now][visit] = Math.min(result + matrix[now][i], dp[now][visit]);
                else dp[now][visit] = Math.min(Integer.MAX_VALUE, dp[now][visit]);
            }
        }

        return dp[now][visit];
    }
}
