import java.util.Scanner;
import java.util.Stack;

public class BOJ_9935 {
    public static void main(String[] args) {
        // 입력 파트
        Scanner sc = new Scanner(System.in);

        String str = sc.next();
        String boom = sc.next();
        Stack<Character> stack = new Stack<>();

        // 문자열 폭발
        int len = str.length();
        for(int i = 0; i < len; i++) {
            stack.push(str.charAt(i));

            if(stack.size() >= boom.length()) {
                boolean isBoom = true;
                for(int j = 0; j < boom.length(); j++) {
                    if(stack.get(stack.size() - boom.length() + j) != boom.charAt(j)) {
                        isBoom = false;
                        break;
                    }
                }
                if(isBoom) {
                    for(int j = 0; j < boom.length(); j++) {
                        stack.pop();
                    }
                }
            }
        }

        // 출력 파트
        StringBuilder sb = new StringBuilder();
        int size  = stack.size();
        for(int i = 0; i < size; i++) {
            sb.append(stack.get(i));
        }
        System.out.println(sb.length() > 0 ? sb.toString() : "FRULA");
    }
}