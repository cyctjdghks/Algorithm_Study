import java.util.Arrays;

class Solution {
    public int solution(int[][] scores) {
        int[] wanho = scores[0];
        int wanhoTotal = wanho[0] + wanho[1];
        int maxScore = 0;
        int rank = 1;

        // 근무 태도 내림차순, 동료 평가 오름차순
        Arrays.sort(scores, (s1, s2) -> (s1[0] == s2[0]) ? s1[1] - s2[1] : s2[0] - s1[0]);

        // 동료 평가 비교
        // 동료 평가는 오름차순했기 때문에 i의 동료 평가 점수가 i-1의 동료 평가 점수보다
        // 높아야지만 인센티브를 받을 수 있다.
        for (int i = 0; i < scores.length; i++) {
            if (scores[i][1] < maxScore) { // 인센티브 못 받는 경우
                // 완호 점수인지 파악
                if (scores[i][0] == wanho[0] && scores[i][1] == wanho[1]) {
                    return -1;
                }
            } else {
                maxScore = scores[i][1];
                if (scores[i][0] + scores[i][1] > wanhoTotal) {
                    rank += 1;
                }
            }
        }
        return rank;
    }
}