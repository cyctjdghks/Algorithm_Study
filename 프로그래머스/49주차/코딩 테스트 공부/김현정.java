import java.util.*;

class Solution {

	static class Problem{
		int startX;
		int startY;
		int moveX;
		int moveY;
		int cost;

		Problem(int startX, int startY, int moveX, int moveY, int cost){
			this.startX = startX;
			this.startY= startY;
			this.moveX = moveX;
			this.moveY = moveY;
			this.cost =cost;
		}
	}
	int[][] time;
	int n = 0, m =0;
	ArrayList<Problem> probs = new ArrayList<>();
	public int solution(int alp, int cop, int[][] problems) {
		//init
		for(int i=0; i<problems.length; i++){
			n = Math.max(problems[i][0], n);
			m = Math.max(problems[i][1], m);
		}
		if(alp >= n && cop >=m)	return 0;
		time = new int[150 + 1][150 + 1];
		for(int i=0; i<=150; i++){
			Arrays.fill(time[i], Integer.MAX_VALUE);
		}
		probs.add(new Problem(0, 0, 0, 1, 1));
		probs.add(new Problem(0, 0, 1, 0, 1));
		for(int i=0; i<problems.length; i++){
			probs.add(new Problem(problems[i][0], problems[i][1], problems[i][2], problems[i][3], problems[i][4]));
		}

		return dp(Math.min(alp,n),Math.min(cop, m));
	}

	private int dp(int startX, int startY){
		time[startX][startY] = 0;
		int minTime = time[n][m];
		for(int i= startX; i <150; i++){
			for(int j = startY; j<150; j++){
				for(int p = 0; p<probs.size(); p++){
					Problem prob = probs.get(p);
					if(prob.startX > i || prob.startY > j)	continue;
					int nextX = i + prob.moveX, nextY = j + prob.moveY;
					if(nextX > 150 || nextY > 150)	continue;
					time[nextX][nextY] = Math.min(time[nextX][nextY], prob.cost + time[i][j]);

					if(nextX >= n && nextY >= m)	minTime = Math.min(minTime, time[nextX][nextY]);
				}
			}
		}
		return minTime;
	}
}
