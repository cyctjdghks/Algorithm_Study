package coplit.dailyCoding.study.백준.week23;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_3273 {
    public int count(int N, int[] arr, int X) {
        int result = 0;
        int sum = 0;
        int lt = 0;
        int rt = N-1;

        Arrays.sort(arr);

        while(lt < rt) {
            sum = arr[rt] + arr[lt];

            if(sum == X) result++;

            if(sum < X) {
                lt++;
            }else {
                rt--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        BOJ_3273 num = new BOJ_3273();
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[] arr = new int[N];
        for(int i=0; i<N; i++) {
            arr[i] = sc.nextInt();
        }
        int X = sc.nextInt();

        System.out.println(num.count(N, arr, X));
    }
}