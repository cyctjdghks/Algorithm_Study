
class Solution {
    int[][] sum;
    long[][] skillSum;
	public int solution(int[][] board, int[][] skill) {
        int n = board.length, m = board[0].length;
        sum = new int[n][m];
        skillSum = new int[n][m];
    
        //init
        Arrays.fill(sum[0], board[0]);
        for(int i=1; i<n; i++){
            sum[i][0] = board[i][0];
            for(int j=1; j<m; j++){
                sum[i][j] = sum[i-1][j] + sum[i][j-1] - sum[i-1][j-1];
            }
        }
	}
    
    //구현
    private int[][] calSkill(int n, int m, int[][] skill){
        for(int cnt=0; cnt<skill.length; cnt++){
            int r1=skill[cnt][1], c1=skill[cnt][2], r2=skill[cnt][3], r3=skill[cnt][4];
            int degree = skill[cnt][5];
            for(int i=r1; i<r2; i++){
                for(int j=c1; j<c2; j++){
                    skillSum[i][j] += degree;
                }
            }
        }
        makeAccumSum(n, m);
    }
    
    private void makeAccumSum(int n, int m){
        
    }
}
