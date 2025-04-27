import java.util.*;

class Solution {
    public int solution(int[] a) {
        int n = a.length;
        if (n < 2) return 0;

        int[] count = new int[n];
        for (int num : a) {
            count[num]++;
        }

        int maxLength = 0;


        for (int num = 0; num < n; num++) {
            if (count[num] == 0) continue; // 등장 안한 숫자는 스킵
            if (count[num] * 2 <= maxLength) continue;
            // 이미 maxLength보다 길어질 수 없는 경우 스킵

            int pairCount = 0;
            for (int i = 0; i < n - 1; i++) {
                // num과 관련된 쌍만 고른다
                if ((a[i] == num || a[i + 1] == num) && (a[i] != a[i + 1])) {
                    pairCount++;
                    i++;
                }
            }
            maxLength = Math.max(maxLength, pairCount * 2);
        }

        return maxLength;
    }
}
