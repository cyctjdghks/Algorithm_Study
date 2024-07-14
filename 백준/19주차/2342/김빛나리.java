import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        ArrayList<Integer> nums = new ArrayList<>();
        int i = 0, j = 0, k = 0;
        int power = Integer.MAX_VALUE;

        Scanner sc = new Scanner(System.in);

        // 처음 위치
        nums.add(0);

        while(true) {
            int n = sc.nextInt();

            if(n == 0) {
                sc.close();
                break;
            }
            
            nums.add(n);
        }

        int size = nums.size();

        // 입력받은 수 / 왼발 위치 / 오른발 위치
        int[][][] dp = new int[size][5][5];

        // 정수 최대값으로 초기화
        for(i=0;i<size;i++) {
            for(j=0;j<5;j++) {
                for(k=0;k<5;k++) {
                    dp[i][j][k] = Integer.MAX_VALUE;
                }
            }
        }

        dp[0][0][0] = 0;

        // 최소값 찾기
        for(i=0;i<size-1;i++) {
            int next = i+1;
            // 다음 위치
            int location = nums.get(next);

            for(j=0;j<5;j++) {
                for(k=0;k<5;k++) {
                    // 왼발 움직였을 때
                    dp[next][location][k] = Math.min(dp[next][location][k], dp[i][j][k] == Integer.MAX_VALUE ? dp[i][j][k] : dp[i][j][k] + ddr(j, location));

                    // 오른발 움직였을 때
                    dp[next][j][location] = Math.min(dp[next][j][location], dp[i][j][k] == Integer.MAX_VALUE ? dp[i][j][k] : dp[i][j][k] + ddr(k, location));
                }
            }
        }

        for(i=0;i<5;i++) {
            for(j=0;j<5;j++) {
                power = Math.min(power, dp[size-1][i][j]);
            }
        }

        System.out.println(power);
    }

    // 현재 위치, 움직일 위치
    public static int ddr (int cur, int move) {
        // 같은 위치 => 1
        if(cur == move) return 1;
        // 첫 위치 => 2
        else if(cur == 0) return 2;
        // 반대편 위치 => 4 / 둘다 짝수 or 둘다 홀수
        else if((cur % 2 == 0 && move % 2 == 0) || (cur % 2 != 0 && move % 2 != 0)) return 4;
        // 인접한 위치 => 3
        else return 3;
    }
}
