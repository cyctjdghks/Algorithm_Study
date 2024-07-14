package coplit.dailyCoding.study.백준;

import java.util.Arrays;
import java.util.Scanner;

public class BOJ_2565 {

    static int n;

    static int dp[], cost[][];


    public static void main(String[] args)   {
        Scanner sc = new Scanner(System.in);

        n=sc.nextInt();
        dp = new int[n+1];
        cost = new int[n+1][2]; // A, B 전봇대 배열


        for(int i=1;i<=n;i++) {
            for(int j=0;j<2;j++) {
                cost[i][j] = sc.nextInt();
            }
        }

        //A를 기준으로 오름차순 정렬
        Arrays.sort(cost, (o1, o2) -> o1[0] - o2[0]);

        dp[1] = 1;

        for(int i=2;i<=n;i++) { // LIS 구하기
            dp[i] =1;
            for(int j=1;j<i;j++) {
                if(cost[i][1]>cost[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j]+1);
                }
            }
        }
        int max =Integer.MIN_VALUE; // 설치가능한 전깃줄의 최대 개수
        for(int j=1;j<=n;j++) {
            if(dp[j]>max)
                max = dp[j];
        }

        System.out.println(n-max); //


    }
}
