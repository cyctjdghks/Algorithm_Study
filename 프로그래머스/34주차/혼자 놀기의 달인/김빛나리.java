class Solution {
    public int solution(int[] cards) {
        int answer = 0;
        
        for(int i=0;i<cards.length;i++) {
            boolean[] visited = new boolean[cards.length];
            int group1 = checkCards(cards, i, visited);
            
            for(int j=0;j<cards.length;j++) {
                // 이미 방문한 카드인 경우
                if(visited[j]) continue;
                
                int group2 = checkCards(cards, j, visited);
                answer = Math.max(answer, group1 * group2);
            }
        }
        
        return answer;
    }
    
    int checkCards(int[] cards, int start, boolean[] visited) {
        int cnt = 0;
        
        while(!visited[start]) {
            visited[start] = true;
            // 다음 카드 순서 -> index로 바꿔주어야하니까 -1
            start = cards[start] - 1;
            // 카드 갯수 +1
            cnt++;
        }
        
        return cnt;
    }
}