import java.util.*;

class Solution {
    ArrayList<Integer>[] graph;
    int[] globalInfo;
    int maxSheep = 0;
    
    public int solution(int[] info, int[][] edges) {
        globalInfo = info;
        graph = new ArrayList[info.length];
        
        for(int[] edge : edges) {
			int parent = edge[0];
			int child = edge[1];
            
			if(graph[parent] == null) graph[parent] = new ArrayList<>();
            
			graph[parent].add(child);
		}
        
        ArrayList<Integer> list = new ArrayList<>();
		list.add(0);
        
		dfs(0, 0, 0, list);
        
        return maxSheep;
    }
    
    void dfs(int idx, int sheep, int wolf, ArrayList<Integer> list) {
		// 늑대의 수, 양의 수 세기
		if(globalInfo[idx] == 0) sheep++;
		else wolf++;
 
        // 늑대의 수가 양의 수보다 크거나 같을 경우
		if(wolf >= sheep) return;
        
		maxSheep = Math.max(sheep, maxSheep);
        
        // 다음으로 탐색할 노드 담는 list
		ArrayList<Integer> next = new ArrayList<>();
		next.addAll(list);
        
        // 연결된 노드가 있는 경우, 연결된 모든 노드 list에 담기
		if(graph[idx] != null) next.addAll(graph[idx]);
        
        // 현재 위치 삭제
		next.remove(Integer.valueOf(idx));
		
		for(int n : next) {
			dfs(n, sheep, wolf, next);
		}
		
		return;
	}
}