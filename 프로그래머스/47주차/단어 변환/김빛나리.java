class Solution {
    boolean[] visited;
    int answer = 51;
    
    public int solution(String begin, String target, String[] words) {
        visited = new boolean[words.length];
        
        dfs(begin, target, words, 0);
        
        // 반환할 수 없는 경우 판단
        return answer==51 ? 0 : answer;
    }
    
    void dfs(String begin, String target, String[] words, int cnt) {
        // target 단어와 같은 단어가 되었을 경우
        if(begin.equals(target)) {
            // 최소 단계의 과정 판단
            answer = Math.min(answer, cnt);
            return;
        }

        for(int i=0;i<words.length;i++) {
            // 이미 방문했던 경우
            if(visited[i]) continue;

            // 같은 알파벳 갯수
            int same = 0;
            
            // begin 단어 길이만큼 반복해서 자릿수마다 비교
            for(int j=0;j<begin.length();j++) {
                if(begin.charAt(j) == words[i].charAt(j)) same++;
            }

            // 한 개의 알파벳만 바뀐 경우
            if(same == begin.length()-1) {  
                visited[i] = true;
                dfs(words[i], target, words, cnt+1);
                visited[i] = false;
            }
        }
    }
}