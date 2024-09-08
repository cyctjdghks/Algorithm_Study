// 90점 코드
// 왜 90점인지 못 찾아서 다음주에 고쳐놓겠습니다!

import java.util.*;

class Solution {
    int len;
    // 빈 곳
    List<List<Point>> empty = new ArrayList<>();
    boolean[][] visited_e;
    // 모형
    List<List<Point>> puzzle = new ArrayList<>();
    boolean[][] visited_p;

    int[] dx = {0, 0, -1, 1};
    int[] dy = {-1, 1, 0, 0};

    int cnt = 0;

    public int solution(int[][] game_board, int[][] table) {
        len = game_board.length;

        visited_e = new boolean[len][len];
        visited_p = new boolean[len][len];

        for(int i=0;i<len;i++){
            for(int j=0;j<len;j++){
                // 모형 탐색
                bfs(table,visited_p, i,j, 1, puzzle);
                // 빈 곳 탐색
                bfs(game_board,visited_e, i,j, 0, empty);
            }
        }
        matchBlock();

        return cnt;

    }

    public void bfs(int[][] map, boolean[][] visited, int i, int j, int match, List<List<Point>> list){
        Queue<Point> q = new LinkedList<>();
        // (0,0) 기준으로 변환한 List
        List<Point> result = new ArrayList<>();
        if(map[i][j] != match || visited[i][j]){
            return;
        }
        visited[i][j] = true;
        q.add(new Point(i, j));
        result.add(new Point(0,0));

        while(!q.isEmpty()){
            Point p = q.poll();
            for(int k=0;k<4;k++){
                int curX = p.x + dx[k];
                int curY = p.y + dy[k];

                if(curX < 0 || curX > len-1 || curY < 0 || curY > len-1){
                    continue;
                }
                // 방문한 적 없고, 모형의 일부라면
                if(visited[curX][curY] == false && map[curX][curY] == match){
                    visited[curX][curY] = true;
                    q.add(new Point(curX, curY));
                    result.add(new Point(curX - i, curY - j));
                }
            }
        }
        list.add(result);
    }

    public int matchBlock(){
        boolean[] visited = new boolean[empty.size()];

        for(int i=0;i<puzzle.size();i++){
            for(int j=0;j<empty.size();j++){
                if(visited[j] || puzzle.get(i).size() != empty.get(j).size()) continue;
                if(rotate(puzzle.get(i), empty.get(j))){
                    // 같다면
                    // 모양이 동일한 퍼즐이 같은 empty에 들어갈 수 있기 때문에
                    visited[j] = true;
                    cnt += puzzle.get(i).size();
                    break; // 결정됐기 때문에 다음 puzzle로 넘어감
                }
            }
        }
        return cnt;
    }

    // Rotate 함수
    // 0,90,180,360 회전 중에 일치하는 게 있다면 true return
    public boolean rotate(List<Point> puz, List<Point> empty){
        // empty를 x,y 순으로 정렬
        Collections.sort(empty);

        for(int i=0;i<4;i++){
            Collections.sort(puz);

            if(i!=0){
                // x가 가장 작고, y가 작은 값을 (0,0)으로 세팅
                // sort를 통해서 index=0이 원점으로 설정됨
                int baseX = puz.get(0).x;
                int baseY = puz.get(0).y;

                for(int k=0;k<puz.size();k++){
                    puz.get(k).x -= baseX;
                    puz.get(k).y -= baseY;
                }
            }

            // 좌표 비교
            boolean same = true;
            for(int k=0;k<puz.size();k++){
                if(puz.get(k).x != empty.get(k).x || puz.get(k).y != empty.get(k).y){
                    same = false;
                    break;
                }
            }

            if(same == true){
                return true;
            }else{
                // 90도 회전 - (x,y) -> (y,-x)
                for(int k=0;k<puz.size();k++){
                    int x = puz.get(k).x;
                    int y = puz.get(k).y;
                    puz.get(k).x = y;
                    puz.get(k).y = x * (-1);
                }
            }
        }
        return false;
    }

    public class Point implements Comparable<Point>{
        int x, y;
        Point(int x, int y){
            this.x = x;
            this.y = y;
        }

        public int compareTo(Point o){
            int res = Integer.compare(this.x, o.x);
            if(res==0){
                res = Integer.compare(this.y, o.y);
            }
            return res;
        }
    }
}