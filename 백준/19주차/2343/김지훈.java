package coplit.dailyCoding.study.백준;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class BOJ_2343 {

    // 1. 강의의 수, 블루레이 개수, 각 강의의 길이를 입력받는다.
    // 2. 이분탐색으로 가능한 블루레이 크기 중 최소를 반환한다.

    // 이분탐색

    static int n, m;
    static int[] arr;
    static int low = 1;
    static int high = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        arr = new int[n];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
            high += arr[i];
            low = Math.max(arr[i], low);
        }

        binarySearch();

        System.out.println(low);
    }

    static void binarySearch() {
        int mid, sum, count;

        while (low <= high) {
            mid = (low + high) / 2;
            sum = 0;
            count = 0;

            for (int i = 0; i < n; i++) {
                if (sum + arr[i] > mid) {
                    count++;
                    sum = 0;
                }

                sum += arr[i];
            }

            if (sum > 0) count++;

            if (count <= m) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
    }
}
