import java.util.*;


class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        
        Arrays.sort(targets, (o1, o2) -> {
            if(o1[1] == o2[1]){
                return o1[0] - o2[0];
            }
            return o1[1] - o2[1];
        });
        
        int idx = 0;
        int standard;
        while(idx < targets.length){
            standard=targets[idx][1];
            idx++;
            answer++;
            for(int i=idx;i<targets.length;i++){
                if(standard > targets[idx][1]){
                    standard = targets[idx][1];
                }

                if(targets[i][0] < standard){    
                    idx++;
                }
            else break;
            
            }

        }
        
        return answer;
    }
}