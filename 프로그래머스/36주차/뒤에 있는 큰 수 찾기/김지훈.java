import java.util.*;

class Solution {
    // 이중포문은 시간초과다 .
    // stack에 idx를 담아서 풀자

    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Stack<Integer> stack = new Stack<>();
        stack.push(0);

        for (int i=1; i<numbers.length; i++) {
            int number = numbers[i];
            while(!stack.isEmpty() && numbers[stack.peek()] < number) {
                Integer idx = stack.pop();
                answer[idx] = number;
            }
            stack.push(i);
        }

        // 모든 index를 탐색 후 뒤에 있는 큰 수가 없는 경우 -1
        while (!stack.isEmpty()) {
            answer[stack.pop()] = -1;
        }

        return answer;
    }
}