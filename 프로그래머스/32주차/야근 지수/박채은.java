import java.util.*;
class Solution {
    public long solution(int n, int[] works) {
        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i = 0; i < works.length; i++) {
            pq.offer(works[i]);
        }

        while(n>0){
            int work = pq.poll();
            if(work == 0) break;
            work -= 1;
            pq.offer(work);
            n -= 1;
        }

        long result = 0;
        for(int work : pq) {
            result += work * work;
        }
        return result;
    }
}