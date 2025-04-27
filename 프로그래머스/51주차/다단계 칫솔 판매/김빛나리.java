import java.util.*;

class Solution {
    class Person {
        String name;
        Person parent;
        int profit;

        public Person(String name, Person parent, int profit) {
            this.name = name;
            this.parent = parent;
            this.profit = profit;
        }

        public void CalProfit(int profit) {
            int profitToParent = profit / 10;
            this.profit += profit - profitToParent;
            
            if(this.parent != null && profitToParent >= 1)
                this.parent.CalProfit(profitToParent);
        }
    }
    
    public int[] solution(String[] enroll, String[] referral, String[] seller, int[] amount) {
        int[] answer = new int[enroll.length];
        // key = 조직원 이름
        HashMap<String, Person> map = new HashMap<>();
        
        for(String name : enroll) {
            map.put(name, new Person(name, null, 0));
        }
        
        for(int i=0;i<referral.length;i++) {
            // 추천인이 없는 경우
            if(referral[i].equals("-")) continue;

            // 현재 조직원의 추천인 저장
            map.get(enroll[i]).parent = map.get(referral[i]);
        }
        
        for(int i=0;i<seller.length;i++) {
            // 판매원 이익금 계산
            map.get(seller[i]).CalProfit(amount[i] * 100);
        }
        
        for(int i=0;i<enroll.length;i++) {
            answer[i] = map.get(enroll[i]).profit;
        }
        
        return answer;
    }
}