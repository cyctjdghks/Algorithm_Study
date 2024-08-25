import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        int[] dp = new int[n+1];
        int[] trace = new int[n+1];


        dp[0]=0;
        dp[1]=0;
        for(int i=2;i<=n;i++){
            dp[i] = dp[i-1] + 1;
            trace[i] = i-1;

            if(i%2==0 && dp[i] > dp[i/2] + 1){
                dp[i] = dp[i/2] + 1;
                trace[i] = i/2;
            }

            if(i%3==0 && dp[i] > dp[i/3] + 1){
                dp[i] = dp[i/3] + 1;
                trace[i] = i/3;
            }

        }

        System.out.println(dp[n]);
        System.out.print(n + " ");
        while(true){
            if(trace[n] == 0) break;
            System.out.print(trace[n] + " ");
            n=trace[n];
        }

    }

}