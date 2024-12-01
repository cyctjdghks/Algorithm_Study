import java.util.*;

class Solution {
    
    private static Stack<Character> stack = new Stack<>();
    private static int count;
    private static String[] answer;
    
    public String[] solution(String[] s) {
        answer = new String[s.length];
        
        for (int i = 0; i < s.length; i++) {
            stack.clear();
            count = 0;
            String nowS = s[i];
            
            for (int j = 0; j < nowS.length(); j++) {
                if (nowS.charAt(j) == '1') {
                    stack.push('1');
                } else {
                    if (stack.size() >= 2) {
                        char first = stack.pop();
                        char second = stack.pop();
                        if ((first == '1') && (second == '1')) {
                            count++;
                        } else {
                            stack.push(second);
                            stack.push(first);
                            stack.push('0');
                        }
                    } else {
                        stack.push('0');
                    }
                }
            }
            
            for (int j = 0; j < count; j++) {
                Stack<Character> tmp = new Stack<>();
                while (true) {
                    if (stack.isEmpty()) {
                        stack.push('1');
                        stack.push('1');
                        stack.push('0');
                        while (!tmp.isEmpty()) {
                            stack.push(tmp.pop());
                        }
                        break;
                    } else {
                        char now = stack.pop();
                        if (now == '0') {
                            stack.push(now);
                            stack.push('1');
                            stack.push('1');
                            stack.push('0');
                            while (!tmp.isEmpty()) {
                                stack.push(tmp.pop());
                            }
                            break;
                        } else {
                            tmp.push(now);
                        }
                    }
                }
            }
            
            StringBuilder sb = new StringBuilder();
            while (!stack.isEmpty()) {
                sb.append(stack.pop());
            }
            String result = sb.reverse().toString();

            answer[i] = result;
            
            System.out.println();
        }
        
            
        
        return answer;
    }
}