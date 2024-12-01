import java.util.*;
class Solution {
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];
        
        for(int i=0; i<s.length; i++){
            answer[i] = convertString(s[i]);
        }
        return answer;
    }
    
    private String convertString(String input){
        StringBuilder sb = new StringBuilder();
        StringBuilder add= new StringBuilder();
        for(int i=0; i<input.length(); i++){
            sb.append(input.charAt(i));
            int len = sb.length();
            if(len>2 &&
              sb.charAt(len-3) == '1' && sb.charAt(len-2) == '1' && sb.charAt(len-1) == '0'){
                sb.delete(len-3, len);
                add.append("110");
            }
        }
        
        if(sb.indexOf("0") == -1){
            sb.insert(0, add);
        }else{
            int idx = sb.lastIndexOf("0");
            sb.insert(idx+1, add);
        }
        
        return sb.toString();
    }
}
