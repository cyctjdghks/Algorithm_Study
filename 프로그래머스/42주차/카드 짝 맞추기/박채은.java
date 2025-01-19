import java.util.*;

class Solution {
    public class Point {
        int x, y, t;

        public Point(int x, int y, int t) {
            this.x = x;
            this.y = y;
            this.t = t;
        }
    }

    static public int[][] board;
    static public boolean[] existCard = new boolean[7];
    static public Point[][] cardPos = new Point[7][2];
    static int total = 0, result = Integer.MAX_VALUE; // 카드의 type 개수

    public int solution(int[][] board, int r, int c) {
        this.board = board;

        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                if (board[i][j] == 0) continue;
                if (!existCard[board[i][j]]) {
                    existCard[board[i][j]] = true;
                    total++;
                    cardPos[board[i][j]][0] = new Point(i, j, 0);
                } else {
                    cardPos[board[i][j]][1] = new Point(i, j, 0);
                }
            }
        }

        dfs(0, 0, r, c);
        return result;
    }

    // 카드 제거 순서
    void dfs(int idx, int totalDest, int r, int c){
        if(idx == total){
            result = Math.min(result, totalDest);
            return;
        }

        for(int i=1;i<=6;i++){
            if(!existCard[i]){
                continue;
            }
            // 현재 > 첫 번째 위치 > 두 번째 위치 + 2(enter)
            int moveCnt1 = bfs(r, c, cardPos[i][0].x, cardPos[i][0].y) + bfs(cardPos[i][0].x, cardPos[i][0].y, cardPos[i][1].x, cardPos[i][1].y) + 2;
            // 현재 > 두 번째 위치 > 첫 번째 위치 + 2(enter)
            int moveCnt2 = bfs(r, c, cardPos[i][1].x, cardPos[i][1].y) + bfs(cardPos[i][1].x, cardPos[i][1].y, cardPos[i][0].x, cardPos[i][0].y) + 2;

            // 탐색 후, 제거 완료 처리
            existCard[i] = false;
            board[cardPos[i][0].x][cardPos[i][0].y] = 0;
            board[cardPos[i][1].x][cardPos[i][1].y] = 0;

            if(moveCnt1 < moveCnt2){
                // moveCnt1으로 dfs, 시작점이 2 번쨰 위치
                dfs(idx + 1, totalDest + moveCnt1, cardPos[i][1].x, cardPos[i][1].y);
            }
            else{
                dfs(idx + 1, totalDest + moveCnt2, cardPos[i][0].x, cardPos[i][0].y);
            }

            // dfs 완료 후, 원상복귀
            board[cardPos[i][0].x][cardPos[i][0].y] = i;
            board[cardPos[i][1].x][cardPos[i][1].y] = i;
            existCard[i] = true;
        }
    }

    public int bfs(int startX, int startY, int endX, int endY){
        Queue<Point> que = new ArrayDeque<>();
        boolean[][] visited = new boolean[4][4];
        int[] dx = {0,0,1,-1};
        int[] dy = {1,-1,0,0};

        que.add(new Point(startX, startY,0));
        visited[startX][startY] = true;

        while(!que.isEmpty()){
            Point cur = que.poll();
            if(cur.x == endX && cur.y == endY){ // 도착지에 도착
                return cur.t;
            }
            // 방향키 이동
            for(int i=0;i<4;i++){
                int X = cur.x + dx[i];
                int Y = cur.y + dy[i];
                if(X < 0 || Y < 0 || X >= 4 || Y>= 4 || visited[X][Y]){
                    continue;
                }
                que.add(new Point(X, Y, cur.t+1));
                visited[X][Y] = true;
            }
            // ctrl 방향키 이동
            for(int i=0;i<4;i++){
                int X = cur.x;
                int Y = cur.y;
                while(true){ // 카드를 만날 때까지 while
                    X+= dx[i];
                    Y+= dy[i];
                    if(X < 0 || Y < 0 || X == 4 || Y == 4){
                        X-= dx[i];
                        Y-=dy[i];
                        break;
                    }
                    if(board[X][Y] != 0){
                        break;
                    }
                }
                if(visited[X][Y]) continue;
                visited[X][Y] = true;
                que.add(new Point(X, Y, cur.t+1));
            }
        }
        return -1;
    }
}