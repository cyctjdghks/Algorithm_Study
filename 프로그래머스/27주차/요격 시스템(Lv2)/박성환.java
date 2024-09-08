import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        
        Arrays.sort(targets, Comparator.comparingInt(o1 -> o1[1]));
        
        int end = targets[0][1];
        answer++;
        
        for(int[] arr : targets){
            if(arr[0] >= end){
                end = arr[1];
                answer++;
            }
        }
        
        return answer;
    }
}