import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.StringTokenizer;

public class Main {
	static int C;
	static long[] numbers;

	public static void main(String[] args) throws IOException {
		//init
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		st = new StringTokenizer(br.readLine());
		numbers = new long[N];
		for (int i = 0; i < N; i++) {
			numbers[i] = Integer.parseInt(st.nextToken());
		}

		System.out.println(makeCollArr(N));
	}

	private static long makeCollArr(int N) {

		ArrayList<Long> na = new ArrayList<>();
		ArrayList<Long> nb = new ArrayList<>();
		dfs(0, N / 2, 0, na);
		dfs(N / 2 + 1, N - 1, 0, nb);

		long count = 0;
		Collections.sort(na);
		Collections.sort(nb);
		for (Long aLong : na) {
			long curr = C - aLong;
			count += binarySearch(nb, curr) + 1;
		}
		return count;
	}

	private static void dfs(int start, int end, long currSum, ArrayList<Long> list) {
		if (currSum > C)
			return;
		if (start > end) {
			list.add(currSum);
			return;
		}

		dfs(start + 1, end, currSum + numbers[start], list);
		dfs(start + 1, end, currSum, list);
	}

	private static int binarySearch(ArrayList<Long> list, long target){
		int left = 0, right = list.size() -1;
		int idx = -1;
		while (left <= right) {
			int mid = (left + right) / 2;
			if (list.get(mid) <= target) {
				idx = mid;
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		return idx;
	}
}
