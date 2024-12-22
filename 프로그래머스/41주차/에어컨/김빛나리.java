import java.util.*;

class Solution {
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        temperature += 10;
        t1 += 10;
        t2 += 10;
        
        int[][] dp = new int[onboard.length][51];
        
        for (int i = 0; i < dp.length; i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        }
        dp[0][temperature] = 0;
        
        for(int i=1;i<onboard.length;i++) {
            int startTemp = 0;
            int endTemp = 50;
            
            if(onboard[i] == 1) {
                startTemp = t1;
                endTemp = t2;
            }

            for(int j=startTemp;j<=endTemp;j++) {
                if(j > 0 && dp[i-1][j-1] != Integer.MAX_VALUE) {
                    if(j > temperature)
                        dp[i][j] = Math.min(dp[i][j], dp[i-1][j-1] + a);
                    else
                        dp[i][j] = Math.min(dp[i][j], dp[i-1][j-1]);
                }
                if(dp[i-1][j] != Integer.MAX_VALUE) {
                    if (j == temperature)
                        dp[i][j] = Math.min(dp[i][j], dp[i-1][j]);
                    else
                        dp[i][j] = Math.min(dp[i][j], dp[i-1][j] + b);
                }
                if (j < 50 && dp[i-1][j+1] != Integer.MAX_VALUE) {
                    if (j < temperature) 
                        dp[i][j] = Math.min(dp[i][j], dp[i-1][j+1] + a);
                    else
                        dp[i][j] = Math.min(dp[i][j], dp[i-1][j+1]);
                }
            }

        }
        
        int answer = Integer.MAX_VALUE;
        
        for(int num : dp[onboard.length-1]) {
            answer = Math.min(answer, num);
        }
        
        return answer;
    }
}