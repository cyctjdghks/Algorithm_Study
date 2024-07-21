import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int n;
    static int c;
    static int[] arr;
    static ArrayList<Long> lv = new ArrayList<>();
    static ArrayList<Long> rv = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        init();
        solution();
    }

    private static void init() throws Exception {
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

    }

    private static void solution() {
        dfs(0, n / 2, 0, lv);
        dfs(n / 2, n, 0, rv);

        Collections.sort(lv);
        long ans = 0;
        for (long ele : rv) {
            if (ele > c)
                continue;
            int left = 0;
            int right = lv.size();
            int mid = 0;
            while (left < right) {
                mid = (left + right) / 2;
                if(ele + lv.get(mid) > c)
                    right = mid;
                else
                    left = mid+1;
            }
            ans += right;
        }
        System.out.println(ans);
    }

    private static void dfs(int s, int e, long sum, ArrayList<Long> v) {
        if (s >= e) {
            v.add(sum);
            return;
        }
        dfs(s + 1, e, sum, v);
        dfs(s + 1, e, sum + arr[s], v);
    }

}