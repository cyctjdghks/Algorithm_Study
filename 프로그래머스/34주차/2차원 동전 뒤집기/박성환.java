class Solution {
    static int[][] map; //global beginning
    static int[][] tar; //glboal target
    static int min = Integer.MAX_VALUE;
    static int n; //행
    static int m; //열

    public int solution(int[][] beginning, int[][] target) {
        n = beginning.length;
        m = beginning[0].length;
        
        boolean[] visitedRow = new boolean[n];
        boolean[] visitedCol = new boolean[m];
        
        map = beginning;
        tar = target;
        
        dfs(0, 0);
        
        return min == Integer.MAX_VALUE ? -1 : min;
    }

    static void dfs(int row, int count) {
        if (row == n) {
            int result = count;
            for (int col = 0; col < m; col++) {
                if (diffColCount(col) == n) result++;
                else if (diffColCount(col) != 0) return;
            }
            min = Math.min(min, result);
            return;
        }

        turnRow(row);
        dfs(row + 1, count + 1); //뒤집는 경우
        turnRow(row);
        dfs(row + 1, count); // 뒤집지 않는 경우
    }

    /* 행 뒤집기 */
    static void turnRow(int row) {
        for (int i = 0; i < m; i++) {
            map[row][i] = turn(map[row][i]);
        }
    }

    /* 뒤집기 */
    static int turn(int k) {
        if (k == 0) return 1;
        return 0;
    }

    /* 각 열마다 일치하는 개수 반환*/
    static int diffColCount(int col) {
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (map[i][col] != tar[i][col]) count++;
        }
        return count;
    }
}