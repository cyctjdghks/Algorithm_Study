import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Main {
	public static void main(String[] args) throws IOException {
		//init
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int count = Integer.parseInt(br.readLine());
		int[][] arr = new int[count][2];
		for (int i = 0; i < count; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			arr[i] = new int[] {Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())};
		}

		Arrays.sort(arr, Comparator.comparingInt(o -> o[0]));
		int[] ans = new int[count];
		int currIdx = 0;
		Arrays.fill(ans, Integer.MAX_VALUE);
		ans[currIdx] = arr[0][1];
		for (int i = 1; i < count; i++) {
			int idx = Arrays.binarySearch(ans, arr[i][1]);
			idx = (idx < 0) ? -1 - idx : idx;
			currIdx = Math.max(idx, currIdx);
			ans[idx] = arr[i][1];
		}
		System.out.println(count - currIdx - 1);
	}

}
