import java.io.*;
import java.util.*;

public class Main {

    static int[][] map;
    static int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    static List<int[]> snake = new ArrayList<>();
    static HashMap<Integer, String> changeDirMap = new HashMap<>();
    static int time = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        map = new int[N + 1][N + 1];
        map[1][1] = 2;
        snake.add(new int[]{1, 1});

        StringTokenizer st;

        for (int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            map[r][c] = 1;
        }

        int L = Integer.parseInt(br.readLine());

        for (int i = 0; i < L; i++) {
            st = new StringTokenizer((br.readLine()));
            int X = Integer.parseInt(st.nextToken());
            String C = st.nextToken();
            changeDirMap.put(X, C);
        }

        int r = 1, c = 1;
        int dirIdx = 1;

        while (true) {

            // 시간
            time++;

            // 뱀 머리가 이동할 위치
            r += dirs[dirIdx][0];
            c += dirs[dirIdx][1];

            // 이동할 위치가 범위를 벗어나거나 뱀 자신을 만날때 종료
            if (r < 1 || r > N || c < 1 || c > N || map[r][c] == 2) break;

            // 사과가 없을 때
            if (map[r][c] == 0) { // 0은 빈 칸일때
                int tr = snake.get(0)[0];
                int tc = snake.get(0)[1];
                map[tr][tc] = 0;
                snake.remove(0);
            }

            // 뱀 이동
            map[r][c] = 2; // 2는 뱀이 있다는 뜻
            snake.add(new int[]{r, c});

            if (changeDirMap.containsKey(time)) {
                if (changeDirMap.get(time).equals("L")) {
                    dirIdx = (dirIdx == 0) ? 3 : dirIdx - 1;
                } else {
                    dirIdx = (dirIdx + 1) % 4;
                }
            }

        }

        System.out.println(time);

    }
}
