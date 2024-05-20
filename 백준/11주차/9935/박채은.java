import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Main {
    static String str;
    static String bomb;
    static Stack<Character> stack = new Stack<>();;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        str = br.readLine();
        bomb = br.readLine();

        for(int i=0;i<str.length();i++){
            stack.push(str.charAt(i));

            if(stack.size() >= bomb.length()){
                if(isContainBomb()){
                    for(int j=0;j<bomb.length();j++){
                        stack.pop();
                    }
                }
            }
        }

        if(stack.isEmpty()){
            System.out.println("FRULA");
        }
        else{
            StringBuilder sb = new StringBuilder();
            for(char c : stack){
                sb.append(c);
            }
            System.out.println(sb.toString());
        }
    }
    static boolean isContainBomb(){
        int bombSize = bomb.length();
        for(int i=0;i<bombSize;i++){
            if(!stack.get(stack.size() - bombSize + i).equals(bomb.charAt(i))){
                return false;
            }
        }
        return true;
    }
}
