class Solution {
    public long solution(int a, int b, int[] g, int[] s, int[] w, int[] t) {
        // 금 
        // i번 도시에는 금 g[i], 은 s[i]
        // 각 도시의 트럭은 i 도시만 왕복 가능
        // 편도 t[i] 시간
        // 최대 w[i] 광물 운반 가능

        long answer = -1;
        //금 a kg과 은 b kg을 전달할 수 있는 가장 빠른 시간을 구해 return

        long left = 1;
        long right = 400_000_000_000_000L; // 10^9 * 2 * 10^5 * 2 (금 * 은 * 왕복 시간)
        int len = s.length;

        while (left <= right) {
            long mid = (left + right) / 2; //운반 시간 지정

            //도시 순회
            int gold = 0, silver = 0, sum = 0;
            for (int i = 0; i < len; i++) {
                int weight = w[i];
                int time = t[i];

                //몇 번 왕복 운반 가능한지
                long cnt = mid / (time * 2);
                if ((mid % (time * 2)) >= time) { //편도 추가 운반 가능한지
                    cnt++;
                }

                gold += Math.min(g[i], weight * cnt);
                silver += Math.min(s[i], weight * cnt);
                sum += Math.min(g[i] + s[i], weight * cnt);
            }

            //운반 가능하면 시간 줄이기
            if (gold >= a && silver >= b && sum >= a + b) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return answer;
    }
}