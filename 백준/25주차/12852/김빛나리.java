import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();

        int X = Integer.parseInt(br.readLine());
        int[] dp = new int[X+1];
        int[] before = new int[X+1];

        dp[1] = 0;

        for(int i=2;i<=X;i++) {
            // 1을 빼는 경우
            dp[i] = dp[i - 1] + 1;
            before[i] = i - 1;

            // 3으로 나누는 경우
            if (i % 3 == 0 && dp[i] > dp[i / 3] + 1) {
                dp[i] = dp[i / 3] + 1;
                before[i] = i / 3;
            }
            // 2로 나누는 경우
            if (i % 2 == 0 && dp[i] > dp[i / 2] + 1) {
                dp[i] = dp[i / 2] + 1;
                before[i] = i / 2;
            }
        }

        sb.append(dp[X] + "\n");

        while(X > 0) {
            sb.append(X + " ");
            X = before[X];
        }

        bw.write(sb.toString() + "\n");
        bw.close();
        br.close();
    }
}
