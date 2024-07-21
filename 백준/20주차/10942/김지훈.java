package coplit.dailyCoding.study.백준;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_10942 {

    // 1. 수열을 입력받는다.
    // 2. 질문을 받고, 그 범위의 수열이 팰린드롬인지 확인한다.

    // 만약 1-10까지가 팰린드롬이라면 2-9, 3-8, 4-7, 5-6도 모두 팰린드롬이 된다.
    // 따라서 dp로 푼다

    public static int N, M;
    public static int[] arr;
    public static int[][] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        arr = new int[N + 1];
        dp = new int[N + 1][N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 1; i <= N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        solve();

        M = Integer.parseInt(br.readLine());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            if (dp[start][end] == 1) bw.write("1\n");
            else bw.write("0\n");
        }

        bw.flush();
    }

    public static void solve() {

        // 한자리 수는 모두 팰린드롬
        for (int i = 1; i <= N; i++) {
            dp[i][i] = 1;
        }

        // 두자리수 일땐 두 수가 같으면 팰린드롬
        for (int i = 1; i <= N - 1; i++) {
            if (arr[i] == arr[i + 1]) {
                dp[i][i + 1] = 1;
            }
        }

        // 세자리수 이상일땐 처음과 끝숫자가 같으면서 두번째와 마지막에서 두번째가 팰린드롬이면 팰린드롬
        for (int i = 2; i < N; i++) {
            for (int j = 1; j <= N - i; j++) {
                if (arr[j] == arr[j + i] && dp[j + 1][j + i - 1] == 1) {
                    dp[j][j + i] = 1;
                }
            }
        }

    }


}