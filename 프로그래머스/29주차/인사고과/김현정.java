import java.util.ArrayList;
import java.util.Collections;

class Solution {
	static class Score implements Comparable<Score> {
		int idx;
		int work;
		int evaluation;

		public Score(int idx, int work, int evaluation) {
			this.idx = idx;
			this.work = work;
			this.evaluation = evaluation;
		}

		@Override
		public int compareTo(Score o) {
			int value = o.work + o.evaluation;
			if (value == this.work + this.evaluation) {
				return this.idx - o.idx;
			}
			return (value) - (this.work + this.evaluation);
		}
	}

	public int solution(int[][] scores) {

		ArrayList<Score> list = new ArrayList<>();
		for (int i = 0; i < scores.length; i++) {
			list.add(new Score(i + 1, scores[i][0], scores[i][1]));
		}

		//work desc, eval asc
		Collections.sort(list, (o1, o2) -> {
