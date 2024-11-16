import java.util.*;

class Solution {
    // info = 양과 늑대, edges = 연결된 노드
    // graph 배열에 그래프 이동경로 저장
    // visited에 방문 노드 리스트로 정의 후, dfs
    int answer = 0;

    public int solution(int[] info, int[][] edges) {

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < info.length; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int parent = edge[0];
            int child = edge[1];
            graph.get(parent).add(child);
        }

        List<Integer> visited = new ArrayList<>();
        visited.add(0);

        dfs(0, 1, info, graph, visited);

        return answer;
    }

    public void dfs(int curWolf, int curSheep, int[] info, List<List<Integer>> graph, List<Integer> visited) {
        answer = Math.max(answer, curSheep);

        for (int curNode : visited) {
            for (int nextNode : graph.get(curNode)) {
                if (visited.contains(nextNode)) continue;

                int nextWolf = curWolf;
                int nextSheep = curSheep;

                if (info[nextNode] == 1) {
                    nextWolf++;
                } else {
                    nextSheep++;
                }

                if (nextWolf >= nextSheep) continue;

                ArrayList<Integer> nextVisited = new ArrayList<>(visited);
                nextVisited.add(nextNode);
                dfs(nextWolf, nextSheep, info, graph, nextVisited);
            }
        }
    }
}