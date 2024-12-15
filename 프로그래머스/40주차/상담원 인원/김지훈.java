import java.util.*;

class Solution {

    static int k;
    static int n;
    static int[][] reqs;
    static int[][] wait;

    public int solution(int k, int n, int[][] reqs) {

        this.k = k;
        this.n = n;
        this.reqs = reqs;

        // 각 상담 유형 별, 멘토 수에 따라 기다린 시간
        wait = new int[k + 1][n - k + 2];
        for(int i = 1; i <= k; i++) {
            getWaitingTime(i);
        }

        // 가장 시간이 적게 걸리는 배치 구하기
        int answer = getMinTime();
        return answer;
    }

    // index번 상담 유형, 멘토 수에 따라 기다린 시간
    public void getWaitingTime(int index) {

        PriorityQueue<Integer> pq;
        int time, next;
        for(int i = 1; i <= n - k + 1; i++) {
            pq = new PriorityQueue<>(); // 상담 중인 리스트
            time = 0; // 기다린 시간

            for(int j = 0; j < reqs.length; j++) {
                if(reqs[j][2] == index) { // index번 상담 요청이라면
                    if(pq.size() < i) { // 상담 자리가 남아있다면
                        pq.add(reqs[j][0] + reqs[j][1]);
                    }
                    else { // 상담 자리가 남지 않았다면
                        next = pq.poll(); // 가장 빨리 상담이 끝나는 사람

                        if(next > reqs[j][0]) { // 기다려야 하는 경우
                            time += next - reqs[j][0];
                            pq.add(next + reqs[j][1]);
                        }
                        else { // 기다리지 않아도 되는 경우
                            pq.add(reqs[j][0] + reqs[j][1]);
                        }

                    }
                }
            }

            // index번 상담을 i명의 멘토가 맡고 있을 때, 기다리는 시간
            wait[index][i] = time;
        }
    }

    public int getMinTime() { // 가장 시간이 적게 걸리는 배치 구하기

        // 상담 별 멘토 수
        int[] num = new int[k + 1];
        Arrays.fill(num, 1);

        // 각 상담마다 1명씩 멘토를 배치하고 남은 수의 멘토들을 시간을 가장 줄일 수 있는 상담에 배치
        int max, index;
        for(int i = 0; i < n - k; i++) {
            max = 0;
            index = 0;
            for(int j = 1; j <= k; j++) {
                if(max < wait[j][num[j]] - wait[j][num[j] + 1]) {
                    max = wait[j][num[j]] - wait[j][num[j] + 1];
                    index = j;
                }
            }
            num[index]++;
        }

        int ret = 0;
        for(int i = 1; i <= k; i++) {
            ret += wait[i][num[i]];
        }
        return ret;
    }
}