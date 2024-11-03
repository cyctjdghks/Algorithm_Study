import java.util.*;
class Solution {
    
    public double[] solution(int k, int[][] ranges) {
        double[] answer = new double[ranges.length];
        
        List<Double> list = new ArrayList<>();
        
        list.add((double)k);

        while(k>1){
            if(k%2==0){
                k/=2;
            }
            else{
                k= k*3+1;
            }
            list.add((double)k);
        }
        // System.out.println(list.size());
        int arrSize = list.size()-1;
        double[] sumArr = new double[arrSize];
        
        for(int i=0;i<list.size()-1;i++){
            sumArr[i] = Math.abs(list.get(i+1) - list.get(i)) / 2 + (Math.min(list.get(i+1),list.get(i)));
        }
        
        double[] prefix = new double[list.size()+1];
        for(int i=0;i<arrSize;i++){
            prefix[i+1] = prefix[i] + sumArr[i];
            
            // System.out.println(prefix[i+1]);
        }
        
        for(int i=0;i<ranges.length;i++){
            int start = ranges[i][0];
            int end = list.size() + ranges[i][1];
            // System.out.println(start + " " + end);
            if(start >= end) answer[i] = -1;
            else answer[i] = prefix[end-1] - prefix[start];
        }
            
        
        return answer;
    }
}