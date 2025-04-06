class Solution {
    public int[] solution(int target) {
        // dp[i] = {최소 다트 수, 싱글/불 횟수}
        int[][] dp = new int[target + 1][2];

        // 초기화
        for (int i = 1; i <= target; i++) {
            dp[i][0] = Integer.MAX_VALUE; // 최소 다트 수 초기화
            dp[i][1] = 0; // 싱글/불 횟수
        }

        for (int i = 1; i <= target; i++) {
            // 1~20 싱글
            for (int s = 1; s <= 20; s++) {
                if (i >= s) {
                    update(dp, i, s, dp[i - s], true);
                }
                if (i >= s * 2) {
                    update(dp, i, s * 2, dp[i - s * 2], false);
                }
                if (i >= s * 3) {
                    update(dp, i, s * 3, dp[i - s * 3], false);
                }
            }
            // 불 (50점)
            if (i >= 50) {
                update(dp, i, 50, dp[i - 50], true);
            }
        }

        return dp[target];
    }

    // dp[i] 를 갱신
    private void update(int[][] dp, int now, int score, int[] prev, boolean isSingleOrBull) {
        int newDarts = prev[0] + 1;
        int newSingleCount = prev[1] + (isSingleOrBull ? 1 : 0);

        if (newDarts < dp[now][0]) {
            dp[now][0] = newDarts;
            dp[now][1] = newSingleCount;
        } else if (newDarts == dp[now][0]) {
            // 다트 수가 같으면 싱글/불 횟수 많은 쪽 선택
            dp[now][1] = Math.max(dp[now][1], newSingleCount);
        }
    }
}
