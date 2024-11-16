import java.util.*;

class Solution {
    public int[] solution(int[] numbers) {
        int[] answer = new int[numbers.length];
        Stack<Integer> stack = new Stack<>();
        
        for(int i=0;i<numbers.length;i++) {
            // 현재 숫자가 이전 숫자보다 클 경우
            while(!stack.isEmpty() && numbers[i] > numbers[stack.peek()]) {
                answer[stack.pop()] = numbers[i];
            }
            
            // stack에 index 저장
            stack.push(i);
        }
        
        while(!stack.isEmpty()) {
			answer[stack.pop()] = -1;
		}
        
        return answer;
    }
}