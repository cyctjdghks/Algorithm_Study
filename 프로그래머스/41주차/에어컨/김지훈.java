import java.util.*;

class Solution {

    static int temp;
    static int t1, t2;
    static int a, b;
    static int[] onboard;

    static HashMap<Integer, Integer> hm;

    public int solution(int temperature, int t1, int t2, int a, int b, int[] onboard) {

        this.temp = temperature;
        this.t1 = t1;
        this.t2 = t2;
        this.a = a;
        this.b = b;
        this.onboard = onboard;


        // i분에 만들 수 있는 모든 온도들 중 가장 작은 비용 구하기
        hm = new HashMap<>();
        hm.put(temperature, 0);
        for(int i = 1; i < onboard.length; i++) {
            getCost(i);
        }

        // 가장 적은 비용이 드는 경우 구하기
        int answer = Integer.MAX_VALUE;
        int c;
        for(int t : hm.keySet()) {
            c = hm.get(t);
            answer = Math.min(answer, c);
        }
        return answer;
    }

    // n분에 만들 수 있는 온도들 중 가장 작은 비용 구하기
    public void getCost(int n) {

        HashMap<Integer, Integer> now = new HashMap<>();

        int c;
        for(int k : hm.keySet()) {

            c = hm.get(k);

            // 에어컨을 끄는 경우
            if(k < temp) putMap(n, now, k + 1, c);
            else if(k == temp) putMap(n, now, k, c);
            else putMap(n, now, k - 1, c);

            // 희망 온도를 변경하는 경우
            putMap(n, now, k - 1, c + a);
            putMap(n, now, k + 1, c + a);

            // 희망 온도를 유지하는 경우
            putMap(n, now, k, c + b);
        }

        hm = now;
    }

    public void putMap(int n, HashMap<Integer, Integer> map, int k, int v) {

        int tmp;

        // n분에 승객이 탑승 중이지 않거나, 탑승 중이라면 쾌적한 실내온도를 유지 중인 경우
        if(onboard[n] == 0 || (k >= t1 && k <= t2)) {
            // 아직 k에 해당하는 온도의 경우의 수가 없는 경우
            if(map.get(k) == null) map.put(k, v);
                // 이미 k에 해당하는 온도의 경우의 수가 있는 경우, 비용이 더 적은 경우로 갱신
            else {
                tmp = map.get(k);
                if(v < tmp) map.put(k, v);
            }
        }
    }
}