class Solution {
    // 최단이동거리를 찾는게 아니라 정해진 이동 수 k에 맞춰서 경로를 찾는거기때문에 백트래킹
    // 이동 규칙은 사전순 d -> l -> r -> u (불필요한 탐색 경로를 줄이기 위해. 규칙이 없으면 메모리 사용량과 시간복잡도 측면에서 현실적으로 비효율적. 마지막에 정렬도 해야함)
    // k가 맨해튼 경로보다 작으면 불가능
    // |k - 맨해튼경로| 가 홀수면 불가능. (맨해튼경로에서 다시 도착점으로 가려면 무조건 짝수번 이동해야 하기 때문)

    int[] dx = {1, 0, 0, -1};
    int[] dy = {0, -1, 1, 0};
    String[] dir = {"d", "l", "r", "u"};

    String answer;

    public String solution(int n, int m, int x, int y, int r, int c, int k) {

        int minDistance = Math.abs(r - x) + Math.abs(c - y);
        dfs(n, m, x, y, r, c, k, minDistance, "");

        return answer;
    }

    public boolean dfs(int n, int m, int x, int y, int r, int c, int k, int minDistance, String str) {

        if (k == 0 && x == r && y == c) {
            answer = str;
            return true;
        }

        if (k < minDistance || (k - minDistance) % 2 != 0) {
            answer = "impossible";
            return false;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (1 <= nx && nx <= n && 1 <= ny && ny <= m) {
                if (dfs(n, m, nx, ny, r, c, k - 1, Math.abs(r - nx) + Math.abs(c - ny), str + dir[i])) {
                    return true;
                }
            }
        }

        return false;
    }
}