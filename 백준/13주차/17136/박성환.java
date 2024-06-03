import java.util.Scanner;

public class BOJ_17136 {

    static int[][] map;
    static int[] coloredPaper = {0, 5, 5, 5, 5, 5};
    static int minCount = Integer.MAX_VALUE;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        map = new int[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                map[i][j] = sc.nextInt();
            }
        }

        dfs(0, 0, 0);

        if (minCount == Integer.MAX_VALUE) {
            System.out.println(-1);
        } else {
            System.out.println(minCount);
        }
    }

    private static void dfs(int x, int y, int count) {
        if (x >= 10) {
            minCount = Math.min(minCount, count);
            return;
        }

        if (y >= 10) {
            dfs(x + 1, 0, count);
            return;
        }

        if (map[x][y] == 1) {
            for (int size = 5; size >= 1; size--) {
                if (canPlace(x, y, size)) {
                    placePaper(x, y, size, 0);
                    coloredPaper[size]--;
                    dfs(x, y + 1, count + 1);
                    placePaper(x, y, size, 1);
                    coloredPaper[size]++;
                }
            }
        } else {
            dfs(x, y + 1, count);
        }
    }

    private static boolean canPlace(int x, int y, int size) {
        if (coloredPaper[size] <= 0) return false;

        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (!isInBound(i, j) || map[i][j] != 1) return false;
            }
        }

        return true;
    }

    private static void placePaper(int x, int y, int size, int value) {
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                map[i][j] = value;
            }
        }
    }

    private static boolean isInBound(int x, int y) {
        return x >= 0 && y >= 0 && x < 10 && y < 10;
    }
}
