import java.util.*;

class Solution {
    public long solution(int n, int[] works) {
        long answer = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i = 0; i < works.length; i++) {
            pq.offer(works[i]);
        }
        
        while(n > 0) {
            int i = pq.poll();
            if(i == 0) break;
            i -= 1;
            pq.offer(i);
            n -= 1;
        }
        
        for(int i : pq) {
            answer += i * i;
        }
        
        return answer;
    }
}