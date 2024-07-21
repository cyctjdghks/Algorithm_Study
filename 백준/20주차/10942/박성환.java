import java.util.Scanner;

public class BOJ_10942 {

    static int N, M;
    static int[] arr;
    static int[][] dp;

    public static void main(String[] args) {

        StringBuilder sb = new StringBuilder();

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        arr = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            arr[i] = sc.nextInt();
        }

        dp = new int[N + 1][N + 1];

        for (int length = 1; length <= N; length++) {
            for (int i = 1; i + length - 1 <= N; i++) {
                int j = i + length - 1;

                if (i == j) {
                    dp[i][j] = 1; // 길이가 1인 경우 팰린드롬
                } else if (j - i == 1) {
                    dp[i][j] = (arr[i] == arr[j]) ? 1 : 0; // 길이가 2인 경우 팰린드롬 체크
                } else {
                    dp[i][j] = (arr[i] == arr[j] && dp[i + 1][j - 1] == 1) ? 1 : 0; // 길이가 3 이상인 경우 팰린드롬 체크
                }
            }
        }

        M = sc.nextInt();
        for (int i = 0; i < M; i++) {
            int start = sc.nextInt();
            int end = sc.nextInt();

            sb.append(dp[start][end]).append("\n");
        }

        System.out.println(sb);

        sc.close();
    }
}