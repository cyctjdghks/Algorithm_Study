import java.util.Scanner;

public class BOJ_2302 {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int M = sc.nextInt();

        int[] dp = new int[41];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= N; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }

        int ans = 1;

        // vip 좌석을 제외한 나머지 좌석의 경우의 수를 서로 곱함.
        int beforeSeat = 0;
        for (int i = 0; i < M; i++) {
            int temp = sc.nextInt();
            ans *= dp[temp - beforeSeat - 1];
            beforeSeat = temp;
        }
        ans *= dp[N - beforeSeat]; // 마지막 vip 좌석 다음 좌석에서 끝 좌석까지의 경우의 수.

        System.out.println(ans);
    }

}