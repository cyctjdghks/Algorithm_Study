import java.util.*;

class Solution {
    public int[] solution(int e, int[] starts) {
        int startsLen = starts.length;
        int[] answer = new int[startsLen];
        int[] freqs = new int[e + 1]; // 특정 값이 출현하는 빈도수 정보
        int[] maxValues = new int[e + 1]; // 특정 값에서의 출현 빈도가 가장 많은 수

        // 억억단표에서 특정값이 출력되는 빈도수를 모두 저장하기
        for (int i = 1; i <= e; ++i) {
            for (int j = i; j <= e; j += i) {
                ++freqs[j];
            }
        }

        // e부터 1까지 거꾸로 탐색하여 최대 빈도 및 최대 빈도를 나타내는 값의 정보를 등록한다.
        // 1부터 e까지 점점 커질수록 출현 빈도가 많아져 오름차순이 보장되기 때문에 거꾸로 탐색하여 최대값 정보를 도출한다.
        // 거꾸로 탐색을 함으로써 특정 지점부터 e까지 최대 빈도수를 가진 값의 정보를 출력할 수 있다.
        int maxFreq = 0;
        int maxValue = 0;
        for (int i = e; i >= 1; --i) {
            if (freqs[i] >= maxFreq) {
                maxFreq = freqs[i];
                maxValue = i;
            }

            maxValues[i] = maxValue;
        }

        // 결과
        for (int i = 0; i < startsLen; ++i) {
            answer[i] = maxValues[starts[i]];
        }

        return answer;
    }
}