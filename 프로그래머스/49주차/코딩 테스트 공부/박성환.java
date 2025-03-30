import java.util.*;

class Solution {

    static final int INF = 1_000_000;
    int maxAlp;
    int maxCop;

    public int solution(int alp, int cop, int[][] problems) {
        for (int[] problem : problems) {
            maxAlp = Math.max(maxAlp, problem[0]);
            maxCop = Math.max(maxCop, problem[1]);
        }

        int[][] dp = new int[maxAlp + 1][maxCop + 1];

        alp = Math.min(alp, maxAlp);
        cop = Math.min(cop, maxCop);

        for (int[] ints : dp) {
            Arrays.fill(ints, INF);
        }

        dp[alp][cop] = 0;

        for (int i = alp; i <= maxAlp; i++) {
            for (int j = cop; j <= maxCop; j++) {
                // 기본 문제 풀 경우
                if (i + 1 <= maxAlp) {
                    dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
                }
                if (j + 1 <= maxCop) {
                    dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);
                }
                // 선택 문제 풀 경우
                for (int[] problem : problems) {
                    if (problem[0] <= i && problem[1] <= j) { // 풀 수 있는 경우
                        int nextAlp = Math.min(maxAlp, i + problem[2]); // maxAlp 범위를 초과하는 경우
                        int nextCop = Math.min(maxCop, j + problem[3]); // maxCop 범위를 초과하는 경우
                        dp[nextAlp][nextCop] = Math.min(dp[nextAlp][nextCop], dp[i][j] + problem[4]);
                    }
                }
            }
        }
        return dp[maxAlp][maxCop];
    }
}