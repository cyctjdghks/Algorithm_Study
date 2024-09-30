import java.util.*;

class Solution {
    static int N, M;
    static int[][] dir = {{-1,0}, {1,0}, {0,1}, {0,-1}};

    class Point{
        int x;
        int y;
        int cnt;
        public Point(int x, int y, int cnt){
            this.x = x;
            this.y = y;
            this.cnt = cnt;
        }
    }

    public boolean checkValue(int x, int y){
        if(x < 0 || y < 0 || x >= N || y >= M){
            return false;
        }
        return true;
    }

    public int solution(String[] board) {
        N = board.length;
        M = board[0].length();
        boolean[][] visited = new boolean[N][M];
        Queue<Point> q = new LinkedList<>();

        // 1. 시작점 찾기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (board[i].charAt(j) == 'R') {
                    q.add(new Point(i, j, 0));
                    visited[i][j] = true;
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        while (!q.isEmpty()) {
            Point cur = q.poll();

            // 2-1. 도착점에 도달하면 cnt를 체크하면서 최솟값을 찾는다.
            if (board[cur.x].charAt(cur.y) == 'G') {
                answer = Math.min(answer, cur.cnt);
            }

            for (int i = 0; i < 4; i++) {
                int next_x = cur.x + dir[i][0];
                int next_y = cur.y + dir[i][1];

                while (true) {
                    // 2-2. 범위 내부에 있는 경우
                    if(checkValue(next_x, next_y) && board[next_x].charAt(next_y) != 'D'){
                        next_x += dir[i][0];
                        next_y += dir[i][1];
                    }
                    else{ // 범위를 벗어나거나 방해물을 만난 경우
                        next_x -= dir[i][0];
                        next_y -= dir[i][1];
                        break;
                    }
                }

                if (!visited[next_x][next_y]) {
                    q.add(new Point(next_x, next_y, cur.cnt + 1));
                    visited[next_x][next_y] = true;
                }
            }
        }
        if (answer == Integer.MAX_VALUE) {
            return -1;
        } else {
            return answer;
        }
    }
}