import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
	static int[][] dMove = {{0, 1}, {1, 0}};
	static int[][] map;
	static int maxSum = 0;
	public static void main(String[] args) throws IOException {
		//init
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()), M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		for (int i = 0; i < N; i++) {
			map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
		}

		boolean[][] isVisited = new boolean[N][M];
		dfs(0, 0, isVisited, 0);
		System.out.println(maxSum);
	}

	private static void dfs(int x, int y, boolean[][] isVisited, int currSum){
		maxSum = Math.max(currSum, maxSum);

		String currNum = "";
		int length = 0, N = isVisited.length, M = isVisited[0].length;
		for (int d = 0; d < dMove.length; d++) {			//direction
			int nextX = x, nextY = y;
			while(isAvailable(nextX, nextY, isVisited)){	//length
				isVisited[nextX][nextY] = true;
				currNum += map[nextX][nextY];
				for (int i = 0; i < N; i++) {
					for (int j = 0; j < M; j++) {
						if (!isVisited[i][j]) {
							dfs(i, j, isVisited, currSum + Integer.parseInt(currNum));
							i = N; j = M;
							break;
						}
					}
				}
				nextX += dMove[d][0]; nextY += dMove[d][1];
				length++;
			}

			//unvisited
			for (int l = 0; l < length; l++) {
				isVisited[nextX - dMove[d][0]][nextY - dMove[d][1]] = true;
			}
		}
	}

	private static boolean isAvailable(int x, int y, boolean[][] isVisited) {
		int N = isVisited.length, M = isVisited[0].length;
		return x >= 0 && x < N && y >= 0 && y < M && !isVisited[x][y];
	}
}
