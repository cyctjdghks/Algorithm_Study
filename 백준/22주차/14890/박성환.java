import java.util.Scanner;

public class BOJ_14890 {

    static int N, L;
    static int[][] map;
    static int res;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        L = sc.nextInt();
        map = new int[N][N];
        res = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        for (int i = 0; i < N; i++) {
            if (row(i)) res++;
            if (col(i)) res++;
        }

        System.out.println(res);

    }

    private static boolean row(int row) {
        boolean[] isIncline = new boolean[N]; //경사면 설치 여부를 확인하는 배열

        for (int i = 0; i < N - 1; i++) {
            int diff = map[row][i] - map[row][i + 1];

            if (Math.abs(diff) > 1) return false; //높이차 1 초과하므로 false
            else if (diff == -1) { // 다음 계단이 한 계단 높다
                for (int j = 0; j < L; j++) { // 올라가는 경사로를 설치할 수 있는지 확인한다.
                    if (i - j < 0 || isIncline[i - j]) return false;
                    if (map[row][i] != map[row][i - j]) return false;
                    isIncline[i - j] = true; //경사면 설치
                }
            } else if (diff == 1) { //다음 계단이 한 계단 낮다
                for (int j = 1; j <= L; j++) { //내려가는 경사로를 설치할 수 있는지 확인한다.
                    if (i + j >= N || isIncline[i + j]) return false;
                    if (map[row][i] - 1 != map[row][i + j]) return false;
                    isIncline[i + j] = true; //경사면 설치
                }
            }
        }
        return true;
    }

    private static boolean col(int col) {
        boolean[] isIncline = new boolean[N]; //경사면 설치 여부를 확인하는 배열

        for (int i = 0; i < N - 1; i++) {
            int diff = map[i][col] - map[i + 1][col];

            if (Math.abs(diff) > 1) return false; //높이차 1 초과하므로 false
            else if (diff == -1) { // 다음 계단이 한 계단 높다
                for (int j = 0; j < L; j++) { // 올라가는 경사로를 설치할 수 있는지 확인한다.
                    if (i - j < 0 || isIncline[i - j]) return false;
                    if (map[i][col] != map[i - j][col]) return false;
                    isIncline[i - j] = true; //경사면 설치
                }
            } else if (diff == 1) { //다음 계단이 한 계단 낮다
                for (int j = 1; j <= L; j++) { //내려가는 경사로를 설치할 수 있는지 확인한다.
                    if (i + j >= N || isIncline[i + j]) return false;
                    if (map[i][col] - 1 != map[i + j][col]) return false;
                    isIncline[i + j] = true; //경사면 설치
                }
            }
        }
        return true;
    }
}
