class Solution {
    public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
        int answer = -1;
        
        int startTime = toSecond(h1,m1,s1);
        int endTime = toSecond(h2,m2,s2);
        
        int num = endTime * 59/3600 + endTime * 719/43200 - startTime * 59/3600 - startTime * 719/43200;
        if (startTime >= 43200) {
            num += 1;
        }
        if (endTime >= 43200) {
            num -= 1;
        }
        if (startTime * 59 % 3600 == 0 || startTime * 719 % 43200 == 0) {
            num += 1;
        }
        
        answer =num;
        
        return answer;

    }
    static int toSecond(int h,int m,int s){
        return h * 3600 + m * 60 + s;
    }
}