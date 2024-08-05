import java.util.Scanner;

public class BOJ_1062 {

    static int N, K;
    static String[] words;
    static boolean[] v;
    static int charToIdx = 97;
    static int res;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        K = sc.nextInt();
        words = new String[N];
        v = new boolean[26];
        res = 0;

        for (int i = 0; i < N; i++) {
            words[i] = sc.next();
        }

        if (K < 5) {
            System.out.println(0);
            return;
        } else if (K == 26) {
            System.out.println(N);
            return;
        }

        v['a' - charToIdx] = true;
        v['n' - charToIdx] = true;
        v['t' - charToIdx] = true;
        v['i' - charToIdx] = true;
        v['c' - charToIdx] = true;
        K -= 5;

        for (int i = 0; i < N; i++) {
            words[i] = words[i].substring(4, words[i].length() - 4);
        }

        dfs(0, 0);

        System.out.println(res);
    }

    private static void dfs(int idx, int len) {
        if (len == K) {
            int cnt = 0;

            for (int i = 0; i < N; i++) {
                boolean isItRead = true;
                for (int j = 0; j < words[i].length(); j++) {
                    if (!v[words[i].charAt(j) - charToIdx]) {
                        isItRead = false;
                        break;
                    }
                }
                if (isItRead) cnt++;
            }

            res = Math.max(res, cnt);
            return;
        }

        for (int i = idx; i < 26; i++) {
            if (!v[i]) {
                v[i] = true;
                dfs(i, len + 1);
                v[i] = false;
            }
        }
    }
}
