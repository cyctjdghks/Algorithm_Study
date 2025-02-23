import java.util.*;

class Solution {
    public String solution(long n, String[] bans) {

        Arrays.sort(bans, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(o1.length() > o2.length()) {
                    return 1;
                } else if (o1.length() < o2.length()) {
                    return -1;
                } else {
                    return o1.compareTo(o2);
                }
            }
        });

        int ban_cnt = 0;

        for(int i = 0; i < bans.length; i++) {
            long ban_num = 0;
            char[] c_array = bans[i].toCharArray();

            for(int j = 0; j < c_array.length; j++) {
                ban_num = ban_num + ((long)(Math.pow(26, c_array.length - 1 - j)) * (c_array[j] - 'a' + 1));
            }

            if((ban_num - ban_cnt) <= n) {
                ban_cnt++;
            }
        }

        n = n + ban_cnt;

        String answer = "";

        while(n > 0) {
            long rem = (n % 26);
            n = n / 26;

            if(rem == 0) {
                answer = (char)('z') + answer;
                n = n - 1;
            } else {
                answer = (char)('a' + rem - 1) + answer;
            }
        }

        return answer;
    }


}