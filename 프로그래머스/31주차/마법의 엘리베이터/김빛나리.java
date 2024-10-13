class Solution {
    public int solution(int storey) {
        int answer = 0;
        
        // int -> string -> string[] -> int[]
        String[] str = Integer.toString(storey).split("");
        int[] nums = new int[str.length];
        
        for(int i=0;i<str.length;i++) {
            nums[i] = Integer.parseInt(str[i]);
        }
        
        // 1의 자리 수부터 판별
        for(int i=nums.length-1;i>=0;i--) {
            // 5보다 크면 더해서 큰 값을 만드는게 이득
            // 5보다 작으면 빼서 작은 값을 만드는게 이득
            int n = nums[i];
            
            // 5보다 클 경우 -> 10으로 만들어주었다고 판단
            if(n > 5) {
                answer += (10 - n);
                
                // 제일 앞 자리수일 경우 +10 버튼을 누르므로, +1
                if(i-1 < 0) {
                    answer += 1;
                    break;
                }
                
                // 10으로 만들어주었기 때문에 앞에 수 증가
                nums[i-1]++;
            } 
            
            // 5보다 작을 경우 -> +1 버튼으로 채워주기
            else if(n < 5) answer += n;
            
            // 5일 경우
            else {
                // 제일 앞 자리수일 경우 -> +1 버튼으로 채워주기
                if(i-1 < 0) {
                    answer += n;
                    break;
                } 
                // 앞자리 수가 5보다 작을 경우 -> +1 버튼으로 채워주기
                else if(nums[i-1] < 5) answer += n;
                // 앞자리 수가 5와 같거나 보다 클 경우 
                // 10으로 만들어주었다고 판단, 앞에 수 증가
                else {
                    answer += n; // (10 - n) = n -> 5니까 가능
                    nums[i-1]++;
                }
            }
        }
        
        return answer;
    }
}