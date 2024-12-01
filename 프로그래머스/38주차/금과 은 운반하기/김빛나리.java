// 이분탐색
// T라는 시간이 주어졌을 때, 이 시간 안에 목표하는 금과 은(a, b)를 운반이 가능한가? 라는 관점으로 접근
// 금을 우선으로 운반한 경우 금과 은의 양 -> Gmax, Smin
// 은을 우선으로 운반한 경우 금과 은의 양 -> Gmin, Smax 라고 했을 때,
// a <= Gmax, b <= Smax
// a + b <= Gmax + Smin = Gmin + Smin
// 을 만족한다면 주어진 시간 T 안에 a만큼의 금과 b만큼의 은을 운반할 수 있다는 것
class Solution {
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        long answer = (long) (10e9 * 2 * 10e5 * 2);
        int cityCnt = g.length;
        long start = 0;
        // 최악의 경우 걸리는 시간
        // ((금의 양)+(은의 양) / (한번에 옮길 수 있는 무게)) * (옮기는데 걸리는 시간 * 2)
        // 금과 은의 최대값은 각각 10^9, 한번에 옮길 수 있는 무게의 최소값은 1, 옮기는데 걸리는 시간의 최대값은 10^5
        // ((10^9 * 2) / 1) * (10^5 *2)
        long end = (long) (10e9 * 2 * 10e5 * 2);
        
        while(start <= end) {
            long mid = (start + end) / 2;
            // 운반된 금의 양, 은의 양
            int gold = 0, silver = 0;
            // 운반된 금과 은의 양
            int mix = 0;

            // 모든 도시들을 순회하며 금과 은을 운반
            for(int i=0;i<cityCnt;i++) {
                int nowGold = g[i];
                int nowSilver = s[i];
                int nowWeight = w[i];
                long nowTime = t[i];
                
                // 주어진 시간인 mid에서 가능한 왕복 횟수
                long moveCnt = mid / (nowTime * 2);
                // 나머지가 해당 도시 편도 시간보다 크거나 같은 경우, 한번 더 움직임 +1
                if(mid % (nowTime * 2) >= nowTime) moveCnt++;

                gold += Math.min(nowGold, moveCnt * nowWeight);
                silver += Math.min(nowSilver, moveCnt * nowWeight);
                mix += Math.min(nowGold + nowSilver, moveCnt * nowWeight);
            }

            // 목표치보다 큰 경우 -> end 값 갱신해서 줄여주기
            if(a <= gold && b <= silver && a + b <= mix) {
                end = mid - 1;
                answer = Math.min(mid, answer);
                continue;
            }

            start = mid + 1;
        }
        
        return answer;
    }
}