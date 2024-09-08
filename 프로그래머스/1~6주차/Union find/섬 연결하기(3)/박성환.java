import java.util.*;

class Solution {
    // 모든 섬 통행 가능하도록 만드는 최소 비용
    // 1 <= n <= 100
    // cost.length <= ((n-1) * n) / 2
    // cost[i][0] -> cost[i][1] 비용 : cost[i][2]
    // 반대도 같음
    
    static int[] parent;
    
    public int solution(int n, int[][] costs) {
        int answer = 0;
        
        parent = new int[n];
        for(int i = 0; i < n; i++) {
            parent[i] = i;
        }
        
        // 가장 작은 값부터 계산하기
        Arrays.sort(costs, (a, b) -> Integer.compare(a[2], b[2]));
        
        for(int i = 0; i < costs.length; i++) {
            if(union(costs[i][0], costs[i][1])) {
                answer += costs[i][2];
            }
        }
        
        
        return answer;
    }
    
	private static int find(int x) {
		if(parent[x] == x) 
			return parent[x] = x;
		else  return find(parent[x]);
		
	}
	
	private static boolean union(int a, int b) {
		a = find(a);
		b = find(b);
		
		if(a!= b) {
			if(a>b) {
				parent[a] = b;
			} else {
				parent[b] = a;
			}
			return true;
		}
		return false;
	}
}