import java.util.*;

class Solution {
    // 0~6까지만 들어있으니까
    boolean[] isNum = new boolean[7];
    boolean[] visitedPer = new boolean[7];
    // board에 존재하는 카드 갯수
    int size = 0;
    int answer = Integer.MAX_VALUE;
    int[][] dist = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int[][] Board;
    int R, C;
    
    class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    public int solution(int[][] board, int r, int c) {
        Board = board;
        R = r;
        C = c;
        
        // 현재 카드 종류를 isNum에 체크 -> 체크해 둔 숫자 중에 순열로 뽑을 예정
        for(int i=0;i<4;i++) {
            for(int j=0;j<4;j++) {
                // 카드가 제거된 빈 칸이거나 이미 isNum에 체크가 되어있는 경우
                if(board[i][j] == 0 || isNum[board[i][j]]) continue;
                
                isNum[board[i][j]] = true;
                size++;
            }
        }
        
        // 순열로 현재 카드의 방문 순서를 정하기
        Permutation(0, new int[size]);
        
        return answer;
    }
    
    public void Permutation(int cnt, int[] arr) {
        // 카드를 모두 방문한 경우
        if(size == cnt) {
            // 정해진 방문 순서대로 bfs 실행
            bfs(arr);
            return;
        }

        // board에 1~6까지 자연수 존재
        for(int i=1;i<=6;i++) {
            // board에 존재하지 않는 카드이거나 이미 방문한 경우
            if(!isNum[i] || visitedPer[i]) continue;
            
            visitedPer[i] = true;
            arr[cnt] = i;
            Permutation(cnt+1, arr);
            visitedPer[i] = false;
        }
    }
    
    public void bfs(int[] arr) {
        Queue<Point> queue = new LinkedList<>();
        // board에 현재 위치 방문 체크하는 배열
        boolean[][] visited = new boolean[4][4];
        // board의 값을 변형시키지 않고, 따로 배열 생성
        // 현재 위치에 Enter 입력 체크하는 배열
        boolean[][] checkEnter = new boolean[4][4];
        // 최단 거리
        int distance = 0;
        // 키 조작 횟수 count
        int cnt = 0;
        // 파라미터 arr의 index
        int idx = 0;
        // 카드 두장 뒤집어야 하니까 -> 두번째 카드 뒤집기인지 체크
        boolean isSecond = false;

        // 최초 위치
        queue.add(new Point(R, C));
        visited[R][C] = true;

        while(!queue.isEmpty()) {
            int len = queue.size();

            for(int l=0;l<len;l++) {
                Point cur = queue.poll();

                // 현재 위치에 있는 자연수가 순열로 만든 방문 순서 arr의 index에 위치하고
                // 현재 위치에 Enter가 입력되지 않은 경우
                if(Board[cur.x][cur.y] == arr[idx] && !checkEnter[cur.x][cur.y]) {
                    // Enter 누적
                    cnt++;
                    // 지금까지 최소 이동횟수 더하기
                    cnt += distance;
                    // 이동횟수 초기화 -> 마지막에 이동횟수 +1을 하기 때문에 -> 0이 됨
                    distance = -1;
                    // Enter 체크
                    checkEnter[cur.x][cur.y] = true;
                    
                    // queue, visited 초기화 -> 다시 최단거리로 이동하기 위해
                    queue.clear();
                    visited = new boolean[4][4];
                    
                    // 현재 좌표에서 다시 시작
                    queue.add(new Point(cur.x, cur.y));
                    visited[cur.x][cur.y] = true;

                    // 처음 도착한 경우
                    if(!isSecond) isSecond = true;
                    else {
                        // 새로운 첫번째 카드 찾기 위해 초기화
                        isSecond = false;
                        // 다음 카드 종류를 찾기위해 순열 배열의 index 증가
                        idx++;
                        
                        // index가 카드 갯수보다 크거나 같은 경우, 모두 찾기 완료
                        if(idx >= size) {
                            // 현재까지 누적한 값과 비교해서 최소값 구하기
                            answer = Math.min(answer, cnt);
                            
                            return;
                        }
                    }
                    
                    break;
                }
                
                // 한칸 움직이기
                for(int i=0;i<4;i++) {
                    int nx = cur.x + dist[i][0];
                    int ny = cur.y + dist[i][1];

                    // 다음 좌표가 board를 넘어가거나 이미 방문한 좌표인 경우
                    if(!isBoard(nx, ny) || visited[nx][ny]) continue;
                    
                    visited[nx][ny] = true;
                    queue.add(new Point(nx, ny));
                }

                // Ctrl + 움직임
                for(int i=0;i<4;i++) {
                    int nx = cur.x;
                    int ny = cur.y;
                    
                    // 다음 좌표가 board의 마지막 칸인 경우, 종료
                    while(isBoard(nx + dist[i][0], ny + dist[i][1])) {
                        nx += dist[i][0];
                        ny += dist[i][1];
                        
                        // Enter를 입력한 적 없고, 카드를 만난 경우
                        if(!checkEnter[nx][ny] && Board[nx][ny] != 0) break;
                    }

                    // 다음 좌표가 board를 넘어가거나 이미 방문한 좌표인 경우
                    if(!isBoard(nx, ny) || visited[nx][ny]) continue;
                    
                    visited[nx][ny] = true;
                    queue.add(new Point(nx, ny));
                }
            }
            
            distance++;
        }
    }
    
    public boolean isBoard(int x, int y) {
        return 0 <= x && x < 4 && 0 <= y && y < 4;
    }
}