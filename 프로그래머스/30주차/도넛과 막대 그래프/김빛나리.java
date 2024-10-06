import java.util.*;

class Solution {
    public int[] solution(int[][] edges) {
        // 생성한 정점의 번호, 도넛 모양 그래프의 수, 막대 모양 그래프의 수, 8자 모양 그래프의 수
        int[] answer = new int[4];
        Map<Integer, Integer> a = new HashMap<>();
        Map<Integer, Integer> b = new HashMap<>();
        
        for(int[] edge : edges) {
            a.put(edge[0], a.getOrDefault(edge[0], 0) + 1);
            b.put(edge[1], b.getOrDefault(edge[1], 0) + 1);
        }
        
        // 생성한 정점 찾기 = 들어오는 간선이 없고 나가는 간선이 2개 이상이면 생성한 정점
        for(Map.Entry<Integer, Integer> edge : a.entrySet()) {
            // 나가는 간선이 2개 이상
            if(edge.getValue() > 1) {
                // 들어오는 간선이 없는 경우
                if(!b.containsKey(edge.getKey())) answer[0] = edge.getKey();
                // 들어오는 간선이 있는 경우 = 8자 모양 그래프
                else answer[3] += 1;
            }
        }
        
        // 들어오는 간선만 있는 경우, 막대 그래프
        for(int node : b.keySet()) {
            if(!a.containsKey(node)) answer[2] += 1;
        }
        
        // 도넛 그래프 개수 = 생성한 정점의 나가는 간선 개수 - 막대 그래프 개수 - 8자 그래프 개수
        answer[1] = a.get(answer[0]) - answer[2] - answer[3];
        
        return answer;
    }
}