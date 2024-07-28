import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int n;
    static int max = Integer.MIN_VALUE;
    static ArrayList<Integer>num = new ArrayList<>();
    static ArrayList<Character> exp = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());

        String t = br.readLine();
        for(int i=0; i<n; i++) {
            if(i%2==0) {
                num.add(t.charAt(i)-'0');
            }
            else {
                exp.add(t.charAt(i));
            }
        }
        int start = num.get(0);
        dfs(0,start);
        System.out.println(max);
    }
    public static void dfs(int now, int sum) {
        if(now>=exp.size()) {
            max = Math.max(max, sum);
            return;
        }

        int calcOneTwo = calc(now, sum, num.get(now+1));
        dfs(now+1, calcOneTwo);

        if(now+1 < exp.size()) {
            int calcTwoThr = calc(now+1, num.get(now+1), num.get(now+2));
            int result = calc(now, sum, calcTwoThr);
            //두개 계산 한번에
            dfs(now+2, result);
        }
    }
    public static int calc(int op_idx,int a, int b) {
        switch(exp.get(op_idx)) {
            case '+':
                return a+b;
            case '-':
                return a-b;
            case '*':
                return a*b;
        }
        return 1;
    }
}