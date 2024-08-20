import java.util.Scanner;

public class BOJ_2792 {

    static int N, M;
    static int arr[];
    static int res;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        res = 0;

        arr = new int[M];

        int left = 1;
        int right = 1;

        for (int i = 0; i < M; i++) {
            arr[i] = sc.nextInt();
            right = Math.max(right, arr[i]);
        }

        while (left <= right) {
            int mid = (left + right) / 2;
            int students = 0;

            for (int i = 0; i < M; i++) {
                if (arr[i] % mid == 0) {
                    students += arr[i] / mid;
                } else {
                    students += arr[i] / mid + 1;
                }
            }

            if (students > N) {
                left = mid + 1;
            } else {
                right = mid - 1;
                res = mid;
            }
        }


        System.out.println(res);
    }
}
