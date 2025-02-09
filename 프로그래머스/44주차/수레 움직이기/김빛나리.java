class Solution {
    int [] dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1};
    int n = 0, m = 0;
    int [][] map, redMap, blueMap;
    int rx = 0, ry = 0, bx = 0, by = 0;
    int answer = Integer.MAX_VALUE;
    
    public int solution(int[][] maze) {
        n = maze.length;
        m = maze[0].length;
        map = new int[n+1][m+1];
        redMap = new int[n+1][m+1];
        blueMap = new int[n+1][m+1];
        
        for(int i=1;i<=n;i++) {
            for(int j=1;j<=m;j++) {
                int val = maze[i-1][j-1];
                map[i][j] = val;
                
                if(val == 1) {
                    redMap[i][j] = 1;
                    rx = i;
                    ry = j;
                }
                else if(val == 2) {
                    blueMap[i][j] = 1; 
                    bx = i;
                    by = j;
                }
            }
        }
        
        dfs(0, rx, ry, bx, by);
        
        if(answer == Integer.MAX_VALUE) return 0;
        else return answer;
    }
    
    public void dfs(int count, int x1, int y1, int x2, int y2) {
        // 최소 턴
        if(map[x1][y1] == 3 && map[x2][y2] == 4) {
            answer = Math.min(answer, count);
            return;
        }
        // 파란색 이동
        else if(map[x1][y1] == 3) {
            for(int i=0;i<4;i++) {
                int nx = x2 + dx[i];
                int ny = y2 + dy[i];
                
                if(checkOver(nx, ny)) continue;
                if(blueMap[nx][ny] == 1) continue;
                if(map[nx][ny] == 3 || map[nx][ny] == 5) continue;
                
                blueMap[nx][ny] = 1;
                dfs(count + 1, x1, y1, nx, ny);
                blueMap[nx][ny] = 0;
            }
        }
        // 빨간색 이동
        else if(map[x2][y2] == 4) {
            for(int i=0;i<4;i++) {
                int nx = x1 + dx[i];
                int ny = y1 + dy[i];
                
                if(checkOver(nx, ny)) continue;
                if(redMap[nx][ny] == 1) continue;
                if(map[nx][ny] == 4 || map[nx][ny] == 5) continue;
                
                redMap[nx][ny] = 1;
                dfs(count + 1, nx, ny, x2, y2);
                redMap[nx][ny] = 0;
            }
        }
        else {
            // 빨간 노드 이동
            for(int i=0;i<4;i++) {
                int nx = x1 + dx[i];
                int ny = y1 + dy[i];
                
                if(checkOver(nx, ny)) continue;
                if(redMap[nx][ny] == 1 || map[nx][ny] == 5) continue;

                // 파란 노드 이동
                for(int j=0;j<4;j++) {
                    int nnx = x2 + dx[j];
                    int nny = y2 + dy[j];
                    
                    if(checkOver(nnx, nny)) continue;
                    if(blueMap[nnx][nny] == 1 || map[nnx][nny] == 5) continue;
                    if(nx == nnx && ny == nny) continue; 
                    if((nx == x2 && ny == y2) && (nnx == x1 && nny == y1)) continue;
                    
                    // 빨간 & 파란 같은 좌표로 이동
                    redMap[nx][ny] = 1;
                    blueMap[nnx][nny] = 1;
                    dfs(count + 1, nx, ny, nnx, nny);
                    redMap[nx][ny] = 0;
                    blueMap[nnx][nny] = 0;
                }
            }
        }
    }
    
    public boolean checkOver(int x, int y) {
        return x < 1 || y < 1 || x > n || y > m;
    }
}