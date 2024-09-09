import java.util.*;

class Solution {

    static boolean[][] visited;    
    static Set<Integer> set;
    static int[][] land, map;
    static List<Integer> oilCount;
    static int rows, cols;

    public int solution(int[][] landInput) {
        land = landInput;
        rows = land.length;
        cols = land[0].length;
        
        visited = new boolean[rows][cols];
        map = new int[rows][cols];  
        oilCount = new ArrayList<>();
        set = new HashSet<>();
        
        for (int w = 0; w < cols; w++) {
            for (int d = 0; d < rows; d++) {
                if (!visited[d][w] && land[d][w] == 1) {
                    int count = bfs(d, w, oilCount.size() + 1);
                    oilCount.add(count);
                }                
            }
        }
        
        return getMaxCount();
    }
    
    private static int getMaxCount() {
        int max = 0;
        
        for (int col = 0; col < cols; col++) {
            int count = 0;
            for (int row = 0; row < rows; row++) {
                if (map[row][col] > 0) {
                    set.add(map[row][col]);
                }
            }
            
            for (Integer id : set) {
                count += oilCount.get(id - 1);
            }
            
            max = Math.max(max, count);
            set.clear();
        }
        return max;
    }
    
    private static int bfs(int d, int w, int oilId) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{d, w});
        visited[d][w] = true;
        map[d][w] = oilId;
        
        int[] dd = {0, 0, 1, -1};
        int[] dw = {1, -1, 0, 0};
        
        int count = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            count += size;
            
            for (int i = 0; i < size; i++) {
                int[] poll = queue.poll();
                int curr_d = poll[0];
                int curr_w = poll[1];
                
                for (int j = 0; j < 4; j++) {
                    int new_d = curr_d + dd[j];
                    int new_w = curr_w + dw[j];
                    
                    if (isValid(new_d, new_w) && !visited[new_d][new_w] && land[new_d][new_w] == 1) {
                        visited[new_d][new_w] = true;
                        map[new_d][new_w] = oilId;
                        queue.offer(new int[]{new_d, new_w});
                    }
                }
            }
        }
        return count;
    }
    
    private static boolean isValid(int d, int w) {
        return d >= 0 && d < rows && w >= 0 && w < cols;
    }
}
