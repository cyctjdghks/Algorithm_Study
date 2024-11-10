import java.util.*;

class Solution {
    // bfs로 식량을 배열에 담는다.

    static int n,m;
    static List<Integer> answer = new ArrayList<>();
    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};
    static boolean[][] visited;
    static Queue<int[]> q = new LinkedList<>();

    public static void main(String[] args) {
        solution(new String[]{"XXX","XXX","XXX"});
    }

    public static List<Integer> solution(String[] maps) {

        n = maps.length;
        m = maps[0].length();

        visited = new boolean[n][m];

        for (int i=0; i<n; i++) {
            String str = maps[i];
            for (int j=0; j<m; j++) {
                char c = str.charAt(j);
                if (c != 'X' && !visited[i][j]) {
                    visited[i][j] = true;
                    bfs(maps, i,j,0);
                }
            }
        }

        Collections.sort(answer);

        if (answer.size() == 0) {
            answer.add(-1);
        }

        return answer;
    }

    public static void bfs(String[] maps, int r, int c, int sum) {
        q.add(new int[]{r,c});

        while (!q.isEmpty()) {
            int[] pos = q.poll();
            r = pos[0];
            c = pos[1];
            sum += Integer.parseInt(String.valueOf(maps[r].charAt(c)));
            for (int i=0; i<4; i++) {
                int nr = r + dr[i];
                int nc = c + dc[i];

                if (0 <= nr && nr < n && 0 <= nc && nc < m && !visited[nr][nc]) {
                    if (maps[nr].charAt(nc) != 'X') {
                        visited[nr][nc] = true;
                        q.add(new int[]{nr,nc});
                    }
                }
            }
        }

        answer.add(sum);
    }
}