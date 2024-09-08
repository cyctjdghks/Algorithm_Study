// 모든 항공권 사용해야함
// 주의) 항공권을 알파벳 순서로 정렬한 뒤에 우선순위를 두면 모든 도시를 방문할 수 없는 경우가 발생한다.

// 원래는 Map<String, String>으로 설정해서 bfs를 돌라고 했는데 예외사항을 고려하면 이렇게 푸는 건 아닌 것 같다.

// 백트렉킹 알고리즘 사용
import java.util.*;

class Solution {
    int[] visited; // 도착지가 아닌, 티켓의 사용유무를 체크
    ArrayList<String> arr;
    public String[] solution(String[][] tickets) {
        visited = new int[tickets.length];
        arr = new ArrayList<>();

        // dfs
        dfs(tickets.length, "ICN", "ICN", tickets);

        // 가능한 경로 중 알파벳 순서가 앞서는 경로를 선택
        Collections.sort(arr);

        String[] answer = arr.get(0).split(" ");

        return answer;
    }
    public void dfs(int cnt, String start, String result, String[][] tickets){
        // 티켓을 모두 사용하면 재귀 탈출 (cnt == dept)
        if(cnt == 0){
            arr.add(result);
            return;
        }

        for(int i=0;i<tickets.length;i++){
            // 티켓의 시작이 일치하는지 && 사용한 티켓인지 확인
            if(visited[i] == 0 && start.equals(tickets[i][0])){
                visited[i] = 1;
                dfs(cnt-1, tickets[i][1], result + " " + tickets[i][1], tickets);
                visited[i] = 0;
            }
        }
    }
}