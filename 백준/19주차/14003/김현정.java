import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws IOException {
		//init
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int[][] arr = new int[2][N];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for(int i=0; i<N; i++){
			arr[0][i] = Integer.parseInt(st.nextToken());
		}

		int[] ans = new int[N];
		Arrays.fill(ans, Integer.MAX_VALUE);
		int size = 0;
		for (int i = 0; i < N; i++) {
			int result = Arrays.binarySearch(ans, 0, size, arr[0][i]);
			if (result < 0) {
				int idx = -result - 1;
				arr[1][i] = idx + 1;
				ans[idx] = arr[0][i];
				size = Math.max(size, idx + 1);
			}
		}

		ArrayList<Integer> list = new ArrayList<>();
		int cnt = size;
		for (int i = N - 1; i >= 0; i--) {
			if (cnt == arr[1][i]) {
				cnt--;
				list.add(arr[0][i]);
			}
		}
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(size + "\n");
		for (int i = list.size()-1; i >=0 ; i--) {
			bw.write(list.get(i) + " ");
		}
		bw.flush();
		bw.close();
	}
}
