import java.util.HashMap;

class Solution {
	public int solution(int[] topping) {
		int answer = 0;

		HashMap<Integer, Integer> left = new HashMap<>();
		HashMap<Integer, Integer> right = new HashMap<>();
		for(int i=0; i<topping.length; i++){
			right.put(topping[i], right.getOrDefault(topping[i], 0) + 1);
		}
		
		for(int i=0; i<topping.length; i++){
			left.put(topping[i], left.getOrDefault(topping[i], 0) + 1);
			int rightValue = right.remove(topping[i]);
			if(rightValue > 1){
				right.put(topping[i], rightValue-1);
			}
			
			if(left.size() == right.size())	answer++;
		}
		
		return answer;
	}
}
