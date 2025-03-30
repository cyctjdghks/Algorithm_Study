// DP 두가지 접근
// Bottom_UP -> 작은 조각들을 모아 목표치를 만드는 것
// Top_Down -> 목표치를 잘게 쪼게어 작은 조각들을 더하는 식

// 초기 알고력, 초기 코딩력이 주어지는 상황 -> Bottom_UP 사용
// 시간복잡도 O(목표알고력*코딩알고력*문제길이)
class Solution {
    public int solution(int alp, int cop, int[][] problems) {
        int goalAl = 0, goalCo = 0;
        
        // 가장 높은 알고력, 코딩력 구하기
        for(int i=0;i<problems.length;i++) {
            goalAl = Math.max(problems[i][0], goalAl);
            goalCo = Math.max(problems[i][1], goalCo);
        }
        
        // 이미 초기 알고력, 코딩력이 가장 높은 경우
        if(goalAl <= alp && goalCo <= cop) return 0;
        
        // 가장 높은 알고력, 코딩력으로 DP 배열 초기화할 예정
        // 그것보다 큰 초기 알고력, 코딩력인 경우, 값 갱신
        if(alp >= goalAl) alp = goalAl;
        if(cop >= goalCo) cop = goalCo;
        
        int[][] dp = new int[goalAl+2][goalCo+2];
        
        for(int i=alp;i<=goalAl;i++) {
            for(int j=cop;j<=goalCo;j++) {
                dp[i][j] = Integer.MAX_VALUE;
          }
        }
        
        dp[alp][cop] = 0;
        
        for(int i=alp;i<=goalAl;i++) {
            for(int j=cop;j<=goalCo;j++) {
                // 알고력 트레이닝
                dp[i+1][j] = Math.min(dp[i+1][j], dp[i][j]+1);
                // 코딩력 트레이닝
                dp[i][j+1] = Math.min(dp[i][j+1], dp[i][j]+1);
                
                // 0, 1 : 문제를 푸는데 필요한 알고력, 코딩력
                // 2, 3 : 문제를 풀었을 때 증가하는 알고력, 코딩력
                // 4 : 문제를 푸는데 드는 시간
                for(int[] p :problems) {
                    // 현재 알고력, 코딩력이 해당 문제의 알고력, 코딩력에 도달한 경우
                    if(i >= p[0] && j >= p[1]) {
                        // 증가된 알고력, 코딩력이 구했던 목표치를 넘긴 경우
                        if(i+p[2] > goalAl && j+p[3] > goalCo) {
                            dp[goalAl][goalCo] = Math.min(dp[goalAl][goalCo], dp[i][j]+p[4]);
                        }
                        // 증가된 알고력만 구했던 목표치를 넘긴 경우
                        else if(i+p[2] > goalAl) {
                            dp[goalAl][j+p[3]] = Math.min(dp[goalAl][j+p[3]], dp[i][j]+p[4]);
                        }
                        // 증가된 코딩력만 구했던 목표치를 넘긴 경우
                        else if(j+p[3] > goalCo) {
                            dp[i+p[2]][goalCo] = Math.min(dp[i+p[2]][goalCo], dp[i][j]+p[4]);
                        }
                        // 증가된 알고력, 코딩력이 구했던 목표치를 넘기지 못한 경우
                        else {
                            dp[i+p[2]][j+p[3]] = Math.min(dp[i+p[2]][j+p[3]], dp[i][j]+p[4]); 
                        }
                    }
                }
            }
        }
        
        return dp[goalAl][goalCo];
    }
}