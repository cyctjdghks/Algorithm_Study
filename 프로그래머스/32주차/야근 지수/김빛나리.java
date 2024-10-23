import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        // 내림차순 정렬
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        
        // 남은 일의 작업량 내림차순으로 정렬
        for(int work : works) {
            queue.add(work);
        }
        
        for(int i=0;i<n;i++) {
            // 작업량이 0일 경우, 남은 작업량이 없다고 판단
            if(queue.peek() == 0) return 0;
            
            queue.add(queue.poll() - 1);
        }
        
        while(!queue.isEmpty()) {
            answer += Math.pow(queue.poll(), 2);
        }

        return answer;
    }
}