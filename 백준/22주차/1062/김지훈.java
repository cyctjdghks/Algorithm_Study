package coplit.dailyCoding.study.백준;

import java.io.*;
import java.util.StringTokenizer;

public class BOJ_1062 {

    static int N,K;
    static boolean[] visited = new boolean[26];
    static String[] words;
    static int max;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        words = new String[N];

        for (int i = 0; i < N; i++) {
            words[i] = br.readLine();
        }

        if (K < 5) {
            System.out.println("0");
            return;
        } else if (K == 26) {
            System.out.println(N);
            return;
        }

        visited['a' - 'a'] = true;
        visited['c' - 'a'] = true;
        visited['i' - 'a'] = true;
        visited['n' - 'a'] = true;
        visited['t' - 'a'] = true;

        backTracking(0,0);

        System.out.println(max);
    }

    public static void backTracking(int alpha, int len) {

        if (len == K - 5) {
            int count = 0;
            for (int i = 0; i < N; i++) {
                String word = words[i];
                boolean canRead = true;
                for (int j = 0; j < word.length(); j++) {
                    if (!visited[word.charAt(j) - 'a']) {
                        canRead = false;
                        break;
                    }
                }
                if (canRead) count++;
            }
            max = Math.max(max,count);
        }

        for (int i = alpha; i < 26; i++) {
            if (!visited[i]) {
                visited[i] = true;
                backTracking(i,len+1);
                visited[i] = false;
            }
        }

    }
}
