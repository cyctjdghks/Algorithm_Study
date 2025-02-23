import java.util.*;

class Solution {
    public int[] solution(int[] nodes, int[][] edges) {
        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        
        for(int node : nodes) {
            map.put(node, new ArrayList<>());
        }
        
        for(int[] edge : edges) {
            map.get(edge[0]).add(edge[1]);
            map.get(edge[1]).add(edge[0]);
        }
        
        HashSet<Integer> visit = new HashSet<>();
        ArrayDeque<Integer> queue = new ArrayDeque<>();
        int[] answer = {0, 0};
        
        for(int node : nodes) {
            if(visit.add(node)) {
                int[] temp = {0, 0};
                queue.offer(node);
                
                while (!queue.isEmpty()) {
                    int cur = queue.poll();
                    
                    for(int next : map.get(cur)) {
                        if(visit.add(next)) queue.offer(next);
                    }
                    
                    temp[(cur ^ (map.get(cur).size())) & 1]++;
                }
                
                if (temp[0] == 1) answer[0]++;
                if (temp[1] == 1) answer[1]++;
            }
        }
        
        return answer;
    }
}