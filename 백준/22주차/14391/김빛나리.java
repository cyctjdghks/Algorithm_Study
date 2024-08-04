import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int N, M;
    static int[][] paper;
    static boolean[][] isRow;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        paper = new int[N][M];
        isRow = new boolean[N][M];

        for(int i=0;i<N;i++) {
            String str = br.readLine();
            char[] charArr = str.toCharArray();

			for(int j=0;j<M;j++) {
                paper[i][j] = Character.getNumericValue(charArr[j]);
            }
		}
		
		dfs(0, 0);
            
        bw.write(max + "\n");
        bw.close();
    }

    public static void dfs(int x, int y) {
        // 모든 행 탐색 > 조각의 합 구하기
        if(x == N) {
            sum();
            return;
        }
        // 모든 열 탐색 > 다음 행으로 이동
        if(y == M) {
            dfs(x + 1, 0);
            return;
        }

        // 가로로 잘랐을 경우
        isRow[x][y] = true;
        dfs(x, y + 1);

        // 세로로 잘랐을 경우
        isRow[x][y] = false;
        dfs(x, y + 1);
    }

    public static void sum() {
        int result = 0;
        int temp = 0;

        // 가로
        for(int i=0;i<N;i++) {
            temp = 0;

            for(int j=0;j<M;j++) {
                // 가로로 자른 경우
                if(isRow[i][j]) {
                    temp *= 10;
                    temp += paper[i][j];
                }
                // 가로로 자르지 않은 경우
                else {
                    result += temp;
                    temp = 0;
                }
            }

            result += temp;
        }

        // 세로
        for(int i=0;i<M;i++) {
            temp = 0;

            for(int j=0;j<N;j++) {
                // 세로로 자른 경우
                if (!isRow[j][i]) {
                    temp *= 10;
                    temp += paper[j][i];
                }
                // 세로로 자르지 않은 경우
                else {
                    result += temp;
                    temp = 0;
                }
            }

            result += temp;
        }

        max = Math.max(result, max);
    }
}
