import java.util.*;

class Solution {
    public static final int INF = -1;
    public int solution(int n, int[] money) {
        int answer = 0;
        Arrays.sort(money);
        
        // variable
        long[] d = new long[n+1];
    
        // 초기화(화폐 한개 지불 방법)
        for(int i =0; i<=n ;i++){
             if(i % money[0] == 0)
                 d[i]=1;
        }
        
        // 화폐단위 개수별로 for문
        for(int i=1; i<money.length ; i++){
            // 화폐 이전까지의 합 + 새로운 화폐로 낼 수 있는 방법
            for(int j=money[i]; j<=n; j++){
                d[j] += d[j-money[i]];
            }
        }
        
        // 형 변환
        answer = (int)(d[n] % 1000000007);
        return answer;
    }
}