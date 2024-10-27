import java.util.Arrays;

class Solution {
	static class Number{
		int value;
		int cnt;
		
		Number(int value, int cnt){
			this.value = value;
			this.cnt = cnt;
		}
	}
	public int[] solution(int e, int[] starts) {
		int[] answer = new int[starts.length];
		//init
		Number[] cnt = new Number[e + 1];
		cnt[1] = new Number(1, 1);
		for(int i=2; i<=e; i++){
			cnt[i] = new Number(i, getDivisorCnt(i));
		}

		Arrays.sort(cnt, (o1, o2) -> {
			if(o1.cnt == o2.cnt)	return o1.value - o2.value;
			return o2.cnt - o1.cnt;
		});
		
		for(int i=0; i<starts.length; i++){
			for(int j=0; j<cnt.length; j++){
				if(starts[i] <= cnt[j].value){
					answer[i] = cnt[j].value;
					break;
				}
			}
		}
		return answer;
	}
	
	private int getDivisorCnt(int number){
		int cnt = 1;
		for(int i=2; i*i < number; i++){
			if(number % i == 0)	cnt++;
		}
		return cnt;
	}
}
