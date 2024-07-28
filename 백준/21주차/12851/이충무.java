import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n,m;
    static int cnt=0;
    static int minTime=Integer.MAX_VALUE;
    static int[] time=new int[100001];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        if(n>= m){
            System.out.println(n-m);
            System.out.println(1);
            return;
        }
        bfs();

        System.out.println(minTime);
        System.out.println(cnt);

    }
    static void bfs(){
        boolean[] visited= new boolean[100001];
        Queue<Integer> q = new LinkedList<>();
        q.add(n);

        time[n]=1;
        visited[n]=true;

        while(!q.isEmpty()){
            int cur = q.poll();

            if(minTime < time[cur]) continue;

            for(int i=0;i<3;i++){
                int next;
                if(i==0) next = cur+1;
                else if(i==1) next = cur-1;
                else next = cur*2;

                if(next>100000 || next<0) continue;

                if(next == m){
                    minTime = time[cur];
                    cnt++;
                }

                if(time[next] == 0 || time[next] == time[cur] + 1){
                    q.add(next);
                    time[next] = time[cur]+1;
                }

            }



        }
    }
}