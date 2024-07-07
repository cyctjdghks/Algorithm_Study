import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    static boolean[] prime;
    static ArrayList<Integer> primeList;

    public static void main(String[] args) throws IOException {


        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        prime = new boolean[N+1];
        primeList = new ArrayList<>();

        // 에라토스테네스의 체
        for (int i = 2; i*i <= N; i++) {
            if (!prime[i]) {
                for (int j = i*i; j <= N; j += i) {
                    prime[j] = true;
                }
            }
        }

        for (int i = 2; i <= N; i++) {
            if (!prime[i]) {
                primeList.add(i);
            }
        }

        // 투포인터
        int start = 0, end = 0, sum = 0, cnt = 0;
        while (true) {
            if (sum >= N) sum -= primeList.get(start++);
            else if (end == primeList.size()) break;
            else sum += primeList.get(end++);

            if (sum == N) cnt++;
        }

        System.out.println(cnt);

    }
}