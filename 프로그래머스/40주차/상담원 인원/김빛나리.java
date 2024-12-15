import java.util.*;

class Solution {
    int type;
    int[][] request;
    
    public int solution(int k, int n, int[][] reqs) {
        type = k;
        request = reqs;
        int[] people = new int[k];

        return dfs(0, n, people);
    }
    
    int dfs(int depth, int mentor, int[] people) {
        if(depth == type) {
            if(mentor == 0) return calWaitTime(people);
            else return Integer.MAX_VALUE;
        }
        
        int result = Integer.MAX_VALUE;
        
        for(int i=1;i<=mentor;i++) {
            people[depth] = i;
            result = Math.min(result, dfs(depth+1, mentor-i, people));
        }
        
        return result;
    }
    
    int calWaitTime(int[] people) {
        int waitTime = 0;
        PriorityQueue<Integer>[] q = new PriorityQueue[type];
        
        for(int i=0;i<type;i++) {
            q[i] = new PriorityQueue<>();
        }
        
        for(int[] req : request) {
            int startTime = req[0];
            int duringTime = req[1];
            int reqType = req[2] - 1;
            int time = startTime;
            
            if(q[reqType].size() >= people[reqType]) {
                int endTime = q[reqType].poll();
                
                if(endTime > startTime) {
                    waitTime += endTime - startTime;
                    time = endTime;
                }
            }
            
            q[reqType].add(time + duringTime);
        }
        
        return waitTime;
    }
}