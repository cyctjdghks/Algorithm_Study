import java.util.*;

class Solution {
    public int solution(int n, int k, int[] enemy) {
        // queue는 무적권을 사용해서 방어할 라운드의 적의 수
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        
        for(int i=0;i<enemy.length;i++) {
            // 각 라운드의 적의 수를 queue에 담기
            queue.add(enemy[i]);
            
            // 무적권의 수보다 방어할 라운드가 클 경우, 적의 수가 가장 적은 라운드에 병사 소모
            if(queue.size() > k) n -= queue.poll();
            
            // 병사가 0보다 작을 경우, 해당 라운드를 반환
            if(n < 0) return i;
        }
        
        // 게임 클리어한 경우, 총 라운드 수 반환
        return enemy.length;
    }
}