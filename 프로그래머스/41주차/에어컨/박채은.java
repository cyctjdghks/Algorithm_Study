import java.util.*;
class Solution {
    static final int MAX = 100 * 1000 + 1;
    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {
        t1 += 10;
        t2 += 10;
        int temp = temperature + 10;

        int [][] DP = new int [onboard.length][51];
        for(int i = 0; i < onboard.length; i++){
            Arrays.fill(DP[i], MAX);
        }
        DP[0][temp] = 0; // 시작 온도 = 실외 온도

        /*
         *  1. 에어컨을 키고, 온도를 유지하는 경우
         *  2. 에어컨을 키고, 온도를 1도 변경하는 경우
         *  3. 에어컨을 끄고, 1도 변경하는 경우
         *  4. 에어컨을 끄고, 온도를 유지하는 경우 (특수한 경우 => 실내온도 == 현재온도)
         * */

        for(int i = 0; i < onboard.length - 1; i++){
            for(int j = 0; j <= 50; j++){
                // 승객 탑승 시점에 적정 온도가 아니라면
                if(onboard[i] == 1 && (j < t1 || t2 < j)) continue;
                // 에어컨 ON + 현재 온도 유지 (b)
                DP[i+1][j] = Math.min(DP[i+1][j], DP[i][j] + b);
                // 에어컨 ON + 현재 온도에서 down (a)
                if(j >= 1) DP[i+1][j-1] = Math.min(DP[i+1][j-1], DP[i][j] + a);
                // 에어컨 ON + 현재 온도에서 up (a)
                if(j < 50) DP[i+1][j+1] = Math.min(DP[i+1][j+1], DP[i][j] + a);

                // 에어컨 OFF
                if(temp == j){ // 온도 유지
                    DP[i+1][j] = Math.min(DP[i+1][j], DP[i][j]);
                }
                else if(temp > j && j < 50){ // 온도 up
                    DP[i+1][j+1] = Math.min(DP[i+1][j+1], DP[i][j]);
                }
                else if(temp < j && j >= 1){ // 온도 down
                    DP[i+1][j-1] = Math.min(DP[i+1][j-1], DP[i][j]);
                }
            }
        }
        int min = MAX;
        int lastMin = onboard.length - 1;
        for(int i = 0; i <= 50; i++){
            if(onboard[lastMin] == 1 && (i < t1 || t2 < i)) continue; // 마지막에 승객이 탑승했다면 적정 온도만 가능
            min = Math.min(min, DP[lastMin][i]);
        }
        return min;
    }
}