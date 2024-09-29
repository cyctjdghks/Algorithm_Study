import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        int answer = 1;

        int[] wh = scores[0];
        int sum = wh[0] + wh[1];
        
        Arrays.sort(scores, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if(o1[0] == o2[0]) { 
                    return o1[1] - o2[1];
                }
                return o2[0] - o1[0];
            }
        });    
        
        int max = Integer.MIN_VALUE;
        
        for(int i = 0; i < scores.length; i++) {
            if(scores[i][1] >= max) {
                max = Math.max(max, scores[i][1]);
                if(sum < scores[i][0] + scores[i][1]) {
                    answer++;
                }
            } else if(scores[i][1] < max) {
                if(wh[0] == scores[i][0] && wh[1] == scores[i][1]) {
                    return -1;
                }
            }
        } 
        
        return answer;
    }
}