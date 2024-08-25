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
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());
        int m = (int)(Double.parseDouble(st.nextToken()) * 100 + 0.5);

        while(n != 0) {
            // dp[j]: j원으로 얻을 수 있는 최대 칼로리
            int[] dp = new int[m+1];

            for(int i=0;i<n;i++) {
                st = new StringTokenizer(br.readLine());

                int c = Integer.parseInt(st.nextToken());
                int p = (int)(Double.parseDouble(st.nextToken()) * 100 + 0.5);

                // p<=j<=m까지 탐색하며 j값일 때 최대 칼로리를 갱신
                for(int j=p;j<=m;j++) {
                    dp[j] = Math.max(dp[j], dp[j-p] + c);
                }
            }
            sb.append(dp[m] + "\n");

            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());
            m = (int)(Double.parseDouble(st.nextToken()) * 100 + 0.5);
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
