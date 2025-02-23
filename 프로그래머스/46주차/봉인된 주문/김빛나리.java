import java.util.*;

class Solution {
    public String solution(long n, String[] bans) {
        int len = bans.length;
        long[] base26Bans = new long[len];
        long result = n;
        
        for(int i=0;i<len;i++) {
            base26Bans[i] = convertStringToBase26(bans[i]);
        }
        
        Arrays.sort(base26Bans);
        
        for(long ban : base26Bans) {
            if (result >= ban) result++;
            else break;
        }
        
        StringBuilder answer = new StringBuilder();

        while(result > 0) {
            result--;
            
            char c = (char) (Long.valueOf(result % 26).intValue() + 97);
            answer.append(c);
            
            result /= 26;
        }
        
        return answer.reverse().toString();
    }
    
    public Long convertStringToBase26(String str) {
        long result = 0L;
        
        for(int i=0;i<str.length();i++) {
            long val = str.charAt(i) - 'a' + 1;
            
            result = result * 26 + val;
        }
        
        return result;
    }
}