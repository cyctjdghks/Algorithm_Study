import java.util.*;
class Solution {
    public int solution(int n, long l, long r) {
        int answer = 0;
 
        double a=getCount(r)-getCount(l-1);    
 
        answer=(int)a;
 
        return answer;
    }
 
    public long getCount(long num){
 
        int[] fk = new int[]{0,1,2,2,3,4};
 
        if(num<=5){
            return fk[(int)num];
        }
 
        int level=1;
 
        while(num>Math.pow(5,level+1)){
            level++;
        }
 
 
        long box = num/(long)(Math.pow(5,level));
        long remain = num%(long)(Math.pow(5,level));
 
        long count=box*(long)Math.pow(4,level);
 
        if(box>=3){
           count-=Math.pow(4,level);
        }
 
        if(box==2){
            return count;
        }
        else {
            return count+getCount((long)remain);
        }
    }
}
