import java.util.*;

class Solution {

    public boolean isPossible(long time, int a, int b, int[] g, int[] s, int[] w, int[] t) {
        int n = g.length;
        long total = 0L;
        long totalG = 0L;
        long totalS = 0L;

        for (int i = 0; i < n; i++) {
            long cnt = time / (2L * t[i]);
            if (time % (2L * t[i]) >= t[i]) cnt++;

            long tmp = Math.min(cnt * w[i], g[i] + s[i]);
            total += tmp;
            totalG += Math.min(tmp, g[i]);
            totalS += Math.min(tmp, s[i]);
        }

        if (total >= a+b && totalG >= a && totalS >= b) return true;
        return false;
    }

    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long hi = 400000000000000L;
        long lo = 0;

        while(lo + 1 < hi) {
            long mid = (lo + hi) / (long)2;

            if (isPossible(mid, a, b, g, s, w, t)) hi = mid;
            else lo = mid;
        }
        return hi;
    }
}