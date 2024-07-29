import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int me, brother;
    static int[] time = new int[100001];
    static int minTime = 987654321;
    static int count = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        me = Integer.parseInt(st.nextToken());
        brother = Integer.parseInt(st.nextToken());

        if (me >= brother) {
            System.out.println((me - brother) + "\n1");
            return;
        }

        BFS();

        System.out.println(minTime + "\n" + count);
    }

    private static void BFS() {
        Queue<Integer> que = new LinkedList<>();
        que.add(me);
        time[me] = 1;
        while (!que.isEmpty()) {
            int now = que.poll();
            if (minTime < time[now]) return;

            for (int i = 0; i < 3; i++) {
                int next;
                if (i == 0) next = now + 1;
                else if (i == 1) next = now - 1;
                else next = now * 2;
                if (next == brother) {
                    minTime = time[now];
                    count++;
                }
                if (next < 0 || next > 100000) continue;

                if (time[next] == 0 || time[next] == time[now] + 1) {
                    que.add(next);
                    time[next] = time[now] + 1;
                }
            }
        }
    }
}
