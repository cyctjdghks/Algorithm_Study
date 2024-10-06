import java.util.*;

class Solution {
    static int n;
    
    public int solution(int coin, int[] cards) {
        n = cards.length;
        
        boolean hand[] = new boolean[n+1];
        boolean check[] = new boolean[n+1];
        
        for(int i = 0; i < n/3; i++){
            hand[cards[i]] = true;
            check[cards[i]] = true;
        }
        
        int answer = 1;
        for(int i = n/3; i < n; i+=2){
            if(coin > 0){
                hand[cards[i]] = true;
                hand[cards[i+1]] = true;
            }
            
            boolean flag = false;
            int minCost = 3;
            int tmp = -1;
            for(int j = 1; j <= n; j++){
                if(!hand[j]){
                    continue;
                }
                
                if(hand[n + 1 - j]){
                    int cost = (check[j] ? 0 : 1) + (check[n + 1 - j] ? 0 : 1);
                    if(coin < cost || minCost <= cost){
                        continue;
                    }
                 
                    flag = true;
                    tmp = j;
                    minCost = cost;
                }
            }
            
            if(!flag){
                break;
            } 
            hand[tmp] = false;
            hand[n + 1 - tmp] = false;
            coin -= minCost;
                
            answer ++;
        }

        return answer;
    }
}