import java.util.*;

class Solution {
    
    // 한변의 길이가 1인 정삼각형 2n+1개를 이어 붙여
    // 윗변의 길이 n
    // 아랫변의 길이 n+1
    
    static int MOD = 10007;
    static int[][] dp;
    
    public int solution(int n, int[] tops) {
        int answer = 0;
        
        dp = new int[n+1][2];
        
        dp[0][0] = 1;
        if(tops[0] == 1) dp[0][1] = 3;
        else dp[0][1] = 2;
        
        for(int i = 1; i < n; i++) {
            dp[i][0] = (dp[i-1][0] + dp[i-1][1]) % MOD;
            
            if(tops[i] == 1) {
                dp[i][1] = (dp[i-1][0] * 2 + dp[i-1][1] * 3) % MOD;
            } else {
                dp[i][1] = (dp[i-1][0] * 1 + dp[i-1][1] * 2) % MOD;
            }
        }
        
        answer = (dp[n-1][0] + dp[n-1][1]) % MOD;
        
        return answer;
    }
}