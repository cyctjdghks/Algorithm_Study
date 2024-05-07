import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class BOJ_14497 {

    static int N, M;
    static int x1, y1, x2, y2;
    static int[][] map;
    static boolean[][] v;
    static int[] dx = {0, 0, 1, -1};
    static int[] dy = {1, -1, 0, 0};
    static int res;

    public static void main(String[] args) {
        //  0은 빈 공간, 1은 친구, *는 주난, #는 범인
        // * > 3
        // # > 4

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N + 1][M + 1];
        x1 = sc.nextInt();
        y1 = sc.nextInt();
        x2 = sc.nextInt();
        y2 = sc.nextInt();

        for (int i = 1; i <= N; i++) {
            String line = sc.next();
            for (int j = 1; j <= M; j++) {
                char c = line.charAt(j - 1);
                if (c == '*') {
                    map[i][j] = 3;
                } else if (c == '#') {
                    map[i][j] = 4;
                } else {
                    map[i][j] = c - '0';
                }
            }
        }

//        // map 확인
//        for (int i = 1; i <= N; i++) {
//            for (int j = 1; j <= M; j++) {
//                System.out.print(map[i][j] + " ");
//            }
//            System.out.println();
//        }

        bfs();

        System.out.println(res);


    }

    private static void bfs() {
        int jump = 0;
        loop:
        while (true) {
            Queue<Node> q = new LinkedList<>();
            q.offer(new Node(x1, y1));
            v = new boolean[N + 1][M + 1];
            v[x1][y1] = true;

            jump++;

            while (!q.isEmpty()) {
                Node node = q.poll();

                for (int d = 0; d < 4; d++) {
                    int nx = dx[d] + node.x;
                    int ny = dy[d] + node.y;

                    if (isInBound(nx, ny) && !v[nx][ny]) {
                        if (map[nx][ny] == 0) {
                            q.offer(new Node(nx, ny));
                            v[nx][ny] = true;
                        }

                        if (map[nx][ny] == 1) {
                            map[nx][ny] = 0;
                            v[nx][ny] = true;
                        }

                        if (map[nx][ny] == 4) {
                            res = jump;
                            break loop;
                        }
                    }
                }
            }
        }

    }

    public static boolean isInBound(int x, int y) {
        return x >= 1 && x <= N && y >= 1 && y <= M;
    }

    public static class Node {
        int x;
        int y;

        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
