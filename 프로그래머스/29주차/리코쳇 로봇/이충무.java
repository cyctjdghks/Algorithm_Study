import java.util.*;

class Solution {
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    int n, m;

    class Point {
        int x, y, depth;

        public Point(int x, int y, int depth) {
            this.x = x;
            this.y = y;
            this.depth = depth;
        }
    }

    public int solution(String[] board) {
        int answer = 0;

        n = board.length;
        m = board[0].length();

        Point robot = null;
        Point goal = null;

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                char ch = board[i].charAt(j);

                if (ch == 'R') {
                    robot = new Point(i, j, 0);
                } else if (ch == 'G') {
                    goal = new Point(i, j, 0);
                }
            }
        }

        answer = bfs(board, robot, goal);

        return answer;
    }

    private int bfs(String[] board, Point robot, Point goal) {
        Queue<Point> qu = new LinkedList<>();
        qu.add(robot);
        boolean[][] visited = new boolean[n][m];
        visited[robot.x][robot.y] = true;

        while (!qu.isEmpty()) {
            Point cn = qu.poll();

            if (cn.x == goal.x && cn.y == goal.y) {
                return cn.depth;
            }

            for (int i = 0; i < 4; i++) {
                int nx = cn.x;
                int ny = cn.y;

                while ((nx>=0 && ny>=0 && nx<n &&ny<m) && board[nx].charAt(ny) != 'D') {
                    nx += dx[i];
                    ny += dy[i];
                }

                nx -= dx[i];
                ny -= dy[i];
                
                if (visited[nx][ny] || (cn.x == nx && cn.y == ny)) continue;

                visited[nx][ny] = true;
                qu.add(new Point(nx, ny, cn.depth + 1));
            }
        }

        return -1;
    }


}