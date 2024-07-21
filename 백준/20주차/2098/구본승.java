import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static StringTokenizer st;
	static StringBuilder sb = new StringBuilder();

	static final int MAX = Integer.MAX_VALUE>>2;
	static int N;
	static int[][] map, dp;
	
	public static void main(String[] args) throws Exception {
		init();
		solution();
	}

	private static void init() throws Exception {
		N = Integer.parseInt(br.readLine());
		map = new int[N][N];
		dp = new int[N][1<<N];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < N; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
			Arrays.fill(dp[i], MAX);
		}
	}
	
	private static void solution() {
		System.out.println(dfs(0,0));
	}
	
	private static int dfs(int bit, int node) {
		bit |= (1<<node);
		// 다 방문했을 때
		if (bit == (1<<N)-1) {
			if (map[node][0] > 0) return map[node][0];
			else return MAX>>2;
		}
		
		// 이미 방문한 루트일 때
		if (dp[node][bit] != MAX) return dp[node][bit];
		
		for (int i = 0; i < N; i++) {
			if ((bit&(1<<i)) == 0 && map[node][i] > 0) {
				dp[node][bit] = Math.min(dp[node][bit], dfs(bit, i) + map[node][i]);
			}
		}
		return dp[node][bit];
	}
}