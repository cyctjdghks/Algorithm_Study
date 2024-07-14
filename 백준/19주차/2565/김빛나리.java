import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main {
    
    static int N;
    static int[] dp;
    static boolean[] visited;
    static int[][] wire;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int max = 0;

        N = sc.nextInt();

        dp = new int[N];
        // 두 전봇대 사이의 연결된 선이기 때문에 1로 초기화
        Arrays.fill(dp, 1);

        visited = new boolean[N];
        wire = new int[N][2];

        for(int i=0;i<N;i++) {
            wire[i][0] = sc.nextInt();
            wire[i][1] = sc.nextInt();
        }

        sc.close();

        // A 전봇대를 기준으로 오름차순 정렬
        Arrays.sort(wire, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });

        // 최대로 연결 가능한 전깃줄 수 찾기
        for(int i=0;i<N;i++) {
            max = Math.max(max, connection(i));
        }

        // 연결된 전깃줄 수에서 최대로 연결 가능한 전깃줄 빼면, 없애야 하는 전깃줄의 최소 개수
        System.out.println(N - max);
    }

    public static int connection(int num) {
        // 한번도 방문하지 않았을 경우
        if(!visited[num]) {
            // 연결되는 위치기 때문에
            dp[num] = 1;

            // num 이후의 A 전봇대 연결 위치부터 반복
            for(int i=num+1;i<N;i++) {
                // 현재 B 전봇대 연결 위치가 A 전봇대와 연결된 전깃줄보다 이후일 경우, 연결 가능
                if(wire[num][1] < wire[i][1]) dp[num] = Math.max(dp[num], connection(i)+1);
            }

            visited[num] = true;
        }

        return dp[num];
    }
}