import java.util.*;

// 0 행열부터 시작
// 역추적 - 역추적시에 벽에 부딪히는 부분을 주의
class Solution {
    public long solution(int n, int m, int x, int y, int[][] queries) {
        long answer = 0;
        int len = queries.length;

        // 공이 존재할 수 있는 범위
        // 초기화 - x행 y열
        long x1 = y, x2 = y;
        long y1 = x, y2 = x;

        for(int i=len-1;i>=0;i--){
            int mode = queries[i][0];
            int dist = queries[i][1];

            if(mode == 0){ // -> (기존 <-)
                // x값이 감소해서 벽에 부딪힌 경우 고려 - x1값을 그대로(x1에서 감소해서 그대로 고정되어 있을 수 있기 때문에)
                // 아닌 경우 - x1값을 이동
                if(x1 != 0){
                    x1 += dist;
                }
                x2 += dist;
                // 경계를 넘어가는지 확인
                if(x2 > m-1){
                    x2 = m-1;
                }
            }else if(mode ==1){ // <- (기존 ->)
                x1 -= dist;
                if(x1 < 0){
                    x1 = 0;
                }
                // 벽 부딪힘 때문에
                if(x2 != m-1){
                    x2 -= dist;
                }
            }else if(mode ==2){ // 아래 (기존은 위로)
                if(y1 != 0){
                    y1 += dist;
                }
                y2 += dist;
                // 경계를 넘어가는지 확인
                if(y2 > n-1){
                    y2 = n-1;
                }
            }else if(mode ==3){ // 위
                y1 -= dist;
                if(y1 < 0){
                    y1 = 0;
                }
                if(y2 != n-1){
                    y2 -= dist;
                }
            }
            if(x2 < x1 || y2 < y1){
                return 0;
            }
        }
        answer = (x2-x1 + 1) * (y2-y1 +1);
        return answer;
    }
}