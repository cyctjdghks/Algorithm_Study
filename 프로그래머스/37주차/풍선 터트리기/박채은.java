import java.util.*;

class Solution {
    public int solution(int[] a) {
        // min이 남는 경우
        int answer = 1;

        int min = Arrays.stream(a).min().getAsInt();
        int min_index = -1;
        for (int i = 0; i < a.length; i++) {
            if (a[i] == min) {
                min_index = i;
                break;
            }
        }

        int left_min = Integer.MAX_VALUE;
        for(int i=0;i<min_index;i++){
            if(left_min >= a[i]){
                left_min = a[i];
                answer+=1;
            }
        }

        int right_min = Integer.MAX_VALUE;
        for(int i=a.length-1;i>min_index;i--){
            if(right_min >= a[i]){
                right_min = a[i];
                answer+=1;
            }
        }

        return answer;
    }
}