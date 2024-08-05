import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, L, ans;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        init();
        solution();
    }

    private static void init() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    private static void solution() {
        for (int i = 0; i < N; i++) {
            if(go(i, true)) ans++;
            if(go(i, false)) ans++;
        }
        System.out.println(ans);
    }

    private static boolean go(int x, boolean isRow) {
        boolean[] isIncline = new boolean[N];
        for(int i = 0; i < N - 1; i++) {
            int diff;
            if (isRow) diff = map[x][i] - map[x][i + 1];
            else diff = map[i][x] - map[i + 1][x];

            if(diff > 1 || diff < -1) return false;
            else if(diff == -1) {
                for(int j = 0; j < L; j++) {
                    if(i - j < 0 || isIncline[i - j]) return false;
                    if (isRow) {
                        if (map[x][i] != map[x][i - j]) return false;
                    } else {
                        if(map[i][x] != map[i - j][x]) return false;
                    }
                    isIncline[i - j]  = true;
                }
            }
            else if(diff == 1) {
                for(int j = 1; j <= L; j++) {
                    if(i + j >= N || isIncline[i + j]) return false;
                    if (isRow) {
                        if (map[x][i] - 1 != map[x][i + j]) return false;
                    } else {
                        if(map[i][x] - 1 != map[i + j][x]) return false;
                    }
                    isIncline[i + j] = true;
                }
            }
        }
        return true;
    }
}