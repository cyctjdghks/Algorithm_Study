package coplit.dailyCoding.study.백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class BOJ_14391 {

    static int N,M;
    static int[][] board;
    static boolean[][] visited;
    static int max = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new int[N][M];
        visited = new boolean[N][M];

        for (int i = 0; i < N; i++) {
            String s = br.readLine();
            char[] charArray = s.toCharArray();
            for (int j = 0; j < M; j++) {
                int num = Integer.parseInt(String.valueOf(charArray[j]));
                board[i][j] = num;
            }
        }

        dfs(0,0);

        System.out.println(max);
    }

    public static void dfs(int x, int y) {

        if (x >= N) {
            sum();
            return;
        }

        if (y >= M) {
            dfs(x+1,0);
            return;
        }

        // visted == true 면 가로로 자름
        visited[x][y] = true;
        dfs(x,y+1);
        // visited == false면 세로로 자름
        visited[x][y] = false;
        dfs(x,y+1);
    }

    public static void sum() {
        int result = 0;
        int temp = 0;

        // 가로로 자른 부분 계산
        for (int i = 0; i < N; i++) {
            temp = 0;
            for (int j = 0; j < M; j++) {
                if(visited[i][j]) {
                    temp*=10;
                    temp += board[i][j];
                }
                else {
                    result += temp;
                    temp = 0;
                }
            }
            result += temp;
        }

        // 세로로 자른 부분 계산
        for (int i = 0; i < M; i++) {
            temp = 0;
            for (int j = 0; j < N; j++) {
                if (!visited[j][i]) {
                    temp *= 10;
                    temp += board[j][i];
                }
                else {
                    result += temp;
                    temp = 0;
                }
            }
            result += temp;
        }

        max = Math.max(max, result);
    }
}
