import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int m = Integer.parseInt(br.readLine());

        StringTokenizer st;
        int[] arr = new int[m+1];
        for(int i=1; i<=m; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // dp[i] = dp[i-1] + dp[i-2]
        int[] dp = new int[41]; // 0~40
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for(int i=3;i<=n;i++){
            dp[i] = dp[i-1] + dp[i-2];
        }

        // vip 좌석을 고정한 경우를 구함
        int len = 1;
        int result = 1;

        for(int i=1;i<=m;i++){
            result *= dp[arr[i] - arr[i-1] - 1];
        }
        result *= dp[n - arr[m]];

        System.out.println(result);
    }
}
