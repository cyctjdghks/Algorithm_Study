class Solution {
	public int solution(int h1, int m1, int s1, int h2, int m2, int s2) {
		int start = toSec(h1, m1, s1), end = toSec(h2, m2, s2);
		
		int answer = countAlarm(end) - countAlarm(start);
		answer += ((start*59%3600) == 0 || (start*719%43200) == 0) ? 1 : 0;
		return answer;
	}
	
	private int toSec(int h, int m, int s){
		return s + m * 60 + h * 60 * 60;
	}
	
	private int countAlarm(int sec){
		int minAlarm = sec *59/3600;
		int hourAlarm = sec *719/43200;
		
		return minAlarm + hourAlarm - (43200 <= sec ? 2 : 1);
	}
}
