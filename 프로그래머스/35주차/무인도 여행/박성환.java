import java.util.*;

class Solution {
    
    static int N, M;
    static int[] dx = {-1, 0, 1, 0};
    static int[] dy = {0, -1, 0, 1};
    static String[][] map;
    static boolean[][] v;
    static PriorityQueue<Integer> ans;
    
    public int[] solution(String[] maps) {
        // maps 에는 X 혹은 1 ~ 9
        
        // 초기화
        int[] answer = {-1};
        ans = new PriorityQueue<>();
        
        N = maps.length;
        M = maps[0].length();
        
        map = new String[N][M];
        v = new boolean[N][M];
        
        for(int i = 0; i < N; i++) {
            String s = maps[i];
            
            for(int j = 0; j < M; j++) {
                map[i][j] = Character.toString(s.charAt(j));
            }
        }
        
        
        // bfs 로 탐색
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < M; j++) {
                if(!map[i][j].equals("X") && !v[i][j]) {
                    bfs(i, j);
                }
            }
        }
        
        
        // 결과 출력
        if(!ans.isEmpty()) {
            int size = ans.size();
            answer = new int[size];
            
            for(int i = 0; i < size; i++) {
                answer[i] = ans.poll();
            }
        }
        
        return answer;
    }
    
    public void bfs(int x, int y) {
        int idx = 0;
        
        Queue<Node> q = new LinkedList<>();
        q.offer(new Node(x, y));
        v[x][y] = true;
        
        while(!q.isEmpty()) {
            Node node = q.poll();
            idx += Integer.parseInt(map[node.x][node.y]);
            
            for(int d = 0; d < 4; d++) {
                int nx = dx[d] + node.x;
                int ny = dy[d] + node.y;
                
                if(isInBound(nx, ny)) {
                    if(!map[nx][ny].equals("X") && !v[nx][ny]) {
                        q.offer(new Node(nx, ny));
                        v[nx][ny] = true;
                    }
                }
            }
        }
        
        ans.offer(idx);
        
    }
    
    public boolean isInBound(int x, int y) {
        return x >= 0 && x < N && y >= 0 && y < M;
    }
    
    public class Node {
        int x;
        int y;
        
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}