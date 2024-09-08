import java.util.*;
class Solution {
    public long solution(int n, int[] times) {
        // 오름차순 정렬
        Arrays.sort(times);

        long left = 0;
        long right = (long) times[times.length-1] * n;
        long min = right;

        while(left <= right){
            long mid = (left+right) / 2;

            // mid 시간 안에 몇명을 심사할 수 있는지
            long total = 0;
            for(long time : times){
                total += (mid / time);
            }

            // total < n (시간이 더 필요한 경우)
            if(total < n){
                left = mid + 1;
            }
            else{ // total <= n (조건을 충족하나 최소값인지는 확신할 수 없음)
                right = mid - 1;
                min = mid;
            }
        }

        return min;
    }
}