import java.util.*;

class Solution {
    static boolean[] visit; // 방문 체크
    static int answer;
    
    public int solution(int n, int[][] wires) {
        // 2 <= n <= 100
        // wires.length = n-1
        // wires 원소는 [v1, v2]
        // 1 <= v1 <= v2 <= n
        // 두 송전탑 개수의 차이가 최소가 되도록
        
        answer = n; // 최대 차이값으로 초기화
        
        for(int i = 0; i < wires.length; i++){
            visit = new boolean[n+1];
            bfs(wires, i, n); // i번째 전선을 끊고 BFS 실행
        }
        
        return answer;
    }
    
    public void bfs(int[][] wires, int skip, int n){
        
        Queue<Integer> q = new LinkedList<>();
        
        q.add(1); // 임의의 시작점
        visit[1] = true;
        int countA = 1; // 방문한 노드 수
        
        while(!q.isEmpty()) {
            int current = q.poll();
            
            for(int i = 0; i < wires.length; i++) {
                if(i == skip) continue; // skip하는 전선 건너뛰기
                
                int next = 0;
                if(wires[i][0] == current && !visit[wires[i][1]]) {
                    next = wires[i][1];
                } else if(wires[i][1] == current && !visit[wires[i][0]]) {
                    next = wires[i][0];
                }
                
                if(next != 0) {
                    q.add(next);
                    visit[next] = true;
                    countA++;
                }
            }
        }
        
        int countB = n - countA; // 다른 네트워크의 크기
        answer = Math.min(answer, Math.abs(countA - countB)); // 최소 차이 갱신
    }
}
