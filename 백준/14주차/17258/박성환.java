import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BOJ_17258 {
    static int N, M, K, T;
    static int[] cnt = new int[301];  // 파티 시간 동안 친구 수를 저장하는 배열
    static int[][] dp = new int[301][301];
    static List<Pair> v = new ArrayList<>();  // 구간을 저장하는 리스트

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 입력 처리
        N = scanner.nextInt();  // 파티가 진행되는 시간
        M = scanner.nextInt();  // 파티에 초대된 사람 수
        K = scanner.nextInt();  // 영선이의 친구 수
        T = scanner.nextInt();  // 영선이가 기대하는 최소 파티 인원

        // 각 친구의 시작 시간과 종료 시간 입력 받아 cnt 배열 업데이트
        for (int j = 0; j < M; j++) {
            int a = scanner.nextInt();
            int b = scanner.nextInt();
            for (int i = a; i < b; i++) {
                cnt[i] = Math.min(T, ++cnt[i]);
            }
        }

        // 연속된 시간 구간 분할
        int temp = cnt[1];  // 현재 구간의 친구 수
        int _count = 1;  // 현재 구간의 길이

        for (int i = 2; i <= N; i++) {
            if (temp != cnt[i]) {
                v.add(new Pair(_count, temp));  // 현재 구간을 리스트에 추가
                _count = 0;  // 새로운 구간의 길이 초기화
                temp = cnt[i];  // 새로운 구간의 친구 수
            }
            _count++;
        }
        v.add(new Pair(_count, temp));  // 마지막 구간 추가

        // DP 테이블 초기화
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        // 결과 출력
        System.out.println(go(0, K, 0));
//        System.out.println(Arrays.toString(cnt));
//        for (int i = 0; i <= N; i++) {
//            System.out.println(Arrays.toString(dp[i]));
//        }
    }

    // DP 함수 정의
    static int go(int here, int num, int prev) {
        if (here == v.size()) return 0;  // 모든 구간을 처리했을 때 종료 조건
        if (dp[here][num] != -1) return dp[here][num];  // 이미 계산된 값 반환

        int cost = Math.max(0, T - v.get(here).people);  // 현재 구간에서 필요한 추가 친구 수
        int realCost = (prev >= cost) ? 0 : cost;  // 실제 필요한 추가 친구 수

        int ret = dp[here][num];  // DP 테이블 참조
        if (num - realCost >= 0) {
            ret = Math.max(
                    go(here + 1, num - realCost, cost) + v.get(here).length,  // 현재 구간 포함
                    go(here + 1, num, 0)  // 현재 구간 포함하지 않음
            );
        } else {
            ret = go(here + 1, num, 0);  // 현재 구간 포함하지 않음
        }

        return dp[here][num] = ret;  // DP 테이블 갱신 및 반환
    }

    static class Pair {
        int length, people;

        Pair(int length, int people) {
            this.length = length;
            this.people = people;
        }
    }
}
