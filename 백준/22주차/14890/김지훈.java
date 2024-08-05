package coplit.dailyCoding.study.백준;

import java.io.*;
import java.util.*;

public class BOJ_14890 {
    static int N, L;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());

        board = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;
        for (int i = 0; i < N; i++) {
            ans += makeRoad(i);
        }

        System.out.println(ans);
    }

    private static int makeRoad(int num) {
        int result = 0;

        if (check(board[num])) {
            result++;
        }

        int[] tmp = new int[N];
        for (int i = 0; i < N; i++) {
            tmp[i] = board[i][num];
        }
        if (check(tmp)) {
            result++;
        }

        return result;
    }

    private static boolean check(int[] arr) {
        boolean[] visit = new boolean[N];

        for (int i = 1; i < N; i++) {
            if (arr[i] == arr[i - 1]) {
                continue;
            }
            // 이전 칸보다 1 높다면 경사로 놓을 수 있는지 검사
            else if (arr[i] == arr[i - 1] - 1) {
                for (int j = 0; j < L; j++) {
                    if (i + j >= N) {
                        return false;
                    }
                    if (arr[i] != arr[i + j]) {
                        return false;
                    }
                    visit[i + j] = true;
                }
            }
            // 이전 칸보다 1 낮다면 경사로 놓을 수 있는지 검사
            else if (arr[i] == arr[i - 1] + 1) {
                for (int j = 1; j <= L; j++) {
                    if (i - j < 0 || visit[i - j]) {
                        return false;
                    }
                    if (arr[i - 1] != arr[i - j]) {
                        return false;
                    }
                    visit[i - j] = true;
                }
            }
            // 이전 칸과 1 이상 차이나면 불가능
            else {
                return false;
            }
        }

        return true;
    }

}