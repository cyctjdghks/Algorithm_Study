import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    static int time = Integer.MAX_VALUE, cnt = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 수빈이가 동생보다 앞에 있는 경우
        if(N >= K) {
            bw.write((N - K) + "\n1\n");
            bw.close();
            return;
        }

        bfs();
            
        bw.write(time + "\n" + cnt + "\n");
        bw.close();
    }

    public static void bfs() {
        // 최대 위치 = 100,000
        int[] visited = new int[100001];
        Queue<Integer> queue = new LinkedList<Integer>();

        queue.add(N);
        visited[N] = 1;

        while(!queue.isEmpty()) {
            // 현 위치
            int location = queue.poll();

            // 최소 시간이 아닌 경우
            if (time < visited[location]) continue;

            for(int i=0;i<3;i++) {
                int next;

                if(i == 0) next = location + 1;
                else if(i == 1) next = location - 1;
                else next = location * 2;

                // 범위를 벗어났을 경우
                if(0 > next || next > 100000) continue;

                if(next == K) {
                    time = visited[location];
                    cnt++;
                }

                // 처음 방문했거나 1초 뒤 동일한 시간이 걸렸을 경우
                if(visited[next] == 0 || visited[next] == visited[location] + 1) {
                    queue.add(next);
                    visited[next] = visited[location] + 1;
                }
            }
        }
    }
}
