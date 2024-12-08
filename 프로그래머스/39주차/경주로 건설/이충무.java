import java.util.*;

class Car {
	int x;
	int y;
	int dir; 
	int cost;

	Car(int x, int y, int dir, int cost) {
		x = x;
		y = y;
		dir = dir;
		cost = cost;
	}
}

class Solution {
	int[][] visit;
	int len;

	public int solution(int[][] board) {
		len = board.length;
		visit = new int[len][len];

		return search(board);
	}

	int[] dx = {-1, 1, 0, 0};
	int[] dy = {0, 0, -1, 1};

	public int search(int[][] board) {
		Queue<Car> q = new ArrayDeque();
		q.add(new Car(0, 0, 1, 100)); 
		q.add(new Car(0, 0, 3, 100));
		visit[0][0] = 100;

		int answer = Integer.MAX_VALUE;
		while (!q.isEmpty()) {
			Car cur = q.poll();

			if (cur.x == len - 1 && cur.y == len - 1) {
				answer = Math.min(answer, cur.cost);
				continue;
			}
            

			for (int d = 0; d < 4; ++d) {
				int nextX = cur.x + dx[d];
				int nextY = cur.y + dy[d];
				if (0 <= nextX && nextX < len && 0 <= nextY && nextY < len && board[nextX][nextY] == 0) {
					int weight = cur.dir == d ? 100 : 600;
					if (visit[nextX][nextY] == 0) {
						visit[nextX][nextY] = cur.cost + weight;
						q.add(new Car(nextX, nextY, d, cur.cost + weight));
					} else if (cur.cost + weight < visit[nextX][nextY] + 500) {
						visit[nextX][nextY] = cur.cost + weight;
						q.add(new Car(nextX, nextY, d, cur.cost + weight));
					} 
				}
			}
		}
		return answer - 100;
	}

}