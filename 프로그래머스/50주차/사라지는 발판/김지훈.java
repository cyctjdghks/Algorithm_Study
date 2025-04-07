class Solution {
    private final int[][] dirs = {{-1, 0}, {0, 1}, {1, 0}, {0, -1}};
    private int row, col;

    public int solution(int[][] board, int[] aloc, int[] bloc) {
        row = board.length;
        col = board[0].length;
        return play(board, aloc[0], aloc[1], bloc[0], bloc[1]).turnCount;
    }

    private Result play(int[][] board, int ar, int ac, int br, int bc) {
        // 현재 플레이어가 설 수 없는 경우 패배
        if (board[ar][ac] == 0) {
            return new Result(false, 0);
        }

        boolean canWin = false;
        int minTurn = Integer.MAX_VALUE;
        int maxTurn = 0;

        for (int[] d : dirs) {
            int nr = ar + d[0];
            int nc = ac + d[1];

            if (nr < 0 || nr >= row || nc < 0 || nc >= col || board[nr][nc] == 0) {
                continue;
            }

            // 이동하고 현재 위치 발판 제거
            board[ar][ac] = 0;
            Result next = play(board, br, bc, nr, nc);
            board[ar][ac] = 1;

            if (!next.canWin) {
                canWin = true;
                minTurn = Math.min(minTurn, next.turnCount + 1);
            } else {
                maxTurn = Math.max(maxTurn, next.turnCount + 1);
            }
        }

        if (canWin) {
            return new Result(true, minTurn);
        } else {
            return new Result(false, maxTurn);
        }
    }

    static class Result {
        boolean canWin;
        int turnCount;

        Result(boolean canWin, int turnCount) {
            this.canWin = canWin;
            this.turnCount = turnCount;
        }
    }
}
