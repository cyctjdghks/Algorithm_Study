// 문제 난이도 <= 숙련도 : time_cur
// 문제 난이도 > 숙련도 : (diff - level) *(time_prev + time_cur) + time_cur

// limit 내에 퍼즐을 모두 해결하기 위한 최솟값
import java.util.*;

class Solution {
    public int solution(int[] diffs, int[] times, long limit) {
        int left = 1;
        int right = Arrays.stream(diffs).max().getAsInt();

        while(left < right){
            int mid = (left + right) / 2;

            // 총 시간 구하기
            long value = totalTime(diffs, times, mid);

            if(value <= limit){
                right = mid;
            }
            else{
                left = mid +1;
            }
        }
        return right;
    }

    public long totalTime(int[] diffs, int[] times, int level){
        long total = 0;
        for(int i = 0;i<diffs.length;i++){
            if(diffs[i] <= level){
                total += times[i];
            }
            else{
                int time_prev = (i==0) ? 0 : times[i-1];
                total += ((diffs[i] - level) *(time_prev + times[i])) + times[i];
            }
        }
        return total;
    }
}


