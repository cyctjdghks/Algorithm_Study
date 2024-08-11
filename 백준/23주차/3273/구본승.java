import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int n, x;
    static int[] arr;

    public static void main(String[] args) throws Exception {
        init();
        solution();
    }

    private static void init() throws Exception {
        n = Integer.parseInt(br.readLine());
        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }
        x = Integer.parseInt(br.readLine());
    }

    private static void solution() {
        int l = 0, r = n - 1, sum = 0, ans = 0;
        Arrays.sort(arr);
        while (l < r) {
            sum = arr[l] + arr[r];
            if (sum == x) ans++;
            if (sum < x) l++;
            else r--;
        }
        System.out.println(ans);
    }
}