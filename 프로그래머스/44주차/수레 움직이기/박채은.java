// 테스트 몇 개 시간 초과 발생 (수정 필요)

import java.util.*;

class Solution {
    static int answer = Integer.MAX_VALUE;
    static int[][] map;
    static int [] dx = {-1,1,0,0};
    static int [] dy = {0,0,-1,1};
    static boolean [][] red;
    static boolean [][] blue;
    static int a, b;
    static int stRedX, stRedY, stBlueX, stBlueY; // start 지점
    static int endRedX, endRedY, endBlueX, endBlueY; // end 지점

    public int solution(int[][] maze) {
        map = maze;
        a = maze.length;
        b = maze[0].length;
        red = new boolean[a][b];
        blue = new boolean[a][b];

        for(int i=0;i< a;i++){
            for(int j=0;j<b;j++){
                if(maze[i][j] == 1){
                    red[i][j] = true;
                    stRedX =i; stRedY=j;
                } else if (maze[i][j] == 2) {
                    blue[i][j] = true;
                    stBlueX =i; stBlueY=j;
                } else if (maze[i][j] == 3) {
                    endRedX =i; endRedY=j;
                } else if (maze[i][j] == 4) {
                    endBlueX =i; endBlueY=j;
                }
            }
        }

        backtracking(stRedX, stRedY, stBlueX, stBlueY, 0, false, false);
        return answer == Integer.MAX_VALUE ? 0 : answer;
    }

    public void backtracking(int srx, int sry, int sbx, int sby, int cnt, boolean redEnd, boolean blueEnd){
        if (cnt > a * b) return;
        if(!redEnd && srx == endRedX && sry == endRedY) redEnd = true;
        if(!blueEnd && sbx == endBlueX && sby == endBlueY) blueEnd = true;

        if(redEnd && blueEnd){
            answer = Math.min(answer, cnt);
            return;
        }

        ArrayList<int[]> r_list = new ArrayList<>();
        ArrayList<int[]> b_list = new ArrayList<>();

        if(!redEnd){
            for(int i=0;i<4;i++){
                int curX = srx + dx[i];
                int curY = sry + dy[i];
                if(checkValid(curX, curY) && map[curX][curY] != 5 && !blue[curX][curY]){
                    r_list.add(new int[]{curX, curY});
                }
            }
        }else{
            r_list.add(new int[]{srx, sry});
        }
        if(!blueEnd){
            for(int i=0;i<4;i++){
                int curX = sbx + dx[i];
                int curY = sby + dy[i];
                if(checkValid(curX, curY) && map[curX][curY] != 5 && !red[curX][curY]){
                    b_list.add(new int[]{curX, curY});
                }
            }
        }else{
            b_list.add(new int[]{sbx, sby});
        }

        for(int i=0;i<r_list.size();i++){
            int[] rp = r_list.get(i);
            for(int j=0;j<b_list.size();j++){
                int[] bp = b_list.get(j);

                // 1. 동시에 두 수레를 같은 칸으로 이동할 때
                if(rp[0] == bp[0] && rp[1]== bp[1]) continue;
                // 2. 수레끼리 자리를 바꾸며 움직일 때
                if(rp[0] == sbx && rp[1] == sby && bp[0] == srx && bp[1] == sry) continue;

                red[rp[0]][rp[1]] = true;
                blue[bp[0]][bp[1]] = true;
                backtracking(rp[0], rp[1], bp[0], bp[1], cnt+1, redEnd, blueEnd);
                red[rp[0]][rp[1]] = false;
                blue[bp[0]][bp[1]] = false;
            }
        }
    }

    public boolean checkValid(int x, int y){
        if(x< 0 || y < 0 || x >= a || y >= b){
            return false;
        }
        return true;
    }
}