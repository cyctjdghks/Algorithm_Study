import java.util.*;

class Solution {
    static String[] answer;
    static boolean[] visit;
    static int len;
    static boolean isFound = false; // 정답을 찾았는지 확인하는 플래그

    public String[] solution(String[][] tickets) {
        // ICN 에서 출발
        // 모두 대문자 3글자
        // 공항 수 3 ~ 10,000
        // tickets [a,b] => a ~ b
        // 모두 사용
        // 경로 2개 이상인 경우 알파벳순
        
        len = tickets.length;
        answer = new String[len + 1];
        visit = new boolean[len];
        
        // 티켓을 출발지 기준으로 정렬
        Arrays.sort(tickets, new Comparator<String[]>() {
            @Override
            public int compare(String[] o1, String[] o2) {
                if (o1[0].equals(o2[0])) {
                    return o1[1].compareTo(o2[1]); // 도착지가 같으면 도착지 기준으로 정렬
                }
                return o1[0].compareTo(o2[0]); // 출발지 기준으로 정렬
            }
        });

        ArrayList<String> list = new ArrayList<>();
        list.add("ICN");
        dfs(0, tickets, list);
        
        return answer;
    }
    
    public void dfs(int count, String[][] tickets, ArrayList<String> list) {
        if(isFound) return; // 이미 경로를 찾은 경우 탐색 중지
        
        if(count == len) {
            for(int i = 0; i < list.size(); i++) {
                answer[i] = list.get(i);
            }
            isFound = true; // 정답을 찾음
            return;
        }
        
        String last = list.get(list.size() - 1);
        for(int i = 0; i < len; i++) {
            if(!visit[i] && tickets[i][0].equals(last)) {
                visit[i] = true;
                list.add(tickets[i][1]);
                
                dfs(count + 1, tickets, list);
                
                if(isFound) return; // 정답을 찾았으면 추가 탐색 중지
                visit[i] = false; // 방문 취소 (백트래킹)
                list.remove(list.size() - 1);
            }
        }
    }
}
