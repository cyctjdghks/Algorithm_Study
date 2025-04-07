// 미니맥스 알고리즘
// 규칙
// 1. 2인용 게임
// 2. 두 경기자를 max, min으로 설정
// 3. 항상 max 먼저 시작 
// 4. 차례로 수를 두는 게임
// 5. 무조건 한명은 승리, 한명은 패배 -> 협동 승리 없음
class Solution {
    int[][] Board;
    int r, c;
    int[][] d = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
    
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        Board = board;
        r = board.length;
        c = board[0].length;
        
        return dfs(aloc[0], aloc[1], bloc[0], bloc[1]);
    }
    
    // a와 b가 번갈아 가면서 이동
    public int dfs(int ax, int ay, int bx, int by) {
        // 발판이 없는 경우
        if(Board[ax][ay] == 0) return 0;
        
        int ret = 0;
        
        // 현재 턴인 플레이어의 갈 수 있는 경우의 수를 계산하여 재귀 호출
        for (int[] dir : d) {
            // a 플레이어 다음 이동 위치
            int nx = ax + dir[0];
            int ny = ay + dir[1];
            
            // 다음 위치가 보드를 벗어나거나 발판이 없는 경우
            if(0 > nx || nx >= r || 0 > ny || ny >= c || Board[nx][ny] == 0) continue;
            
            // 다른 발판으로 이동했으므로 밟고 있던 발판 사라짐
            Board[ax][ay] = 0;
            
            // 서로 번갈아 가면서 이동하기 때문에
            int val = dfs(bx, by, nx, ny) + 1;
            
            Board[ax][ay] = 1;
            
            // 짝수 -> 패배, 홀수 -> 승리
            // 현재는 턴은 지고, 새로 계산된 턴이 이기는 경우
            if(ret % 2 == 0 && val % 2 == 1) ret = val;
            // 모두 지는 경우, 최대로 지는걸 선택
            else if(ret % 2 == 0 && val % 2 == 0) ret = ret > val ? ret : val;
            // 모두 이기는 경우, 최소로 이기는걸 선택
            else if(ret % 2 == 1 && val % 2 == 1) ret = ret < val ? ret : val;
        }
        
        return ret;
    }
}