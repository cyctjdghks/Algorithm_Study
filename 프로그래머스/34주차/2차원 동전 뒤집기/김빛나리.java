class Solution {
    int n, m;
    int[][] Beginning, Target;
    int answer = Integer.MAX_VALUE;
    
    public int solution(int[][] beginning, int[][] target) {
        n = beginning.length; m = beginning[0].length;
        Beginning = beginning; Target = target;
        
        dfs(0, 0, 0);
        
        if(answer == Integer.MAX_VALUE) return -1;
        
        return answer;
    }
    
    public void dfs(int r, int c, int cnt) {
        // 모든 행에서 뒤집거나 뒤집지 않은 경우
        if(r == n) {
            boolean flag = true;
            
            // 열의 상태를 비교하여 TARGET과 동일하게 만들 수 있는지 판별
            for (int i=0;i<m;i++) {
                int check = compareColunm(i);
                
                // 현재 상태에서 TARGET과 동일하게 만들 수 없는 경우
                if(check == -1) { 
                    flag = false; 
                    continue; 
                }
                
                // 해당 열이 전부 반대인 경우, 1이 반환 -> 뒤집기 횟수 + 1
                cnt += check; 
            }
            
            // TARGET과 동일하게 만들 수 있는 경우, 최소 뒤집기 횟수 갱신
            if(flag) answer = Math.min(answer, cnt); 
            
            return;
        }
        
        // 해당 행 뒤집기 X
        dfs(r + 1, c, cnt);
        
        // 해당 행 뒤집어서 dfs
        reverseRow(r);
        dfs(r + 1, c, cnt + 1);
        
        // 되돌리기
        reverseRow(r);
    }
    
    public int compareColunm(int c) {
        int cnt = 0;
        
        for(int i=0;i<n;i++) {
            if(Beginning[i][c] == Target[i][c]) cnt++;
        }
        
        // beginning과 target애서 해당 열이 전부 반대인 경우
        if(cnt == 0) return 1; 
        // beginning과 target애서 해당 열이 전부 동일한 경우
        else if(cnt == n) return 0; 
        // 둘다 아닌 경우
        else return -1; 
    }
    
    public void reverseRow(int r) {
        // 해당 행 뒤집어주기
        for(int i=0;i<m;i++) {
            Beginning[r][i] = (Beginning[r][i] + 1) % 2;
        }
    }
}