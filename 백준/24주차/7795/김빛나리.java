import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        for(int i=0;i<T;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            int[] A = new int[N];
            int[] B = new int[M];

            st = new StringTokenizer(br.readLine());

            for(int j=0;j<N;j++) {
                A[j] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());

            for(int j=0;j<M;j++) {
                B[j] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(B);
            int cnt = 0;

            for(int j=0;j<N;j++) {
                int left = 0, right = M - 1;
                int idx = 0;

                while(left <= right) {
                    int mid = (left + right) / 2;

                    if(B[mid] < A[j]) {
                        left = mid + 1;
                        idx = mid + 1;
                    }
                    else right = mid - 1;
                }

                cnt += idx;
            }

            sb.append(cnt + " ");
        }
        
        bw.write(sb.toString() + "\n");
        bw.close();
        br.close();
    }
}
