class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = -1;
        int start = changeSec(h1, m1, s1);
        int end = changeSec(h2, m2, s2);
        
        answer = count(end) - count(start);
        
        // 시작부터 일치할 경우
        if(start * 59 / 3600 == 0 || start * 719 % 43200 == 0) answer++;
        
        return answer;
    }
    
    public int changeSec(int h, int m, int s) {
        return h * 3600 + m * 60 + s;
    }
    
    public int count(int seconds) {
        int minute = seconds * 59 / 3600;
        int hour = seconds * 719 / 43200;
        
        // 시침, 분침이 모두 겹친 경우
        int dupli = 43200 <= seconds ? 2 : 1;
        
        return minute + hour - dupli;
    }
}