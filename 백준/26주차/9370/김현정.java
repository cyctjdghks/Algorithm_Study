import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
	static class Edge{
		int start;
		int end;
		int cost;
		
		Edge(int start, int end, int cost){
			this.start = start;
			this.end = end;
			this.cost = cost;
		}
	}

	public static void main(String[] args) throws IOException {
		//init
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int T = Integer.parseInt(br.readLine());
		for(int i=0; i<T; i++){
			StringTokenizer st = new StringTokenizer(br.readLine());
			int n = Integer.parseInt(st.nextToken()), m = Integer.parseInt(st.nextToken()), t = Integer.parseInt(st.nextToken());
			st = new StringTokenizer(br.readLine());
			int s = Integer.parseInt(st.nextToken()), g = Integer.parseInt(st.nextToken()), h = Integer.parseInt(st.nextToken()); 
			int[] costs = new int[n+1];
			Arrays.fill(costs, Integer.MIN_VALUE);
			
			//linkedlist
			ArrayList<Edge>[] linkedlist = new ArrayList[n+1];
			for(int l=1; l<=n; l++){
				linkedlist[l] = new ArrayList<>();
			}
			for(int road = 0; road < m; road++){
				st = new StringTokenizer(br.readLine());
				int start = Integer.parseInt(st.nextToken()), end = Integer.parseInt(st.nextToken()), cost = Integer.parseInt(st.nextToken());
				linkedlist[start].add(new Edge(start, end, cost));
				linkedlist[end].add(new Edge(end, start, cost));
			}
			
			ArrayList<Integer> candidates = new ArrayList<>();
			for(int dest=0; dest<t; dest++){
				candidates.add(Integer.parseInt(br.readLine()));
			}
			
			getMinCosts(costs, s, linkedlist);
			for(int cand : candidates){
				if(costs[cand] % 2 == 1){
					bw.write(cand + " ");
				}
			}
			bw.write("\n");
		}
		bw.flush(); bw.close();
	}

  //다익스트라
	private static void getMinCosts(int[] arr, int start, ArrayList<Edge>[] linkedlist){
		arr[start] = 0;
		boolean[] isVisited = new boolean[arr.length]; isVisited[start] = true;
		PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.cost));
		pq.add(new Edge(start, start, 0));
		while (!pq.isEmpty()) {
			Edge curr = pq.poll();
			ArrayList<Edge> currList = linkedlist[curr.end];
			for (Edge next : currList) {
				int nextCost = curr.cost + next.cost;
				if (!isVisited[next.end] && arr[next.end] > nextCost) {
					isVisited[next.end] = true;
					arr[next.end] = nextCost;
					pq.add(new Edge(next.end, next.end, curr.cost + next.cost));
				}
			}
		}
	}
}
