import java.util.*;
class Solution {
    static int MAX = 12;
    static long[] power26 = new long[MAX];
    static List<Long>[] bannedList = new ArrayList[MAX];

    public String solution(long n, String[] bans) {
        // 1. power26 초기화
        power26[0] = 1;
        for(int i=1;i<MAX;i++){
            power26[i] = power26[i-1] * 26;
        }

        // 2. bannedList 초기화
        for(int i=1;i<MAX;i++){
            bannedList[i] = new ArrayList<>();
        }

        for(String ban : bans){
            int len = ban.length();
            long num = stringToNum(ban);
            bannedList[len].add(num);
        }

        for(int i=1;i<MAX;i++){
            Collections.sort(bannedList[i]);
        }


        long curN = n;
        for(int i=1;i<MAX;i++){
            long totalInLen = power26[i];
            long bannedCnt = bannedList[i].size();
            long totalWithBan = totalInLen - bannedCnt;

            // 현재 글자수보다 글자수가 크다.
            if(curN > totalWithBan){
                curN -= totalWithBan;
                continue;
            }

            // 현재 글자수에 포함됨
            long k = curN;
            long prevBan = -1;
            long result = -1;
            for(long ban : bannedList[i]){
                long gap = ban - (prevBan+1);

                if(k <= gap){
                    result = prevBan + k;
                    break;
                }

                k-= gap;
                prevBan = ban;
            }

            if(result == -1){
                result = prevBan + k;
            }
            return numToString(result, i);
        }


        return "";
    }

    static public long stringToNum(String s){
        long result = 0;
        for (char c : s.toCharArray()) {
            result = result * 26 + (c - 'a');
        }
        return result;
    }

    static public String numToString(long num, int len){
        char[] s = new char[len];
        for (int i = len - 1; i >= 0; i--) {
            s[i] = (char) ('a' + (num % 26));
            num /= 26;
        }
        return new String(s);
    }
}