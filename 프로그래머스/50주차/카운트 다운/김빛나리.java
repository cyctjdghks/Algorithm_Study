import java.util.*;

// dp를 사용해서 [50, *1, *2, *3] 4가지 경우의 수

class Solution {
    // 싱글, 불 
    ArrayList<Integer> SB = new ArrayList<>();
    // 더블, 트리플
    ArrayList<Integer> DT = new ArrayList<>();
    int[][] dp;
    int INF = Integer.MAX_VALUE-1;
    
    public int[] solution(int target) {
        dp = new int[target + 1][2];

        for(int i=1;i<=target;i++) {
            // for(int j=0;j<1;j++) {
            //     dp[i][j] = Integer.MAX_VALUE;
            // }
            dp[i][0] = INF;
            dp[i][1] = INF;
        }
        
        // 불
        SB.add(50);
        // 싱글
        for(int i=1;i<21;i++) {
            SB.add(i);
        }
        
        for(int i=1;i<21;i++) {
            for(int j=2;j<4;j++) {
                // 싱글에 이미 20까지 존재하니까 ?
                if(i * j <= 20) continue;
                
                DT.add(i*j);
            }
        }

        return solve(target);
    }
    
    public int[] solve(int target) {
        // 0일 경우
        if(target == 0) return new int[] {0, 0};
        // 0보다 작을 경우
        if(target < 0) return new int[] {INF, INF};
        // dp에 존재할 경우
        if(dp[target][0] != INF) return dp[target];

        int[] result = new int[] {INF, INF};

        // 싱글, 불 숫자 순회
        for(int i=0;i<SB.size();i++) {
            int[] tmpRes = solve(target - SB.get(i));
            
            // result와 tmpRes 비교 -> result가 최솟값이 되도록 설정
            setMin(result, new int[] {tmpRes[0] + 1, tmpRes[1] + 1});
        }
        
        // 더블, 트리플 숫자 순회
        for(int i=0;i<DT.size();i++) {
            int[] tmpRes = solve(target - DT.get(i));
            
            // result와 tmpRes 비교 -> result가 최솟값이 되도록 설정
            setMin(result, new int[] {tmpRes[0] + 1, tmpRes[1]});;
        }

        dp[target][0] = result[0];
        dp[target][1] = result[1];
        
        return dp[target];
    }
    
    public void setMin(int[] res, int[] comp) {
        if(res[0] > comp[0]) {
            res[0] = comp[0];
            res[1] = comp[1];
        } 
        else if(res[0] == comp[0]) {
            if(res[1] < comp[1]) res[1] = comp[1];
        }
    }
}