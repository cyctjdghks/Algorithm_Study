import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N+1];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=1;i<=N;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int e = 0;
        long cnt = 0;
        int[] visited = new int[100001];
 
        for(int s=1;s<=N;s++) {
            if(visited[arr[s-1]] > 0) visited[arr[s-1]]--;
 
            while(e+1 <= N && visited[arr[e+1]] <= 0) {
                visited[arr[++e]]++;
            }
 
            cnt += (e - s + 1);
        }
        
        bw.write(cnt + "\n");
        bw.close();
        br.close();
    }
}
