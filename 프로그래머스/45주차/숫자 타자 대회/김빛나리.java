import java.util.*;

class Solution {
    int[][] udlr = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
	int[][] diagonal = {{-1, -1}, {1, -1}, {-1, 1}, {1, 1}}; 
    int[][] board = {
        {1, 2, 3}, 
        {4, 5, 6}, 
        {7, 8, 9}, 
        {-1, 0, -1}
    };
	int[][][] dp;
	int[][] w = new int[10][10];
	int[] number;
	int len;
    
    public int solution(String numbers) {
        len = numbers.length();
		number = new int[len];
        // dp[누를 숫자의 인덱스][왼쪽 손가락의 위치][오른쪽 손가락의 위치] = 가중치
		dp = new int[len][10][10];

        // String으로된 numbers를 int 배열 number로 변환 + dp를 max value로 초기화
		for(int i=0;i<len;i++) {
			number[i] = numbers.charAt(i) - '0';
            
			for(int j=0;j<10;j++) {
                Arrays.fill(dp[i][j], Integer.MAX_VALUE);
            }
		}

        // w는 i에서 j로 가는 경우의 가중치 값을 저장
		for(int i=0;i<10;i++) {
            for(int j=0;j<10;j++) {
                w[i][j] = 100;
            }
        }

		for(int i=0;i<4;i++) {
            for(int j=0;j<3;j++) {
                // *이나 #이 아닐 경우 -> 숫자일 경우
                if(board[i][j] != -1) bfs(i, j, board[i][j]);
            }
        }
        
        // 0 = index, 4 = left 시작점, 6 = right 시작점
		return getMinTime(0, 4, 6);
    }
    
    public void bfs(int row, int col, int num) {
        // 0, 1 : 좌표 | 2 : 해당 숫자 | 3 : 가중치 합
		Queue<int[]> queue = new LinkedList<>();

		queue.offer(new int[] {row, col, num, 0});
        // 자기자신을 눌렀을 때 가중치
		w[num][num] = 1;

		while(!queue.isEmpty()) {
			int[] cur = queue.poll();

            // 상하좌우 이동
			for(int i=0;i<4;i++) {
				int nr = cur[0] + udlr[i][0];
				int nc = cur[1] + udlr[i][1];

                // 다음 위치가 board를 벗어나지 않고, *이나 #이 아니고,
                // 현재 숫자 num에서 다음 위치의 숫자 board[nr][nc]의 가중치가 가중치 합에서 상하좌우 이동 가중치(2) 더한 값보다 클 경우 -> 최솟값을 구해야 하니까
                if(check(nr, nc) && board[nr][nc] != -1 && w[num][board[nr][nc]] > cur[3] + 2) {
					w[num][board[nr][nc]] = cur[3] + 2;
					w[board[nr][nc]][num] = cur[3] + 2;
					queue.offer(new int[] {nr, nc, board[nr][nc], cur[3] + 2});
				}
			}

            // 대각선 이동
			for(int i=0;i<4;i++) { 
				int nr = cur[0] + diagonal[i][0];
				int nc = cur[1] + diagonal[i][1];

                // 다음 위치가 board를 벗어나지 않고, *이나 #이 아니고,
                // 현재 숫자 num에서 다음 위치의 숫자 board[nr][nc]의 가중치가 가중치 합에서 상하좌우 이동 가중치(3) 더한 값보다 클 경우 -> 최솟값을 구해야 하니까
                if(check(nr, nc) && board[nr][nc] != -1 && w[num][board[nr][nc]] > cur[3] + 3) {
					w[num][board[nr][nc]] = cur[3] + 3;
					w[board[nr][nc]][num] = cur[3] + 3;
					queue.offer(new int[] {nr, nc, board[nr][nc], cur[3] + 3});
				}
			}
		}
	}
    
    public boolean check(int r, int c) {
		if(0 > r || r >= 4 || 0 > c || c >= 3) return false;
        
		return true;
	}
    
    public int getMinTime(int idx, int left, int right) {
        // 모든 숫자 배열을 다 돌았을 경우
		if(idx == len) return 0;

        // 점화식
        // dp[n][left][right] = min(w[left][n] + dp[n+1][n][right], w[right][n] + dp[n+1][left][n])
		if(dp[idx][left][right] == Integer.MAX_VALUE) {
            // 둘을 비교해서 최솟값을 구할 예정
			int first = Integer.MAX_VALUE, second = Integer.MAX_VALUE;
            
            // 오른손 위치가 눌러야 할 숫자 위치가 아닌 경우, 왼손을 위치 시키고, 다음 숫자로 이동
			if(right != number[idx]) 
				first = w[left][number[idx]] + getMinTime(idx + 1, number[idx], right);
            
            // 왼손 위치가 눌러야 할 숫자 위치가 아닌 경우, 오른손을 위치 시키고, 다음 숫자로 이동
			if(left != number[idx]) 
				second = w[right][number[idx]] + getMinTime(idx + 1, left, number[idx]);
			
			dp[idx][left][right] = Math.min(first, second);
		}

		return dp[idx][left][right];
	}
}