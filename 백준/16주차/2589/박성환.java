import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_2589 {
    static int R, C;
    static char[][] map;
    static boolean[][] v;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int answer;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        R = sc.nextInt();
        C = sc.nextInt();

        map = new char[R][C];
        v = new boolean[R][C];

        for (int i = 0; i < R; i++) {
            String str = sc.next();
            for (int j = 0; j < C; j++) {
                map[i][j] = str.charAt(j);
            }
        }

        for (int i = 0; i < R; i++) {
            for (int j = 0; j < C; j++) {
                if (map[i][j] == 'L') {
                    v = new boolean[R][C];
                    int val = bfs(i, j);
                    answer = Math.max(answer, val);

                }
            }
        }

        System.out.println(answer);

    }

    private static int bfs(int i, int j) {
        Queue<Node> q = new LinkedList<>();
        int val = 0;
        v[i][j] = true;
        q.add(new Node(j, i, 0));
        while (!q.isEmpty()) {
            Node p = q.poll();
            for (int d = 0; d < 4; d++) {
                int newX = p.x + dx[d];
                int newY = p.y + dy[d];
                if (0 <= newX && newX < C && 0 <= newY && newY < R && !v[newY][newX] && map[newY][newX] == 'L') {
                    v[newY][newX] = true;
                    q.add(new Node(newX, newY, p.cnt + 1));
                    val = Math.max(val, p.cnt + 1);
                }
            }
        }
        return val;
    }

    public static class Node {
        int x;
        int y;
        int cnt;

        public Node(int x, int y, int cnt) {
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }
}
