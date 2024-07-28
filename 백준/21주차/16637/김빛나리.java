import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static int N;
    static char[] expression;
    static int max = Integer.MIN_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        expression = new char[N];
        expression = br.readLine().toCharArray();

        compute(2, expression[0] - '0');
            
        bw.write(max + "\n");
        bw.close();
    }

    public static void compute(int idx, int cal) {
        if(idx >= N) {
            max = Math.max(max, cal);
            return;
        }

        // 괄호 추가하지 않고 계산
        compute(idx + 2, calculate(cal, expression[idx] - '0', expression[idx-1]));

        if(idx + 2 < N){
            // 현재 위치에 괄호 추가한 계산
            int nowResult = calculate(expression[idx] - '0', expression[idx+2] - '0', expression[idx+1]);
            // 지금까지 누적 값에 위 괄호 계산 값 계산
            int totalResult = calculate(cal, nowResult, expression[idx-1]);
            compute(idx + 4, totalResult);
        }
    }

    public static int calculate(int total, int val, char exp) {
        if(exp == '+') return total + val;
        else if(exp == '-') return total - val;
        else return total * val;
    }
}
