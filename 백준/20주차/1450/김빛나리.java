import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

// 최대 30개 > 모든 부분집합의 수 = 2^30 > O(2^30) = 약 10억
// 둘로 쪼개 각 15개 > O(2^15) + O(2^15) = 약 6만
public class Main {
    static int[] weight;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        weight = new int[N];

        st = new StringTokenizer(br.readLine());

        for(int i=0;i<N;i++) {
            weight[i] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Long> left = new ArrayList<>();
        ArrayList<Long> right = new ArrayList<>();

        dfs(0, N / 2, 0, left);
        dfs(N / 2, N, 0, right);

        Collections.sort(right);

        int cnt = 0;
		int idx = 0;
		for(int i=0; i<left.size(); i++) {
            int start = 0;
            int end = right.size()-1;
            long val = left.get(i);
            
            while(start <= end) {
                int mid = (start + end) / 2;

                if(right.get(mid) <= C - val) start = mid+1;
                else end = mid-1;
            }

			idx = end;
			cnt += idx+1;
		}
            
        bw.write(cnt + "\n");
        bw.close();
    }

    public static void dfs(int start, int end, long sum, ArrayList<Long> list) {
        if (start >= end) {
            list.add(sum);
            return;
        }

        dfs(start + 1, end, sum, list);
        dfs(start + 1, end, sum + weight[start], list);
    }
}
