import java.util.*;
class Solution {

    static int[][] pos = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}}; // 동서남북(y,x)
    static int n;
    static int m;
    static boolean[][] visited;
    static ArrayList<Integer> result;

    public ArrayList<Integer> solution(String[] maps) {
        result = new ArrayList<>();
        n = maps.length; //세로
        m = maps[0].length(); //가로
        visited = new boolean[n][m];

        for(int i=0; i<n; i++){
            for(int j=0; j<m; j++){
                if(!visited[i][j] && maps[i].charAt(j) != 'X'){
                    result.add(bfs(j,i,maps)); // i=y, j=x
                }
            }
        }

        if(result.size() == 0){
            result.add(-1);
        }else{
            Collections.sort(result);
        }
        return result;
   } 

    private int bfs(int x, int y, String[] graph) {
        // System.out.println(graph[x].charAt(y));
        Queue<int[]> queue = new LinkedList<>();
        queue.add(new int[]{y,x});
        visited[y][x] = true;

        int count = 0;

        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i=0; i<size; i++){
                int[] arr = queue.poll();
                int nowY = arr[0];
                int nowX = arr[1];
                count += Integer.parseInt(graph[nowY].substring(nowX,nowX+1));

                // System.out.println("nowY"+nowY);
                // System.out.println("nowX"+nowX);
                // System.out.println("target"+graph[nowY].charAt(nowX));

                for(int[] p : pos){
                    int newY = nowY + p[0];
                    int newX = nowX + p[1];
                    if(newX >= 0 && newX < m && newY >= 0 && newY < n
                       && !visited[newY][newX] 
                       && graph[newY].charAt(newX) != 'X'){
                        visited[newY][newX] = true;
                        queue.add(new int[]{newY, newX});
                        // System.out.println("newY"+newY);
                        // System.out.println("newX"+newX);
                    // System.out.println("target"+graph[newY].charAt(newX));
                    }
                }   
            }
        }

        return count;
    }
}
