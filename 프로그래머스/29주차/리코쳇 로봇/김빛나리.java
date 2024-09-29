// 최소로 움직이는 횟수를 구하는 문제이기 때문에 bfs 사용
import java.util.*;

class Solution {
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};
    
    class Point {
        int x = 0, y = 0, depth = 0;

        public Point(int x, int y, int depth) {
            this.x = x;
            this.y = y;
            this.depth = depth;
        }
    }
    
    public int solution(String[] board) {
        Point robot = null;
        // 좌표 : x, y
        int[] goal = new int[2];
        
        for(int i=0;i<board.length;i++) {
            for(int j=0;j<board[0].length();j++) {
                char ch = board[i].charAt(j);

                // 로봇의 처음 위치인 경우
                if(ch == 'R') robot = new Point(i, j, 0);
                // 목표 지점일 경우
                else if(ch == 'G') {
                    goal[0] = i;
                    goal[1] = j;
                }
            }
        }
        
        return bfs(board, robot, goal);
    }
    
    int bfs(String[] board, Point robot, int[] goal) {
        Queue<Point> queue = new LinkedList<>();
        boolean[][] visited = new boolean[board.length][board[0].length()];
        int depth = 0;
        
        queue.add(robot);
        visited[robot.x][robot.y] = true;

        while(!queue.isEmpty()) {
            Point cur = queue.poll();

            // 목표 지점까지 이동한 경우
            if(cur.x == goal[0] && cur.y == goal[1]) return cur.depth;

            // 상, 하, 좌, 우 방향 이동
            for(int i=0;i<4;i++) {
                int nx = cur.x;
                int ny = cur.y;

                // 게임판 위의 장애물이나 맨 끝에 부딪힐 때까지 반복
                while(0 <= nx && nx < board.length && 0 <= ny && ny < board[0].length() && board[nx].charAt(ny) != 'D') {
                    nx += dx[i];
                    ny += dy[i];
                }

                // 게임판 위의 장애물이나 맨 끝에 부딪히기 바로 전
                nx -= dx[i];
                ny -= dy[i];

                // 이미 방문했거나 같은 위치일 경우
                if(visited[nx][ny] || (cur.x == nx && cur.y == ny)) continue;

                visited[nx][ny] = true;
                queue.add(new Point(nx, ny, cur.depth + 1));
            }
        }

        return -1;
    }
}