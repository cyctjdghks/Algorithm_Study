import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
	static int[] orders, ans;
	public static void main(String[] args) throws IOException {
		//init
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int K = Integer.parseInt(br.readLine());
		StringTokenizer st = new StringTokenizer(br.readLine());
		orders = new int[(int)Math.pow(2.0, K) - 1];
		for (int i = 0; i < orders.length; i++) {
			orders[i] = Integer.parseInt(st.nextToken());
		}

		ans = new int[orders.length + 1];
		makeComplTree(K, 0,1);
		int j=1;
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		for (int i = 1; i <= K; i++) {
			for (; j < Math.pow(2.0, i); j++) {
				bw.write(ans[j] + " ");
			}
			bw.write("\n");
		}
		bw.flush();
		bw.close();
	}

	private static int makeComplTree(int level, int currOrderIdx, int currTreeIdx){
		int nextTreeIdx = currTreeIdx * 2;
		if (nextTreeIdx >= Math.pow(2.0, level)){
			ans[currTreeIdx] = orders[currOrderIdx];
			return currOrderIdx;
		}
		int order = makeComplTree(level, currOrderIdx, nextTreeIdx);
		ans[currTreeIdx] = orders[order + 1];
		return makeComplTree(level, order + 2, nextTreeIdx + 1);
	}
}
