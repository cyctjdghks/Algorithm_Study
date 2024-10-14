class Solution {
    public int solution(int n, long l, long r) {
        int answer = 0;
        
        for(long i=l;i<=r;i++) {
            answer += query(n, i-1);
        }
        
        return answer;
    }
    
    int query(int n, long l) {
        if(n == 0 || l == 0) return 1;
        // 3번째인 경우, 0
        if (l % 5 == 2) return 0;
        
        return query(n-1, l / 5);
    }
}