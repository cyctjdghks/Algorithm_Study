import java.util.Arrays;

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int answer = 0;

        int[][] streets = new int[n+1][m+1];
        for (int i = 0; i < streets.length; i++) {
            Arrays.fill(streets[i], -1);
        }

        //puddles
        int a, b;
        for (int i = 0; i < puddles.length; i++) {
            if (puddles[i].length == 2) {
                a = puddles[i][0];
                b = puddles[i][1];
                streets[b][a] = 0;
            }
        }

        for (int row = 1; row <= n; row++) {
            for (int col = 1; col <= m; col++) {
                if (streets[row][col] == 0) continue;
                if (col == 1 && row == 1) {
                    streets[row][col] = 1;
                    continue;
                }

                if (col == 1 || row == 1) {
                    if (row == 1) {
                        streets[row][col] = streets[row][col - 1];
                    } else if (col == 1) {
                        streets[row][col] = streets[row - 1][col];
                    }
                } else {
                    streets[row][col] = (streets[row][col - 1] + streets[row - 1][col]) % 1000000007;
                }
            }
        }
        answer = streets[n][m];
        return answer;
    }
}
