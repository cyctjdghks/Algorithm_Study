import java.util.*;
class Solution {
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];

        for(int i=0;i<s.length;i++){
            answer[i] = makeString(s[i]);
        }

        return answer;
    }

    public String makeString(String s){
        StringBuilder sb = new StringBuilder();
        StringBuilder tmp = new StringBuilder();

        for(int i=0;i<s.length();i++){
            Character c = s.charAt(i);
            // 110인 경우
            if(c == '0' && tmp.length() >= 2 && tmp.charAt(tmp.length()-2) == '1' && tmp.charAt(tmp.length()-1) == '1'){
                sb.append("110");
                tmp.delete(tmp.length() - 2, tmp.length());
            }
            else{
                tmp.append(c);
            }
        }

        // 110이 여러 개인 경우에도 sb 전체를 한번에 붙여도 상관없다.
        // 110은 가장 뒤에 있는 0의 뒷 자리에 붙어서 110의 0이 가장 마지막 0이 되기 때문에
        if(sb.length() > 0){
            if(tmp.indexOf("0") == -1){ // 0이 없는 경우, 가장 앞에 insert
                tmp.insert(0, sb);
            }
            else{
                int lastZeroIndex = tmp.lastIndexOf("0");
                tmp.insert(lastZeroIndex + 1, sb);
            }
        }

        return tmp.toString();
    }
}