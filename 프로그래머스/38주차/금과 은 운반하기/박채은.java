import java.util.*;

// 마지막에는 왕복이 아니라 편도
class Solution {
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long answer = -1;

        long left = 1;
        long right = (long)(10e9 * 2 * 10e5 * 2);

        while(left <= right){
            long mid = (left + right) / 2;
            // 도시 순회
            int gold = 0;
            int silver = 0;
            int mix = 0;
            for(int i=0;i<g.length;i++){
                int weight = w[i];
                int time = t[i];

                // 몇 회 방문 가능한지
                long cnt = mid / (time*2);
                if(mid %(time*2) >= time){ // 마지막에 편도로 한번 더 방문
                    cnt+=1;
                }

                // mid 시간동안 이동할 수 있는 금, 은 갯수
                gold += Math.min(g[i], cnt*weight);
                silver += Math.min(s[i], cnt*weight);
                // 금,은을 같이 이동시키는 경우
                mix += Math.min(g[i]+s[i], cnt*weight);
            }

            // 운반량이 더 많은 경우에 시간 줄이기
            if(gold>=a && silver>=b && mix>=a+b){
                answer = mid;
                right = mid-1;
            }
            else{
                left = mid+1;
            }
        }

        return answer;
    }
}