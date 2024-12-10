import java.util.*;

class Solution {
    public int solution(int[][] board) {
        int[] dy = {-1, 0, 1, 0}, dx = {0, 1, 0, -1};
        int N = board.length;
        // 2가지 방향 - 세로 방향, 가로 방향
        int[][][] cost = new int[N][N][2];
        
        for(int i=0;i<N;i++) {
            for(int j=0;j<N;j++) {
                Arrays.fill(cost[i][j], Integer.MAX_VALUE);
            }
        }
        
        Queue<int[]> queue = new LinkedList<>();
        
        cost[0][0][0] = 0;
        cost[0][0][1] = 0;
        
        queue.add(new int[]{0, 0, 0});
        queue.add(new int[]{0, 0, 1});
        
        while(!queue.isEmpty()) {
            int[] cur = queue.poll();

            // 상하좌우
            for(int d=0;d<4;d++) {
                int ny = cur[0] + dy[d];
                int nx = cur[1] + dx[d];
                // 이동하는 위치에 따라 건설비용 계산 - 가로세로 판단
                int nc = cost[cur[0]][cur[1]][cur[2]] + ((cur[2] + d) % 2 == 0 ? 100 : 600);
                
                // 다음 위치가 설계 도면을 넘어간 경우
                if(ny < 0 || nx < 0 || ny >= N || nx >= N) continue;
                // 벽으로 채워져 있는 경우
                if(board[ny][nx] == 1) continue;
                // 이미 건설 비용이 최소 비용인 경우
                if (cost[ny][nx][d % 2] <= nc) continue;
                
                cost[ny][nx][d % 2] = nc;
                queue.add(new int[]{ny, nx, d % 2});
            }
        }
        
        int answer = Math.min(cost[N-1][N-1][0], cost[N-1][N-1][1]);
        
        return answer;
    }
}