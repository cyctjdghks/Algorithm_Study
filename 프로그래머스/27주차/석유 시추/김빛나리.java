import java.util.*;

class Solution {
    int[] dx = {-1, 0, 1, 0};
	int[] dy = {0, 1, 0, -1};
    int n = 0, m = 0;
    int[] cnt;
    boolean[][] visited;
    
    public int solution(int[][] land) {
        int answer = 0;
        
        n = land.length;
        m = land[0].length;
        cnt = new int[m];
        visited = new boolean[n][m];

        for(int i=0;i<n;i++) {
            for(int j=0;j<m;j++) {
                if(land[i][j] == 0 || visited[i][j]) continue;
                
                bfs(land, i, j);
            }
        }
        
        for(int i=0;i<m;i++) {
            answer = Math.max(answer, cnt[i]);
        }
        
        return answer;
    }
    
    public void bfs(int[][] land, int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        HashSet<Integer> set = new HashSet<>();
        
        queue.add(new int[]{x, y});
        visited[x][y] = true;
        int count = 1;

        while(!queue.isEmpty()) {
            int[] cur = queue.poll();
            set.add(cur[1]);

            for(int i=0;i<4;i++) {
                int nx = cur[0] + dx[i];
                int ny = cur[1] + dy[i];

                if(
                    (nx < 0 || nx >= n || ny < 0 || ny >= m) ||
                    land[nx][ny] == 0 ||
                    visited[nx][ny]
                ) continue;
                
                queue.add(new int[]{nx, ny});
                visited[nx][ny] = true;
                count++;
            }
        }

        for(int idx : set) {
            cnt[idx] += count;
        }
    }
}