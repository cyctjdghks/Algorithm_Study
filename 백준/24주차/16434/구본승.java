import java.io.*;
import java.util.*;

public class Main {

    static long[][] rooms;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(bf.readLine());

        int n = Integer.parseInt(st.nextToken());
        long atk = Long.parseLong(st.nextToken());

        rooms = new long[n][3];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(bf.readLine());

            for (int j = 0; j < 3; j++) {
                rooms[i][j] = Long.parseLong(st.nextToken());
            }
        }

        System.out.println(binarySearch(atk));
    }

    static long binarySearch(long atk) {
        long left = 0;
        long right = (long) 1e18;

        while (left <= right) {
            long mid = (left + right) / 2;

            if (check(mid, atk)) {
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        return left;
    }

    static boolean check(long maxHP, long atk) {
        long hp = maxHP;

        for (long[] room : rooms) {
            long t = room[0];
            long a = room[1];
            long h = room[2];

            if (t == 1L) {
                if (h % atk == 0L) {
                    hp -= (h / atk - 1) * a;
                } else {
                    hp -= (h / atk) * a;
                }
                if (hp <= 0) return false;
            } else if (t == 2L) {
                atk += a;
                hp += h;
                if (hp > maxHP) hp = maxHP;
            }
        }
        return true;
    }
}