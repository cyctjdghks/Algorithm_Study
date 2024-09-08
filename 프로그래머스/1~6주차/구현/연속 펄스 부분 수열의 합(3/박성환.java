import java.util.*;

class Solution {
    // 1부터 시작 or -1부터 시작해서 번갈아 나오는 수열
    
    public long solution(int[] sequence) {
        long answer = 0;
        long ans1 = 0; // 1부터 시작
        long ans2 = 0; // -1부터 시작
        
        for(int i = 0; i < sequence.length; i++) {
            if(i % 2 == 0) {
                ans1 += sequence[i];
                ans2 -= sequence[i];
            } else {
                ans1 -= sequence[i];
                ans2 += sequence[i];
            }
            
            ans1 = Math.max(0, ans1);
            ans2 = Math.max(0, ans2);
            
            answer = Math.max(answer, Math.max(ans1, ans2));
        }
        
        return answer;
    }
}