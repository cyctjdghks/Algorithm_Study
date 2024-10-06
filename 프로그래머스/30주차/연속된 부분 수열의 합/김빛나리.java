class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        // 가장 짧은 길이
        int minLen = Integer.MAX_VALUE;
        int sum = 0, idx = 0;
        
        for(int i=0;i<sequence.length;i++) {
            while(idx < sequence.length && sum < k) {
                sum += sequence[idx++];
            }
            
            // idx는 증가하고 나왔기 때문에 -1
            if(sum == k && minLen > (idx-1-i)) {
                answer[0] = i;
                answer[1] = idx-1;
                minLen = idx-1-i;
            }
            
            sum -= sequence[i];
        }
        
        return answer;
    }
}