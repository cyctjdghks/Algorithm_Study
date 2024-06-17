import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

// BOJ 2636. 치즈
public class BOJ_2636 {

    static int N, M;
    static int[][] map;
    static boolean[][] v;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    // 상 하 좌 우
    static int cnt;
    static int cheese;

    public static void main(String[] args) {
        // 공기와 접촉된 칸은 녹음 ( 사방위 )

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        map = new int[N][M];
        cnt = 0;
        cheese = 0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                map[i][j] = sc.nextInt();
            }
        }

//		// map 확인
//		for(int i = 0; i < N; i++) {
//			for(int j = 0; j < M; j++) {
//				System.out.print(map[i][j]);
//			}
//			System.out.println();
//		}
//		System.out.println();

        while(!isEnd()) {
            v = new boolean[N][M];
            melting();
            cnt++;
        }

        System.out.println(cnt);
        System.out.println(cheese);

    }

    public static boolean isEnd() {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] == 1) return false;
            }
        }
        return true;
    }

    public static void melting() { // bfs
        Queue<Data> q = new LinkedList<Data>();
        cheese = 0;
        q.offer(new Data(0, 0));
        v[0][0] = true;

        while(!q.isEmpty()) {
            Data data = q.poll();

            int nx;
            int ny;

            for(int d = 0; d < 4; d++) {
                nx = data.x + dx[d];
                ny = data.y + dy[d];

                if(isInBound(nx, ny) && !v[nx][ny]) {
                    if(map[nx][ny] == 0) {
                        q.offer(new Data(nx, ny));
                        v[nx][ny] = true;
                    }
                    if(map[nx][ny] == 1) {
                        map[nx][ny] = 0;
                        v[nx][ny] = true;
                        cheese++;
                    }
                }
            }
        }
    }

    public static boolean isInBound(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    static class Data {
        int x, y;

        public Data(int x, int y) {
            super();
            this.x = x;
            this.y = y;
        }
    }
}
