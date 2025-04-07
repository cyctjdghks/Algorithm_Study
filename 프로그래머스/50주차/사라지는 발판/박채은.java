class Solution {

    int[][] gameBoard;
    int rowCount;
    int colCount;
    int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        this.gameBoard = board;
        this.rowCount = board.length;
        this.colCount = board[0].length;
        return dfs(aloc[0], aloc[1], bloc[0], bloc[1]);
    }

    public int dfs(int currentX, int currentY, int opponentX, int opponentY) {
        if (gameBoard[currentX][currentY] == 0) {
            return 0;
        }

        int result = 0;
        for (int[] direction : directions) {
            int nextX = currentX + direction[0];
            int nextY = currentY + direction[1];

            if (isOutOfBounds(nextX, nextY) || gameBoard[nextX][nextY] == 0) {
                continue;
            }

            gameBoard[currentX][currentY] = 0;
            int moveResult = dfs(opponentX, opponentY, nextX, nextY) + 1;
            gameBoard[currentX][currentY] = 1;

            if (result % 2 == 0 && moveResult % 2 == 1) {
                result = moveResult;
            } else if (result % 2 == 0 && moveResult % 2 == 0) {
                result = Math.max(result, moveResult);
            } else if (result % 2 == 1 && moveResult % 2 == 1) {
                result = Math.min(result, moveResult);
            }
        }

        return result;
    }

    public boolean isOutOfBounds(int x, int y) {
        return x < 0 || x >= rowCount || y < 0 || y >= colCount;
    }
}
