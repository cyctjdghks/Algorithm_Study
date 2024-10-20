import java.util.*;
class Solution {
    public int solution(int n, int k, int[] enemy) {
        int answer = enemy.length;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());

        for (int i = 0; i < enemy.length; i++) {
            n -= enemy[i];
            pq.add(enemy[i]);

            if(n<0){ // 가장 높은 숫자에 무적권을 사용
                if(k > 0 && !pq.isEmpty()){
                    int max = pq.poll();
                    n+= max;
                    k--;
                }
                else{ // 무적권이 없음
                    answer = i;
                    break;
                }
            }
        }

        return answer;
    }
}