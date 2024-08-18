package coplit.dailyCoding.study.백준.week24;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class BOJ_16434 {

    static int N, HATK;
    static int[] t, a, h;

    public static void main(String[] args) {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
             BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            HATK = Integer.parseInt(st.nextToken());
            t = new int[N];
            a = new int[N];
            h = new int[N];
            for (int i = 0; i < N; ++i) {
                st = new StringTokenizer(br.readLine());
                t[i] = Integer.parseInt(st.nextToken());
                a[i] = Integer.parseInt(st.nextToken());
                h[i] = Integer.parseInt(st.nextToken());
            }

            long l = 0, r = 999999000001L * N;
            long ans = r;
            while (l <= r) {
                long m = (l + r) >> 1;
                if (isVaild(m)) {
                    ans = m;
                    r = m - 1;
                } else {
                    l = m + 1;
                }
            }
            bw.write(ans + "\n");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean isVaild(long Hmaxhp) {
        long Hcurhp = Hmaxhp, Hatk = HATK;
        for (int i = 0; i < N; ++i) {
            if (t[i] == 1) {
                long count = h[i] / Hatk;
                if (h[i] % Hatk == 0) count--;
                Hcurhp -= count * a[i];
                if (Hcurhp <= 0) return false;
            } else {
                Hatk += a[i];
                Hcurhp = Math.min(Hcurhp + h[i], Hmaxhp);
            }
        }
        return true;
    }
}