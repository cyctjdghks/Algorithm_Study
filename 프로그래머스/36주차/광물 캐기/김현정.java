import java.util.*;

class Solution {
    static class Stress implements Comparable<Stress>{
        int currPick;
        int value;
        
        Stress(int currPick, int value){
            this.currPick = currPick;
            this.value = value;
        }
        
        //value asc, currPick desc
        @Override
        public int compareTo(Stress s){
            if(this.value == s.value)   return s.currPick - this.currPick;
            return this.value - s.value;
        }
    }
    
    int[][] costs = {
        {1, 1, 1},
        {5, 1, 1},
        {25, 5, 1}
    };
    public int solution(int[] picks, String[] minerals) {
        int[] currCnt = picks.clone();
        int len = (int)Math.ceil(minerals.length/5.0);
        Stress[][] dp = new Stress[len][3];
        
        init(minerals, dp, len);
        
        int answer = 0;
        for(int i=0; i<len; i++){
            Arrays.sort(dp[i]);
            
            // System.out.println("len = " + i + ", pick = " +dp[i][0].currPick + ", value = " + dp[i][0].value);
            for(int idx = 0; idx<3; idx++){
                Stress curr = dp[i][idx];
                if(currCnt[curr.currPick] > 0){
                    // System.out.println("curr len = " + i + ", pick = " + curr.currPick + ", value = " + curr.value);
                    currCnt[curr.currPick]--;
                    answer += curr.value;
                    break;
                }
            }
        }
        return answer;
    }
    
    private void init(String[] minerals, Stress[][] arr, int len){
        for(int i=0; i<len; i++){
            arr[i][0] = new Stress(0, 0); arr[i][1] = new Stress(1, 0); arr[i][2] = new Stress(2, 0);
            for(int add=0; add<5 && 5*i+add<minerals.length; add++){
                int currMine;
                switch (minerals[i*5+add]){
                    case "diamond":
                        currMine = 0;
                        break;
                    case "iron":
                        currMine = 1;
                        break;
                    case "stone":
                        currMine = 2;
                        break;
                    default:
                        currMine = 0;
                }
                arr[i][0].value += costs[0][currMine];
                arr[i][1].value += costs[1][currMine];
                arr[i][2].value += costs[2][currMine];
            }
        }
    }
}
