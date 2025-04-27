import java.util.*;

class Solution {

    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        HashMap<String,String> referralMap = new HashMap<>();
        HashMap<String,Integer> moneyMap = new HashMap<>();
        for(int i=0; i<enroll.length; i++) {
            referralMap.put(enroll[i],referral[i]);
            moneyMap.put(enroll[i],0);
        }

        moneyMap.put("-",0);

        for(int i=0; i< seller.length; i++) {
            dfs(seller[i],amount[i]*100,referralMap,moneyMap);
        }

        for (int i = 0; i < enroll.length; i++) {
            answer[i] = moneyMap.get(enroll[i]);
        }

        return answer;
    }

    public void dfs(String name, Integer sales, HashMap<String,String> referralMap, HashMap<String,Integer> moneyMap) {
        int dividend = sales / 10;
        if (name.equals("-") || dividend == 0) {
            moneyMap.put(name,moneyMap.get(name)+sales);
            return;
        }
        moneyMap.put(name, moneyMap.get(name) + sales - dividend);
        dfs(referralMap.get(name), dividend, referralMap, moneyMap);
    }
}