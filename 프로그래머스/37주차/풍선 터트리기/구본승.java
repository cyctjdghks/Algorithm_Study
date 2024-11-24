import java.util.*;

class Solution {
    public int solution(int[] a) {
        int answer = 2;
        int[] lMin = new int[a.length];
        int[] rMin = new int[a.length];
        int min = Integer.MAX_VALUE;
        for (int i = 0; i < a.length; i++) {
            min = Math.min(min, a[i]);
            lMin[i] = min;
        }
        min = Integer.MAX_VALUE;
        for (int i = a.length - 1; i >= 0; i--) {
            min = Math.min(min, a[i]);
            rMin[i] = min;
        }
        for (int i = 1; i < a.length - 1; i++) {
            if (lMin[i-1] < a[i] && rMin[i+1] < a[i]) continue;
            else answer++;
        }
        
        return answer;
    }
}