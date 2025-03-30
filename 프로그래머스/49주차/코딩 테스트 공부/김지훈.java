import java.util.*;

class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int maxAlp = 0, maxCop = 0;

        // 필요한 최대 알고력, 코딩력 구하기
        for (int[] p : problems) {
            maxAlp = Math.max(maxAlp, p[0]);
            maxCop = Math.max(maxCop, p[1]);
        }

        // 현재 능력이 이미 충분하다면 굳이 더 키울 필요 x
        alp = Math.min(alp, maxAlp);
        cop = Math.min(cop, maxCop);

        int[][] dp = new int[maxAlp + 2][maxCop + 2];
        for (int[] row : dp) Arrays.fill(row, Integer.MAX_VALUE);
        dp[alp][cop] = 0;

        for (int i = alp; i <= maxAlp; i++) {
            for (int j = cop; j <= maxCop; j++) {
                // 알고력 1 증가
                dp[i + 1][j] = Math.min(dp[i + 1][j], dp[i][j] + 1);
                // 코딩력 1 증가
                dp[i][j + 1] = Math.min(dp[i][j + 1], dp[i][j] + 1);

                // 문제 풀기
                for (int[] p : problems) {
                    int reqAlp = p[0], reqCop = p[1];
                    int gainAlp = p[2], gainCop = p[3], cost = p[4];

                    if (i >= reqAlp && j >= reqCop) {
                        int newAlp = Math.min(maxAlp, i + gainAlp);
                        int newCop = Math.min(maxCop, j + gainCop);
                        dp[newAlp][newCop] = Math.min(dp[newAlp][newCop], dp[i][j] + cost);
                    }
                }
            }
        }

        return dp[maxAlp][maxCop];
    }
}
