import java.util.*;

class Solution {
    public int solution(int[][] scores) {
        // 원호의 두 점수
        int[] he = scores[0];
        
        // 근무 태도 점수 내림차순정렬
        // 근무 태도 점수가 동점인 경우, 동료 평가 점수 오름차순
        Arrays.sort(scores, (a, b) -> a[0] == b[0] ? a[1] - b[1] : b[0] - a[0]);
        
        int answer = 1, maxScore = 0;

        for(int[] score : scores) {
            // 앞에 동료 평가 점수보다 높은 경우, 인센티브를 받지 못함
            if(score[1] < maxScore) {
                // 완호가 인센티브를 받지 못하는 경우
                if(score.equals(he)) return -1;
            } 
            else {
                maxScore = Math.max(maxScore, score[1]);
                // 현재 점수의 합이 완호 점수의 합보다 큰 경우 
                if(score[0] + score[1] > he[0] + he[1]) answer++;
            }
        }
        
        return answer;
    }
}