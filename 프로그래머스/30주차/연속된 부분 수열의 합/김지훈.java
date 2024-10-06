public class Solution {
    public int[] solution(int[] sequence, int k) {
        int start = 0, end = 0, sum = sequence[0];
        int minLen = Integer.MAX_VALUE;
        int[] answer = new int[2];

        while (start <= end && end < sequence.length) {
            if (sum == k) {
                // 현재 부분 수열의 길이가 더 짧은 경우
                if (end - start < minLen) {
                    minLen = end - start;
                    answer[0] = start;
                    answer[1] = end;
                }
                // sum이 k와 같을 때 start를 오른쪽으로 이동
                sum -= sequence[start];
                start++;
            } else if (sum < k) {
                // sum이 k보다 작으면 end를 오른쪽으로 이동하여 합을 증가
                end++;
                if (end < sequence.length) {
                    sum += sequence[end];
                }
            } else {
                // sum이 k보다 크면 start를 오른쪽으로 이동하여 합을 감소
                sum -= sequence[start];
                start++;
            }
        }

        return answer;
    }
}