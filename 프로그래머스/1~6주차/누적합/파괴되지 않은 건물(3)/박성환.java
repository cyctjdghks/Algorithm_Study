import java.util.*;

class Solution {
    // board 의 행/열 길이는 1 ~ 1000
    // skill의 길이 1 ~ 250,000
    // skill의 각 행은 [type, r1, c1, r2, c2, degree]
    // type 1 : 공격
    // type 2 : 회복
    
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        
        int N = board.length;
        int M = board[0].length;
        
        // out of bound 방지
        int[][] sum = new int[N + 1][M + 1];
        
        // sum 배열 0으로 초기화
        for(int i = 0; i <= N; i++) {
            Arrays.fill(sum[i], 0);
        }
        
        for(int[] info : skill) {
            int type = info[0];
            
            int x1 = info[1];
            int y1 = info[2];
            
            int x2 = info[3];
            int y2 = info[4];
            
            // type == 1 이면 degree에 -1을 붙임
            int degree = (type == 1) ? -info[5] : info[5];
            
            
            // 누적합 공식(https://howudong.tistory.com/418 참고)
            sum[x1][y1] += degree;
            sum[x1][y2+1] += -degree;
            sum[x2+1][y1] += -degree;
            sum[x2+1][y2+1] += degree;
        }
        
        // 세로로 누적합
        for(int col = 0; col < M + 1; col++){
            for(int row = 0; row < N; row++){
                sum[row+1][col] += sum[row][col];
            }
        }
        
        // 가로로 누적합
        for(int row = 0; row < N + 1; row++){
            for(int col = 0; col < M; col++){
                sum[row][col+1] += sum[row][col];
            }
        }
        
        // sum 배열과 기존 board 배열을 더함
        for(int row = 0; row < N; row++){
            for(int col = 0; col < M; col++){
                board[row][col] += sum[row][col];
                // 해당칸의 값이 0을 넘으면 +1
                if(board[row][col] > 0) answer++;
            }
        }
        
        
        return answer;
    }
}