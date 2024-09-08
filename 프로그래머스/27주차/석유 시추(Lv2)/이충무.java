import java.util.*;

class Solution {
    class Point{
        int x,y;
        public Point(int x,int y){
            this.x=x;
            this.y=y;
        }
    }
    int n,m;
    int[] sum;
    boolean[][] visited;
    public int solution(int[][] land) {
        int answer = 0;
        
        n = land.length;
        m = land[0].length;
        sum = new int[m];
        visited = new boolean[n][m];
        
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(!visited[j][i] && land[j][i] == 1){
                    bfs(j,i,land);    
                }
                
            }
            
        }
        for(int i=0;i<m;i++){
            answer = Math.max(answer, sum[i]);
            //System.out.println(sum[i]);
        }
        return answer;
    }
    public void bfs(int a,int b,int[][] land){
        int[] dx = {1,0,-1,0};
        int[] dy = {0,-1,0,1};
        Queue<Point> q = new LinkedList<>();
        q.add(new Point(a,b));
        visited[a][b] = true;
        boolean[] check = new boolean[m+1];
        //check[a] = true;
        int cnt = 1;
        while(!q.isEmpty()){
            Point p = q.poll();
            int x = p.x;
            int y = p.y;
            if(!check[y]){
                check[y] =true;
            }
            for(int i=0;i<4;i++){
                int nx = x+dx[i];
                int ny = y+dy[i];
                
                if(nx>=0&&ny>=0&&nx<n&&ny<m){
                    if(!visited[nx][ny] && land[nx][ny] == 1){
                        cnt++;
                        visited[nx][ny]=true;
                        q.add(new Point(nx,ny));
                    }
                }
            }
        }
        // System.out.println(cnt);
        // for(int i=0;i<m;i++){
        //     System.out.print(check[i] + " ");
        // }
        // System.out.println();
        for(int i=0;i<m;i++){
            if(check[i]) sum[i]+=cnt;
        }

        
    }
}
