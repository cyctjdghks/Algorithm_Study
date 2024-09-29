class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];

        for(int i=0;i<balls.length;i++){
            int targetX = balls[i][0];
            int targetY = balls[i][1];
            int min = 0;

            if(startX == targetX){
                // 1. x=0 대칭
                int d1 = (int) (Math.pow(2*startX, 2) + Math.pow(targetY - startY, 2));
                // 2. x=m 대칭
                int d2 = (int) (Math.pow(2*(m-startX), 2) + Math.pow(targetY - startY, 2));
                // 3. y=0 대칭 / y=n 대칭
                int d3 = 0;
                if(startY > targetY){ // y=n 대칭
                    d3 = (int) (Math.pow(2*n-startY-targetY, 2));
                }
                else{
                    d3 = (int) Math.pow(startY + targetY, 2);
                }
                min = Math.min(Math.min(d1, d2), d3);
            } else if (startY == targetY) {
                // 1. x=0 대칭
                int d1 = (int) (Math.pow(2*startY, 2) + Math.pow(targetX - startX, 2));
                // 2. x=m 대칭
                int d2 = (int) (Math.pow(2*(n-startY), 2) + Math.pow(targetX - startX, 2));
                // 3. y=0 대칭 / y=n 대칭
                int d3 = 0;
                if(startX > targetX){ // y=n 대칭
                    d3 = (int) (Math.pow(2*m-startX-targetX, 2));
                }
                else{
                    d3 = (int) Math.pow(targetX + startX, 2);
                }
                min = Math.min(Math.min(d1, d2), d3);
            }
            else{
                // 1. x=0 대칭
                int d1 = (int) (Math.pow(startX+targetX, 2) + Math.pow(targetY - startY, 2));
                // 2. x=m 대칭
                int d2 = (int) (Math.pow(2*m - startX - targetX, 2) + Math.pow(targetY - startY, 2));
                // 3. y=0 대칭
                int d3 = (int) (Math.pow(startX-targetX, 2) + Math.pow(targetY + startY, 2));
                // 4. y=n 대칭
                int d4 = (int) (Math.pow(startX - targetX, 2) + Math.pow(2*n - targetY - startY, 2));
                min = Math.min(Math.min(Math.min(d1, d2), d3), d4);
            }

            answer[i] = min;
        }
        return answer;
    }
}