import java.util.*;

class Solution {
    int len;
    int[][] Dice;
    int[] A;
    int[] B;
    
    ArrayList<Integer> score;
    int maxScore = Integer.MIN_VALUE;
    int tempSum;
    int[] maxList;
    
    public int[] solution(int[][] dice) {
        len = dice.length;
        Dice = dice;
        A = new int[len/2];
        B = new int[len/2];
        maxList = new int[len/2];
        
        getDice(0, 0);

        return maxList;
    }
    
    public void getDice(int cur, int idx) {
        if(idx == len/2) {
            int index = 0;
            
            for(int i=0;i<len;i++) {
                boolean flag = false;
                
                for(int j=0;j<len/2;j++) {
                    if (A[j] == i) flag = true;
                }
                    
                if(flag) continue;
                
                B[index++] = i;
            }
            
            countScore();
            return;
        }

        if(cur >= len) return;
        
        A[idx] = cur;
        
        getDice(cur+1, idx+1);
        getDice(cur+1, idx);
    }
    
     public void countScore() {
        score = new ArrayList<>();
        tempSum = 0;
         
        sumB(0, 0);
        Collections.sort(score);

        sumA(0, 0);
        if(maxScore < tempSum) {
            maxScore = tempSum;
            
            for(int i=0;i<len/2;i++) {
                maxList[i] = A[i] + 1;
            }
        }
    }
    
    public void sumB(int idx, int sum) {
        if(idx == len/2) {
            score.add(sum);
            return;
        }
        
        for(int i=0;i<6;i++) {
            sumB(idx+1, sum + Dice[B[idx]][i]);
        }
    }
    
    public void sumA(int idx, int sum) {
        if(idx == len/2) {
            int left = 0;
            int right = score.size();
            
            while(left + 1 < right) {
                int mid = (left + right) / 2;
                
                if (score.get(mid) < sum) left = mid;
                else right = mid;
            }
            
            tempSum += (left + 1);
            return;
        }
        
        for(int i=0;i<6;i++) {
            sumA(idx+1, sum + Dice[A[idx]][i]);
        }
    }
}