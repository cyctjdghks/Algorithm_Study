class Solution {
    // 규칙
    // 0이 있을 때 → 가장 뒤에 있는 0 다음에 110을 넣는 경우가 가장 작아진다.
    // 0이 없을 때 → 가장 앞에 110을 넣는 경우가 가장 작아진다.
    
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];
        int idx = 0;
        
        for(String str : s) {
            StringBuilder sb = new StringBuilder();
            int cnt = 0;
		
            // 해당 문자열에 들어있는 "110" 모두 찾기
            for(int i=0;i<str.length();i++) {
                char c = str.charAt(i);
                sb.append(c);
                
                if(sb.length() >= 3 && sb.charAt(sb.length()-3) == '1' && sb.charAt(sb.length()-2) == '1'  && sb.charAt(sb.length()-1) == '0') {
                    cnt++;
                    sb.delete(sb.length()-3, sb.length());
                }
            }
            
            if(cnt > 0) {
                String re = "110".repeat(cnt);
                
                if(sb.indexOf("0") == -1) sb.insert(0, re);
                else sb.insert(sb.lastIndexOf("0")+1, re);
            }
            
            answer[idx++] = sb.toString();
        }
        
        return answer;
    }
}