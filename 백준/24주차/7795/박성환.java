import java.util.Arrays;
import java.util.Scanner;

public class BOJ_7795 {

    static int N, M;
    static int[] A, B;
    static int res;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt();

        for (int tc = 1; tc <= T; tc++) {
            N = sc.nextInt();
            M = sc.nextInt();
            res = 0;

            A = new int[N];
            B = new int[M];

            for (int i = 0; i < N; i++) {
                A[i] = sc.nextInt();
            }
            for (int i = 0; i < M; i++) {
                B[i] = sc.nextInt();
            }

            Arrays.sort(B);

            for (int i = 0; i < N; i++) {
                binary(A[i]);
            }

            System.out.println(res);
        }
    }

    private static void binary(int idx) {
        int left = 0;
        int right = M;

        while (left < right) {
            int mid = (left + right) / 2;

            if (B[mid] < idx) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        res += left;
    }
}
