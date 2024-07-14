import java.util.Scanner;

public class BOJ_2343 {

    static int N, M;
    static int[] arr;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        M = sc.nextInt();
        arr = new int[N];

        int left = 0;
        int right = 0;

        for (int i = 0; i < N; i++) {
            arr[i] = sc.nextInt();
            right += arr[i];
            left = Math.max(left, arr[i]);
        }


        while (left <= right) {
            int mid = (left + right) / 2;

            int count = getCount(mid);

            if(count > M){
                left = mid + 1;
            }else{
                right = mid - 1;
            }
        }

        System.out.println(left);
    }

    private static int getCount(int mid) {
        int sum = 0;
        int count = 0;
        for (int i = 0; i < N; i++) {
            if (sum + arr[i] > mid) {
                sum = 0;
                count++;
            }
            sum += arr[i];
        }

        if(sum != 0) count++;
        return count;
    }
}
