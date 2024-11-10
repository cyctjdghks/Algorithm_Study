class Solution {
    int maxNumOfSubscriber = 0;
    int maxSalePrice = 0;
    int[] discount = {40, 30, 20, 10};
    
    public int[] solution(int[][] users, int[] emoticons) {
        recursion(0, emoticons.length, new int[emoticons.length], users, emoticons);
        
        int[] answer = new int[]{maxNumOfSubscriber, maxSalePrice};
        
        return answer;
    }
    
    void recursion(int cur, int num, int[] input, int[][] users, int[] emoticons) {
        if(cur == num) {
            // 이모티콘 플러스 서비스 가입 수
            int numOfSubscriber = 0;
            // 이모티콘 매출액
            int salePrice = 0;
            
            for(int[] user : users) {
                // 사용자가 원하는 할인율
                int userDiscountRate = user[0];
                // 사용자가 구매할 수 있는 비용
                int userLimit = user[1];
                
                int sum = 0;
                
                for(int i=0;i<num;i++) {
                    // 현재 할인율이 사용자가 원하는 할인율보다 클 경우
                    // 해당 할인율로 계산
                    if(input[i] >= userDiscountRate)
                        sum += emoticons[i] * (100 - input[i]) / 100;
                }
                
                if(sum >= userLimit) numOfSubscriber++;
                else salePrice += sum;
            }
            
            // 최대 구독자 수보다 클 경우
            if(maxNumOfSubscriber < numOfSubscriber) {
                maxNumOfSubscriber = numOfSubscriber;
                maxSalePrice = salePrice;
            }
            // 최대 구독자 수와 같을 경우, 매출액이 더 큰 값
            else if(maxNumOfSubscriber == numOfSubscriber) {
                maxSalePrice = Math.max(maxSalePrice, salePrice);
            }
            
            return;
        }
        
        // 할인율 10~40 -> 4개
        for(int i=0;i<4;i++) {
            input[cur] = discount[i];
            recursion(cur+1, num, input, users, emoticons);
        }
    }
}