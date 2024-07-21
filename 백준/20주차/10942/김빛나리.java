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
        StringBuilder sb = new StringBuilder();

        // 홍준이가 칠판에 적은 수의 개수
        int N = Integer.valueOf(br.readLine());
        // 홍준이가 칠판에 적은 수
        int[] nums = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<N;i++) {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[N][N];

        for(int i=0;i<N;i++) {
            // 길이가 1인 경우, 팰린드롬
            dp[i][i] = 1;

            // 길이가 2인 경우, 앞뒤 숫자가 같으면 팰린드롬
            if(i != N-1 && nums[i] == nums[i+1]) dp[i][i+1] = 1;
        }

        // 길이가 3 이상인 경우, 양끝 숫자가 같고 + 그 사이 숫자들이 팰린드롬 = 팰린드롬
        for(int i=2;i<=N;i++) {
            for(int j=0;j<N-i;j++) {
                if(nums[j] == nums[j+i] && dp[j+1][j+i-1] == 1)  dp[j][j+i] = 1;
            }
        }

        // 홍준이가 한 질문의 개수
        int M = Integer.valueOf(br.readLine());

        for(int i=0;i<M;i++) {
            st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int E = Integer.parseInt(st.nextToken());

            // S, E의 위치에서 -1 = 해당 위치의 값이 저장된 배열의 index
            sb.append(dp[S-1][E-1] + "\n");
        }

        bw.write(sb.toString());
        bw.close();
    }
}
