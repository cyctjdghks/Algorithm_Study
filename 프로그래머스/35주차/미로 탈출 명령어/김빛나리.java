class Solution {
    int[] dx = {1, 0, 0, -1};
    int[] dy = {0, -1, 1, 0};
    String[] str = {"d", "l", "r", "u"};
    int N = 0, M = 0, R = 0, C = 0;
    String answer = "";
    
    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        N = n;
        M = m;
        R = r-1;
        C = c-1;
        
        // 현재 위치에서 도착 위치까지 남은 거리
        int diff = Math.abs((r-1)-(x-1)) + Math.abs((c-1)-(y-1));
        
        dfs(x-1, y-1, k, "", diff);
        
        if(answer.length() == 0) return "impossible";
        
        return answer;
    }
    
    public boolean dfs(int x, int y, int k, String s, int diff) {
        // 탈출까지 이동해야 하는 거리를 만족하고, 남은 거리가 0인 경우
        if(k == 0 && diff == 0) {
            answer = s;
            return true;
        }
        
        for(int i=0;i<4;i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];;
            
            // 지도를 벗어나지 않은 경우
            // 남은 거리가 이동해야 하는 거리보다 적거나 같은 경우
            if(0 <= nx && nx < N && 0 <= ny && ny < M && diff <= k) {
                // 남은 거리와 이동해야하는 거리가 같이 홀수이거나 짝수인 경우
                if((diff % 2 == 0 && k % 2 == 0) || (diff % 2 == 1 && k % 2 == 1)) {
                    StringBuilder sb = new StringBuilder(s);
                    sb.append(str[i]);
                    
                    if(dfs(nx, ny, k-1, sb.toString(), Math.abs(nx - R) + Math.abs(ny - C))) return true;
                }
            }
        }
        
        return false;
    }
}