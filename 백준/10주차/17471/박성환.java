import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_17471 {

    static int N;
    static int[] population; // 각 구역의 인구 저장 배열
    static ArrayList<Integer>[] list; // 인접리스트로 연결
    static boolean[] v; // 방문 체크
    static int[] output;
    static int min = 9999_9999;

    public static void main(String[] args) {
        // 구역은 1번부터 N번까지
        // 구역의 개수 N
        // 1번 ~ N번 까지의 인구 수
        // 각 구역과 인접한 구역의 정보
        // 인접한 구역의 수 + 인접한 구역의 번호
        // N 개의 구역이 있을 때 순서 상관 없이 1개 ~ N-1 개 뽑기 => 조합
        // 조합으로 선택된 구역 모두 연결됐는지 확인
        // 선택 안된 구역 연결됐는지 확인
        // 둘다 연결이 되었다면 두 구역의 합 구하기
        // 두 구역의 합 차이 구하기

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        population = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            population[i] = sc.nextInt();
        }
        list = new ArrayList[N + 1];
        v = new boolean[N + 1];
        for (int i = 0; i <= N; i++) {
            list[i] = new ArrayList<Integer>();
        }
        output = new int[N];
        int num;
        int x;
        for (int i = 1; i <= N; i++) {
            num = sc.nextInt();
            for (int j = 0; j < num; j++) {
                x = sc.nextInt();
                list[i].add(x);
            }
        }

//		// 인구 확인
//		System.out.println(Arrays.toString(population));
//		// 리스트 확인
//		for (int i = 0; i <= N; i++) {
//			for (int j = 0; j < list[i].size(); j++) {
//				System.out.print(list[i].get(j) + " ");
//			}
//			System.out.println();
//		}

        for (int i = 1; i < N; i++) { // 1 ~ N-1 까지의 조합 구하기
            comb(N, i, 0, 1);
        }
        if (min != 9999_9999) {
            System.out.println(min);
        } else {
            System.out.println(-1);
        }
    }

    public static void comb(int n, int r, int depth, int start) {
        if (depth == r) {
//			// 조합 확인
//			System.out.println(Arrays.toString(output));

            int A = 0; // A 구역의 합
            int B = 0; // B 구역의 합
            // 체크
            if (check()) {
//				System.out.println(Arrays.toString(v));
//				System.out.println(Arrays.toString(output));
                for (int i = 0; i < v.length; i++) {
                    if (v[i]) {
                        A += population[i];
                    } else {
                        B += population[i];
                    }
                }
                min = Math.min(min, Math.abs(A - B));
            }

            return;
        }

        for (int i = start; i <= n; i++) {
            if (v[i] == false) {
                v[i] = true;
                output[depth] = i;
                comb(n, r, depth + 1, i + 1);
                v[i] = false;
            }
        }
    }

    public static boolean check() {
        Queue<Integer> q = new LinkedList<>();
        boolean[] v_s = new boolean[N + 1]; // 그래프 방문 체크

        q.offer(output[0]); // 선택된 구역 확인
        v_s[output[0]] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (Integer idx : list[cur]) {
                if (v_s[idx]) { // 방문한 곳이라면 pass
                    continue;
                }
                if (!v[idx]) { // 선택안된 구역이라면 pass
                    continue;
                }
                q.offer(idx);
                v_s[idx] = true;
            }
        }

        // 선택 안된 구역 탐색
        int unselect = 0;
        for (int i = 1; i <= N; i++) {
            if (!v[i]) {
                unselect = i;
                break;
            }
        }

        q.offer(unselect); // 선택안된 구역 확인
        v_s[unselect] = true;

        while (!q.isEmpty()) {
            int cur = q.poll();
            for (Integer idx : list[cur]) {
                if (v_s[idx]) { // 방문한 곳이라면 pass
                    continue;
                }
                if (v[idx]) { // 선택된 구역이라면 pass
                    continue;
                }
                q.offer(idx);
                v_s[idx] = true;
            }
        }

        for (int i = 1; i <= N; i++) { // 빠진 곳이 있으면 안됨
            if (!v_s[i]) {
                return false;
            }
        }

        return true;
    }

}
