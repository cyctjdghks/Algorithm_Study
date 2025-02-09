import java.util.*;

class Solution {
    int dx[] = {1,-1,0,0};
    int dy[] = {0,0,1,-1};
    int answer;
    boolean red[][];
    boolean blue[][];
    int x,y;

    public int solution(int[][] maze) {
        answer = Integer.MAX_VALUE;

        x = maze.length;
        y = maze[0].length;

        red = new boolean[x][y];
        blue = new boolean[x][y];
        int r[] = new int[2];
        int b[] = new int[2];

        //빨간, 파란 수레의 시작위치 설정
        for(int i=0;i<x;i++)
        {
            for(int j=0;j<y;j++)
            {
                if(maze[i][j] == 1)
                {
                    r = new int[]{i,j};
                    red[i][j] = true;
                }
                else if(maze[i][j] == 2)
                {
                    b = new int[]{i,j};
                    blue[i][j] = true;
                }
            }
        }

        dfs(0,r,b,maze);

        return answer == Integer.MAX_VALUE ? 0 : answer;
    }

    private void dfs(int count, int r[],int b[],int[][] maze)
    {
        if(maze[r[0]][r[1]] == 3 && maze[b[0]][b[1]] == 4)
        {
            answer = Math.min(answer,count);
            return ;
        }

        int rx,ry,bx,by;
        //빨간 수레이동완료
        if(maze[r[0]][r[1]] == 3)
        {
            //파란 수레만 이동
            for(int d=0;d<4;d++)
            {
                bx = b[0] + dx[d];
                by = b[1] + dy[d];

                if( 0<= bx && bx < x && 0<= by && by < y)
                {
                    if(!blue[bx][by] && maze[bx][by] != 5 && maze[bx][by] != 3)
                    {
                        //빨간 수레와 같은 위치인지 확인
                        if(!(bx == r[0] && by == r[1]))
                        {
                            blue[bx][by] = true;
                            dfs(count+1,r,new int[]{bx,by},maze);
                            blue[bx][by] = false;
                        }
                    }
                }

            }
        }
        //파란 수레이동완료
        else if(maze[b[0]][b[1]] == 4)
        {
            //빨간 수레만 이동
            for(int d=0;d<4;d++)
            {
                rx = r[0] + dx[d];
                ry = r[1] + dy[d];

                if( 0<= rx && rx < x && 0<= ry && ry < y)
                {
                    if(!red[rx][ry] && maze[rx][ry] != 5 && maze[rx][ry] != 4)
                    {
                        //빨간 수레와 같은 위치인지 확인
                        if(!(rx == b[0] && ry == b[1]))
                        {
                            red[rx][ry] = true;
                            dfs(count+1,new int[]{rx,ry},b,maze);
                            red[rx][ry] = false;
                        }
                    }
                }
            }

        }else
        {
            //빨간 수레 이동
            for(int i=0;i<4;i++)
            {
                rx = r[0] + dx[i];
                ry = r[1] + dy[i];

                if( 0<= rx && rx < x && 0<= ry && ry < y)
                {
                    if(!red[rx][ry] && maze[rx][ry] != 5)
                    {

                        //파란색 수레 이동
                        for(int j=0;j<4;j++)
                        {
                            bx = b[0] + dx[j];
                            by = b[1] + dy[j];

                            if( 0<= bx && bx < x && 0<= by && by < y)
                            {
                                if(!blue[bx][by] && maze[bx][by] != 5)
                                {
                                    //같은 위치로 이동했을 경우
                                    if(rx == bx && ry == by) continue;
                                    //빨간 파란 수레의 자리를 바꾼 경우
                                    if((rx == b[0] && ry == b[1]) && (bx == r[0] && by == r[1])) continue;

                                    red[rx][ry] = true;
                                    blue[bx][by] = true;
                                    dfs(count+1,new int[]{rx,ry}, new int[]{bx,by},maze);
                                    red[rx][ry] = false;
                                    blue[bx][by] = false;

                                }
                            }
                        }
                    }
                }
            }
        }
    }

}