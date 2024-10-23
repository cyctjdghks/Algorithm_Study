import java.util.*;

class Solution {
    
    public int solution(int n, int k, int[] enemy) {
        // n 명의 병사
        // 매 라운드 적 등장
        // 1명의 병사 = 1명의 적
        // 무적권 존재
        // 무적권은 k번 사용 가능
        // 최대한 많은 라운드 진행
        // 병사 수 n 무적권 횟수 k 적 enemy
        
        int answer = enemy.length;
        int N = n;
        int K = k;
        
        Queue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for(int i = 0; i < enemy.length; i++) {
            N -= enemy[i];
            pq.add(enemy[i]);
            
            if(N < 0) {
                if(K > 0 && !pq.isEmpty()) {
                    N += pq.poll();
                    K--;
                } else {
                    answer = i;
                    break;
                }
            }
        }
        
        return answer;
    }
}