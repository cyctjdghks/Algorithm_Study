import java.util.*;

class Solution {
    public long solution(int[] sequence) {
        long max = Long.MIN_VALUE;
        int a = 1, b = -1;
        long purse1 = 0;
        long purse2 = 0;

        for(int i=0;i<sequence.length;i++){
            a*=-1;
            b*=-1;

            // DP
            purse1 += (a * sequence[i]);
            purse2 += (b * sequence[i]);

            // 음수가 되면, 앞 부분을 0으로 변경 (sub array)
            purse1 = Math.max(0, purse1);
            purse2 = Math.max(0, purse2);

            max = Math.max(max, Math.max(purse1, purse2));
        }

        return max;
    }
}