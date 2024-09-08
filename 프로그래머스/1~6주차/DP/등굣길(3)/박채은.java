// 오른쪽, 아래쪽으로만 이동

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        // 시작점을 (1,1)로 설정
        int[][] map = new int[m+1][n+1];

        // puddles를 -1로 설정
        for(int[] puddle: puddles){
            map[puddle[0]][puddle[1]] = -1;
        }

        map[1][1] = 1;

        for(int i= 1;i<m+1;i++){
            for(int j=1;j<n+1;j++){
                if(i==1 && j==1) continue;
                if(map[i][j] == -1){ // puddle인 경우
                    continue;
                }else if(map[i-1][j] == -1){
                    map[i][j] = map[i][j-1] % 1000000007;
                }else if(map[i][j-1] == -1){
                    map[i][j] = map[i-1][j] % 1000000007;
                }
                else{
                    map[i][j] = (map[i-1][j] + map[i][j-1]) % 1000000007;
                }
            }
        }

        return map[m][n] % 1000000007;
    }
}
