import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_4781 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int money = (int) (Double.parseDouble(st.nextToken()) * 100 + 0.5);
        StringBuilder sb = new StringBuilder();
        while (n != 0) {

            int[] cal = new int[n];
            int[] cost = new int[money];
            for (int i = 0; i < n; ++i) {
                st = new StringTokenizer(br.readLine());
                cal[i] = Integer.parseInt(st.nextToken());
                cost[i] = (int) (Double.parseDouble(st.nextToken()) * 100 + 0.5);
            }

            int[] dp = new int[money + 1];
            for (int i = 0; i < n; ++i) {
                for (int j = 0; j < money + 1; ++j) {
                    int remainedMoney = j - cost[i];
                    if (0 <= remainedMoney) {
                        dp[j] = Math.max(dp[j], dp[remainedMoney] + cal[i]);
                    }
                }
            }

            sb.append(dp[money]).append("\n");

            // 다음 테스트 케이스
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            money = (int) (Double.parseDouble(st.nextToken()) * 100 + 0.5);
        }

        System.out.print(sb);
    }
}