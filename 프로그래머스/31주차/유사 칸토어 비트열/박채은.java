class Solution {
    public int solution(int n, long l, long r) {
        // (1~r) - (1~l)
        return (int)(count(n, r) - count(n, l-1));
    }

    // k까지의 1개의 개수
    private long count(int n, long k) {
        if (n == 0) {
            return 1;
        }

        long prev = n-1;
        long divisor = (long) Math.pow(5, prev);
        long numOfOne = (long) Math.pow(4, prev);
        // 위치한 범위를 파악
        long zone = (int) (k / divisor);
        if ((k % divisor) == 0) zone--;

        if (zone == 2) { // 0만 있는 구역
            return numOfOne * zone;
        } else if (zone < 2) { // 0 이전 구역
            return numOfOne * zone + count(n - 1, k - (divisor * zone));
        } else { // 0 이후 구역
            return numOfOne * (zone - 1) + count(n - 1, k - (divisor * zone));
        }
    }
}