package coplit.dailyCoding.study.백준.week24;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

public class BOJ_7795 {

    /**
     * 1. A,B 배열 내림차순 정렬
     * 2. for문 두개 돌아가면서 answer에 더해주기. start 기억
     * @param args
     */

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            Integer[] arrA = new Integer[N];
            Integer[] arrB = new Integer[M];

            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < N; j++) {
                int num = Integer.parseInt(st.nextToken());
                arrA[j] = num;
            }

            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < M; j++) {
                int num = Integer.parseInt(st.nextToken());
                arrB[j] = num;
            }

            int answer = 0;

            Arrays.sort(arrA,Collections.reverseOrder());
            Arrays.sort(arrB,Collections.reverseOrder());

            int start = 0;

            for (int j = 0; j < N; j++) {
                int numA = arrA[j];
                for ( int k = start; k < M; k++) {
                    int numB = arrB[k];

                    if (numA > numB) {
                        answer += M - k;
                        start = k;
                        break;
                    }
                }
            }

            System.out.println(answer);

        }


    }
}
