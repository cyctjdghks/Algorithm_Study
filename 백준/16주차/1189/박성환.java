import java.util.Scanner;

public class BOJ_1189 {

    static int R, C, K;
    static char[][] map;
    static boolean[][] v;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int answer;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        R = sc.nextInt();
        C = sc.nextInt();
        K = sc.nextInt();

        map = new char[R][C];
        v = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String str = sc.next();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        v[R - 1][0] = true;
        dfs(R - 1, 0, 1);

        System.out.println(answer);
    }

    static void dfs(int x, int y, int moved) {
        // 도착
        if (x == 0 && y == C - 1) {
            if (moved == K)
                answer++;
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            if (nextX < 0 || nextX >= R || nextY < 0 || nextY >= C)
                continue;
            if (v[nextX][nextY] || map[nextX][nextY] == 'T')
                continue;
            v[nextX][nextY] = true;
            dfs(nextX, nextY, moved + 1);
            v[nextX][nextY] = false;
        }
    }

}
