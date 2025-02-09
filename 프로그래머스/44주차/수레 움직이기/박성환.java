class Solution {
    static int N, M;
    static int[] dx = {1, -1, 0, 0}; // 남, 북, 동, 서 방향 이동을 위한 배열
    static int[] dy = {0, 0, 1, -1};
    static int[][] map;
    static boolean[][] redVisited;
    static boolean[][] blueVisited;
    static int answer;

    public int solution(int[][] maze) {
        answer = Integer.MAX_VALUE;

        N = maze.length;
        M = maze[0].length;
        map = maze;

        redVisited = new boolean[N][M];
        blueVisited = new boolean[N][M];
        int[] redStart = new int[2];
        int[] blueStart = new int[2];

        // 빨간 수레와 파란 수레의 시작 위치 찾기
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (maze[i][j] == 1) {
                    redStart = new int[]{i, j};
                    redVisited[i][j] = true;
                } else if (maze[i][j] == 2) {
                    blueStart = new int[]{i, j};
                    blueVisited[i][j] = true;
                }
            }
        }

        dfs(0, redStart, blueStart);
        return answer == Integer.MAX_VALUE ? 0 : answer;
    }

    private void dfs(int count, int[] red, int[] blue) {
        // 빨간 수레와 파란 수레가 각각 도착지에 도착했을 경우 최소 이동 횟수 갱신
        if (map[red[0]][red[1]] == 3 && map[blue[0]][blue[1]] == 4) {
            answer = Math.min(answer, count);
            return;
        }

        // 불필요한 탐색 방지
        if (count >= answer) return;

        int nextRedX,nextRedY,nextBlueX,nextBlueY;
        // 빨간 수레가 먼저 도착한 경우, 파란 수레만 이동
        if (map[red[0]][red[1]] == 3) {
            for (int d = 0; d < 4; d++) {
                nextBlueX = blue[0] + dx[d];
                nextBlueY = blue[1] + dy[d];

                if (!isInBound(nextBlueX, nextBlueY) || isItWall(nextBlueX, nextBlueY) || map[nextBlueX][nextBlueY] == 3) continue;

                if (!(nextBlueX == red[0] && nextBlueY == red[1]) && !blueVisited[nextBlueX][nextBlueY]) {
                    blueVisited[nextBlueX][nextBlueY] = true;
                    dfs(count + 1, red, new int[]{nextBlueX, nextBlueY});
                    blueVisited[nextBlueX][nextBlueY] = false;
                }
            }
        }
        // 파란 수레가 먼저 도착한 경우, 빨간 수레만 이동
        else if (map[blue[0]][blue[1]] == 4) {
            for (int d = 0; d < 4; d++) {
                nextRedX = red[0] + dx[d];
                nextRedY = red[1] + dy[d];

                if (!isInBound(nextRedX, nextRedY) || isItWall(nextRedX, nextRedY) || map[nextRedX][nextRedY] == 4) continue;

                if (!(nextRedX == blue[0] && nextRedY == blue[1]) && !redVisited[nextRedX][nextRedY]) {
                    redVisited[nextRedX][nextRedY] = true;
                    dfs(count + 1, new int[]{nextRedX, nextRedY}, blue);
                    redVisited[nextRedX][nextRedY] = false;
                }
            }
        }
        // 두 수레가 모두 이동 가능한 경우
        else {
            for (int rd = 0; rd < 4; rd++) {
                nextRedX = red[0] + dx[rd];
                nextRedY = red[1] + dy[rd];

                if (!isInBound(nextRedX, nextRedY) || isItWall(nextRedX, nextRedY)) continue;

                for (int bd = 0; bd < 4; bd++) {
                    nextBlueX = blue[0] + dx[bd];
                    nextBlueY = blue[1] + dy[bd];

                    if (!isInBound(nextBlueX, nextBlueY) || isItWall(nextBlueX, nextBlueY)) continue;

                    if (nextRedX == nextBlueX && nextRedY == nextBlueY) continue;
                    if (nextRedX == blue[0] && nextRedY == blue[1] && nextBlueX == red[0] && nextBlueY == red[1]) continue;

                    if (!redVisited[nextRedX][nextRedY] && !blueVisited[nextBlueX][nextBlueY]) {
                        redVisited[nextRedX][nextRedY] = true;
                        blueVisited[nextBlueX][nextBlueY] = true;
                        dfs(count + 1, new int[]{nextRedX, nextRedY}, new int[]{nextBlueX, nextBlueY});
                        redVisited[nextRedX][nextRedY] = false;
                        blueVisited[nextBlueX][nextBlueY] = false;
                    }
                }
            }
        }
    }

    static boolean isInBound(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }

    static boolean isItWall(int x, int y) {
        return map[x][y] == 5;
    }
}
