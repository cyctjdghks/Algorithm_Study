class Solution {
    int n, m;
    int[][] board;
    int[][] t;
    int ans = Integer.MAX_VALUE;
    public int solution(int[][] beginning, int[][] target) {
        int answer = 0;
        
        n = beginning.length;
        m = beginning[0].length;
        
        board = new int[n][m];
        for(int i=0; i<n; i++){
            board[i] = beginning[i].clone();
        }
        t = target;
        
        dfs(0, 0);
        
        if(ans==Integer.MAX_VALUE){
            answer = -1;
        } else answer = ans;
        
        return answer;
    }
    
    public void flip_row(int r){
        for(int i=0; i<m; i++){
            board[r][i] = (board[r][i]+1)%2;
        }
    }
    
    public int compare_col(int c){
        int check = 0;
        for(int i=0; i<n; i++){
            if(t[i][c]==board[i][c]){
                check++; 
            } 
        }
        
        if(check==n) return 0; 
        else if(check==0) return 1; 
        else return -1;
    }
    
    public void dfs(int r, int cnt){
        
        if(r==n){
            boolean flag = true;
            for(int i=0; i<m; i++){
                int result = compare_col(i);
                if(result==-1){
                    flag = false;
                    break;
                }
                cnt += result;
            }
            
            if(flag){
                ans = Math.min(ans, cnt);   
            }
            return;
        }
        
        flip_row(r);
        dfs(r+1, cnt+1);
        flip_row(r);
        
        dfs(r+1, cnt);
    }
}