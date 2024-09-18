class Solution {
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        // x = 로봇의 수, m = 운송 경로
        int xLen = routes.length, mLen = routes[0].length;
        int[][] p = new int[xLen][3];
        int finish = 0;
        
        for(int i=0;i<xLen;i++) {
            p[i][0] = points[routes[i][0]-1][0] - 1;
            p[i][1] = points[routes[i][0]-1][1] - 1;
            p[i][2] = 1;
        }
        
        while(finish < xLen) {
            int[][] visited = new int[100][100];
            
            for(int i=0;i<xLen;i++) {
                if (p[i][2] < mLen) {
                    visited[p[i][0]][p[i][1]]++;
                    
                    if (visited[p[i][0]][p[i][1]]==2) answer++;
                }
            }

            for(int i=0;i<xLen;i++) {
                if(p[i][2] < mLen) {
                    int nr = points[routes[i][p[i][2]] - 1][0]-1;
                    int nc = points[routes[i][p[i][2]] - 1][1]-1;
                    
                    if(p[i][0] == nr && p[i][1] == nc) {
                        p[i][2]++;
                        
                        if(p[i][2] == mLen) {
                            finish++;
                            continue;
                        }
                        nr = points[routes[i][p[i][2]] - 1][0]-1;
                        nc = points[routes[i][p[i][2]] - 1][1]-1;
                    }

                    if(nr != p[i][0]) p[i][0] += nr > p[i][0] ? 1 : -1;
                    else p[i][1] += nc > p[i][1] ? 1 : -1;
                }
            }
        }
        
        return answer;
    }
}