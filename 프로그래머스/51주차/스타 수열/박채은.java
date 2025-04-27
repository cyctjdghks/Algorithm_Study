import java.util.*;

class Solution {
    public int solution(int[] a) {
        int resultLen = 0;

        int[] cnt = new int[a.length];
        for(int i=0;i<a.length;i++){
            cnt[a[i]]++;
        }

        // 교집합이 되는 숫자 = i
        for(int i=0;i<cnt.length;i++){
            if(cnt[i] <= resultLen) continue;
            int tmp = 0;
            for(int j=0;j<a.length-1;j++){
                if(a[j]!= a[j+1] && (a[j] == i || a[j+1] == i)){
                    tmp++;
                    j++;
                }
            }
            resultLen = Math.max(tmp, resultLen);
        }
        return resultLen*2;
    }
}

