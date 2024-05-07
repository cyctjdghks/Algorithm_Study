import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][] damage = {{-9,-3,-1},{-9,-1,-3},{-3,-9,-1},{-3,-1,-9},{-1,-9,-3},{-1,-3,-9}};
    static int[][][] dp;
    static int result = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int[] scv = new int[3];
        st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++){
            scv[i] = Integer.parseInt(st.nextToken());
        }

        dp = new int[61][61][61];

        dfs(scv[0], scv[1], scv[2], 0);
        System.out.println(result);
    }
    public static void dfs(int scv1, int scv2, int scv3, int cnt){
        if(result <= cnt){
            return;
        }

        // 중복 체크
        // 이미 방문했는데 현재 공격횟수보다 작은 경우
        if(dp[scv1][scv2][scv3] != 0 && dp[scv1][scv2][scv3] <= cnt){
            return;
        }
        dp[scv1][scv2][scv3] = cnt;

        if(scv1 == 0 && scv2 == 0 && scv3==0){
            result = Math.min(result, cnt);
            return;
        }

        for(int i=0;i<6;i++) {
            dfs(Math.max(scv1 + damage[i][0], 0), Math.max(scv2 + damage[i][1], 0), Math.max(scv3 + damage[i][2], 0), cnt+1);
        }
    }
}