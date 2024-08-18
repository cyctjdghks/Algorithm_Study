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

        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] gem = new int[M];

        for(int i=0;i<M;i++) {
            gem[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(gem);

        int min = Integer.MAX_VALUE;
        int left = 1, right = gem[gem.length-1];
        int result = 0;

        while(left <= right) {
            int mid = (left + right) / 2;
            int sum = 0;

            for(int i=0;i<M;i++) {
                if(gem[i] % mid == 0) sum += gem[i] / mid;
                else sum += gem[i] / mid + 1;
            }

            if(sum > N) left = mid + 1;
            else {
                right = mid - 1;
                result = mid;
            }
        }

        
        bw.write(result + "\n");
        bw.close();
        br.close();
    }
}
