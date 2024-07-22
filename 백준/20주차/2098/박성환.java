import java.util.*;

public class BOJ_2098 {
    static int N;
    static final int INF = 16 * 1000000;
    static int[][] W, dp;
    static int res;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        W = new int[N][N];
        res = 0;

        // 비용 행렬 입력 받기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                W[i][j] = sc.nextInt();
            }
        }

        dp = new int[N][(1 << N) - 1];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }

        res = dfs(0, 1);

        System.out.println(res);
    }

    // now: 현재 위치한 도시
    // visit: 방문한 도시들을 비트마스크로 표현한 값
    static int dfs(int now, int visit) {
        // 모든 도시를 방문한 경우
        if (visit == (1 << N) - 1) {
            // 현재 도시에서 출발 도시로 돌아갈 수 없는 경우
            if (W[now][0] == 0) return INF;
            // 출발 도시로 돌아가는 비용 반환
            return W[now][0];
        }

        // 이미 계산된 값이 있는 경우
        if (dp[now][visit] != -1) return dp[now][visit];
        dp[now][visit] = INF;

        // 모든 도시를 탐색
        for (int i = 0; i < N; i++) {
            // 아직 방문하지 않은 도시이고 현재 도시에서 i번 도시로 갈 수 있는 경우
            if ((visit & (1 << i)) == 0 && W[now][i] != 0) {
                // 다음 도시를 방문하는 비용과 현재 저장된 최소 비용을 비교하여 갱신
                dp[now][visit] = Math.min(dfs(i, visit | (1 << i)) + W[now][i], dp[now][visit]);
            }
        }

        // 현재 도시에서 방문한 도시들의 집합을 기준으로 최소 비용 반환
        return dp[now][visit];
    }
}
