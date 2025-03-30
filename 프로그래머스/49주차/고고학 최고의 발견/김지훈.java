class Solution {
    int N;
    int minCount = Integer.MAX_VALUE;
    int[][] dirs = {{0, 0}, {0, 1}, {0, -1}, {1, 0}, {-1, 0}}; // 자기 자신 + 상하좌우

    public int solution(int[][] clockHands) {
        N = clockHands.length;
        int[][] original = new int[N][N];

        // 원본 배열 복사
        for (int i = 0; i < N; i++) {
            System.arraycopy(clockHands[i], 0, original[i], 0, N);
        }

        // 첫 번째 줄의 모든 조합 시도 (0~3번까지)
        for (int mask = 0; mask < Math.pow(4, N); mask++) {
            int[][] board = new int[N][N];
            for (int i = 0; i < N; i++) {
                System.arraycopy(original[i], 0, board[i], 0, N);
            }

            int temp = mask;
            int count = 0;
            int[] topMoves = new int[N];

            for (int i = 0; i < N; i++) {
                topMoves[i] = temp % 4;
                temp /= 4;
            }

            // 첫 줄 회전
            for (int j = 0; j < N; j++) {
                if (topMoves[j] > 0) {
                    rotate(board, 0, j, topMoves[j]);
                    count += topMoves[j];
                }
            }

            // 나머지 줄 회전
            for (int i = 1; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    int times = (4 - board[i - 1][j]) % 4;
                    if (times > 0) {
                        rotate(board, i, j, times);
                        count += times;
                    }
                }
            }

            // 마지막 줄 확인
            boolean solved = true;
            for (int j = 0; j < N; j++) {
                if (board[N - 1][j] != 0) {
                    solved = false;
                    break;
                }
            }

            if (solved) {
                minCount = Math.min(minCount, count);
            }
        }

        return minCount;
    }

    private void rotate(int[][] board, int x, int y, int times) {
        for (int t = 0; t < times; t++) {
            for (int[] d : dirs) {
                int nx = x + d[0];
                int ny = y + d[1];
                if (0 <= nx && nx < N && 0 <= ny && ny < N) {
                    board[nx][ny] = (board[nx][ny] + 1) % 4;
                }
            }
        }
    }
}
