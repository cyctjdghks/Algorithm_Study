import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int N;
    static long ans;
    static int[] arr;
    static HashSet<Integer> set = new HashSet<>();

    public static void main(String[] args) throws Exception {
        init();
        solution();
    }

    private static void init() throws Exception {
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
    }

    private static void solution() {
        int start = 0;

        for(int i = 0; i < N; i++) {
            if(set.contains(arr[i])) {
                for(int j = start; j < i; j++) {
                    ans += i - j;
                    start++;
                    if(arr[j] == arr[i])
                        break;
                    set.remove(arr[j]);
                }
            } else
                set.add(arr[i]);
        }

        for(int i=start;i<N;i++)
            ans += N-i;

        System.out.println(ans);
    }
}