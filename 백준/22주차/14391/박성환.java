import java.util.Arrays;
import java.util.Scanner;

public class BOJ_14391 {

    static int N, M;
    static int[][] map;
    static boolean[][] v;
    static int res;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][M];
        v = new boolean[N][M];
        res = 0;

        for (int i = 0; i < N; i++) {
            String str = sc.next();

            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }

        dfs(0, 0, 0);

        System.out.println(res);
    }

    private static void dfs(int dep, int num, int sum) {
        if (dep == N * M) {
            res = Math.max(res, sum);
            return;
        }

        int r = dep / M;
        int c = dep % M;

        if (v[r][c]) {
            dfs(dep + 1, num, sum);
        } else {
            v[r][c] = true;

            // 현재 위치에서 숫자를 새로 시작
            num = num * 10 + map[r][c];
            dfs(dep + 1, 0, sum + num);

            int i, temp = num;
            for (i = r + 1; i < N; i++) {
                if (v[i][c]) break;
                v[i][c] = true;
                temp = temp * 10 + map[i][c];
                dfs(dep + 1, 0, sum + temp);
            }

            for (int j = r + 1; j < i; j++) {
                v[j][c] = false;
            }

            temp = num;
            for (i = c + 1; i < M; i++) {
                if (v[r][i]) break;
                v[r][i] = true;
                temp = temp * 10 + map[r][i];
                dfs(dep + i - c + 1, 0, sum + temp);
            }

            for (int j = c + 1; j < i; j++) {
                v[r][j] = false;
            }

            v[r][c] = false;
        }
    }
}
