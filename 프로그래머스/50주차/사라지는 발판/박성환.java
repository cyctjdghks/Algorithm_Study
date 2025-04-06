class Solution {

    int[][] board;
    int rowCount;
    int colCount;
    int[] dx = {-1, 1, 0, 0};
    int[] dy = {0, 0, -1, 1};

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        this.board = board;
        this.rowCount = board.length;
        this.colCount = board[0].length;

        return dfs(aloc[0], aloc[1], bloc[0], bloc[1]);
    }

    private int dfs(int playerX, int playerY, int opponentX, int opponentY) {
        if (board[playerX][playerY] == 0) {
            return 0;
        }

        int maxTurn = 0;

        for (int d = 0; d < 4; d++) {
            int nextX = playerX + dx[d];
            int nextY = playerY + dy[d];

            if (!isInBounds(nextX, nextY) || board[nextX][nextY] == 0) {
                continue;
            }

            board[playerX][playerY] = 0;

            int turnCount = dfs(opponentX, opponentY, nextX, nextY) + 1;

            board[playerX][playerY] = 1;

            if (maxTurn % 2 == 0 && turnCount % 2 == 1) {
                maxTurn = turnCount; // 이길 수 있는 경우
            } else if (maxTurn % 2 == 0 && turnCount % 2 == 0) {
                maxTurn = Math.max(maxTurn, turnCount); // 지는 경우 중 가장 오래 버티기
            } else if (maxTurn % 2 == 1 && turnCount % 2 == 1) {
                maxTurn = Math.min(maxTurn, turnCount); // 이기는 경우 중 최소 턴
            }
        }

        return maxTurn;
    }

    private boolean isInBounds(int x, int y) {
        return x >= 0 && x < rowCount && y >= 0 && y < colCount;
    }
}
