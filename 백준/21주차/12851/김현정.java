import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Vertex{
		int time;
		int idx;

		Vertex(int time, int idx) {
			this.time = time;
			this.idx = idx;
		}
	}
	static int[] move = {-1, 1, 0};
	public static void main(String[] args) throws IOException {
		//init
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()), K = Integer.parseInt(st.nextToken());
		int[] counts = new int[100000 + 1];	//count
		int[] times = new int[100000 + 1];	//count
		Arrays.fill(times, -1);
		times[N] = 0;
		counts[N] = 1;
		counts[K] = 1;
		PriorityQueue<Vertex> queue = new PriorityQueue<>(Comparator.comparingInt(o -> o.time));
		queue.add(new Vertex(0, N));
		int minTime = Integer.MAX_VALUE;
		while (!queue.isEmpty() && queue.peek().time <= minTime) {
			Vertex curr = queue.poll();
			for (int i = 0; i < move.length; i++) {
				Vertex next;
				if (move[i] == 0) {
					next = new Vertex(curr.time + 1, curr.idx * 2);
				} else {
					next = new Vertex(curr.time + 1, curr.idx + move[i]);
				}
				if (next.idx >=0 && next.idx < 100001
					&& (times[next.idx] == -1 || times[next.idx] == next.time)) {
					if (counts[next.idx] == 0) {
						queue.add(next);
						times[next.idx] = next.time;
					}
					counts[next.idx] += counts[curr.idx];
				}

				if (next.idx == K) {
					minTime = Math.min(minTime, next.time);
				}
			}
		}
		System.out.println(minTime);
		System.out.println(counts[K]);
	}
}
