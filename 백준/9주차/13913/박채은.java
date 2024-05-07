import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Main {
    static int N, K;
    // 방문한 시간
    static int time[] = new int[100001]; // 방문하지 않으면 time[i]=0
    static int before[] = new int[100001];

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 1초: x-1, x+1
        // 순간이동(1초): 2x
        bfs();

        Stack<Integer> stack = new Stack<>();
        stack.push(K); // 뒤에서부터 넣기
        int i = K;

        while (i!=N){
            stack.push(before[i]);
            i = before[i];
        }

        sb.append(time[K] + "\n");
        while (!stack.isEmpty()) {
            sb.append(stack.pop() + " ");
        }

        System.out.println(sb.toString());
    }
    public static void bfs(){
        Queue<Integer> queue = new LinkedList<>();
        queue.add(N);
        time[N] = 0;

        while(!queue.isEmpty()){
            int q = queue.poll();

            if(q== K) break;

            for(int i=0;i<3;i++){
                int next = q;
                if(i==0) next = q-1;
                else if(i==1) next = q+1;
                else if(i==2) next = 2*q;

                if(next < 0 || next > 100000){
                    continue;
                }
                // 방문하지 않았다면
                if(time[next] == 0){
                    queue.add(next);
                    time[next] = time[q] + 1;
                    before[next] = q; // 그 전의 위치가 q라고 저장함
                }
            }

        }
    }
}