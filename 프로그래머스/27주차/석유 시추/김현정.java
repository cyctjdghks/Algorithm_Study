import java.util.*;

class Solution {
	static class Dot{
		int x;
		int y;

		Dot(int x, int y){
			this.x = x;
			this.y = y;
		}
	}

	boolean[][] isVisited;
	int[] dX = {0, 1, 0, -1}, dY = {1, 0, -1, 0};
	public int solution(int[][] land) {
		int n = land.length, m = land[0].length;
		isVisited = new boolean[n][m];
		int[] oilArr = new int[m];
		for(int i=0; i<n; i++){
			for(int j=0; j<m; j++){
				if(!isVisited[i][j] && land[i][j] == 1){
					bfs(oilArr, land, i, j);
				}
			}
		}
		Arrays.sort(oilArr);
		return oilArr[m-1];
	}

	private void bfs(int[] arr, int[][] land, int x, int y){
		Set<Integer> xSet = new HashSet<>();
		LinkedList<Dot> queue = new LinkedList<>();
		int size = 1;
		xSet.add(x);
		queue.addLast(new Dot(x, y));
		isVisited[x][y] = true;
		while(!queue.isEmpty()){
			Dot curr = queue.removeFirst();
			for(int i=0; i<dX.length; i++){
				int nextX = curr.x + dX[i], nextY = curr.y + dY[i];
				if(isAvailable(nextX, nextY)
					&& !isVisited[nextX][nextY] && land[nextX][nextY] == 1){
					size++; xSet.add(nextX);
					isVisited[nextX][nextY] = true;
					queue.addLast(new Dot(nextX, nextY));
				}
			}
		}

		for(Integer idx : xSet){
			arr[idx] += size;
		}
	}

	private boolean isAvailable(int x, int y){
		int n = isVisited.length, m = isVisited[0].length;
		return x>=0 && x<n && y>=0 && y<m;
	}
}
