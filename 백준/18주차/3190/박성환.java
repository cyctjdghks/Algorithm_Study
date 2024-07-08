import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class BOJ_3190 {

    static int N, K, L;
    static int[][] map;
    static int[] dx = { 0, 1, 0, -1 };
    static int[] dy = { 1, 0, -1, 0 };
    static List<int[]> snake = new ArrayList<>();
    static HashMap<Integer, String> hash = new HashMap<>();
    static int res;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();
        res = 0;

        map = new int[N][N];

        for (int i = 0; i < K; i++) {
            int x = sc.nextInt();
            int y = sc.nextInt();
            map[x-1][y-1] = 1;
        }

        L = sc.nextInt();

        for (int i = 0; i < L; i++) {
            int x = sc.nextInt();
            String c = sc.next();
            hash.put(x, c);
        }

        res = solve();

        System.out.println(res);
    }

    public static int solve() {
        int cx = 0, cy = 0;
        int time = 0;
        int d = 0;
        snake.add(new int[] { 0, 0 });

        while (true) {
            // 1. 시간재기
            time++;

            // 2. 뱀 이동하기
            int nx = cx + dx[d];
            int ny = cy + dy[d];

            // 3. 범위를 벗어나거나, 뱀 몸통 만날 때 종료
            if (isFinish(nx, ny))
                break;

            // 4. 사과가 있을 때 없을 때 처리
            if (map[nx][ny] == 1) {
                map[nx][ny] = 0;
                snake.add(new int[] { nx, ny });

            } else {
                snake.add(new int[] { nx, ny });
                snake.remove(0);
            }

            // 5. 방향을 바꿔주는 시간을 만날 때 방향 변경
            if (hash.containsKey(time)) {
                if (hash.get(time).equals("D")) {
                    d += 1;
                    if (d == 4)
                        d = 0;
                } else {
                    d -= 1;
                    if (d == -1)
                        d = 3;
                }
            }

            // 6. 현재값 업데이트
            cx = nx;
            cy = ny;
            // cx cy 업데이트 위에서
        }

        return time;
    }

    public static boolean isFinish(int nx, int ny) {
        if (nx < 0 || ny < 0 || nx >= N || ny >= N) {
            return true;
        }

        for (int i = 0; i < snake.size(); i++) {
            int[] t = snake.get(i);
            if (nx == t[0] && ny == t[1])
                return true;
        }
        return false;
    }
}
