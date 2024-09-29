import java.util.*;

class Solution {
    
    int M, N;
    int[] start;
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int cnt = balls.length;
        int[] answer = new int[cnt];
        M = m;
        N = n;
        start = new int[]{startX, startY};
        for(int i=0; i<balls.length; i++){
            answer[i] = calMinValue(balls[i]);     
        }
        
        return answer;
    }
    
    private int calMinValue(int[] ball){
        
        int minValue = Integer.MAX_VALUE;
        //direction 1
        int currValue=0;
        if(ball[1] != start[1] || ball[0] <= start[0]){
            currValue += (pow(ball[1]-start[1]));
            currValue += (pow(M-start[0] + M-ball[0]));
            minValue = Math.min(minValue, currValue);
        }
        
        //direction 2
        currValue = 0;
        if(ball[0] != start[0] || start[1] >=s ball[1]){
            currValue += (pow(ball[0] - start[0]));
            currValue += (pow(N-start[1] + N-ball[1]));
            minValue = Math.min(minValue, currValue);            
        }

        
        //direction 3
        currValue = 0;
        if(ball[1] != start[1] || start[0] <= ball[0]){
            currValue += (pow(ball[1] - start[1]));
            currValue += (pow(start[0] + ball[0]));
            minValue = Math.min(minValue, currValue);            
        }


        //direction 4
        currValue =0;
        if(ball[0] != start[0] || ball[1] >= start[1]){
            currValue += (pow(ball[0] - start[0]));
            currValue += (pow(ball[1] + start[1]));
            minValue = Math.min(minValue, currValue);              
        }
     
        return minValue;
        
    }
    
    private int pow(int number){
        return (int)Math.pow(Math.abs(number), 2);
    }
}
