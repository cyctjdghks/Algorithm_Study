import java.util.*;

class Solution {
    public int solution(int coin, int[] cards) {
        int answer = 0;
        int n = cards.length;
        int idx = n / 3;
        Set<Integer> myCard = new HashSet<>();
        Set<Integer> newCard = new HashSet<>();
        
        for(int i=0;i<idx;i++) {
            myCard.add(cards[i]);
        }
        
        int target = n+1;
        while(true) {
            answer++;
            
            if(idx >= n) break;
            
            newCard.add(cards[idx]);
            newCard.add(cards[idx+1]);
            idx += 2;

            boolean flag = false;
            for(int num : myCard) {
                if(myCard.contains(target - num)) {
                    myCard.remove(num);
                    myCard.remove(target - num);
                    flag = true;
                    break;
                }
            }
            
            if(!flag) {
                if(coin >= 1) {
                    for(int num : myCard) {
                        if(newCard.contains(target - num)) {
                            myCard.remove(num);
                            newCard.remove(target - num);
                            coin--;
                            flag = true;
                            break;
                        }
                    } 
                }
                
                if(!flag && coin >= 2) {
                    for(int num : newCard) {
                        if(newCard.contains(target - num)) {
                            newCard.remove(num);
                            newCard.remove(target - num);
                            coin -= 2;
                            flag = true;
                            break;
                        }
                    } 
                }
            }
            
            if(!flag) break;
        }
        
        return answer;
    }
}