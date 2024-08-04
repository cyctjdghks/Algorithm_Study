import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static int n, m;
    static int[][] map;
    static boolean[][] cutRow;
    static int maxValue = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st= new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        cutRow = new boolean[n][m];
        for(int i = 0; i < n; i++) {
            String str = br.readLine();
            for(int j = 0; j < m; j++) {
                map[i][j] = str.charAt(j) - '0';
            }
        }
        dfs(0,0);
        System.out.println(maxValue);
    }
    static void dfs(int r, int c){
        if (r == n) {
            maxValue = Math.max(calc(), maxValue);
            return;
        }
        if (c == m) {
            dfs(r + 1, 0);
            return;
        }

        cutRow[r][c] = true;
        dfs(r, c + 1);
        cutRow[r][c] = false;
        dfs(r, c + 1);
    }
    static int calc(){
        int sum = 0;

        for (int i = 0; i < n; i++) {
            int tmp = 0;
            for (int j = 0; j < m; j++) {
                if (cutRow[i][j]) {
                    tmp *= 10;
                    tmp += map[i][j];
                }
                else {
                    sum += tmp;
                    tmp = 0;
                }
            }
            sum += tmp;
        }
        for (int i = 0; i < m; i++) {
            int tmp = 0;
            for (int j = 0; j < n; j++) {
                if (!cutRow[j][i]) {
                    tmp *= 10;
                    tmp += map[j][i];
                }
                else {
                    sum += tmp;
                    tmp = 0;
                }
            }
            sum += tmp;
        }
        return sum;
    }

}
