import java.util.*;

class Solution {
    
    static final int MAX = 20000001; // 200 * 100000 + 1
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        // 지점 개수 n, 3 <= n <= 200
        // 출발지점 s
        // A의 도착지점 a
        // B의 도착지점 b
        // 1 <= f <= 100,000
        
        int answer = 0;
        
        int[][] dp = new int[n + 1][n + 1];
        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], MAX);
            dp[i][i] = 0;
        }
        
        for(int i = 0; i < fares.length; i++) {
            int start = fares[i][0];
            int end = fares[i][1];
            int cost = fares[i][2];

            dp[start][end] = cost;
            dp[end][start] = cost;
        }
        
        for(int k = 1; k < n+1; k++) {
            for(int i = 1; i < n+1; i++) {
                for(int j = 1; j < n+1; j++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k][j]);
                }
            }
        }
        
        answer = dp[s][a] + dp[s][b];
        // 경유지점을 끼는경우
        for(int i = 1; i <= n; i++) {
            answer = Math.min(answer, dp[s][i] + dp[i][a] +dp[i][b]);
        }
        return answer;
    }
}
