import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.StringTokenizer;

public class BOJ_13144 {

    static int N;
    static int[] arr;
    static int[] cnt;
    static long res;

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        //arr,cnt 배열만들고 값 담아주기
        arr = new int[N+1];
        cnt = new int[100000 + 1];
        res = 0;

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        twoPointer();

        System.out.println(res);

    }

    private static void twoPointer() {
        int idx = 1;
        int range = 0;

        // 현재 위치(idx)에서 시작해서 조건 만족할때까지 r을 늘려나간다.
        while (idx <= N) {
            // 처음쓰는 숫자인지, 범위를 안넘는지 체크
            while (range + 1 <= N && cnt[arr[range + 1]] == 0) {
                range++; // while 조건에서 이미 확인을 했으므로 안심하고 증가.
                cnt[arr[range]]++; // 해당 수 썼으니 썼다고 표시
            }

            res += range - idx + 1;
            // idx을 한칸씩 밀면서 볼것이기 때문에 계산이 끝나고 나면 idx을 한 칸 밀어준다.
            // 이전 위치의 cnt는 다음번 계산에서 제외하기 위해 cnt[arr[idx]]--;
            cnt[arr[idx++]]--;
        }
    }
}