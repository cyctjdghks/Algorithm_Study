import java.io.BufferedReader;
import java.io.InputStreamReader;

class BOJ_1344 {
    static int DIFF_NUM = 90 / 5;
    static double[][][] dp = new double[DIFF_NUM + 1][DIFF_NUM + 1][DIFF_NUM + 1];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        double A = Double.parseDouble(br.readLine()) / 100;
        double B = Double.parseDouble(br.readLine()) / 100;
        setDp(A, B);

        double res = 0;
        for (int i = 0; i <= DIFF_NUM; i++) {
            for (int j = 0; j <= DIFF_NUM; j++) {
                if (isPrimeNum(i) || isPrimeNum(j)) res += dp[DIFF_NUM][i][j];
            }
        }

        System.out.printf("%.7f", res);
    }

    private static void setDp(double A, double B) {
        dp[0][0][0] = 1.0;
        for (int i = 1; i <= DIFF_NUM; i++) {
            for (int j = 0; j <= i; j++) {
                for (int k = 0; k <= i; k++) {
                    if (j > 0) dp[i][j][k] += dp[i - 1][j - 1][k] * A * (1 - B);
                    if (k > 0) dp[i][j][k] += dp[i - 1][j][k - 1] * (1 - A) * B;
                    if (j > 0 && k > 0) dp[i][j][k] += dp[i - 1][j - 1][k - 1] * A * B;
                    dp[i][j][k] += dp[i - 1][j][k] * (1 - A) * (1 - B);
                }
            }
        }
    }

    private static boolean isPrimeNum(int n) {
        if (n < 2) return false;
        for (int i = 2; i * i <= n; i++) if (n % i == 0) return false;
        return true;
    }
}