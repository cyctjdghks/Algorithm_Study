class Solution {
    public int solution(int n, int[] money) {
        // 각 index가 n일 때, 거슬러 줄 방법의 수
        long[] dp = new long[n+1];

        for(int i=1;i<money.length;i++) {
            for(int j=0;j<=n;j++) {
                // 제일 처음에만 실행되도록
                // money[0]을 사용했을 때, 나누어 떨어지는 경우 -> 무조건 1가지수
                if(i == 1 && j % money[0] == 0) dp[j] = 1;
                
                // 해당 index j를 money만큼 뺐을 때, 경우의 수 더하기
                if(j >= money[i]) dp[j] += dp[j-money[i]];
            }
        }

        return (int)(dp[n] % 1000000007);
    }
}