import java.util.*;

class Solution{
    int[] maxArr;
    public int solution(int[] money) {       
        
        maxArr = new int[money.length];
        maxArr[0] = money[0];
        maxArr[1] = Math.max(money[0], money[1]);
        //첫번째 o
        for(int i=2; i<money.length; i++){
            if(i == money.length -1){
                maxArr[i] = maxArr[i-1];
            }else{
                maxArr[i] = Math.max(maxArr[i-2] + money[i], maxArr[i-1]);
            }
        }
        int maxValue = maxArr[money.length-1];
        //첫번째 x
        maxArr[0] = 0;
        maxArr[1] = money[1];
        for(int i=2; i<money.length; i++){
            maxArr[i] = Math.max(maxArr[i-2] + money[i], maxArr[i-1]);
        }
        return Math.max(maxValue, maxArr[money.length-1]);
    }
    
}
