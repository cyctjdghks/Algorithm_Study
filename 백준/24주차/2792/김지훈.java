package coplit.dailyCoding.study.백준.week24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2792 {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] jewels = new int[M];
        int low = 1;
        int high = 0;

        for (int i = 0; i < M; i++) {
            int num = Integer.parseInt(br.readLine());
            jewels[i] = num;
            if (num > high) {
                high = num;
            }
        }

        int answer = Integer.MAX_VALUE;

        while (low <= high) {
            int mid = (low + high) / 2;
            int sum = 0;

            for (int num : jewels) {
                sum += Math.ceil((double) num / mid);
            }

            if (N >= sum) {
                high = mid - 1;
                answer = Math.min(answer, mid);
            } else {
                low = mid + 1;
            }
        }

        System.out.println(answer);
    }
}
