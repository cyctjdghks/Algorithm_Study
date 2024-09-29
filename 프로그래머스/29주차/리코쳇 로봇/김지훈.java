import java.util.LinkedList;
import java.util.Queue;

public class Solution {

    // 상, 하, 좌, 우 방향을 나타내는 배열
    static int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public static int solution(String[] board) {
        int rows = board.length;
        int cols = board[0].length();

        int[] startPos = new int[2];
        int[] goalPos = new int[2];

        // 시작 위치와 목표 위치를 찾는다
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (board[i].charAt(j) == 'R') {
                    startPos[0] = i;
                    startPos[1] = j;
                } else if (board[i].charAt(j) == 'G') {
                    goalPos[0] = i;
                    goalPos[1] = j;
                }
            }
        }

        // BFS를 위한 큐와 방문 배열
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[rows][cols];

        // 시작점 큐에 삽입 및 방문 처리
        queue.add(new int[]{startPos[0], startPos[1], 0});
        visited[startPos[0]][startPos[1]] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];
            int moves = current[2];

            // 목표 위치에 도달한 경우
            if (x == goalPos[0] && y == goalPos[1]) {
                return moves;
            }

            // 4방향으로 이동
            for (int[] dir : directions) {
                int newX = x;
                int newY = y;

                // 벽 또는 장애물에 부딪힐 때까지 이동
                while (newX + dir[0] >= 0 && newX + dir[0] < rows &&
                        newY + dir[1] >= 0 && newY + dir[1] < cols &&
                        board[newX + dir[0]].charAt(newY + dir[1]) != 'D') {
                    newX += dir[0];
                    newY += dir[1];
                }

                // 새로운 위치가 방문된 적이 없으면 큐에 추가
                if (!visited[newX][newY]) {
                    visited[newX][newY] = true;
                    queue.add(new int[]{newX, newY, moves + 1});
                }
            }
        }

        // 목표에 도달할 수 없는 경우
        return -1;
    }

    public static void main(String[] args) {
        String[] board = {
                "...D..R",
                ".D.G...",
                "....D.D",
                "D....D.",
                "..D...."
        };

        int result = solution(board);
//        System.out.println("최소 이동 횟수: " + result);  // 출력: 7
    }
}