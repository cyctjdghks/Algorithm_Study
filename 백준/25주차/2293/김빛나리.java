import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] value = new int[n];

        for(int i=0;i<n;i++) {
            value[i] = Integer.parseInt(br.readLine());
        }

        // dp[i] = j > i: 동전 / j: i원을 만드는데 가능한 경우의 수
        // dp[i] = dp[i] + dp[i - value]
        int[] dp = new int[k+1];
        dp[0] = 1;

        for(int i=0;i<n;i++) {
			for(int j=1;j<=k;j++) {
				if(j >= value[i]) dp[j] += dp[j - value[i]];
			}
		}

        bw.write(dp[k] + "\n");
        bw.close();
        br.close();
    }
}
