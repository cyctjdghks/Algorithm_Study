import java.util.Arrays;
import java.util.Scanner;

public class BOJ_3273 {

    static int N, X;
    static int[] arr;
    static int res;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        arr = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            arr[i] = sc.nextInt();
        }
        X = sc.nextInt();
        res = 0;

        Arrays.sort(arr);

        twoPoint();

        System.out.println(res);
    }

    private static void twoPoint() {
        int left = 1;
        int right = N;
        int sum = 0;

        while (left < right) {
            sum = arr[left] + arr[right];

            if(sum == X) res++;

            if(sum < X) left++;
            else right--;
        }

    }
}