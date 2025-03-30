class Solution {
    int answer = Integer.MAX_VALUE;
    int len = 0;
    int[][] map;
    int[] row;
    int [] dx = {0, 1, -1, 0, 0}, dy = {0, 0, 0, 1, -1};
    
    public int solution(int[][] clockHands) {
        len = clockHands.length;
        map = new int[len][len];
        row = new int[len];

        dfs(clockHands, 0);

        return answer;
    }
    
    public void dfs(int[][] arr, int depth) {
        if(depth == len) {
            int ans = 0;
            arrCopy(arr);
            
            for(int i=0;i<depth;i++) {
                if(row[i] != 0) {
                    rotate(0, i, row[i]);
                    ans += row[i];
                }
                
                if(answer < ans) return;
            }
            
            for(int i=1;i<depth;i++) {
                for(int j=0;j<depth;j++) {
                    if(map[i-1][j] != 0) {
                        int tmp = (map[i-1][j] * 3) % 4;
                        rotate(i, j, tmp);
                        ans += tmp;
                    }
                    
                    if(answer < ans) return;
                }
            }
            
            if(check()) answer = Math.min(answer, ans);
            
            return;
        }

        for(int i=0;i<4;i++) {
            row[depth] = i;
            dfs(arr, depth+1);
        }
    }
    
    public void arrCopy(int[][] arr) {
        for(int i=0;i<len;i++) {
            for(int j=0;j<len;j++) 
                map[i][j] = arr[i][j];
        }
    }
    
    public void rotate(int x, int y, int n) {
        for(int i=0;i<5; i++) {
            int nx = x+dx[i], ny = y+dy[i];
            
            if(nx < len && nx >= 0 && ny < len && ny >= 0) {
                map[nx][ny] = (map[nx][ny] + n) % 4;
            }
        }
    }
    
    public boolean check() {
        for(int i=0;i<len;i++) {
            if(map[len-1][i] != 0) return false;
        }
        
        return true;
    }
}