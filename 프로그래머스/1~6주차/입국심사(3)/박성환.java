import java.util.*;

class Solution {
    
    // n 명이 줄을 서서 기다리는 중
    // 심사관 times.length 명
    // 동시에 한명씩 심사
    // 첫 시작 시 1명씩 가서 심사 받음
    // times[i] 초가 흐르면 다음 사람 심사
    // 우선 times 를 오름차순으로 정렬
    // 최소 시간 = left (0)
    // 가능한 가장 오래 걸리는 시간 = right
    
    
    public long solution(int n, int[] times) {
        long answer = 0;
        
        Arrays.sort(times);
        
        long left = 0;
        long right = (long) times[times.length - 1] * n;
        
        while(left <= right) {
            long mid = (left + right) / 2;
            long check = 0;
            
            for(int time : times) {
                check += mid / time;
            }
            
            if(check < n) {
                left = mid + 1;
            } else {
                right = mid - 1;
                answer = mid;
            }
        }
        
        return answer;
    }
}