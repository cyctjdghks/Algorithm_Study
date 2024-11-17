import java.util.*;

class Solution {
    private int[][] users;
    private int[] emoticons;
    private int[] sale = {10, 20, 30, 40};
    private int[] answer = new int[2];
    
    public int[] solution(int[][] users, int[] emoticons) {
        this.users = users;
        this.emoticons = emoticons;
        int[] rates = new int[emoticons.length];
        
        back(rates, 0);
        
        return answer;
    }
    
    private void back(int[] rates, int depth) {
        if (depth == rates.length) {
            check(rates);
            
            return;
        }
        
        for (int i = 0; i < sale.length; i++) {
            rates[depth] = sale[i];
            back(rates, depth + 1);
        }
    }
    
    private void check(int[] rates) {
        int[] result = new int[2];
        
        for (int i = 0; i < users.length; i++) {
            int totalPurchase = 0;
            
            for (int j = 0; j < emoticons.length; j++) {
                if (users[i][0] <= rates[j]) {
                    totalPurchase += emoticons[j] * (100 - rates[j]) / 100;
                }
            }
            
            if (totalPurchase >= users[i][1]) {
                result[0]++;
            } else {
                result[1] += totalPurchase;
            }
        }
        
        if (result[0] > answer[0]) {
            answer[0] = result[0];
            answer[1] = result[1];
        }
        
        if (result[0] == answer[0] && result[1] > answer[1]) {
            answer[0] = result[0];
            answer[1] = result[1];
        }
    }
}