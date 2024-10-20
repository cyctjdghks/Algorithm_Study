import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        // 야근을 하면 피로도가 쌓임
        // 야근 피로도는 남은 일의 작업량을 제곱하여 더한 값
        // 1시간 동안 1을 처리
        // 퇴근까지 남은 n 시간
        // 각 일에 대한 작업량 works
        
        long answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        
        for (int work : works) {
            pq.offer(work);
        }
        
        for (int i = 0; i < n; i++) {
            int max = (int)pq.poll();
            if (max <= 0) break;
            pq.offer(max - 1);
        }
        
        long sum = 0;
        while (!pq.isEmpty()) {
            sum += Math.pow(pq.poll(), 2);
        }
        
        answer = sum;
        
        return answer;
    }
}