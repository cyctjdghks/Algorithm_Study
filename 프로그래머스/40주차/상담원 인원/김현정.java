import java.util.*;

class Solution {
    public class Point {
        int s, d;

        public Point(int s, int d) {
            this.s = s;
            this.d = d;
        }
    }

    List<Point>[] reqList;
    int[][] time;
    int K, N;

    public int solution(int k, int n, int[][] reqs) {
        //init
        reqList = new ArrayList[k];
        for (int i = 0; i < k; i++) reqList[i] = new ArrayList<>();
        for (int i = 0; i < reqs.length; i++)
            reqList[reqs[i][2] - 1].add(new Point(reqs[i][0], reqs[i][1]));
        requiredTime = new int[k][n - k + 2];
        K = k; N = n;
        for (int i = 0; i < k; i++)
            for (int j = 1; j < n - k + 2; j++)
                requiredTime[i][j] = calTime(i, j);
        return calTotalMinTime();
    }

    public int calTotalMinTime() {
        int remain = N - K;
        int[] cnt = new int[K];
        Arrays.fill(cnt, 1);
        while(remain-- > 0) {
            int maxDiff = 0;
            int maxIndex = 0;
            for (int i = 0; i < K; i++) {
                if (cnt[i] == N - K + 1) continue;
                int diff = requiredTime[i][mentorCnt[i]] - requiredTime[i][cnt[i] + 1];
                if (maxDiff < diff) {
                    maxDiff = diff;
                    maxIndex = i;
                }
            }
            cnt[maxIndex]++;
        }
        //결과 계산
        int sum = 0;
        for (int i = 0; i < K; i++)
            sum += requiredTime[i][cnt[i]];
        return sum;
    }
}
