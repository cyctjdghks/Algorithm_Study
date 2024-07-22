import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M, S, E;
    static int[] arr;
    static boolean[][] dp;

    public static void main(String[] args) throws Exception {
        init();
        solution();
    }

    private static void init() throws Exception {
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        dp = new boolean[N][N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            dp[i][i] = true;
        }
        for (int i = 0; i < N - 1; i++) {
            if (arr[i] == arr[i+1]) {
                dp[i][i+1] = true;
            }
        }
        for (int i = N - 2; i >= 0; i--) {
            for (int j = i + 2; j < N; j++) {
                if (arr[i] == arr[j] && dp[i + 1][j - 1]) {
                    dp[i][j] = true;
                }
            }
        }
    }

    private static void solution() throws Exception {
        M = Integer.parseInt(br.readLine());
        for (int i = 0; i< M; i++) {
            st = new StringTokenizer(br.readLine());
            S = Integer.parseInt(st.nextToken()) - 1;
            E = Integer.parseInt(st.nextToken()) - 1;
            sb.append(dp[S][E] ? "1\n" : "0\n");
        }
        System.out.println(sb);
    }
}