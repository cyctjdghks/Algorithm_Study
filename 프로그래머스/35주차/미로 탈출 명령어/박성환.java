import java.util.*;

class Solution {
    // d, l, r, u 순으로 탐색
    private static final int[] dx = {1, 0, 0, -1};
    private static final int[] dy = {0, -1, 1, 0};
    private static final String[] directionLabels = {"d", "l", "r", "u"};
    
    static int N, M;
    static int X, Y;
    static int R, C;
    private String answer = "";

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        N = n;
        M = m;
        X = x - 1; // 출발점 0-based 인덱스로 변환
        Y = y - 1;
        R = r - 1; // 도착점 0-based 인덱스로 변환
        C = c - 1;

        // 시작 지점에서 목표 지점까지의 거리
        int minDistance = Math.abs(X - R) + Math.abs(Y - C);

        // 도달 불가능한 경우 미리 "impossible" 반환
        if (minDistance > k || (k - minDistance) % 2 != 0) {
            return "impossible";
        }

        // DFS로 경로 탐색
        dfs(X, Y, k, new StringBuilder(), minDistance);

        return answer.isEmpty() ? "impossible" : answer;
    }

    private boolean dfs(int x, int y, int k, StringBuilder path, int diff) {
        // 종료 조건: 이동 횟수 소진하고 목표 지점 도달 시 정답 저장
        if (k == 0 && diff == 0) {
            answer = path.toString();
            return true;
        }

        // 사전순으로 가능한 경로 탐색 (d, l, r, u 순으로 탐색)
        for (int i = 0; i < 4; i++) {
            int nextX = x + dx[i];
            int nextY = y + dy[i];

            // 맵 범위 내에 있는지 확인
            if (isInBounds(nextX, nextY)) {
                int newDiff = Math.abs(nextX - R) + Math.abs(nextY - C);

                // 남은 이동 횟수와 목표 지점까지의 거리가 유효한 경우에만 탐색
                if (newDiff <= k - 1 && ((k - 1 - newDiff) % 2 == 0)) {
                    path.append(directionLabels[i]);
                    
                    if (dfs(nextX, nextY, k - 1, path, newDiff)) {
                        return true;
                    }
                    
                    // 백트래킹
                    path.deleteCharAt(path.length() - 1);
                }
            }
        }
        return false;
    }

    private boolean isInBounds(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
}
