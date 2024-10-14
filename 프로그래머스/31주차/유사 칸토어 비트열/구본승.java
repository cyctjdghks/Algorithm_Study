class Solution {
    public boolean check(long l) {
        while (l >= 5) {
            if (l % 5 == 2) return false;
            l /= 5;
        }
        return l != 2;
    }

    public int solution(int n, long l, long r) {
        int answer = 0;
        
        for (long i = l - 1; i < r; i++) {
            if (check(i)) answer++;
        }
        
        return answer;
    }
}