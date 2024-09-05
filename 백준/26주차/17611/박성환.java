import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ_17611 {

    static int N;
    static int[][] sum;
    static int startX, startY;
    static int result;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        sum = new int[1000001][2];
        result = 0;

        int x = sc.nextInt() + 500000;
        int y = sc.nextInt() + 500000;

        startX = x;
        startY = y;

        for (int i = 1; i < N; i++) {
            int curX = sc.nextInt() + 500000;
            int curY = sc.nextInt() + 500000;

            if (x == curX) {
                yLineSearch(y, curY);
            } else {
                xLineSearch(x, curX);
            }

            x = curX;
            y = curY;
        }

        if (x == startX) {
            yLineSearch(y, startY);
        } else {
            xLineSearch(x, startX);
        }

        for (int i = 0; i < 1000001; i++) {
            result = Math.max(result, Math.max(sum[i][0], sum[i][1]));
        }

        System.out.println(result);
    }

    private static void xLineSearch(int x, int curX) {
        if (x > curX) {
            for (int j = curX; j < x; j++) {
                sum[j][0]++;
            }
        } else {
            for (int j = x; j < curX; j++) {
                sum[j][0]++;
            }
        }
    }

    private static void yLineSearch(int y, int curY) {
        if (y > curY) {
            for (int j = curY; j < y; j++) {
                sum[j][1]++;
            }
        } else {
            for (int j = y; j < curY; j++) {
                sum[j][1]++;
            }
        }
    }
}
