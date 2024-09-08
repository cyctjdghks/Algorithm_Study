import java.util.*;

class Solution {
    // dx 만큼 이동
    // 0 : 0, -1 왼쪽
    // 1 : 0, 1 오른쪽
    // 2 : -1, 0 위쪽
    // 3 : 1, 0 아래쪽
    // 반대로 이동

    public long solution(int n, int m, int x, int y, int[][] queries) {
        long answer = 0;

        long x1 = x;
        long x2 = x;
        long y1 = y;
        long y2 = y;

        for(int i = queries.length - 1; i >= 0 ; i--) {
            int dir = queries[i][0];
            int cost = queries[i][1];

            switch(dir) {
                case 0 :
                    if(y1 > 0) {
                        y1 += cost;
                    }
                    y2 = Math.min(m - 1, y2 + cost);

                    break;
                case 1 :
                    if(y2 < m - 1) {
                        y2 -= cost;
                    }
                    y1 = Math.max(0, y1 - cost);

                    break;
                case 2 :
                    if(x1 > 0) {
                        x1 += cost;
                    }
                    x2 = Math.min(n - 1, x2 + cost);

                    break;
                case 3 :
                    if(x2 < n - 1) {
                        x2 -= cost;
                    }
                    x1 = Math.max(0, x1 - cost);

                    break;
                default :
                    break;
            }

            if(x1 >= n || x2 < 0 || y1 >= m || y2 < 0) {
                return 0;
            }

        }

        answer = (x2 - x1 + 1) * (y2 - y1 + 1);

        return answer;
    }
}