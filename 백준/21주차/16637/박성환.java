import java.util.Scanner;

public class BOJ_16637 {

    static int N;
    static char[] map;
    static int res;

    public static void main(String[] args) {
        // i 가 홀수면 숫자
        // i 가 짝수면 연산자

        Scanner sc = new Scanner(System.in);

        N = sc.nextInt();
        map = sc.next().toCharArray(); // 문자열 배열로 읽기
        res = Integer.MIN_VALUE;

        dfs(2, map[0] - '0');

        System.out.println(res);
    }

    private static void dfs(int idx, int sum) {
        if (idx >= N) {
            res = Math.max(res, sum);
            return;
        }

        // 현재 위치에 괄호가 없을 경우 , 연산자하나와 수를 사용했음으로 2;
        dfs(idx + 2, calc(sum, map[idx] - '0', map[idx - 1]));
        // 현재 위치에 괄호가 있을 경우
        if (idx + 2 < N) { // 뒤에 숫자가 있어야 괄호 연산을 먼저 할 수 있다.
            int ta = calc(map[idx] - '0', map[idx + 2] - '0', map[idx + 1]);
            // 다음 재귀 호출하기위해서 다음 다음숫자로 이동해야 함
            dfs(idx + 4, calc(sum, ta, map[idx - 1]));
        }
    }

    static int calc(int a, int b, char op) {
        if (op == '+') {
            return a + b;
        } else if (op == '-') {
            return a - b;
        } else {
            return a * b;
        }
    }
}
