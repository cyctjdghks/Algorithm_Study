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

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i=0;i<n;i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int x = Integer.parseInt(br.readLine());

        Arrays.sort(arr);

        int cnt = 0;

        for(int i=0;i<n-1;i++) {
            // 오름차순 정렬했기 때문에 x보다 큰 값이 나온 경우, 그 이후는 체크하지 않아도 됨
            if(arr[i] > x) break;

            for(int j=i+1;j<n;j++) {
                if(arr[i] + arr[j] == x) {
                    cnt++;
                    // 오름차순 정렬했기 때문에 x 값와 동일한 값이 나온 경우, 그 이후는 체크하지 않아도 됨
                    break;
                }
            }
        }
            
        bw.write(cnt + "\n");
        bw.close();
    }
}
