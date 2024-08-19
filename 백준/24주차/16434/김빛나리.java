import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] info;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int atk = Integer.parseInt(st.nextToken());
        info = new int[N][3];

        for(int i=0;i<N;i++) {
            st = new StringTokenizer(br.readLine());

            info[i][0] = Integer.parseInt(st.nextToken());
            info[i][1] = Integer.parseInt(st.nextToken());
            info[i][2] = Integer.parseInt(st.nextToken());
        }
        
        long maxHp = Long.MAX_VALUE;
        long left = 0, right = Long.MAX_VALUE;

        while(left <= right) {
            long mid = (left + right) / 2;

            if(enterRoom(atk, mid, mid)) {
                right = mid - 1;
                maxHp = mid;
            }
            else left = mid + 1;
        }

        bw.write(maxHp + "\n");
        bw.close();
        br.close();
    }

    public static boolean enterRoom(long atk, long curHp, long maxHp) {
        for(int i=0;i<N;i++) {
            int t = info[i][0];
            int a = info[i][1];
            int h = info[i][2];

            // 몬스터 방
            if(t == 1) {
                long monster = (h / atk) + (h % atk == 0 ? 0 : 1);
                long soldier = (curHp / a) + (curHp % a == 0 ? 0 : 1);

                if(monster <= soldier) {
                    curHp -= a * (monster - 1);
                    continue;
                }
                else return false;
            }
            // 포션 방
            else {
                atk += a;
                curHp = Math.min(curHp + h, maxHp);
            }
        }

        return true;
    }
}
