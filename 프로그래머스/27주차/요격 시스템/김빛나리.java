import java.util.*;

class Solution {
    public int solution(int[][] targets) {
        int answer = 0;
        // 개구간 끝나는 지점
        int spot = Integer.MIN_VALUE;
        
        // 개구간 끝나는 지점을 기준으로 오름차순 정렬
        Arrays.sort(targets, (a, b) -> Integer.compare(a[1], b[1]));
        
        for(int[] target : targets) {
            if(spot < target[0]) {
                // 지점 이동
                spot = target[1] - 1;
                answer++;
            }
        }
        
        return answer;
    }
}