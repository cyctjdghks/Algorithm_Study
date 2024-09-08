class Solution {
    // 집에서 학교까지 M*N
    // 집은 1, 1
    // 1,1 => m, n
    // 1 <= m, n <= 100
    // 잠긴 지역 0 ~ 10
    // 오른쪽과 아래쪽 => 오른쪽과 위쪽 으로만 이동
    
    static Integer[][] dp;
    static int MOD = 1000000007;
    
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;
        
        dp = new Integer[m + 1][n + 1];
        dp[1][1] = 1;
        
        for(int i = 0; i < puddles.length; i++) {
            dp[puddles[i][0]][puddles[i][1]] = 0;
        }
        
        answer = move(m, n);
        
        return answer % MOD;
    }
    
    public int move(int x, int y) {
        if(x < 1 || y < 1) return 0;
        
        if(dp[x][y] == null) {
            dp[x][y] = (move(x-1, y) + move(x, y-1)) % MOD;
        }
        
        
        return dp[x][y];
    }
}