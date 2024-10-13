class Solution {
    public int solution(int storey) {
        int answer = 0;

        while (storey > 0) {
            int one = storey % 10;
            storey = storey / 10;

            if (one == 5) {
                if (storey % 10 >= 5) {
                    answer = answer + (10 - one);
                    storey++;
                }
                else {
                    answer = answer + one;    
                }
            }
            else if (one > 5) {
                answer = answer + (10 - one);
                storey++;
            }
            else {
                answer = answer + one;
            }

        }
        return answer;
    }
}