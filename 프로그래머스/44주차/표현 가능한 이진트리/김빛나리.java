class Solution {
    public int[] solution(long[] numbers) {
        int len = numbers.length;
        int[] answer = new int[len];
        
        for(int i=0;i<len;i++) {
        	// String cur = Long.toBinaryString(numbers[i]);
            // 해당 index에 위치한 numbers를 이진법으로 변형
            StringBuffer str = new StringBuffer(Long.toBinaryString(numbers[i]));
        	int cnt = 0;
            
            // 포화이진트리 만들기 위해서 길이 계산 -> root는 1개니까 -1
        	while((int)Math.pow(2, cnt)-1 < str.length()) {
        		cnt++;
        	}
            
            // 포화이진트리 만들기 -> 부족한 길이만큼 앞에 "0" 추가
        	while((int)Math.pow(2, cnt)-1 != str.length()) {
                // 0번째 위치에 "0" 추가
                str.insert(0, "0");
        		// cur = "0"+ cur;
        	}
            
        	if(dfs(str.toString())) answer[i] = 1;
        }
        
        return answer;
    }
    
    private boolean dfs(String num) {
        int len = num.length();
		boolean valid = true;
		
		int mid = (len-1) / 2;
		char root = num.charAt(mid);
		String left = num.substring(0, mid);
		String right = num.substring(mid+1, len);
		
        // root가 더미 노드인데, (왼쪽 자식이나 오른쪽에서의 root가 더미 노드가 아닌) 경우
		if(root == '0' && (left.charAt((left.length()-1) / 2) == '1' || right.charAt((right.length()-1) / 2) == '1')) return false;
		
        // 한쪽 자식 노드가 3개 이상인 경우, 탐색
		if(left.length() >= 3) {
			valid = dfs(left);
            
            // left의 자식들이 하나의 이진트리로 표현할 수 있는 경우, right 탐색
			if(valid) valid = dfs(right);
		}
        
		return valid;
	}
}