import java.util.*;
class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        // 1. 목표 알고력/코딩력 구하기
        int goalA = 0;
        int goalC = 0;

        for(int i=0;i<problems.length;i++){
            goalA = Math.max(goalA, problems[i][0]);
            goalC = Math.max(goalC, problems[i][1]);
        }

        // 이미 목표치를 달성한 경우
        if(alp>= goalA && cop>=goalC) return 0;
        else if(alp>= goalA) alp = goalA;
        else if(cop>= goalC) cop = goalC;

        // DP
        int[][] dp = new int[goalA+2][goalC+2];

        // 초기화
        for(int i=alp;i<goalA+1;i++){
            for(int j=cop;j<goalC+1;j++){
                dp[i][j] = Integer.MAX_VALUE;
            }
        }

        dp[alp][cop] = 0;

        for(int i=alp;i<=goalA;i++){
            for(int j=cop;j<=goalC;j++){
                // 2. 알고력 공부
                dp[i+1][j] = Math.min(dp[i][j] +1,dp[i+1][j]);

                // 3. 코딩력 공부
                dp[i][j+1] = Math.min(dp[i][j] +1,dp[i][j+1]);

                // 4. 문제풀기
                for(int[] problem : problems){
                    if(i>=problem[0] && j>=problem[1]){
                        int newAlp = Math.min(i + problem[2], goalA);
                        int newCop = Math.min(j + problem[3], goalC);
                        dp[newAlp][newCop] = Math.min(dp[newAlp][newCop], dp[i][j] + problem[4]);
                    }
                }
            }
        }

        return dp[goalA][goalC];
    }
}