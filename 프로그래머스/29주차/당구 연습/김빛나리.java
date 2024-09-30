import java.util.*;

class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];
        
        for(int i=0;i<balls.length;i++) {
            // 벽을 기준으로 접었다 핀 위치
            int[][] position = {{-balls[i][0], balls[i][1]}, {balls[i][0], -balls[i][1]}, {2*m-balls[i][0], balls[i][1]}, {balls[i][0], 2*n-balls[i][1]}};
            // 최솟값 구하기위해 사용
            PriorityQueue<Integer> queue = new PriorityQueue<>();
            
            for(int[] p : position) {
                // x의 위치가 동일할 경우
                if(startX == balls[i][0]) {
                    // y의 위치가 start와 이동 위치 사이에 존재하지 않는 경우
                    if((startY < balls[i][1] && balls[i][1] < p[1]) || (startY > balls[i][1] && balls[i][1] > p[1])) continue;
                }
                // y의 위치가 동일할 경우
                else if(startY == balls[i][1]) {
                    // x의 위치가 start와 이동 위치 사이에 존재하지 않는 경우
                    if((startX < balls[i][0] && balls[i][0] < p[0]) || (startX > balls[i][0] && balls[i][0] > p[0])) continue;
                }
                
                // 피타고라스 정리
                queue.add((int)Math.pow(startX - p[0], 2) + (int)Math.pow(startY - p[1], 2));
            }
            
            // 최솟값
            answer[i] = queue.poll();
        }
        
        return answer;
    }
}