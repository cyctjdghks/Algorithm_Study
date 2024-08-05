import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N, M, ans = 0;
    static int[][] map;

    public static void main(String[] args) throws Exception {
        init();
        solution();
    }

    private static void init() throws Exception {
        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }
    }

    private static void solution() {
        for (int bit = 0; bit < 1<<(N*M); bit++) {
            int sum = 0;
            int order;
            for (int i = 0; i < N; i++) {
                order = 1;
                for (int j = M - 1; j >= 0; j--) {
                    int num = j + i * M;
                    if ((bit&(1<<num))>0) {
                        sum += map[i][j]*order;
                        order*=10;
                    } else order = 1;
                }
            }
            for(int j = 0; j < M; j++) { //세로
                order=1;
                for(int i=N-1;i>=0;i--) {
                    int num = j+i*M;
                    if((bit&(1<<num))==0) {
                        sum += (map[i][j]*order);
                        order*=10;
                    } else {
                        order=1;
                    }
                }
            }
            ans = Math.max(ans, sum);
        }

        System.out.println(ans);
    }
}