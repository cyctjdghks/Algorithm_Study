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

        int n = Integer.parseInt(br.readLine());
        int[] x = new int[n];
        int[] y = new int[n];

        for(int i=0;i<n;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            x[i] = Integer.parseInt(st.nextToken());
            y[i] = Integer.parseInt(st.nextToken());
        }

        bw.write(Math.max(countCross(x, n), countCross(y, n))+"\n");
        bw.close();
        br.close();
    }

    public static int countCross(int[] pos, int n) {
        int temp = findMin(pos);
        if (temp != 0) {
            for(int i=0;i<n;i++) {
                pos[i] -= temp;
            }
        }

        int[] sum = new int[findMax(pos) + 1];

        for(int i=0;i<n-1;i++) {
            sum[Math.min(pos[i], pos[i+1])] += 1;
            sum[Math.max(pos[i], pos[i+1])] -= 1;
        }
        sum[Math.min(pos[0], pos[n-1])] += 1;
        sum[Math.max(pos[0], pos[n-1])] -= 1;

        // 누적합 계산
        for(int i=1;i<sum.length-1;i++) {
            sum[i] += sum[i-1];
        }

        return findMax(sum);
    }

    public static int findMin(int[] arr) {
        int min = Integer.MAX_VALUE;

        for(int num : arr) {
            min = Math.min(min, num);
        }

        return min;
    }

    public static int findMax(int[] arr) {
        int max = Integer.MIN_VALUE;

        for(int num : arr) {
            max = Math.max(max, num);
        }

        return max;
    }
}
