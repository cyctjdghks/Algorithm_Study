import java.util.Scanner;

public class BOJ_12869 {

    static int[][] attack = {{-9,-3,-1},{-9,-1,-3},{-3,-9,-1},{-3,-1,-9},{-1,-9,-3},{-1,-3,-9}};
    static int[][][] dp;
    static int res;

    public static void main(String[] args) {
        // N 개의 scv
        // 각 체력
        // 첫 번째로 공격받는 SCV는 체력 9를 잃는다.
        // 두 번째로 공격받는 SCV는 체력 3을 잃는다.
        // 세 번째로 공격받는 SCV는 체력 1을 잃는다.
        // 1 ≤ N ≤ 3
        // 체력 <= 60

        Scanner sc = new Scanner(System.in);

        int N = sc.nextInt();
        int[] scv = new int[3];

        for (int i = 0; i < N; i++) {
            scv[i] = sc.nextInt();
        }

        dp = new int[61][61][61];
        res = Integer.MAX_VALUE;

        dfs(scv, 0);

        System.out.println(res);
    }

    private static void dfs(int[] scv, int cnt) {
        int scv1 = scv[0];
        int scv2 = scv[1];
        int scv3 = scv[2];

        if(res <= cnt) return;

        if(dp[scv1][scv2][scv3] != 0 && dp[scv1][scv2][scv3] <= cnt) return;

        dp[scv1][scv2][scv3] = cnt;

        if (scv1 == 0 && scv2 == 0 && scv3 == 0) {
            res = Math.min(res, cnt);
            return;
        }

        for (int i = 0; i < 6; i++) {
            dfs(new int[]{Math.max(scv1 + attack[i][0], 0), Math.max(scv2 + attack[i][1], 0), Math.max(scv3 + attack[i][2], 0)}, cnt + 1);
        }
    }
}
