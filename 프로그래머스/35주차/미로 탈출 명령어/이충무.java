import java.util.*;

class Solution {
    static int sx, sy, ex, ey, len;
    static int ln, lm;
    static int[] dx = {1, 0, 0, -1};
    static int[] dy = {0, -1, 1, 0};
    static String[] di = {"d", "l", "r", "u"};
    static String ans = null;
    public String solution(int n, int m, int x, int y, int r, int c, int k) {

        sx = x; sy = y; ex = r; ey = c; len = k;
        ln = n; lm = m;

        if((Math.abs(sx-ex) + Math.abs(sy-ey)) % 2 != k%2) {
            return "impossible";
        }

        if((Math.abs(sx-ex) + Math.abs(sy-ey)) > k) return "impossible";
        dfs(sx, sy, new StringBuffer(""));

        return ans;
    }

    public static void dfs(int cx, int cy, StringBuffer s) {
        if(ans != null) return;

        if(s.length() == len && cx == ex && cy == ey) {
            ans = s.toString();
            return;
        } else if(s.length() == len) return;

        int dist = Math.abs(ex-cx) + Math.abs(ey-cy);
        if(len - s.length() < dist) return;


        for(int i=0; i<4; ++i) {
            int nx = cx + dx[i], ny = cy + dy[i];
            if(nx < 1 || nx > ln || ny < 1 || ny > lm) {
                continue;
            }

            s.append(di[i]);

            dfs(nx, ny, s);
            s.delete(s.length()-1, s.length());
        }

    }
}