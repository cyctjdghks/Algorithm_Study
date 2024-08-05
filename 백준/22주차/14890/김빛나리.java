import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    static int N, L;
    static int[][] map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int cnt = 0;
        for(int i=0;i<N;i++) {
            if(checkRow(i)) cnt++;
            if(checkCol(i)) cnt++;
        }
            
        bw.write(cnt + "\n");
        bw.close();
    }

    public static boolean checkRow(int row) {
        boolean[] possible = new boolean[N];

        for(int i=0;i<N-1;i++) {
            int diff = map[row][i] - map[row][i+1];
            
            // 높이차 1 초과인 경우
            if(Math.abs(diff) > 1) return false;
            // 1 높은 경우
            else if(diff == -1) {
                for(int j=0;j<L; j++) {
                    if(i - j < 0 || possible[i-j]) return false;
                    if(map[row][i] != map[row][i-j]) return false;

                    possible[i-j]  = true;
                }
            } 
            // 1 낮은 경우
            else if(diff == 1) {
                for(int j=1;j<=L;j++) {
                    if(i + j >= N || possible[i+j]) return false;
                    if(map[row][i] - 1 != map[row][i+j]) return false;

                    possible[i+j] = true;
                }
            }
        }

        return true;
    }

    public static boolean checkCol(int col) {
        boolean[] possible = new boolean[N];

        for(int i=0;i<N-1;i++) {
            int diff = map[i][col] - map[i+1][col];
            
            // 높이차 1 초과인 경우
            if(Math.abs(diff) > 1) return false;
            // 1 높은 경우
            else if(diff == -1) {
                for(int j=0;j<L; j++) {
                    if(i - j < 0 || possible[i-j]) return false;
                    if(map[i][col] != map[i-j][col]) return false;

                    possible[i-j]  = true;
                }
            } 
            // 1 낮은 경우
            else if(diff == 1) {
                for(int j=1;j<=L;j++) {
                    if(i + j >= N || possible[i+j]) return false;
                    if(map[i][col] - 1 != map[i+j][col]) return false;

                    possible[i+j] = true;
                }
            }
        }

        return true;
    }
}
